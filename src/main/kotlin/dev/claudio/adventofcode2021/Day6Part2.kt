package dev.claudio.adventofcode2021

fun main() {
    Day6Part2().main()
}

private class Day6Part2 {
    fun main() {
        val inputList: List<Int> = Support.readFileAsListString("day6-input.txt")
            .flatMap { it.split(",") }
            .map { it.toInt() }
        val fishMap: MutableMap<Int, Double> = inputList.groupingBy { it }.eachCount().mapValues { it.value.toDouble() }.toMutableMap()
        fishMap.putIfAbsent(0, 0.0)
        fishMap.putIfAbsent(1, 0.0)
        fishMap.putIfAbsent(2, 0.0)
        fishMap.putIfAbsent(3, 0.0)
        fishMap.putIfAbsent(4, 0.0)
        fishMap.putIfAbsent(5, 0.0)
        fishMap.putIfAbsent(6, 0.0)
        fishMap.putIfAbsent(7, 0.0)
        fishMap.putIfAbsent(8, 0.0)
        (0 until 256).forEach{
            tick(fishMap)
        }
        println(fishMap)
        println(fishMap.values.sum())
        println( String.format("%.3f", fishMap.values.sum()))
    }

    private fun tick(fishMap: MutableMap<Int, Double>) {
        val oldFishMap = fishMap.toMap()
        val spawns = oldFishMap[0]!!
        fishMap[7] = oldFishMap[8]!!
        fishMap[6] = oldFishMap[7]!! + oldFishMap[0]!!
        fishMap[5] = oldFishMap[6]!!
        fishMap[4] = oldFishMap[5]!!
        fishMap[3] = oldFishMap[4]!!
        fishMap[2] = oldFishMap[3]!!
        fishMap[1] = oldFishMap[2]!!
        fishMap[0] = oldFishMap[1]!!
        fishMap[8] = spawns
    }
}