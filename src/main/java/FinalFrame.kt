class FinalFrame : Frame(index = 9) {

    override fun totalRolled(): Score {
        return super.totalRolled().add(Score(rolls.third() ?: 0))
    }

    override fun isComplete(): Boolean {
        if (isStrike() || isSpare()) {
           return rolls.third() != null
        }
        return super.isComplete()
    }
}