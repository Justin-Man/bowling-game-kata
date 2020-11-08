open class Frame(
    private val nextFrame: IFrame,
    private var rolls: Rolls = Rolls()
) : IFrame {

    companion object {
        const val MAX_PINS = 10
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

    override fun reportScore(index: Int, score: Score) : String {
        if(!isStrike() && !isSpare()) return "* ${index + 1} ${rolls.getScoreReport()} ${score.gameScore}"
        if (isStrike() && nextFrame.isStrikeBonusAvailable()) return "* ${index + 1} ${rolls.getScoreReport()} ${score.gameScore}"
        if (isSpare() && nextFrame.isSpareBonusAvailable()) return "* ${index + 1} ${rolls.getScoreReport()} ${score.gameScore}"

        return "* ${index + 1} ${rolls.getScoreReport()}"
    }

    override fun isStrikeBonusAvailable(): Boolean {
        return if (isStrike()) nextFrame.isSpareBonusAvailable()
        else (rolls.second() !is NotRolled)
    }

    override fun isSpareBonusAvailable(): Boolean {
        return rolls.first() !is NotRolled
    }
}
