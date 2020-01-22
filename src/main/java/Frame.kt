open class Frame : IFrame {

    override var isSpare: Boolean = false
    override var isStrike: Boolean = false
    override var totalRolled: Int = 0
    override var roll1Pins : Int? = null
    override var roll2Pins : Int? = null
    override var isComplete : Boolean = false

    override fun roll(pins: Int) {
        if (!firstRollIncomplete() && secondRollIncomplete()) {
            roll2Pins = pins
            isSpare = calculateTotalRolled() == 10
        }

        if (firstRollIncomplete()) {
            roll1Pins = pins
            isStrike = pins == 10
        }

        totalRolled = calculateTotalRolled()
        isComplete = isFrameComplete()
    }

    private fun firstRollIncomplete() = roll1Pins == null

    private fun secondRollIncomplete() = roll2Pins == null

    private fun calculateTotalRolled() = (roll1Pins ?: 0) + (roll2Pins ?: 0)

    private fun isFrameComplete() = (roll1Pins != null && roll2Pins != null) || isStrike
}