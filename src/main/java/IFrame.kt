interface IFrame {

    var IsSpare: Boolean
    var IsStrike: Boolean
    var TotalRolled: Int
    var roll1Pins : Int?
    var roll2Pins : Int?

    fun roll(pins: Int)
    fun isComplete() : Boolean
}