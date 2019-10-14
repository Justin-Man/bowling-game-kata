class Game {
    // TODO(on a refactor step)
    var score: Int = 0
    var numberOfRolls = 0
    var addingStrikeBonus = false

    fun roll(pins: Int) {
        val test =2
        if (numberOfRolls == 2 && score == 10) {
            score += pins
        }
        if (addingStrikeBonus) {
            score += pins
        }
        score += pins
        numberOfRolls++

        if (numberOfRolls == 1 && score == 10) {
            addingStrikeBonus = true
        }
    }
}