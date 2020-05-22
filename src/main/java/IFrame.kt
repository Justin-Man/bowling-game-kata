interface IFrame {

    var rolls : Rolls

    fun totalRolled(): Score

    fun isComplete(): Boolean

    fun getFrameBonus(): Score

    fun giveStrikeBonus(): Score

    fun giveSpareBonus() : Score
}