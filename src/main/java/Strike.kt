import Frame.Companion.MAX_PINS

class Strike(val roll: Roll = Roll(MAX_PINS)) : IRoll {

    override fun apply(score: Score) = roll.apply(score)

    override fun scoreReport() = "[x]"
}

class Spare(val pins: Int) : IRoll {

    val roll: IRoll

    init {
        roll = Roll(pins)
    }

    override fun apply(score: Score) = roll.apply(score)

    override fun scoreReport() = "[/]"
}