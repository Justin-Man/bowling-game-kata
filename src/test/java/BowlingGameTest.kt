import org.junit.Assert.*
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
        assertEquals(0, game.score)
    }

    @Test
    fun `score is two after first roll scores two`() {
        game.roll(2)

        assertEquals(2, game.score)
    }

    @Test
    fun `score is sum of two rolls`() {
        game.roll(1)
        game.roll(2)

        assertEquals(3, game.score)
    }

    @Test
    fun `spare score is ten plus the next roll value`() {
        rollSpare()
        game.roll(5)

        assertEquals(20, game.score)

    }

    @Test
    fun `strike score is ten plus next two roll values`() {
        rollStrike()
        game.roll(1)
        game.roll(5)

        assertEquals(22, game.score)
    }

    @Test
    fun `score is 30 after two strikes`() {
        rollStrike()
        rollStrike()

        assertEquals(30, game.score)
    }

    @Test
    fun `score is 0 for 20 gutter rolls`() {
        rollManyEmptyFrames(10)

        assertEquals(0, game.score)
    }

    @Test
    fun `score is 16 after strike on ninth frame and rolled 1 and 2 on tenth frame`() {
        rollManyEmptyFrames(8)
        rollStrike()
        game.roll(1)
        game.roll(2)
        assertEquals(16, game.score)
    }

    @Test
    fun `score is 14 after spare on ninth frame and rolled 1 and 2 on tenth frame`() {
        rollManyEmptyFrames(8)
        rollSpare()
        game.roll(1)
        game.roll(2)

        assertEquals(14, game.score)
    }

    @Test
    fun `on tenth frame player not allowed to roll extra ball if spare not scored on ninth frame`() {
        rollManyEmptyFrames(9)
        game.roll(1)
        game.roll(2)

        assertFailsWith(Exception::class) {
            game.roll(3)
        }
    }

    @Test
    fun `score is 12 when spare is rolled on tenth frame followed by 2`() {
        rollManyEmptyFrames(9)
        rollSpare()
        game.roll(2)

        assertEquals(12, game.score)
        // todo: get to green, create class called FinalFrame implementing IFrame,
        //  add to Frame and FinalFrame to Starting Array to handle cases specific to final frame
        //  maybe move logic from Game to Frame - eg: getFrameBonus?
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
        for(i in 0 until frames) {
            rollEmptyFrame()
        }
    }
}