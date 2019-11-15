class Frame(private val frameIndex: Int) : IFrame {

    override var IsSpare: Boolean = false
    override var IsStrike: Boolean = false
    override var TotalRolled: Int = 0
    override var roll1Pins : Int? = null
    private var roll3pins : Int? = null

    private var roll2Pins : Int? = null

    override fun roll(pins: Int) {
        if (roll1Pins == null) {
            roll1Pins = pins
            IsStrike = pins == 10
        } else if (roll2Pins == null) {
            roll2Pins = pins
            IsSpare = (roll1Pins ?: 0) + (roll2Pins ?: 0) == 10
        } else {
            roll3pins = pins
        }

        TotalRolled = (roll1Pins ?: 0) + (roll2Pins ?: 0) + (roll3pins ?: 0)
    }

    override fun isComplete() : Boolean {
        if(frameIndex == 9 && (IsStrike || IsSpare)) {
            return roll3pins != null
        }
        return (roll1Pins != null && roll2Pins != null) || IsStrike
    }
}