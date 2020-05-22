class Rolls {
    private val rolls = mutableListOf<Roll>()

    private val notRolled = NotRolled()
    fun add(pins: Int) {
        rolls.add(Roll(pins))
    }

    fun first() : Roll {
        return rolls.getOrElse(0) { notRolled }
    }

    fun second() : Roll {
        return rolls.getOrElse(1) { notRolled }
    }

    fun third() : Roll {
        return rolls.getOrElse(2) { notRolled }
    }
}