class Frame {

    var IsSpare: Boolean = false
    var IsStrike: Boolean = false
    var TotalRolled: Int = 0
    var roll1Pins : Int? = null
    private var roll2Pins : Int? = null

    fun roll(pins: Int) {
        if (roll1Pins == null) {
            roll1Pins = pins
            IsStrike = pins == 10
        } else {
            roll2Pins = pins
            IsSpare = (roll1Pins ?: 0) + (roll2Pins ?: 0) == 10
        }

        TotalRolled = (roll1Pins ?: 0) + (roll2Pins ?: 0)
    }

    fun isComplete() : Boolean {
        return (roll1Pins != null && roll2Pins != null) || IsStrike
    }
}