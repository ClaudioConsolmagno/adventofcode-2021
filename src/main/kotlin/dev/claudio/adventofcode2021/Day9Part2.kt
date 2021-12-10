package dev.claudio.adventofcode2021

fun main() {
    Day9Part2().main()
}

private class Day9Part2 {
    fun main() {
        val input: List<String> = Support.readFileAsListString("day9-input.txt")
        val xSize = input[0].length
        val input2: MutableList<String> = mutableListOf()
        input2.add("9".repeat(xSize))
        input2.addAll(input)
        input2.add("9".repeat(xSize))
        val map: List<List<Int>> = input2
            .map { "9" + it + "9" }
            .map { it.toCharArray().map { it2 -> it2.titlecase().toInt() } }
        val basins: MutableList<Pair<Int, Int>> = mutableListOf()
        (1 until map.size-1).forEach { x ->
            (1 until map[x].size-1).forEach { y ->
                val candidate = map[x][y]
                val neighbours = listOf(map[x-1][y-1],map[x-1][y-0],map[x-1][y+1],
                    map[x-0][y-1],map[x-0][y+1],
                    map[x+1][y-1],map[x+1][y-0],map[x+1][y+1],)
                if (neighbours.all { candidate < it }) {
                    basins.add(Pair(x, y))
                }
            }
        }
        println(basins)
        basins.map{ pair ->
            var basinSize = 1
            var excluded: MutableSet<Pair<Int, Int>> = basins.toMutableSet()
            var neighbours: MutableSet<Pair<Int, Int>> = getNon9Neighbours(map, pair, excluded)
            while(neighbours.isNotEmpty()) {
                basinSize += neighbours.size
                excluded.addAll(neighbours)
                neighbours = neighbours.flatMap { getNon9Neighbours(map, it, excluded) }.toMutableSet()
            }
            basinSize
        }.sortedDescending().take(3).reduce(Int::times).let { println(it) }
    }

    private fun getNon9Neighbours(
        map: List<List<Int>>,
        pair: Pair<Int, Int>,
        excluded: MutableSet<Pair<Int, Int>>,
    ): MutableSet<Pair<Int, Int>> {
        val neighbours: MutableSet<Pair<Int, Int>> = mutableSetOf()
        (-1 .. 1).forEach { x ->
            (-1 .. 1).forEach { y ->
                if (x != y && x == 0 || y == 0) {
                    val candidate = map[pair.first + x][pair.second +y]
                    val candidatePair = Pair(pair.first + x, pair.second +y)
                    if (candidate < 9 && candidatePair !in excluded) {
                        neighbours.add(candidatePair)
                    }
                }
            }
        }
        return neighbours
    }
}