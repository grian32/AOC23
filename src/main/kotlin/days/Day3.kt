package days

import containsAny
import isInt
import unique
import util.Loc

class Day3(private val input: String) : Day {

    override fun part1() {
        val inputArr: MutableList<MutableList<String>> = input.split("\n").map { it.split("").filter(String::isNotEmpty).toMutableList() }.toMutableList()
        val acceptedSymbols = listOf("-", "#", "=", "*", "+", "@", "$", "&", "/", "%")
        var partNumberSum = 0

        inputArr.forEachIndexed { x, i ->
            var number = ""
            var possibleSymbols = mutableListOf<String>()
            i.forEachIndexed { y, j ->
                if (j.isInt()) {
                    number += j

                    val indexesAroundNumber = listOf(
                        x - 1 to y - 1, x to y - 1, x + 1 to y - 1,
                        x - 1 to y, x + 1 to y, // space in the middle is x, y
                        x - 1 to y + 1, x to y + 1, x + 1 to y + 1
                    )

                    for ((x2, y2) in indexesAroundNumber) {
                        if (x2 in inputArr.indices && y2 in inputArr[x2].indices) {
                            possibleSymbols += inputArr[x2][y2]
                        }
                    }

                    if (i.lastIndex == y) {
                        if (possibleSymbols.containsAny(acceptedSymbols)) {
                            partNumberSum += number.toInt()
                        }
                        number = ""
                        possibleSymbols = mutableListOf()
                    }
                } else {
                    if (possibleSymbols.containsAny(acceptedSymbols)) {
                        partNumberSum += number.toInt()
                    }
                    number = ""
                    possibleSymbols = mutableListOf()
                }
            }
        }

        println(partNumberSum)
    }

    override fun part2() {
        val inputArr: MutableList<MutableList<String>> = input.split("\n").map { it.split("").filter(String::isNotEmpty).toMutableList() }.toMutableList()
        var gearRatioSum = 0
        val numbersToLocs: MutableMap<Loc, Int> = mutableMapOf()

        inputArr.forEachIndexed { x, i ->
            var number = ""
            var locs = mutableListOf<Loc>()
            i.forEachIndexed { y, j ->
                if (j.isInt()) {
                    number += j
                    locs += Loc(x, y)
                } else {
                    if (locs.size > 0) numbersToLocs[locs[0]] = number.toInt()
                    number = ""
                    locs = mutableListOf()
                }
            }
        }

        println(numbersToLocs)

        inputArr.forEachIndexed { x, i ->
            i.forEachIndexed { y, j ->
                if (j == "*") {
                    val numbersSurrounding = mutableListOf<Int>()

                    val indexesAroundStar = listOf(
                        x - 1 to y - 1, x to y - 1, x + 1 to y - 1,
                        x - 1 to y, x + 1 to y, // space in the middle is x, y
                        x - 1 to y + 1, x to y + 1, x + 1 to y + 1
                    )

                    // widen search range by 1 on top and bottom for star to account for 3 digit numbers and then
                    // just look up the position of any numbers in that table

                    for ((x2, y2) in indexesAroundStar) {
                        if (x2 in inputArr.indices && y2 in inputArr[x2].indices && inputArr[x2][y2].isInt()) {
                            var number = inputArr[x2][y2]



                            if (y2 - 1 in inputArr[x2].indices) {
                                number = inputArr[x2][y2 - 1] + number
                                if (inputArr[x2][y2 - 1].isInt() && y2 - 2 in inputArr[x2].indices) {
                                    number = inputArr[x2][y2 - 2] + number
                                }
                            }

                            if (y2 + 1 in inputArr[x2].indices) {
                                number += inputArr[x2][y2 + 1]
                                if (inputArr[x2][y2 + 1].isInt() && y2 + 2 in inputArr[x2].indices) {
                                    number += inputArr[x2][y2 + 2]
                                }
                            }

                            numbersSurrounding += number.filter { it.isDigit() }.toInt()
                        }
                    }

                    if (numbersSurrounding.unique().size == 2) gearRatioSum += numbersSurrounding[0] * numbersSurrounding[1]
                }
            }
        }

        println(gearRatioSum)
    }
}