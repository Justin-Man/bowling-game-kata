import Frame.Companion.MAX_PINS

class Rolls {
    private val rolls = mutableListOf<Roll>()

    private val notRolled = NotRolled()

    fun add(pins: Int) {
        if (pins == MAX_PINS) {
            rolls.add(Strike())
        } else {
            rolls.add(Roll(pins))
        }
    }

    fun first() : Roll {
        return rolls.getOrElse(0) { notRolled }
    }

    fun second() : Roll {
        return rolls.getOrElse(1) { notRolled }
    }

    fun totalRolled() = rolls.fold(Score(0)) { acc, roll -> roll.apply(acc) }
}