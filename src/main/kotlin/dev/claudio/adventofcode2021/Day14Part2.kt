package dev.claudio.adventofcode2021

fun main() {
    Day14Part2().main()
}

private class Day14Part2 {
    fun main() {
        val input = Support.readFileAsListString("day14-input.txt")
        val template: String = input[0]
        val pairInsertions: Map<String, String> = input.takeLastWhile { it.isNotBlank() }.associate {
            it.substring(0, it.indexOf(" ")) to it.substring(it.lastIndexOf(" ") + 1, it.length)
        }
        val pairTotalCount: HashBag<String> = HashBag(findPairs(template))
        val elemTotalCount: HashBag<Char> = HashBag(template.toList())
        var currentPairs: HashBag<String> = HashBag(findPairs(template))

        (1..40).forEach {
            val newPairs: List<Pair<String, Long>> = currentPairs.getMap().keys.flatMap {
                elemTotalCount.add(pairInsertions[it]!![0], currentPairs.getCount(it))
                listOf(it[0].toString() + pairInsertions[it] to currentPairs.getCount(it),
                    pairInsertions[it] + it[1].toString() to currentPairs.getCount(it))
            }
            currentPairs = HashBag(10)
            newPairs.forEach { currentPairs.add(it.first, it.second) }
            pairTotalCount.addAll(currentPairs)
        }

        println(elemTotalCount.getMap().values.maxOf { it.toLong() } - elemTotalCount.getMap().values.minOf { it.toLong() })
    }

    private fun findPairs(template: String): List<String> {
        return (0 until template.length - 1).map {
            template[it].toString() + template[it + 1]
        }
    }
}