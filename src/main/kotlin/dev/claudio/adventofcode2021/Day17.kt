package dev.claudio.adventofcode2021

import java.awt.Point
import kotlin.math.max
import kotlin.math.min

fun main() {
    Day17().main()
}

private class Day17 {
    // test input: target area: x=20..30, y=-10..-5  -  velocity:6,9 maxy=45
    // puzzle input: target area: x=29..73, y=-248..-194
//    val targetX = (20..30)
//    val targetY = (-10..-5)
    val targetX = (29..73)
    val targetY = (-248..-194)
    fun main() {
        val currPos = Point(0, 0)
        val velocity = Point(0, 0)
        var maxY = 0
        val startX = (0..10000)
        val startY = (1..10000)
        startY.forEach { y ->
            startX.forEach { x ->
                currPos.setLocation(0,0)
                velocity.setLocation(x, y)
                val candidateY = findMaxY(currPos, velocity)
                if (candidateY != null) {
                    if (candidateY > maxY) maxY = candidateY
                }
            }
        }
        println(maxY)
    }

    private fun findMaxY(currPos: Point, velocity: Point): Int? {
        var maxY = currPos.y
        while (true) {
            if (currPos.y > maxY) maxY = currPos.y
            if (targetX.contains(currPos.x) && targetY.contains(currPos.y)) {
                return maxY
            }
            if (currPos.x > targetX.last || currPos.y < targetY.first) {
                return null
            }
            currPos.translate(velocity.x, velocity.y)
            velocity.setLocation(max(0, velocity.x - 1), velocity.y -1)
        }
    }
}