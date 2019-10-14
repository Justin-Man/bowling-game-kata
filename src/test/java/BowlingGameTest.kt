import org.junit.Assert.*
import org.junit.Test

class BowlingGameTest {

    @Test
    fun `score is zero for no rolls`() {
        val game = Game()

        val result = game.score

        assertEquals(0, result)
    }

    @Test
    fun `score is two after first roll scores two`() {
        val game = Game()
        val pinsKnockedDown = 2

        game.roll(pinsKnockedDown)

        assertEquals(pinsKnockedDown, game.score)
    }

    @Test
    fun `score is sum of two rolls`() {
        val game = Game()

        game.roll(1)
        game.roll(2)

        assertEquals(3, game.score)
    }

    @Test
    fun `spare score is ten plus the next roll value`() {
        val game = Game()

        game.roll(9)
        game.roll(1)
        game.roll(5)

        assertEquals(20, game.score)

    }

    @Test
    fun `strike score is ten plus next two roll values`() {
        val game = Game()

        game.roll(10)
        game.roll(1)
        game.roll(5)

        assertEquals(22, game.score)
    }
}