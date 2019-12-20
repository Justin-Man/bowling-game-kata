class FinalFrame : Frame(), IFrame {

    private var roll3pins : Int? = null

    override fun roll(pins: Int) {
        if (roll1Pins != null && roll2Pins != null) {
            roll3pins = pins
        } else {
            super.roll(pins)
        }
        totalRolled = super.totalRolled + (roll3pins ?: 0)

        if(isStrike || isSpare) {
          isComplete = roll3pins != null
        }
    }
}