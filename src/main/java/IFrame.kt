interface IFrame {
    var rolls : Rolls

    fun roll(pins: Int)
    fun isComplete() : Boolean
    fun applyFrameScore(index: Int, frames: Frames) : Int
}