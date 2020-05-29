interface IFrame {


    fun totalRolled(): Score

    fun isComplete(): Boolean

    fun applyStrikeBonus(score: Score): Score

    fun applySpareBonus(score: Score) : Score
}