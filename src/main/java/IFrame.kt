interface IFrame {

    fun roll(pins: Int)

    fun applyFrameScore(score: Score): Score

    fun totalRolled(): Score

    fun isComplete(): Boolean

    fun addStrikeBonusForPreviousFrame(score: Score): Score

    fun addSpareBonusForPreviousFrame(score: Score) : Score
}