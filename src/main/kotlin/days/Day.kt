package days

import java.io.File
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
            return File(this::class.java.getResource("/input/day${day}.txt")?.file ?: "").readText()
        }
    }
}