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
        for (index in 0 until rolls.size) {
            if (startNewFrame) frameIndex++

            runningScore += rolls[index]
            if (frameIndex != 10) {
                val isSpare = !startNewFrame && (rolls[index] + rolls[index - 1] == 10)
                val isStrike = startNewFrame && rolls[index] == 10
                if (!isStrike && !isSpare) {
                    startNewFrame = !startNewFrame
                    continue
                }
                val bonusRollIndex = index + 1
                val strikeOnlyBonusIndex = index + 2

                runningScore += rolls.getOrNull(bonusRollIndex) ?: 0

                if (isStrike) {
                    runningScore += rolls.getOrNull(strikeOnlyBonusIndex) ?: 0
                }

                startNewFrame = true

            } else {
                startNewFrame = false
                finalFrameRolls++

                if (finalFrameRolls == 3) isGameOver = true
                else if (finalFrameRolls == 2 && (rolls[index] + rolls[index - 1] < 10)) isGameOver = true
            }
        }

        score = Score(runningScore)
    }
}

