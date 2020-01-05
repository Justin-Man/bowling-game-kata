private const val MAX_FRAMES = 10

class Game {
    var score: Int = 0
    private var currentFrame = 0
    private val finalFrame = 9

    private val frames = Array(MAX_FRAMES) {
       if (it < finalFrame) Frame() else FinalFrame()
    }

    fun roll(pins: Int) {
        if (frames.all { it.isComplete }) {
            throw GameOverException()
        }

        frames[currentFrame].roll(pins)
        if (frames[currentFrame].isComplete && currentFrame < finalFrame) {
                currentFrame++
        }

        score = 0
        frames.forEachIndexed { index, frame ->
            val currentFrameScore = frame.totalRolled
            var bonus = 0
            if (index < finalFrame) {
                bonus = getFrameBonus(index, frame)
            }
            score += currentFrameScore + bonus
        }
    }

    private fun getFrameBonus(index: Int, frame: IFrame): Int {
        val penultimateFrame = 8
        val nextFrame = frames[index + 1]
        return when {
            frame.isStrike -> when {
                nextFrame.isStrike && index < penultimateFrame -> {
                    val frameAfter = frames[index + 2]
                    nextFrame.totalRolled + (frameAfter.roll1Pins ?: 0)
                }
                index == penultimateFrame -> (nextFrame.roll1Pins ?: 0) + (nextFrame.roll2Pins ?: 0)
                else -> nextFrame.totalRolled
            }
            frame.isSpare -> nextFrame.roll1Pins ?: 0
            else -> 0
        }
    }
}