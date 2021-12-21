package dev.claudio.adventofcode2021

import dev.claudio.adventofcode2021.Support.Companion.addPadding
import dev.claudio.adventofcode2021.Support.Companion.printGridSimple
import dev.claudio.adventofcode2021.Support.Companion.removePadding
import dev.claudio.adventofcode2021.Support.Companion.surroundingPoints8
import java.awt.Point

fun main() {
    Day20Part2().main()
}

// Note: Slow
private class Day20Part2 {
    val input: List<String> = Support.readFileAsListString("day20-input.txt")
    val imageEnhancementAlgorithmString = input[0]
    val inputImage = input.takeLastWhile { it.isNotBlank() }
    val lightPixelImage: Set<PointValue<Int>> = inputImage.flatMapIndexed{ y, lineY ->
        lineY.toList()
            .mapIndexed{ x, pixel ->
                if (pixel == '#') {
                    PointValue(x,y,1)
                } else {
                    PointValue(x,y,0)
                }
            }
    }.toSet()
    fun main() {
        var imageInput = lightPixelImage.toSet()
        var toggle = false
        imageInput.filterNot { it.value == 0 }.printGridSimple()
        println("Lit pixels at the beginning of process: ${imageInput.size}")
        repeat(50) { iteration ->
            imageInput = enhance(imageInput, toggle)
            toggle = !toggle
//            imageInput.filterNot { it.value == 0 }.printGridSimple()
            println("Lit pixels after ${iteration+1} iteration(s): ${imageInput.filterNot { it.value == 0 }.size}")
        }
    }

    private fun enhance(origImageInput: Set<PointValue<Int>>, toggle: Boolean): Set<PointValue<Int>> {
        val padding = if (toggle) { 1 } else { 0 }
        val paddedImage: Set<PointValue<Int>> = origImageInput.addPadding(padding).addPadding(padding)
        val finalImage = paddedImage.map { point ->
            var current = ""
            paddedImage.surroundingPoints8(point).plus(point)
                .sortedWith(compareBy(Point::y, Point::x))
                .forEach { current += it.value }
            val toInt: Int = current.toInt(2)
            if (imageEnhancementAlgorithmString[toInt] == '#') {
                PointValue(point.x, point.y, 1)
            } else {
                PointValue(point.x, point.y, 0)
            }
        }
        return finalImage.removePadding()
    }
}
