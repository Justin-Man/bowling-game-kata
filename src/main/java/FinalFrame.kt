import java.lang.Exception

class FinalFrame(private val frame: Frame = Frame()) : IFrame {

    private var rollCount = 0

    override fun roll(pins: Int) {
        if (!isComplete()) {
            frame.roll(pins)
            rollCount++
        }
    }

    override fun applyFrameScore(score: Score): Score {
        return frame.applyFrameScore(score)
    }

    override fun setNext(frame: IFrame) {
        throw Exception("Invalid operation")
    }

    override fun totalRolled(): Score {
        return frame.totalRolled()
    }

    override fun isComplete(): Boolean {
        if (frame.isStrike() || frame.isSpare()) {
           return rollCount == 3
        }
        return frame.isComplete()
    }

    override fun applyStrikeBonus(score: Score): Score {
        return frame.applyStrikeBonus(score)
    }

    override fun applySpareBonus(score: Score): Score {
        return frame.applySpareBonus(score)
    }
}