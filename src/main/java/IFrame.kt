interface IFrame {
    var rolls : Rolls

    fun roll(pins: Int)
    fun isSpare() : Boolean
    fun isStrike() : Boolean
    fun totalRolled() : Int
    fun isComplete() : Boolean
}