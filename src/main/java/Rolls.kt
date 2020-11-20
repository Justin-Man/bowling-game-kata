import Frame.Companion.MAX_PINS

open class Rolls {
    protected val rolls = mutableListOf<IRoll>()

    val notRolled = NotRolled()

    fun add(pins: Int) {
       rolls.add(createRoll(pins))
    }

    open fun createRoll(pins: Int) =
        when {
            allPinsKnockedDown(pins) -> if(rolls.isEmpty()) Strike() else Spare(pins)
            else -> Roll(pins)
        }

    private fun allPinsKnockedDown(pins: Int) = last().apply(Score(pins)) == Score(MAX_PINS)

    fun first() : IRoll {
        return rolls.getOrElse(0) { notRolled }
    }

    fun second() : IRoll {
        return rolls.getOrElse(1) { notRolled }
    }

    fun last() : IRoll {
        return if (rolls.isEmpty()) notRolled else rolls.last()
    }

    fun totalRolled() = rolls.fold(Score(0)) { acc, roll -> roll.apply(acc) }

    open fun getScoreReport() = rolls.fold("") {acc, roll -> acc + roll.scoreReport() }
}