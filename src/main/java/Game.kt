class Game {
    var score = Score()

    private val rolls = mutableListOf<Int>()

    private var frameIndex = 0
    var isGameOver = false

    fun roll(pins: Int) {
        if (isGameOver) throw GameOverException()

        rolls.add(pins)
        score = Score(0)
        var startNewFrame = true
        var finalFrameRolls = 0
        frameIndex = 0
        rolls.forEachIndexed { index, roll ->
            if (startNewFrame) frameIndex++

            if (frameIndex != 10) {
                val isSpare = !startNewFrame && (roll + rolls[index - 1] == 10)
                val isStrike = startNewFrame && roll == 10
                score = score.add(Score(roll))
                if(isSpare) {
                    if (index < rolls.lastIndex) {
                        score = score.add(Score(rolls[index + 1]))
                    }
                }
                if(isStrike) {
                    if (index < rolls.lastIndex) {
                        score = score.add(Score(rolls[index + 1]))
                    }
                    if(index + 1 < rolls.lastIndex) {
                        score = score.add(Score(rolls[index + 2]))
                    }
                    startNewFrame = true
                } else {
                    startNewFrame = !startNewFrame
                }
            } else {
                startNewFrame = false
                finalFrameRolls++
                score = score.add(Score(roll))
                if(finalFrameRolls == 3) isGameOver = true
                else if (finalFrameRolls == 2 && (roll + rolls[index - 1] < 10)) isGameOver = true
            }
        }
    }
}

