package dev.claudio.adventofcode2021

fun main() {
    Day6().main()
}

private class Day6 {
    fun main() {
        val inputList: List<Int> = Support.readFileAsListString("day6-input.txt")
            .flatMap { it.split(",") }
            .map { it.toInt() }

        var outputList = inputList
        (0 until 80).forEach{
            outputList = tick(outputList)
        }
        println(outputList.count())
    }

    private fun tick(inputList: List<Int>): List<Int> {
        val spawns = inputList.count {
            it == 0
        }
        return inputList
            .map { if (it == 0) { 6 } else { it - 1 } }
            .plus((0 until spawns).map { 8 })
    }
}