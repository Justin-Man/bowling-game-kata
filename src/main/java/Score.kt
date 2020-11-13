data class Score(var gameScore: Int = 0) {

    fun add(value: Score) = Score(gameScore + value.gameScore)

    fun add(roll: IRoll) = roll.apply(this)
}