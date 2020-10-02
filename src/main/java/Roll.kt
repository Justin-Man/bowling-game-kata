open class Roll(val pins : Int) {
    fun apply(score: Score) : Score {
        return score.add(Score(pins))
    }
}

class NotRolled : Roll(0)