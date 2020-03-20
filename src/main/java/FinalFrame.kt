class FinalFrame : Frame(), IFrame {

    private var roll3pins : Int? = null

    override fun roll(pins: Int) {
        val rollStrategy = getRollStrategy()
        rollStrategy(pins)
    }

    override fun totalRolled(): Int {
        return super.totalRolled() + (roll3pins ?: 0)
    }

    override fun isComplete(): Boolean {
        if (isStrike() || isSpare()) {
           return roll3pins != null
        }
        return super.isComplete()
    }

    fun getRollStrategy() : (Int) -> Unit {
        if (roll1Pins != null && roll2Pins != null)
            return {pins -> roll3pins = pins}

        return {pins -> super.roll(pins)}
    }
}