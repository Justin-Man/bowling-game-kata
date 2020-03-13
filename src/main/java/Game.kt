
class Game {
    var score = Score()

    private val frames = Frames()

    fun roll(pins: Int) {
        if (frames.allFramesComplete()) {
            throw GameOverException()
        }

        frames.roll(pins)

        score = frames.calculateTotalScore()
    }
}

