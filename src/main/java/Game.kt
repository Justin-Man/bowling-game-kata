class Game {
    var score: Int = 0
    val frames = Array(10) { Frame(it) }
    var currentFrame = 0

    fun roll(pins: Int) {
        if(frames.all {
            frame -> frame.isComplete()
        }) {
            throw GameOverException()
        }

        frames[currentFrame].roll(pins)
        if (frames[currentFrame].isComplete()) {
            if (currentFrame < 9)
            currentFrame++
        }

        score = 0
        frames.forEachIndexed { index, frame ->
            val currentFrameScore = frame.TotalRolled
            var bonus = 0
            if ( index < 9 ) {
                bonus = getFrameBonus(index, frame)
            }
            score += currentFrameScore + bonus
        }
    }

    private fun getFrameBonus(index: Int, frame: Frame): Int {
        val nextFrame = frames[index + 1]
        if (frame.IsStrike) {
            return nextFrame.TotalRolled
        }
        if (frame.IsSpare) {
            return nextFrame.roll1Pins ?: 0
        }
        return 0
    }
}