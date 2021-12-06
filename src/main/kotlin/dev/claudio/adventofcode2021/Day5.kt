package dev.claudio.adventofcode2021

fun main() {
    Day5().main()
}

private class Day5 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("day5-input.txt")
        val lines = inputList
            .map {
                val split: List<String> = it.split(" -> ", ",")
                Line(
                    Integer.valueOf(split[0]),
                    Integer.valueOf(split[1]),
                    Integer.valueOf(split[2]),
                    Integer.valueOf(split[3]),
                )
            }
            .filter { it.isHorizontalOrVertical() }

        val hits: Map<String, Int> = lines.flatMap { it.hits() }.groupingBy { it }.eachCount()
        val count = hits.filter {
            it.value > 1
        }.count()
        println(lines)
        println(hits)
        println(count)
    }

    data class Line(
        val x1: Int,
        val y1: Int,
        val x2: Int,
        val y2: Int,
    ) {
        fun isHorizontalOrVertical(): Boolean {
            return x1 == x2 || y1 == y2
        }

        fun hits() : List<String> {
            val hits: MutableList<String> = mutableListOf()
            if (x1 == x2) {
                var low : Int
                var high : Int
                if (y1 <= y2) { low = y1; high = y2 } else { low = y2; high = y1 }
                (low..high).forEach {
                    hits.add("$x1,$it")
                }
            } else {
                var low : Int
                var high : Int
                if (x1 <= x2) { low = x1; high = x2 } else { low = x2; high = x1 }
                (low..high).forEach {
                    hits.add("$it,$y1")
                }
            }
            return hits
        }
    }
}