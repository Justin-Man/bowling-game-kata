private const val MAX_FRAMES = 10

class Game {
    var score = Score()
    private var currentFrame = 0
    private val finalFrame = 9

    private val frames = Array<IFrame>(MAX_FRAMES) {
        if (it == finalFrame) FinalFrame()
        else Frame()
    }

    fun roll(pins: Int) {
        if (frames.all { it.isComplete }) {
            throw GameOverException()
        }
        frames[currentFrame].roll(pins)
        if (frames[currentFrame].isComplete && currentFrame < finalFrame) {
            currentFrame++
        }

        score.calculateGameScore(frames)
    }
}

data class Score(private var gameScore: Int = 0) {

    fun calculateGameScore(frames: Array<IFrame>) {
        // why should the game score need to be reset all the time?
        gameScore = 0
        for (index in frames.indices) {
            gameScore += calculateFrameScore(index, frames)
        }
    }

    private fun calculateFrameScore(index: Int, frames: Array<IFrame>) : Int {
        return frames[index].totalRolled + getFrameBonus(index, frames)
    }

    private fun getFrameBonus(index: Int, frames: Array<IFrame>): Int {
        val finalFrame = 9
        if (index == finalFrame) return 0
        val penultimateFrame = 8
        val nextFrame = frames[index + 1]
        return when {
            frames[index].isStrike -> getStrikeBonus(frames, index, penultimateFrame)
            frames[index].isSpare -> nextFrame.roll1Pins ?: 0
            else -> 0
        }
    }

    private fun getStrikeBonus(
            frames: Array<IFrame>,
            index: Int,
            penultimateFrame: Int
    ): Int {
        val nextFrame = frames[index + 1]
        var strikeBonus = nextFrame.totalRolled
        if (nextFrame.isStrike && index < penultimateFrame) {
            val frameAfter = frames[index + 2]
            strikeBonus = nextFrame.totalRolled + (frameAfter.roll1Pins ?: 0)
        }
        if (index == penultimateFrame) {
            strikeBonus = (nextFrame.roll1Pins ?: 0) + (nextFrame.roll2Pins ?: 0)
        }
        return strikeBonus
    }
}