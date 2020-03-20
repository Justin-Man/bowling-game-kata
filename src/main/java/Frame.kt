open class Frame : IFrame {

    override var roll1Pins : Int? = null
    override var roll2Pins : Int? = null

    override fun roll(pins: Int) {
        if (roll1Pins != null && roll2Pins == null) {
            roll2Pins = pins
        }

        if (roll1Pins == null) {
            roll1Pins = pins
        }
    }

    override fun isSpare() = !isStrike() && totalRolled() == 10

    override fun isStrike() = roll1Pins == 10

    override fun totalRolled() = (roll1Pins ?: 0) + (roll2Pins ?: 0)

    override fun isComplete() = (roll1Pins != null && roll2Pins != null) || isStrike()
}