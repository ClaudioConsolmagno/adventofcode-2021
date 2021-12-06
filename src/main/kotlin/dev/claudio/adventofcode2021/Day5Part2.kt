package dev.claudio.adventofcode2021

import kotlin.math.abs
import kotlin.math.sign

fun main() {
    Day5Part2().main()
}

private class Day5Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("day5-input.txt")
        val lines = inputList
            .map {
                val split: List<String> = it.split(" -> ", ",")
                Line(
                    Integer.valueOf(split[0]),
                    Integer.valueOf(split[1]),
                    Integer.valueOf(split[2]),
                    Integer.valueOf(split[3]),
                )
            }
            .filter { it.isHorizontalOrVerticalOrDiagonal45() }

        val hits: Map<String, Int> = lines.flatMap { it.hits() }.groupingBy { it }.eachCount()
        val count = hits.filter {
            it.value > 1
        }.count()
        println(lines)
        println(hits)
        println(count)
    }

    data class Line(
        val x1: Int,
        val y1: Int,
        val x2: Int,
        val y2: Int,
    ) {
        fun isHorizontalOrVerticalOrDiagonal45(): Boolean {
            return x1 == x2 || y1 == y2 || abs(x1-x2) == abs(y1-y2)
        }

        fun hits() : List<String> {
            val hits: MutableList<String> = mutableListOf()
            if (x1 == x2) {
                var low : Int
                var high : Int
                if (y1 <= y2) { low = y1; high = y2 } else { low = y2; high = y1 }
                (low..high).forEach {
                    hits.add("$x1,$it")
                }
            } else if (y1 == y2) {
                var low : Int
                var high : Int
                if (x1 <= x2) { low = x1; high = x2 } else { low = x2; high = x1 }
                (low..high).forEach {
                    hits.add("$it,$y1")
                }
            } else if (abs(x1-x2) == abs(y1-y2)) {
                val directionX = sign((x1-x2).toDouble()).toInt()
                val directionY = sign((y1-y2).toDouble()).toInt()
                var currentX = x2
                var currentY = y2
                hits.add("$currentX,$currentY")
                while (currentX != x1) {
                    currentX += directionX
                    currentY += directionY
                    hits.add("$currentX,$currentY")
                }
            }
            return hits
        }
    }
}