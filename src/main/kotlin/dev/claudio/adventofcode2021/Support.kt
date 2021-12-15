package dev.claudio.adventofcode2021

import java.awt.Point

class Support {
    companion object {
        fun readFileAsListInt(fileName: String): List<Int> = readFileAsListString(fileName).map { Integer.valueOf(it) }

        fun readFileAsListString(fileName: String): List<String> =
            object {}.javaClass.classLoader.getResourceAsStream(fileName)!!
                .bufferedReader()
                .readLines()

        fun stringTranspose(list: List<String>): List<String> {
            val tranposedList = mutableListOf<CharArray>()
            (1..list[0].length).forEach {
                tranposedList.add(" ".repeat(list.size).toCharArray())
            }
            list.forEachIndexed { index1, originalEntry ->
                tranposedList.forEachIndexed { index, it ->
                    it[index1] = originalEntry[index]
                }
            }
            return tranposedList.map { String(it) }
        }

        fun Collection<Point>.printGrid() {
            val xSize: Int = this.maxOf { it.x }
            val ySize: Int = this.maxOf { it.y }
            val pointsSet = this.toSet()
            (0..ySize).forEach { y ->
                (0..xSize).forEach { x ->
                    if (pointsSet.contains(Point(x, y))) {
                        print("# ")
                    } else {
                        print(". ")
                    }
                }
                println()
            }
        }

        fun Point.surroundingPoints8(maxPoint: Point): Set<Point> {
            return (-1..1).flatMap { x ->
                    (-1..1).mapNotNull { y ->
                        if (!(x == 0 && y == 0)) {
                            Point(this.x + x, this.y + y)
                        } else {
                            null
                        }
                    }
                }
                .filter { it.y > -1 && it.x > -1 && it.x <= maxPoint.x && it.y <= maxPoint.y }
                .toSet()
        }

        fun Point.surroundingPoints4(maxPoint: Point): Set<Point> {
            return this.surroundingPoints8(maxPoint).filter { it.x == this.x || it.y == this.y }.toSet()
        }
    }
}
