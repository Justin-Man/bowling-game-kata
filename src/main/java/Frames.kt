

class Frames {
    private val frames = Array<IFrame>(10) {
        if (it == 9) FinalFrame()
        else Frame()
    }

    fun allFramesComplete() = frames.all { it.isComplete }
    fun roll(index: Int, pins: Int) {
        frames[index].roll(pins)
    }

    fun isFrameComplete(index: Int) = frames[index].isComplete

    fun calculateTotalScore() : Score {
        var score = 0
        for (index in frames.indices) {
            score += calculateFrameScore(index, frames)
        }

        return Score(score)
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