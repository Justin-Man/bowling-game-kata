import java.util.*

class Frames {
    private val frames = mutableListOf<IFrame>()
    private var currentFrame = 0

    init {
        frames.add(FinalFrame())
        for(i in 0 until 9) {
            val nextFrame = frames[i]
            frames.add(Frame(nextFrame))
        }
        frames.reverse()
    }

    fun allFramesComplete() = frames.all { it.isComplete() }
    fun roll(pins: Int) {
        frames[currentFrame].roll(pins)

        if (frames[currentFrame].isComplete()) {
            currentFrame++
        }
    }

    fun calculateTotalScore(): Score {
        var score = Score()
        frames.forEach {
            score = it.applyFrameScore(score)
        }

        return score
    }
}