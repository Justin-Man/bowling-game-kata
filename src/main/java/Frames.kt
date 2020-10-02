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

    fun getCumulativeScore() : List<Score> {
        val scores = mutableListOf<Score>()
        var score = Score()
        frames.forEach {
            score = it.applyFrameScore(score)
            scores.add(score)
        }

        return scores
    }

    fun getScoreReport(): List<String> {
        val scoreReport = mutableListOf<String>()

        scoreReport.add(frames.first().reportScore(0))

        return scoreReport
    }
}