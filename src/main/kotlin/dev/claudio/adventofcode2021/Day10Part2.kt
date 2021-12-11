package dev.claudio.adventofcode2021

fun main() {
    Day10Part2().main()
}

private class Day10Part2 {
    val openChars = setOf('(', '[', '{', '<')
    val opensToCloseChars = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>'
    )
    val pointsMap = mapOf(
        ')' to 1L,
        ']' to 2L,
        '}' to 3L,
        '>' to 4L,
    )

    fun main() {
        val input: List<String> = Support.readFileAsListString("day10-input.txt")

        val results = input
            .map {
                processLine(it)
            }
            .filter { it.isNotEmpty() }
            .map { score(it) }
            .sorted()

        println(results[results.size/2])
    }

    private fun score(chars: List<Char>) : Long =
        chars.map { pointsMap[it]!! }
            .fold(0L) { acc, next -> acc * 5L + next }

    fun processLine(line: String): List<Char> {
        val opens: ArrayDeque<Char> = ArrayDeque()
        line.forEach {
            if (openChars.contains(it)) {
                opens.add(it)
            } else {
                val lastOpen = opens.removeLast()
                val expected = opensToCloseChars[lastOpen]
                if (expected != it) {
                    return listOf()
                }
            }
        }
        return opens.asReversed().map {
            opensToCloseChars[it]!!
        }
    }
}