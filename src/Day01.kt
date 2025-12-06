fun main() {

    fun partOne() {
        val testInput = readInput("Day01_test")

        var dial = TheDial()
        var zerCount = 0

        testInput.forEachIndexed { index, it ->
            val direction = it.first()
            val distance = it.drop(1).toInt()
            dial = when (direction) {
                'R' -> {
                    dial.dialRight(clicks = distance)
                }

                'L' -> {
                    dial.dialLeft(clicks = distance)
                }

                else -> throw IllegalArgumentException("Unknown direction $direction")
            }
            if (dial.isAtZero) zerCount++
        }
        println("zeroCount: $zerCount")
    }

    fun partTwo() {

    }

    partTwo()
}

@JvmInline
value class TheDial(val pointer: Int = 50) {
    val isAtZero get() = pointer == 0
    fun dialRight(clicks: Int): TheDial = TheDial((pointer + clicks) % 100)
    fun dialLeft(clicks: Int): TheDial = TheDial((pointer - clicks) % 100)
}

class TheDialButWithCounter(val pointer: Int = 0) {
    private var count = 0
    fun getPassword() = count
    fun dialRight(clicks: Int): TheDial {
        val newPointer = pointer + clicks
        if (newPointer/100 > 0) count++
        return TheDial(newPointer % 100)
    }
    fun dialLeft(clicks: Int): TheDial = TheDial((pointer - clicks) % 100)
}