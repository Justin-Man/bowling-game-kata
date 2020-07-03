data class Score(private var gameScore: Int = 0) {

    fun add(value: Score) = Score(gameScore + value.gameScore)
}