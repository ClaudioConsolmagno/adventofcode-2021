package dev.claudio.adventofcode2021

fun main() {
    Day8Part2().main()
}

private class Day8Part2 {

    fun main() {
        val input: List<InputLine> = Support.readFileAsListString("day8-input.txt")
            .map {
                val split = it.split("|")
                InputLine(
                    split[0].trim().split(" "),
                    split[1].trim().split(" ")
                )
            }

        val resultsString: List<String> = input.map {
            val deduction = mutableMapOf<Int, Set<Char>>()
            (it.signalPattern + it.outputValue).forEach { input ->
                if (input.length == 2) {
                    deduction[1] = setOf(input[0], input[1])
                }
                if (input.length == 4) {
                    deduction[4] = setOf(input[0], input[1], input[2], input[3])
                }
                if (input.length == 3) {
                    deduction[3] = setOf(input[0], input[1], input[2])
                }
            }
            it.outputValue.map { output: String ->
                var char = 'X'
                if (output.length == 2) {
                    char = '1'
                }
                if (output.length == 4) {
                    char = '4'
                }
                if (output.length == 3) {
                    char = '7'
                }
                if (output.length == 7) {
                    char = '8'
                }
                if (output.length == 5) {
                    char = '5'
                    if (deduction[1] != null) {
                        if (output.toCharArray().toList().containsAll(deduction[1]!!)) {
                            char = '3'
                        } else {
                            if (deduction[4] != null) {
                                if (output.toCharArray().toList().count { it in deduction[4]!! } == 2) {
                                    char = '2'
                                } else {
                                    char = '5'
                                }
                            }
                        }
                    }

                    if (deduction[7] != null) {
                        if (output.toCharArray().toList().containsAll(deduction[1]!!)) {
                            char = '3'
                        } else {
                            if (deduction[4] != null) {
                                if (output.toCharArray().toList().count { it in deduction[4]!! } == 2) {
                                    char = '2'
                                } else {
                                    char = '5'
                                }
                            }
                        }
                    }
                }
                if (output.length == 6) {
                    char = 'Z'
                    if (deduction[1] != null) {
                        if (output.toCharArray().toList().containsAll(deduction[1]!!)) {
                            char = '0'
                        } else {
                            char = '6'
                        }
                    }
                    if (deduction[4] != null) {
                        if (output.toCharArray().toList().containsAll(deduction[4]!!)) {
                            char = '9'
                        }
                    }
                }
                char
            }.joinToString("")
        }
        println(resultsString.map { it.toInt() }.sum())
    }

    data class InputLine(
        val signalPattern: List<String>,
        val outputValue: List<String>,
    )
}