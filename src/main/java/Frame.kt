open class Frame : IFrame {

    override var isSpare: Boolean = false
    override var isStrike: Boolean = false
    override var totalRolled: Int = 0
    override var roll1Pins : Int? = null

    override var roll2Pins : Int? = null

    override var isComplete : Boolean = false

    override fun roll(pins: Int) {
        if (roll1Pins == null) {
            roll1Pins = pins
            isStrike = pins == 10
        } else if (roll2Pins == null) {
            roll2Pins = pins
            isSpare = (roll1Pins ?: 0) + (roll2Pins ?: 0) == 10
        }

        totalRolled = (roll1Pins ?: 0) + (roll2Pins ?: 0)
        isComplete = (roll1Pins != null && roll2Pins != null) || isStrike
    }
}