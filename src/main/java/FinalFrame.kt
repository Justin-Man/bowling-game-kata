class FinalFrame : Frame() {

    override fun totalRolled(): Score {
        return super.totalRolled().add(rolls.third())
    }

    override fun isComplete(): Boolean {
        if (isStrike() || isSpare()) {
           return rolls.third() !is NotRolled
        }
        return super.isComplete()
    }

    override fun applyFrameBonus(score: Score): Score {
        return score
    }

    override fun applyStrikeBonus(score: Score): Score {
        return score.add(super.totalRolled())
    }
}