open class Frame : IFrame {

    companion object {
        const val MAX_PINS = 10
    }

    override var rolls = Rolls()

    protected var nextFrame : IFrame = EmptyFrame()

    fun setNext(frame: Frame) {
        nextFrame = frame
    }

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    protected fun isSpare() = !isStrike() && totalRolled() == Score(MAX_PINS)

    protected fun isStrike() = rolls.first() is Strike

    override fun totalRolled() =
            Score().add(rolls.first())
                   .add(rolls.second())

    override fun isComplete() = (rolls.first() !is NotRolled && rolls.second() !is NotRolled) || isStrike()

    fun applyFrameScore(score: Score) =
            score.add(totalRolled())
                 .add(getFrameBonus())

    override fun getFrameBonus(): Score {
        return when {
            this.isStrike() -> nextFrame.giveStrikeBonus()
            this.isSpare() -> nextFrame.giveSpareBonus()
            else -> Score()
        }
    }

    override fun giveStrikeBonus() : Score {
        var strikeBonus = totalRolled()
        if (isStrike()) {
            strikeBonus = strikeBonus.add(nextFrame.rolls.first())
        }
        return strikeBonus
    }

     override fun giveSpareBonus(): Score {
        return Score().add(rolls.first())
    }
}
