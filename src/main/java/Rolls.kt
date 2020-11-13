import Frame.Companion.MAX_PINS

open class Rolls {
    protected val rolls = mutableListOf<IRoll>()

    val notRolled = NotRolled()

    fun add(pins: Int) {
       rolls.add(createRoll(pins))
    }

    open fun createRoll(pins: Int) : IRoll {
        return when {
            rolls.isEmpty() && pins == MAX_PINS -> Strike()
            pins + last().pins == MAX_PINS -> Spare(pins)
            else -> Roll(pins)
        }
    }

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