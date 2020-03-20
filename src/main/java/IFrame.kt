interface IFrame {

    var roll1Pins : Int?
    var roll2Pins : Int?

    fun roll(pins: Int)
    fun isSpare() : Boolean
    fun isStrike() : Boolean
    fun totalRolled() : Int
    fun isComplete() : Boolean
}