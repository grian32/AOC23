package days

class Day2(private val input: String) : Day {
    override fun part1() {
        val processedInput = input.lines()
        var possibleGameSum = 0

        first@ for (i in processedInput) {
            val split = i.split(": ")
            val gameId = split[0].removePrefix("Game ").toInt()

            for (j in split[1].split(";")) {
                val pairs = j.split(", ").map(String::trim)

                for (k in pairs) {
                    val splitPair = k.split(" ")
                    val amount = splitPair[0].toInt()
                    val color = splitPair[1]

                    when (color) {
                        "red" -> {
                            if (amount > 12) continue@first
                        }
                        "green" -> {
                            if (amount > 13) continue@first
                        }
                        "blue" -> {
                            if (amount > 14) continue@first
                        }
                    }
                }
            }

            possibleGameSum += gameId
        }

        println(possibleGameSum)
    }

    override fun part2() {
        val processedInput = input.lines()
        var powerSetsSum = 0

        for (i in processedInput) {
            val redCubesArr = mutableListOf<Int>()
            val greenCubesArr = mutableListOf<Int>()
            val blueCubesArr = mutableListOf<Int>()

            for (j in i.split(": ")[1].split(";")) {
                val pairs = j.split(", ").map(String::trim)

                for (k in pairs) {
                    val splitPair = k.split(" ")
                    val amount = splitPair[0].toInt()
                    val color = splitPair[1]

                    when (color) {
                        "red" -> redCubesArr += amount
                        "green" -> greenCubesArr += amount
                        "blue" -> blueCubesArr += amount
                    }
                }
            }

            powerSetsSum += redCubesArr.max() * greenCubesArr.max() * blueCubesArr.max()
        }

        println(powerSetsSum)
    }
}