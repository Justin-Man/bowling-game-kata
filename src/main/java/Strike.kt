import Frame.Companion.MAX_PINS

class Strike : Roll(MAX_PINS) {
    override fun scoreReport() = "[x]"
}

class Spare(pins: Int) : Roll(pins) {
    override fun scoreReport() = "[/]"
}