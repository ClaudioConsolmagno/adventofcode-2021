package dev.claudio.adventofcode2021

fun main() {
    Day7Part2().main()
}

private class Day7Part2 {
    fun main() {
        val input: Map<Int, Int> = Support.readFileAsListString("day7-input.txt")
            .flatMap { it.split(",") }
            .map { it.toInt() }
            .groupingBy { it }.eachCount()

        val min = input.keys.minOrNull()!!
        val max = input.keys.maxOrNull()!!
        var lastBest : Int = Integer.MAX_VALUE
        (min until max).forEach { candidate ->
            var currentRun = 0
            input.keys.forEach {
                val distance = kotlin.math.abs(candidate - it)
                val triangularNumber = distance * (distance+1) / 2
                currentRun += (triangularNumber * input[it]!!)
            }
            if (currentRun < lastBest) {
                lastBest = currentRun
            }
        }
        println(min)
        println(max)
        println(lastBest)
    }
}