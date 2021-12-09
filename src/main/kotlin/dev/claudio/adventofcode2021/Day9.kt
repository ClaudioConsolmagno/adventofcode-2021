package dev.claudio.adventofcode2021

fun main() {
    Day9().main()
}

private class Day9 {
    fun main() {
        val input: List<String> = Support.readFileAsListString("day9-input.txt")
        val xSize = input[0].length
        val input2: MutableList<String> = mutableListOf()
        input2.add("9".repeat(xSize))
        input2.addAll(input)
        input2.add("9".repeat(xSize))
        val map: List<List<Int>> = input2
            .map { "9" + it + "9" }
            .map {
                println(it.toCharArray())
                it.toCharArray().map { it2 -> it2.titlecase().toInt() } }

        var riskLevelSum = 0
        (1 until map.size-1).forEach { x ->
            (1 until map[x].size-1).forEach { y ->
                val candidate = map[x][y]
                println(candidate)
                val neighbours = listOf(map[x-1][y-1],map[x-1][y-0],map[x-1][y+1],
                    map[x-0][y-1],map[x-0][y+1],
                    map[x+1][y-1],map[x+1][y-0],map[x+1][y+1],)
                if (neighbours.all { candidate < it }) {
                    riskLevelSum += (candidate + 1)
                }
            }
        }

        println(riskLevelSum)
    }
}