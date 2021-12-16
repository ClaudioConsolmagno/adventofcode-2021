package dev.claudio.adventofcode2021

import dev.claudio.adventofcode2021.Support.Companion.printGrid
import dev.claudio.adventofcode2021.Support.Companion.surroundingPoints4
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.builder.GraphTypeBuilder
import java.awt.Point

fun main() {
    Day15Part2().main()
}

private class Day15Part2 {

    // https://jgrapht.org/guide/UserOverview#graph-algorithms
    val graph: Graph<Point, DefaultWeightedEdge> = GraphTypeBuilder
        .directed<Point, DefaultWeightedEdge>()
        .weighted(true)
        .edgeClass(DefaultWeightedEdge::class.java)
        .buildGraph()

    fun main() {
        val input = Support.readFileAsListString("day15-input-test.txt")
        val ySize = input.size
        val xSize = input[0].length
        val maxPoint = Point(5 * xSize - 1, 5 * ySize - 1)
        (0 until 5).map { it * ySize}.forEach { tileY ->
            (0 until 5).map { it * xSize }.forEach { tileX ->
                (0 until ySize).forEach { y ->
                    (0 until xSize).forEach { x ->
                        graph.addVertex(Point(tileX + x, tileY + y))
                    }
                }
            }
        }

        (0 until 5).forEach { yCount ->
            val tileY = yCount * ySize
            (0 until 5).forEach { xCount ->
                val tileX = xCount * xSize
                input.forEachIndexed { y, str ->
                    str.toList().forEachIndexed { x, weight ->
                        val currentPoint = Point(tileX + x, tileY + y)
                        val adjustedWeight = (xCount + yCount + weight.digitToInt().toDouble()).let { if (it > 9) { it % 9 } else { it } }
                        currentPoint.surroundingPoints4(maxPoint).forEach {
                            graph.addEdge(currentPoint, it)
                                .apply { graph.setEdgeWeight(this, adjustedWeight) }
                        }
                    }
                }
            }
        }
        val dijkstraAlg = DijkstraShortestPath(graph).getPaths(maxPoint)
        println(dijkstraAlg.getWeight(Point(0,0)))
//        dijkstraAlg.getPath(Point(0,0)).vertexList.printGrid()
    }
}
