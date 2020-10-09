open class Roll(val pins : Int) {
    fun apply(score: Score) : Score {
        return score.add(Score(pins))
    }

    open fun scoreReport() = "[$pins]"
}

class NotRolled : Roll(0) {
    override fun scoreReport() = "[]"
}