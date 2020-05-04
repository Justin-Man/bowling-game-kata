

class Frames {
    private val frames = mutableListOf<Frame>()

    fun allFramesComplete() = frames.size == 10 && frames.all { it.isComplete() }
    fun roll(pins: Int) {
        if (frames.size == 0){
            addNewFrame()
        }
        frames.last().roll(pins)

        if (frames.size < 10 && frames.last().isComplete()) {
            addNewFrame()
        }
    }

    private fun addNewFrame(): Frame {
        val frame = if (frames.size == 9) FinalFrame() else Frame(frames.size)
        frames.add(frame)
        return frame
    }

    fun calculateTotalScore() : Score {
        var score = Score(0)
        frames.forEach {
          score = it.applyFrameScore(score, this)
        }

        return score
    }

    fun tryGetFrame(index: Int) =
            if (index < frames.size)
                frames[index]
            else
                null
}