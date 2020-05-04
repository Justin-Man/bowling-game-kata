open class Frame(private val index: Int) {

    var rolls = Rolls()

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    protected fun isSpare() = !isStrike() && totalRolled() == Score(10)

    protected fun isStrike() = rolls.first() == 10

    open fun totalRolled() = Score((rolls.first() ?: 0) + (rolls.second() ?: 0))

    open fun isComplete() = (rolls.first() != null && rolls.second() != null) || isStrike()

    fun applyFrameScore(score: Score, frames: Frames) =
            score.add(totalRolled())
                 .add(getFrameBonus(frames))

    private fun getFrameBonus(frames: Frames): Score {
        val finalFrame = 9
        if (index == finalFrame) return Score(0)
        val nextFrame = frames.tryGetFrame(index + 1)
        return when {
            this.isStrike() -> getStrikeBonus(frames)
            this.isSpare() -> getSpareBonus(nextFrame)
            else -> Score(0)
        }
    }

    private fun getStrikeBonus(frames: Frames): Score {
        val penultimateFrame = 8
        val nextFrame = frames.tryGetFrame(index + 1)
        var strikeBonus = nextFrame?.totalRolled()
        if (nextFrame != null && nextFrame.isStrike() && index < penultimateFrame) {
            val frameAfter = frames.tryGetFrame(index + 2)
            strikeBonus = strikeBonus?.add(Score((frameAfter?.rolls?.first() ?: 0)))
        }

        if (index == penultimateFrame) {
            strikeBonus = Score((nextFrame?.rolls?.first() ?: 0) + (nextFrame?.rolls?.second() ?: 0))
        }
        return strikeBonus ?: Score(0)
    }

    private fun getSpareBonus(nextFrame: Frame?): Score {
        return Score(nextFrame?.rolls?.first() ?: 0)
    }
}