package dev.claudio.adventofcode2021

fun main() {
    Day11().main()
}

private class Day11 {
    fun main() {
        val input: List<String> = Support.readFileAsListString("day11-input.txt")
        val map: MutableList<MutableList<Int>> = input
            .map { it.toCharArray().map { it2 -> it2.titlecase().toInt() }.toMutableList() }.toMutableList()
        (0 until map.size).forEach {
            map[it].add(0, Int.MIN_VALUE)
            map[it].add(Int.MIN_VALUE)
        }
        map.add(0, (0 until map[0].size).map { Int.MIN_VALUE }.toMutableList())
        map.add((0 until map[0].size).map { Int.MIN_VALUE }.toMutableList())
//        map.forEach{ println(it) }

        var flashesTotal = 0L
        (0 until 100).forEach { _ ->
            var flashed = step(map)
            val allFlashed: MutableSet<Pair<Int, Int>> = flashed.toMutableSet()
            while (flashed.isNotEmpty()) {
                flashed = flashed.flatMap {
                    val flashedNeighbours = stepNeighbours(map, it, allFlashed)
                    flashedNeighbours
                }.toMutableSet()
                allFlashed.addAll(flashed)
            }
            flashesTotal += cleanStep(map)
            map.forEach{ println(it) }
        }
        println(flashesTotal)
    }

    private fun cleanStep(map: MutableList<MutableList<Int>>): Long {
        var sum = 0L
        (0 until map.size).forEach { x ->
            (0 until map[x].size).forEach { y ->
                if (map[x][y] > 9) {
                    sum++
                    map[x][y] = 0
                }
            }
        }
        return sum
    }

    private fun step(map: MutableList<MutableList<Int>>): MutableSet<Pair<Int, Int>> {
        val flashed: MutableSet<Pair<Int, Int>> = mutableSetOf()
        // flash
        (0 until map.size).forEach { x ->
            (0 until map[x].size).forEach { y ->
                map[x][y] += 1
                if (map[x][y] > 9) {
                    flashed.add(Pair(x, y))
                }
            }
        }
        return flashed
    }

    private fun stepNeighbours(
        map: MutableList<MutableList<Int>>,
        pair: Pair<Int, Int>,
        flashedPairs: MutableSet<Pair<Int, Int>>,
    ): MutableSet<Pair<Int, Int>> {
        val neighbours: MutableSet<Pair<Int, Int>> = mutableSetOf()
        (-1 .. 1).forEach { x ->
            (-1 .. 1).forEach { y ->
                if (!(x == 0 && y == 0)) {
                    val candidatePair = Pair(pair.first + x, pair.second +y)
                    if (candidatePair !in flashedPairs) {
                        map[pair.first + x][pair.second +y] += 1
                        val candidate = map[pair.first + x][pair.second +y]
                        if (candidate > 9) {
                            neighbours.add(candidatePair)
                        }
                    }
                }
            }
        }
        return neighbours
    }
}