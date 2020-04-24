class FinalFrame : Frame(index = 9) {

    override fun totalRolled(): Int {
        return super.totalRolled() + (rolls.third() ?: 0)
    }

    override fun isComplete(): Boolean {
        if (isStrike() || isSpare()) {
           return rolls.third() != null
        }
        return super.isComplete()
    }
}