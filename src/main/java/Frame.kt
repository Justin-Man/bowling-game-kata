open class Frame(private val index: Int)  {

    var rolls = Rolls()

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    protected fun isSpare() = !isStrike() && totalRolled() == 10

    protected fun isStrike() = rolls.first() == 10

    open fun totalRolled() = (rolls.first() ?: 0) + (rolls.second() ?: 0)

    open fun isComplete() = (rolls.first() != null && rolls.second() != null) || isStrike()

    fun applyFrameScore(frames: Frames) =
            totalRolled() + getFrameBonus(frames)

    private fun getFrameBonus(frames: Frames): Int  {
        val finalFrame = 9
        if (index == finalFrame) return 0
        val nextFrame = frames.tryGetFrame(index + 1)
        return when {
            this.isStrike() -> getStrikeBonus(frames)
            this.isSpare() -> getSpareBonus(nextFrame)
            else -> 0
        }
    }

    private fun getStrikeBonus(frames: Frames): Int {
        val penultimateFrame = 8
        val nextFrame = frames.tryGetFrame(index + 1)
        var strikeBonus = nextFrame?.totalRolled()
        if (nextFrame != null && nextFrame.isStrike() && index < penultimateFrame) {
            val frameAfter = frames.tryGetFrame(index + 2)
            strikeBonus = nextFrame.totalRolled() + (frameAfter?.rolls?.first() ?: 0)
        }

        if (index == penultimateFrame) {
            strikeBonus = (nextFrame?.rolls?.first() ?: 0) + (nextFrame?.rolls?.second() ?: 0)
        }
        return strikeBonus ?: 0
    }

    private fun getSpareBonus(nextFrame: Frame?): Int {
        return nextFrame?.rolls?.first() ?: 0
    }
}