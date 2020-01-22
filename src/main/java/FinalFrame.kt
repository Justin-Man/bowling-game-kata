class FinalFrame : Frame(), IFrame {

    private var roll3pins : Int? = null

    override fun roll(pins: Int) {
        val rollStrategy = getRollStrategy()
        rollStrategy(pins)

        totalRolled = super.totalRolled + (roll3pins ?: 0)

        if (isStrike || isSpare) {
            isComplete = roll3pins != null
        }
    }

    fun getRollStrategy() : (Int) -> Unit {
        if (roll1Pins != null && roll2Pins != null)
            return {pins -> roll3pins = pins}

        return {pins -> super.roll(pins)}
    }
}