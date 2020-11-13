import Frame.Companion.MAX_PINS

class Strike(val roll: Roll = Roll(MAX_PINS)) : IRoll {

    override val pins: Int = roll.pins

    override fun apply(score: Score) = roll.apply(score)

    override fun scoreReport() = "[x]"
}

class Spare(override val pins: Int) : IRoll {

    val roll: IRoll = Roll(pins)

    override fun apply(score: Score) = roll.apply(score)

    override fun scoreReport() = "[/]"
}