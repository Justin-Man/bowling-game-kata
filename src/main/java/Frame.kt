open class Frame() : IFrame {

    companion object {
        const val MAX_PINS = 10
    }

    private var rolls = Rolls()

    private var nextFrame: IFrame = EmptyFrame()

    override fun setNext(frame: IFrame) {
        nextFrame = frame
    }

    override fun roll(pins: Int) {
        rolls.add(pins)
    }

    fun isSpare() = !isStrike() && totalRolled() == Score(MAX_PINS)

    fun isStrike() = rolls.first() is Strike

    override fun totalRolled() = rolls.totalRolled()

    override fun isComplete() = (rolls.first() !is NotRolled && rolls.second() !is NotRolled) || isStrike()

    override fun applyFrameScore(score: Score): Score {
        return applyFrameBonus(score.add(totalRolled()))
    }

    open fun applyFrameBonus(score: Score): Score {
        return when {
            this.isStrike() -> nextFrame.applyStrikeBonus(score)
            this.isSpare() -> nextFrame.applySpareBonus(score)
            else -> score
        }
    }

    override fun applyStrikeBonus(score: Score): Score {
        var updatedScore = score
                .add(rolls.first())
                .add(rolls.second())
        if (isStrike()) {
            updatedScore = nextFrame.applySpareBonus(updatedScore)
        }
        return updatedScore
    }

    override fun applySpareBonus(score: Score): Score {
        return score.add(rolls.first())
    }
}
