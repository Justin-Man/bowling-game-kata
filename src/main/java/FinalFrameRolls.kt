import Frame.Companion.MAX_PINS

class FinalFrameRolls : Rolls() {
    override fun createRoll(pins: Int): IRoll {
        return when {
            pins == MAX_PINS && (super.last() is Strike || super.last() is Spare) -> Strike()
            else -> super.createRoll(pins)
        }
    }

    fun third() : IRoll {
        return rolls.getOrElse(2) { notRolled }
    }

    override fun getScoreReport(): String {

        return "${super.first().scoreReport()}${super.second().scoreReport()}${third().scoreReport()}"
    }
}