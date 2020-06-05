class Frames {
    private val frames = mutableListOf<IFrame>()

    fun allFramesComplete() = frames.size == 10 && frames.all { it.isComplete() }
    fun roll(pins: Int) {
        if (frames.size == 0) {
            addNewFrame()
        }
        frames.last().roll(pins)

        if (frames.size < 10 && frames.last().isComplete()) {
           frames.last().setNext(addNewFrame())
        }
    }

    private fun addNewFrame(): IFrame {
        val frame = if (frames.size == 9) FinalFrame() else Frame()
        frames.add(frame)
        return frame
    }

    fun calculateTotalScore(): Score {
        var score = Score()
        frames.forEach {
            score = it.applyFrameScore(score)
        }

        return score
    }
}