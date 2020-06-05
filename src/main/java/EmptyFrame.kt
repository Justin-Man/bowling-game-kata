class EmptyFrame : IFrame {

    override fun roll(pins: Int) {}

    override fun applyFrameScore(score: Score): Score {
        return score
    }

    override fun setNext(frame: IFrame) {}

    override fun totalRolled(): Score {
        return Score(0)
    }

    override fun isComplete(): Boolean {
        return false
    }

    override fun applyStrikeBonus(score: Score): Score {
        return score
    }

    override fun applySpareBonus(score: Score): Score {
        return score
    }
}