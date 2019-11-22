interface IFrame {

    var IsSpare: Boolean
    var IsStrike: Boolean
    var TotalRolled: Int
    var roll1Pins : Int?
    var roll2Pins : Int?
    var isComplete: Boolean

    fun roll(pins: Int)
}