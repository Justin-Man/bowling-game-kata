open class Frame {

    protected var rolls = Rolls()

    protected var nextFrame : Frame? = null

    fun setNext(frame: Frame) {
        nextFrame = frame
    }

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    protected fun isSpare() = !isStrike() && totalRolled() == Score(10)

    protected fun isStrike() = rolls.first() == 10

    open fun totalRolled() = Score((rolls.first() ?: 0) + (rolls.second() ?: 0))

    open fun isComplete() = (rolls.first() != null && rolls.second() != null) || isStrike()

    fun applyFrameScore(score: Score) =
            score.add(totalRolled())
                 .add(getFrameBonus())

    open fun getFrameBonus(): Score {
        return when {
            this.isStrike() -> nextFrame?.giveStrikeBonus() ?: Score(0)
            this.isSpare() -> nextFrame?.giveSpareBonus() ?: Score(0)
            else -> Score(0)
        }
    }

    open fun giveStrikeBonus() : Score {
        var strikeBonus = totalRolled()
        if (isStrike()) {
            strikeBonus = strikeBonus.add(Score((nextFrame?.rolls?.first() ?: 0)))
        }
        return strikeBonus
    }

    private fun giveSpareBonus(): Score {
        return Score(rolls.first() ?: 0)
    }
}
