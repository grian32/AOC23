package days

import java.net.URI
import java.nio.file.Paths

interface Day {
    fun part1() {

    }

    fun part2() {


    }

    fun run() {
        part1()
        part2()
    }

    companion object {
        fun importInput(day: Int): String {
            val url = this::class.java.getResource("/inputs/day${day}.txt")
            val path = Paths.get(url?.toURI() ?: URI(""))
            return path.toFile().readText()
        }
    }
}