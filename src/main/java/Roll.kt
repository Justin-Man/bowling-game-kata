interface IRoll {

    val pins: Int

    fun apply(score: Score) : Score

    fun scoreReport(): String
}

class Roll(override val pins : Int) : IRoll {
    override fun apply(score: Score) : Score {
        return score.add(Score(pins))
    }

    override fun scoreReport() = "[$pins]"
}

fun IRoll.hasBeenRolled(): Boolean {
    return this !is NotRolled
}

class NotRolled(override val pins: Int = 0) : IRoll {
    override fun apply(score: Score) = score

    override fun scoreReport() = "[]"
}