package dev.claudio.adventofcode2021

import dev.claudio.adventofcode2021.Support.Companion.printGrid
import java.awt.Point

fun main() {
    Day13Part2().main()
}

private class Day13Part2 {
    fun main() {
        val input: List<String> = Support.readFileAsListString("day13-input.txt")

        val coords: MutableSet<Point> = input.filter { !it.startsWith("fold") && it.isNotEmpty() }.map {
            val split = it.split(",")
            Point(split[0].toInt(), split[1].toInt())
        }.toMutableSet()
        val folds: List<Point> = input.filter { it.startsWith("fold") }.map {
            val split = it.split(" ")
            val split2 = split[2].split("=")
            if (split2[0] == "x") {
                Point(split2[1].toInt(), -1)
            } else {
                Point(-1, split2[1].toInt())
            }
        }

        folds.forEach { fold ->
            if (fold.y >= 0) {
                val foldedCoords: List<Point> = coords.filter { it.y > fold.y }.map { Point(it.x, fold.y - (it.y - fold.y)) }
                coords.removeIf { it.y >= fold.y }
                coords.addAll(foldedCoords)
            }
            if (fold.x >= 0) {
                val foldedCoords: List<Point> = coords.filter { it.x > fold.x }.map { Point(fold.x - (it.x - fold.x), it.y) }
                coords.removeIf { it.x >= fold.x }
                coords.addAll(foldedCoords)
            }
        }
        coords.printGrid()
    }
}