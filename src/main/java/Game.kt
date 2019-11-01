/**
 * Write a class named “Game” that has two methods
roll(pins : int) is called each time the player rolls a ball.  The argument is the number of pins knocked down.
score() : int is called only at the very end of the game.  It returns the total score for that game.
 * */

class Game {
    // a way of keeping track of previous rolls
    private val rolls = IntArray(21)
    private var currentRoll = 0

    fun roll(pins: Int) {
        rolls[currentRoll++] = pins
    }

    fun score(): Int {
        var score = 0
        var frameIndex = 0
        for (frames in 0 until 10) {
            when {
                isStrike(frameIndex) -> {
                    score += 10 +
                            rolls[frameIndex + 1] +
                            rolls[frameIndex + 2]
                    frameIndex++
                }
                isSpare(frameIndex) -> {
                    score += 10 + rolls[frameIndex + 2]
                    frameIndex += 2
                }
                else -> {
                    score += rolls[frameIndex] + rolls[frameIndex + 1]
                    frameIndex += 2
                }
            }
        }
        return score
    }

    private fun isStrike(frameIndex: Int) = rolls[frameIndex] == 10

    private fun isSpare(frameIndex: Int): Boolean = rolls[frameIndex] + rolls[frameIndex + 1] == 10

}