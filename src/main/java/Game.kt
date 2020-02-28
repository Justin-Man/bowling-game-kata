
class Game {
    var score = Score()
    private var currentFrame = 0
    private val finalFrame = 9

    private val frames = Frames()

    fun roll(pins: Int) {
        if (frames.allFramesComplete()) {
            throw GameOverException()
        }

        frames.roll(currentFrame, pins)
        if (frames.isFrameComplete(currentFrame) && currentFrame < finalFrame) {
            currentFrame++
        }

        score = frames.calculateTotalScore()
    }
}

