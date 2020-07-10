class Game {
    var score = Score()

    private val rolls = mutableListOf<Int>()

    var isGameOver = false

    fun roll(pins: Int) {
        if (isGameOver) throw GameOverException()

        rolls.add(pins)
        var runningScore = 0
        var startNewFrame = true
        var finalFrameRolls = 0
        var frameIndex = 0
        rolls.forEachIndexed { index, roll ->
            if (startNewFrame) frameIndex++

            runningScore += roll
            if (frameIndex != 10) {
                val isSpare = !startNewFrame && (roll + rolls[index - 1] == 10)
                val isStrike = startNewFrame && roll == 10
                if (isSpare) {
                    if (index < rolls.lastIndex) {
                        runningScore += rolls[index + 1]
                    }
                }
                if (isStrike) {
                    if (index < rolls.lastIndex) {
                        runningScore += rolls[index + 1]

                    }
                    if (index + 1 < rolls.lastIndex) {
                        runningScore += rolls[index + 2]
                    }
                    startNewFrame = true
                } else {
                    startNewFrame = !startNewFrame
                }
            } else {
                startNewFrame = false
                finalFrameRolls++

                if (finalFrameRolls == 3) isGameOver = true
                else if (finalFrameRolls == 2 && (roll + rolls[index - 1] < 10)) isGameOver = true
            }
        }
        score = Score(runningScore)
    }
}

