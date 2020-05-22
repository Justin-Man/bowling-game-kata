open class Roll(private val pins : Int) {
    fun apply(score: Score) : Score {
        return score.add(Score(pins))
    }
}

class NotRolled : Roll(0)