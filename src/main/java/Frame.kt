open class Frame(private val nextFrame: IFrame) : IFrame {

    companion object {
        const val MAX_PINS = 10
    }

    private var rolls = Rolls()

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
            this.isStrike() -> nextFrame.addStrikeBonusForPreviousFrame(score)
            this.isSpare() -> nextFrame.addSpareBonusForPreviousFrame(score)
            else -> score
        }
    }

    override fun addStrikeBonusForPreviousFrame(score: Score): Score {
        var updatedScore = score
                .add(rolls.first())
                .add(rolls.second())
        if (isStrike()) { // if current frame is strike add the next two rolls
            updatedScore = nextFrame.addSpareBonusForPreviousFrame(updatedScore)
        }
        return updatedScore
    }

    override fun addSpareBonusForPreviousFrame(score: Score): Score {
        return score.add(rolls.first())
    }

    override fun reportScore(index: Int) = "* ${index + 1} [${rolls.first().pins}][${rolls.second().pins}]"
}
