class Game {
    var score = Score()

    private val rolls = mutableListOf<Int>()
    private val scoreCard = mutableListOf<Int>()

    var isGameOver = false

    fun roll(pins: Int) {
        if (isGameOver) throw GameOverException()

        rolls.add(pins)
        var runningScore = 0
        scoreCard.clear()
        var startNewFrame = true
        var finalFrameRolls = 0
        var frameIndex = 1
        for (index in 0 until rolls.size) {
            runningScore += rolls[index]
            if (frameIndex != 10) {
                val isSpare = !startNewFrame && (rolls[index] + rolls[index - 1] == 10)
                val isStrike = startNewFrame && rolls[index] == 10
                if (!isStrike && !isSpare) {
                    startNewFrame = !startNewFrame
                } else {
                    val bonusRollIndex = index + 1
                    val strikeOnlyBonusIndex = index + 2

                    runningScore += rolls.getOrNull(bonusRollIndex) ?: 0

                    if (isStrike) {
                        runningScore += rolls.getOrNull(strikeOnlyBonusIndex) ?: 0
                    }

                    startNewFrame = true
                }
            } else {
                startNewFrame = false
                finalFrameRolls++

                if (finalFrameRolls == 3) isGameOver = true
                else if (finalFrameRolls == 2 && (rolls[index] + rolls[index - 1] < 10)) isGameOver = true
            }
            if (startNewFrame || isGameOver) {
                scoreCard.add(runningScore)
                frameIndex++
            }
        }

        score = Score(runningScore)
    }

    fun getScoreCard(): List<Int> {
        return scoreCard
    }
}

// TODO Method to print out score card at any time
// write for both procedural implementation and refactored impl and compare how easy it is to integrate
// and change the code


