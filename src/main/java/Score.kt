data class Score(private var gameScore: Int = 0) {

    constructor(roll: Roll) : this(roll.pins)

    fun add(value: Score) = Score(gameScore + value.gameScore)

    fun add(roll: Roll) = Score(gameScore + roll.pins)
}