class FinalFrame : Frame() {

    override fun totalRolled(): Score {
        return super.totalRolled().add(Score(rolls.third() ?: 0))
    }

    override fun isComplete(): Boolean {
        if (isStrike() || isSpare()) {
           return rolls.third() != null
        }
        return super.isComplete()
    }

    override fun getFrameBonus(): Score {
        return Score(0)
    }

    override fun giveStrikeBonus(): Score {
        return super.totalRolled()
    }
}