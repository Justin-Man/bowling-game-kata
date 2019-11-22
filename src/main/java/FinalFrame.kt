class FinalFrame : Frame(), IFrame {

    private var roll3pins : Int? = null

    override fun roll(pins: Int) {
        if (roll1Pins != null && roll2Pins != null) {
            roll3pins = pins
        } else {
            super.roll(pins)
        }
        TotalRolled = super.TotalRolled + (roll3pins ?: 0)

        if(IsStrike || IsSpare) {
          isComplete = roll3pins != null
        }
    }
}