class FinalFrame(private val frame: Frame = Frame(EmptyFrame())) : IFrame {

    private var rollCount = 0

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

    override fun reportScore(index: Int): String {
        TODO("Not yet implemented")
    }
}