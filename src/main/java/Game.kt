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
            if ( index < 9 ) {
                var nextFrame = frames[index + 1]
                var bonus = 0
                if (frame.IsStrike) {
                    bonus = nextFrame.TotalRolled
                } else if (frame.IsSpare) {
                    bonus = nextFrame.roll1Pins ?: 0
                }

                score += currentFrameScore + bonus
            }
        }
    }
}