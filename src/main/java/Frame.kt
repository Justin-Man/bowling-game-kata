class Frame : IFrame {

    override var IsSpare: Boolean = false
    override var IsStrike: Boolean = false
    override var TotalRolled: Int = 0
    override var roll1Pins : Int? = null

    private var roll2Pins : Int? = null

    override fun roll(pins: Int) {
        if (roll1Pins == null) {
            roll1Pins = pins
            IsStrike = pins == 10
        } else {
            roll2Pins = pins
            IsSpare = (roll1Pins ?: 0) + (roll2Pins ?: 0) == 10
        }

        TotalRolled = (roll1Pins ?: 0) + (roll2Pins ?: 0)
    }

    override fun isComplete() : Boolean {
        return (roll1Pins != null && roll2Pins != null) || IsStrike
    }
}