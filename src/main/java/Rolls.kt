class Rolls {
    private val rolls = mutableListOf<Int>()

    fun add(pins: Int) {
        rolls.add(pins)
    }

    fun first() : Int? {
        return rolls.firstOrNull()
    }

    fun second() : Int? {
        return rolls.getOrNull(1)
    }

    fun third() : Int? {
        return rolls.getOrNull(2)
    }
}