package dev.claudio.adventofcode2021

fun main() {
    Day8().main()
}

private class Day8 {
    fun main() {
        val input: List<InputLine> = Support.readFileAsListString("day8-input.txt")
            .map {
                val split = it.split("|")
                InputLine(
                    split[0].trim().split(" "),
                    split[1].trim().split(" ")
                )
            }

        val count = input
            .flatMap { it.outputValue }
            .map {
                it.toCharArray()
            }
            .count {
                it.size in setOf(2, 4, 3, 7)
            }


        println(input)
        println(count)
    }

    data class InputLine(
        val signalPattern: List<String>,
        val outputValue: List<String>
    )
}