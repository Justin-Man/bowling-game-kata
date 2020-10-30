class Game {
    var score = Score()

    private val rolls = mutableListOf<Int>()
    private val scoreCard = mutableListOf<Int>()
    private val scoreReport = mutableListOf<String>()

    var isGameOver = false

    fun roll(pins: Int) {
        if (isGameOver) throw GameOverException()

        rolls.add(pins)
        var runningScore = 0
        scoreCard.clear()
        scoreReport.clear()
        var startNewFrame = true
        var finalFrameRolls = 0
        var frameIndex = 1
        var frameReport = "* 1 "
        var isSpare = false
        var isStrike = false

        for (index in 0 until rolls.size) {
            runningScore += rolls[index]
            if (frameIndex != 10) {
                isSpare = !startNewFrame && (rolls[index] + rolls[index - 1] == 10)
                isStrike = startNewFrame && rolls[index] == 10

                if (!isStrike && !isSpare) {
                    frameReport += "[${rolls[index]}]"
                    startNewFrame = !startNewFrame
                } else {
                    val bonusRollIndex = index + 1
                    val strikeOnlyBonusIndex = index + 2

                    runningScore += rolls.getOrNull(bonusRollIndex) ?: 0

                    if (isStrike) {
                        frameReport += "[x]"
                        runningScore += rolls.getOrNull(strikeOnlyBonusIndex) ?: 0
                    } else {
                        frameReport += "[/]"
                    }

                    startNewFrame = true
                }
            } else {
                startNewFrame = false
                finalFrameRolls++

                if (finalFrameRolls == 2 && rolls[index - 1] != 10 && (rolls[index] + rolls[index - 1] == 10)) {
                    frameReport += "[/]"
                } else if(rolls[index] == 10) {
                    frameReport += "[x]"
                } else {
                    frameReport += "[${rolls[index]}]"
                }

                if (finalFrameRolls == 3) isGameOver = true
                else if (finalFrameRolls == 2 && (rolls[index] + rolls[index - 1] < 10)) {
                    frameReport += "[]"

                    isGameOver = true
                }
            }
            if (startNewFrame || isGameOver) {
                scoreCard.add(runningScore)

                if ((isStrike && (rolls.size > index + 2))
                        || (isSpare && (rolls.size > index + 1))
                        || (!isStrike && !isSpare)) {
                      frameReport += " $runningScore"
                }

                scoreReport.add(frameReport)
                frameIndex++
                frameReport = "* $frameIndex "
            }
        }

        score = Score(runningScore)
    }

    fun getScoreCard(): List<Int> {
        return scoreCard
    }

    fun getScoreReport(): List<String> {
        return scoreReport
    }
}


