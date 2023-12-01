package days

class Day1(val input: String) : Day {
    override fun part1()  {
        val processedInput = input.lines().map { it.removeAllLetters() }
        var sum = 0

        for (i in processedInput) {
            val split = i.split("").filter { it.isNotBlank() }
            val number = "${split.first()}${split.last()}"

            sum += number.toInt()
        }

        println(sum)
    }

    override fun part2() {
        val spelledToDigits = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
        )
        var sum = 0
        val inputLines = input.lines()

        inputLines.forEach { str ->
            val first = Regex("one|two|three|four|five|six|seven|eight|nine|\\d").find(str).let {
                if (it!!.value.toIntOrNull() != null) {
                    return@let it.value
                }

                spelledToDigits[it.value]
            }

            val last = Regex("eno|owt|eerht|ruof|evif|xis|neves|thgie|enin|\\d").find(str.reversed()).let{
                if (it!!.value.toIntOrNull() != null) {
                    return@let it.value
                }

                spelledToDigits[it.value.reversed()]
            }

            println("$first$last")

            sum += "$first$last".toInt()
        }

        println(sum)
    }

    companion object {
        fun String.removeAllLetters(): String {
            return this.filterNot { it.isLetter() }
        }

    }
}