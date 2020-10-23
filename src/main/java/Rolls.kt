import Frame.Companion.MAX_PINS

open class Rolls {
    protected val rolls = mutableListOf<Roll>()

    val notRolled = NotRolled()

    fun add(pins: Int) {
       rolls.add(createRoll(pins))
    }

    open fun createRoll(pins: Int) : Roll {
        return when {
            rolls.isEmpty() && pins == MAX_PINS -> Strike()
            pins + last().pins == MAX_PINS -> Spare(pins)
            else -> Roll(pins)
        }
    }

    fun first() : Roll {
        return rolls.getOrElse(0) { notRolled }
    }

    fun second() : Roll {
        return rolls.getOrElse(1) { notRolled }
    }

    fun last() : Roll {
        return if (rolls.isEmpty()) notRolled else rolls.last()
    }

    fun totalRolled() = rolls.fold(Score(0)) { acc, roll -> roll.apply(acc) }

    open fun getScoreReport() = rolls.fold("") {acc, roll -> acc + roll.scoreReport() }
}