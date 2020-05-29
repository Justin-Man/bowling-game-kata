open class Frame : IFrame {

    companion object {
        const val MAX_PINS = 10
    }

    protected var rolls = Rolls()

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

    fun applyFrameScore(score: Score): Score {

        return applyFrameBonus(score.add(totalRolled()))
    }

    open fun applyFrameBonus(score: Score): Score {
        return when {
            this.isStrike() -> nextFrame.applyStrikeBonus(score)
            this.isSpare() -> nextFrame.applySpareBonus(score)
            else -> score
        }
    }

    override fun applyStrikeBonus(score: Score) : Score {
        var updatedScore = score.add(totalRolled())
        if (isStrike()) {
            updatedScore = nextFrame.applySpareBonus(updatedScore)
        }
        return updatedScore
    }

     override fun applySpareBonus(score: Score): Score {
        return score.add(rolls.first())
    }
}
