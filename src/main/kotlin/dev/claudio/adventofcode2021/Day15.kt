package dev.claudio.adventofcode2021

import dev.claudio.adventofcode2021.Support.Companion.surroundingPoints4
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.builder.GraphTypeBuilder
import java.awt.Point

fun main() {
    Day15().main()
}

private class Day15 {

    // https://jgrapht.org/guide/UserOverview#graph-algorithms
    val graph: Graph<Point, DefaultWeightedEdge> = GraphTypeBuilder
        .directed<Point, DefaultWeightedEdge>()
        .weighted(true)
        .edgeClass(DefaultWeightedEdge::class.java)
        .buildGraph()

    fun main() {
        val input = Support.readFileAsListString("day15-input.txt")
        val ySize = input.size
        val xSize = input[0].length
        val maxPoint = Point(xSize-1, ySize-1)
        (0 until ySize).forEach { y ->
            (0 until xSize).forEach { x ->
                graph.addVertex(Point(x, y))
            }
        }
        input.forEachIndexed{ y, str ->
            str.toList().forEachIndexed { x, weight ->
                val currentPoint = Point(x, y)
                currentPoint.surroundingPoints4(maxPoint).forEach {
                    graph.addEdge(currentPoint, it).apply { graph.setEdgeWeight(this, weight.digitToInt().toDouble()) }
                }
            }
        }
        val dijkstraAlg = DijkstraShortestPath(graph).getPaths(maxPoint)
        println(dijkstraAlg.getWeight(Point(0,0)))
    }
}