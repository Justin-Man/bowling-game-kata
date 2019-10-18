import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

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
        game.roll(9)
        game.roll(1)
        game.roll(5)

        assertEquals(20, game.score)

    }

    @Test
    fun `strike score is ten plus next two roll values`() {
        game.roll(10)
        game.roll(1)
        game.roll(5)

        assertEquals(22, game.score)
    }

    @Test
    fun `score is 30 after two strikes`() {
        game.roll(10)
        game.roll(10)

        assertEquals(30, game.score)
    }

    @Test
    fun `score is 0 for 20 gutter rolls`() {
        for(i in 0 until 20) {
            game.roll(0)
        }

        assertEquals(0, game.score)
    }

    @Test
    fun `score is 12 after strike on ninth frame and rolled 1 on tenth frame`() {
        for(i in 0 until 16) {
            game.roll(0)
        }
        game.roll(10)
        game.roll(1)

        assertEquals(12, game.score) // TODO: Get to green and refactor
    }
}