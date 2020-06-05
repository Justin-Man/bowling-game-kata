interface IFrame {

    fun roll(pins: Int)

    fun applyFrameScore(score: Score): Score

    fun setNext(frame: IFrame)

    fun totalRolled(): Score

    fun isComplete(): Boolean

    fun applyStrikeBonus(score: Score): Score

    fun applySpareBonus(score: Score) : Score
}