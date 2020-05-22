open class Frame : IFrame {

    override var rolls = Rolls()

    protected var nextFrame : IFrame = EmptyFrame()

    fun setNext(frame: Frame) {
        nextFrame = frame
    }

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    protected fun isSpare() = !isStrike() && totalRolled() == Score(10)

    protected fun isStrike() = rolls.first() == 10

    override fun totalRolled() = Score((rolls.first() ?: 0) + (rolls.second() ?: 0))

    override fun isComplete() = (rolls.first() != null && rolls.second() != null) || isStrike()

    fun applyFrameScore(score: Score) =
            score.add(totalRolled())
                 .add(getFrameBonus())

    override fun getFrameBonus(): Score {
        return when {
            this.isStrike() -> nextFrame.giveStrikeBonus()
            this.isSpare() -> nextFrame.giveSpareBonus()
            else -> Score(0)
        }
    }

    override fun giveStrikeBonus() : Score {
        var strikeBonus = totalRolled()
        if (isStrike()) {
            strikeBonus = strikeBonus.add(Score((nextFrame.rolls.first() ?: 0)))
        }
        return strikeBonus
    }

     override fun giveSpareBonus(): Score {
        return Score(rolls.first() ?: 0)
    }
}
