

class Frames {
    private val frames = mutableListOf<IFrame>()

    fun allFramesComplete() = frames.size == 10 && frames.all { it.isComplete }
    fun roll(pins: Int) {
        if (frames.size == 0){
            addNewFrame()
        }
        frames.last().roll(pins)

        if (frames.size < 10 && frames.last().isComplete) {
            addNewFrame()
        }
    }

    private fun addNewFrame(): IFrame {
        val frame = if (frames.size == 9) FinalFrame() else Frame()
        frames.add(frame)
        return frame
    }

    fun calculateTotalScore() : Score {
        var score = 0
        for (index in frames.indices) {
            score += calculateFrameScore(index)
        }

        return Score(score)
    }

    private fun calculateFrameScore(index: Int) : Int {
        return frames[index].totalRolled + getFrameBonus(index)
    }

    private fun getFrameBonus(index: Int): Int {
        val finalFrame = 9
        if (index == finalFrame) return 0
        val nextFrame = tryGetFrame(index + 1)
        return when {
            frames[index].isStrike -> getStrikeBonus(index)
            frames[index].isSpare -> nextFrame?.roll1Pins ?: 0
            else -> 0
        }
    }

    private fun tryGetFrame(index: Int) =
            if (index < frames.size)
                frames[index]
            else
                null

    private fun getStrikeBonus(index: Int): Int {
        val penultimateFrame = 8
        val nextFrame = frames[index + 1]
        var strikeBonus = nextFrame.totalRolled
        if (nextFrame.isStrike && index < penultimateFrame) {
            val frameAfter = frames[index + 2]
            strikeBonus = nextFrame.totalRolled + (frameAfter.roll1Pins ?: 0)
        }
        if (index == penultimateFrame) {
            strikeBonus = (nextFrame.roll1Pins ?: 0) + (nextFrame.roll2Pins ?: 0)
        }
        return strikeBonus
    }
}