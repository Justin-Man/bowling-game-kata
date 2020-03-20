open class Frame : IFrame {

    override var rolls = Rolls()

    override fun roll(pins: Int) {
        rolls.add(pins)
    }

    override fun isSpare() = !isStrike() && totalRolled() == 10

    override fun isStrike() = rolls.first() == 10

    override fun totalRolled() = (rolls.first() ?: 0) + (rolls.second() ?: 0)

    override fun isComplete() = (rolls.first() != null && rolls.second() != null) || isStrike()
}