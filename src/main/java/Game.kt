class Game {
    var score: Int = 0
    val frames = Array(10) {
       if (it < 9) Frame() else FinalFrame()
    }
    var currentFrame = 0

    fun roll(pins: Int) {
        if (frames.all { frame ->
                    frame.isComplete
                }) {
            throw GameOverException()
        }

        frames[currentFrame].roll(pins)
        if (frames[currentFrame].isComplete) {
            if (currentFrame < 9)
                currentFrame++
        }

        score = 0
        frames.forEachIndexed { index, frame ->
            val currentFrameScore = frame.totalRolled
            var bonus = 0
            if (index < 9) {
                bonus = getFrameBonus(index, frame)
            }
            score += currentFrameScore + bonus
        }
    }

    private fun getFrameBonus(index: Int, frame: IFrame): Int {
        val nextFrame = frames[index + 1]
        if (frame.isStrike) {
            if (nextFrame.isStrike && index < 8) {
                val frameAfter = frames[index + 2]
                return nextFrame.totalRolled + (frameAfter.roll1Pins ?: 0)
            }
            if (index == 8) {
                return (nextFrame.roll1Pins ?: 0) + (nextFrame.roll2Pins ?: 0)
            }
            return nextFrame.totalRolled
        }
        if (frame.isSpare) {
            return nextFrame.roll1Pins ?: 0
        }
        return 0
    }
}