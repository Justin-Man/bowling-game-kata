import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javafx.beans.binding.Bindings.`when`
import javafx.beans.binding.Bindings.`when`





class BowlingGameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    private fun rollMany(numberOfRolls: Int, pins: Int) {
        for (i in 0 until numberOfRolls) {
            game.roll(pins);
        }
    }

    @Test
    fun `score is zero for 20 rolls`() {
        rollMany(20, 0)
        assertEquals(0, game.score())

    }

    @Test
    fun `score is 20 for 20 rolls`() {
        rollMany(20, 1)
        assertEquals(20, game.score())
    }

    @Test
    fun `score is 16 when one spare is scored in 20 rolls`() {
        rollSpare()
        game.roll(3)
        rollMany(17,0);
        assertEquals(16, game.score());
    }

    @Throws(Exception::class)
    @Test
    fun testOneStrike() {
        rollStrike()
        game.roll(3)
        game.roll(4)
        rollMany(16, 0)
        assertEquals(24, game.score())
    }

    @Throws(Exception::class)
    @Test
    fun testPerfectGame() {
        rollMany(12, 10)
        assertEquals(300, game.score())
    }

    private fun rollStrike() {
        game.roll(10)
    }

    private fun rollSpare() {
        game.roll(5)
        game.roll(5)
    }


}