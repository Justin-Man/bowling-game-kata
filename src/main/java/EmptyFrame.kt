class EmptyFrame : IFrame {

    override fun totalRolled(): Score {
        return Score(0)
    }

    override fun isComplete(): Boolean {
        return false
    }

    override fun getFrameBonus(): Score {
        return Score(0)
    }

    override fun giveStrikeBonus(): Score {
        return Score(0)
    }

    override fun giveSpareBonus(): Score {
        return Score(0)
    }
}