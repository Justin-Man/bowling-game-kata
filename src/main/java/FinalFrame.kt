class FinalFrame(private val rolls: FinalFrameRolls = FinalFrameRolls(), private val frame: Frame = Frame(EmptyFrame(), rolls)) : IFrame {

    override fun roll(pins: Int) {
        if (!isComplete()) {
            frame.roll(pins)
            rollCount++
        }
    }

    override fun applyFrameScore(score: Score): Score {
        return frame.applyFrameScore(score)
    }

    override fun totalRolled(): Score {
        return frame.totalRolled()
    }

    override fun isComplete(): Boolean {
        if (frame.isStrike() || frame.isSpare()) {
           return rollCount == 3
        }
        return frame.isComplete()
    }

    override fun addStrikeBonusForPreviousFrame(score: Score): Score {
        return frame.addStrikeBonusForPreviousFrame(score)
    }

    override fun addSpareBonusForPreviousFrame(score: Score): Score {
        return frame.addSpareBonusForPreviousFrame(score)
    }

    override fun reportScore(index: Int, score: Score): String {
        if (isComplete()) return "* ${index + 1} ${rolls.getScoreReport()} ${score.gameScore}"

        return "* ${index + 1} ${rolls.getScoreReport()}"
    }

    override fun isStrikeBonusAvailable(): Boolean {
        return rollCount >= 2
    }

    override fun isSpareBonusAvailable(): Boolean {
        return rollCount >= 1
    }
}