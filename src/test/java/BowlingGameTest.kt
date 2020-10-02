import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class BowlingGameTest {

    lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    @Test
    fun `score is zero for no rolls`() {
        assertEquals(Score(0), game.score)
    }

    @Test
    fun `score is two after first roll scores two`() {
        game.roll(2)

        assertEquals(Score(2), game.score)
    }

    @Test
    fun `score is sum of two rolls`() {
        game.roll(1)
        game.roll(2)

        assertEquals(Score(3), game.score)
    }

    @Test
    fun `spare score is ten plus the next roll value`() {
        rollSpare()
        game.roll(5)

        assertEquals(Score(20), game.score)

    }

    @Test
    fun `strike score is ten plus next two roll values`() {
        rollStrike()
        game.roll(1)
        game.roll(5)

        assertEquals(Score(22), game.score)
    }

    @Test
    fun `score is 30 after two strikes`() {
        rollStrike()
        rollStrike()
        rollEmptyFrame()

        assertEquals(Score(30), game.score)
    }

    @Test
    fun `score is 0 for 20 gutter rolls`() {
        rollManyEmptyFrames(10)

        assertEquals(Score(0), game.score)
    }

    @Test
    fun `score is 16 after strike on ninth frame and rolled 1 and 2 on tenth frame`() {
        rollManyEmptyFrames(8)
        rollStrike()
        game.roll(1)
        game.roll(2)
        assertEquals(Score(16), game.score)
    }

    @Test
    fun `score is 14 after spare on ninth frame and rolled 1 and 2 on tenth frame`() {
        rollManyEmptyFrames(8)
        rollSpare()
        game.roll(1)
        game.roll(2)

        assertEquals(Score(14), game.score)
    }

    @Test
    fun `on tenth frame player not allowed to roll extra ball if spare not scored`() {
        rollManyEmptyFrames(9)
        game.roll(1)
        game.roll(2)

        assertFailsWith(GameOverException::class) {
            game.roll(3)
        }
    }

    @Test
    fun `on tenth frame player is allowed to roll extra ball if spare is scored`() {
        rollManyEmptyFrames(9)
        rollSpare()
        game.roll(1)
    }

    @Test
    fun `on tenth frame player is allowed to roll extra ball if strike is scored`() {
        rollManyEmptyFrames(9)
        rollStrike()
        game.roll(1)
    }

    @Test
    fun `score is 12 when spare is rolled on tenth frame followed by 2`() {
        rollManyEmptyFrames(9)
        rollSpare()
        game.roll(2)

        assertEquals(Score(12), game.score)
    }

    @Test
    fun `score is 30 when 3 strikes is rolled on tenth frame`() {
        rollManyEmptyFrames(9)
        rollStrike()
        rollStrike()
        rollStrike()

        assertEquals(Score(30), game.score)
    }

    @Test
    fun `score is 300 for game with all strikes scored`() {
        for (i in 0 until 9) {
            rollStrike()
        }

        rollStrike()
        rollStrike()
        rollStrike()

        assertEquals(Score(300), game.score)
    }

    @Test
    fun `Score is 60 when 3 strikes rolled in a row`() {
        rollStrike()
        rollStrike()
        rollStrike()

        assertEquals(Score(60), game.score)
    }

    @Test
    fun `score card is 300 for perfect game`() {
        rollPerfectScore()

        val scores = game.getScoreCard()

        assertEquals(10, scores.size)
        for (i in scores.indices) {
            assertEquals(i.toString(), 30 * (i + 1), scores[i].gameScore)
        }
    }

    @Test
    fun `score card is 60 for 3 strikes`() {
        for (i in 0 until 3) {
            rollStrike()
        }

        val scores = game.getScoreCard()

        assertEquals(30, scores[0].gameScore)
        assertEquals(50, scores[1].gameScore)
        assertEquals(60, scores[2].gameScore)
    }

    @Test
    fun `score card for spares`() {
        rollSpare()
        rollSpare()

        val scores = game.getScoreCard()

        assertEquals(16, scores[0].gameScore)
        assertEquals(26, scores[1].gameScore)
    }

    @Test
    fun `score card is 0 for empty game`() {
        rollManyEmptyFrames(10)

        val scores = game.getScoreCard()

        assertEquals(0, scores.sumBy { it.gameScore })
    }

    @Test
    fun `score report is displayed for 1 frame without strike or spare`() {
        game.roll(8)
        game.roll(1)

        val scoreReport = game.getScoreReport()

        assertEquals("* 1 [8][1]", scoreReport.first())
    }

    private fun rollStrike() {
        game.roll(10)
    }

    private fun rollSpare() {
        game.roll(6)
        game.roll(4)
    }

    private fun rollEmptyFrame() {
        game.roll(0)
        game.roll(0)
    }

    private fun rollManyEmptyFrames(frames: Int) {
        for (i in 0 until frames) {
            rollEmptyFrame()
        }
    }

    private fun rollPerfectScore() {
        for (i in 0 until 9) {
            rollStrike()
        }

        rollStrike()
        rollStrike()
        rollStrike()
    }
}