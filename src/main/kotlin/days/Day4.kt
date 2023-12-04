package days

class Day4(private val input: String) : Day {

    private val numberOfGames = input.lines().size

    override fun part1() {
        val parsedInput = input
            .lines()
            .map { it
                .split(": ")[1]
                .split(" | ")
                .map { it
                    .split(" ")
                    .map(String::trim)
                    .filter(String::isNotEmpty)
                }
            }

        var pointSum = 0

        for (game in parsedInput) {
            val yourNumbers = game[0]
            val winningNumbers = game[1]
            var points = 0

            for (i in yourNumbers) {
                if (i in winningNumbers) {
                    if (points == 0) {
                        points = 1
                        continue
                    } else {
                        points *= 2
                    }
                }
            }

            pointSum += points
        }

        println(pointSum)
    }

    override fun part2() {
        val inputLines = input.lines()
        val gameToWinning: MutableMap<Int, Int> = mutableMapOf()


        for (i in inputLines) {
            var amountOfWinningNums = 0
            val split = i.split(": ")
            val gameId = split[0].split(" ").last().trim().toInt()
            val game = split[1].split(" | ")
                .map { it
                    .split(" ")
                    .map(String::trim)
                    .filter(String::isNotEmpty)
                }
            val yourNumbers = game[0]
            val winningNumbers = game[1]

            for (j in yourNumbers) {
                if (j in winningNumbers) {
                    amountOfWinningNums++
                }
            }

            gameToWinning[gameId] = amountOfWinningNums
        }

        val countToCard = MutableList(numberOfGames) { 1 }
        for (i in 1..numberOfGames) {
            for (j in 0..<(gameToWinning[i]!!)) {
                countToCard[(i + j + 1).coerceAtMost(numberOfGames - 1)] += countToCard[i]
            }
        }

        println(countToCard.sum())
    }
}