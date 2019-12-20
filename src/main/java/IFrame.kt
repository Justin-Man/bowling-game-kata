interface IFrame {

    var isSpare: Boolean
    var isStrike: Boolean
    var totalRolled: Int
    var roll1Pins : Int?
    var roll2Pins : Int?
    var isComplete: Boolean

    fun roll(pins: Int)
}