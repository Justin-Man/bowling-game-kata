interface IRoll {

    fun apply(score: Score) : Score

    fun scoreReport(): String
}

class Roll(val pins : Int) : IRoll {
    override fun apply(score: Score) : Score {
        return score.add(Score(pins))
    }

    override fun scoreReport() = "[$pins]"
}

fun IRoll.hasBeenRolled(): Boolean {
    return this !is NotRolled
}

class NotRolled : IRoll {
    override fun apply(score: Score) = score

    override fun scoreReport() = "[]"
}