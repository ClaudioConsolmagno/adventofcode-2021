package dev.claudio.adventofcode2021

fun main() {
    Day10().main()
}

private class Day10 {
    val openChars = setOf('(', '[', '{', '<')
    val closeChars = setOf(')', ']', '}', '>')
    val opensToCloseChars = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    val pointsMap = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    fun main() {
        val input: List<String> = Support.readFileAsListString("day10-input.txt")

        input.map {
            processLine(it)
        }.sum().let { println(it) }
    }

    fun processLine(line: String) : Int {
        val opens: ArrayDeque<Char> = ArrayDeque()
        line.forEach {
            if (openChars.contains(it)) {
                opens.add(it)
            } else {
                val lastOpen = opens.removeLast()
                val expected = opensToCloseChars[lastOpen]
                if (expected != it) {
                    return pointsMap[it]!!
                }
            }
        }
        return 0
    }
}