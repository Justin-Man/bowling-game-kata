class EmptyFrame : IFrame {

    override fun roll(pins: Int) {}

    override fun applyFrameScore(score: Score): Score {
        return score
    }

    override fun totalRolled(): Score {
        return Score(0)
    }

    override fun isComplete(): Boolean {
        return false
    }

    override fun addStrikeBonusForPreviousFrame(score: Score): Score {
        return score
    }

    override fun addSpareBonusForPreviousFrame(score: Score): Score {
        return score
    }

    override fun reportScore(index: Int): String {
        TODO("Not yet implemented")
    }
}