class Game {
    var score: Int = 0
    val frames = Array(10) { Frame() }
    var currentFrame = 0

    fun roll(pins: Int) {
        frames[currentFrame].roll(pins)
        if (frames[currentFrame].isComplete()) {
            currentFrame++
        }

        score = 0
        frames.forEachIndexed { index, frame ->
            var currentFrameScore = frame.TotalRolled
            var bonus = 0
            if ( index < 9 ) {
                bonus = getFrameBonus(index, frame)
            }
            score += currentFrameScore + bonus
        }
    }

    private fun getFrameBonus(index: Int, frame: Frame): Int {
        var nextFrame = frames[index + 1]
        if (frame.IsStrike) {
            return nextFrame.TotalRolled
        }
        if (frame.IsSpare) {
            return nextFrame.roll1Pins ?: 0
        }
        return 0
    }
}