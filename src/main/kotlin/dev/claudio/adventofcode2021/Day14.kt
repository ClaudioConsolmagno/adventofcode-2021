package dev.claudio.adventofcode2021

import org.apache.commons.lang3.StringUtils

fun main() {
    Day14().main()
}

private class Day14 {
    fun main() {
        val input = Support.readFileAsListString("day14-input.txt")
        var template: String = input[0]
        val pairInsertions = input.takeLastWhile { it.isNotBlank() }.associate {
            it.substring(0, it.indexOf(" ")) to it.substring(it.lastIndexOf(" ")+1, it.length)
        }
//        println(template)
//        pairInsertions.forEach { println(it) }

        (1 .. 10).forEach {
            val pairs : List<String> = findPairs(template)
            var newTemplate = template[0].toString()
            pairs.forEach {
                newTemplate += pairInsertions[it]!! + it[1]
            }
            template = newTemplate
        }
//        println(template.length)
        val counts = template.associate {
            it to StringUtils.countMatches(template, it)
        }
        println(counts.values.maxOf { it } - counts.values.minOf { it })
    }

    private fun findPairs(template: String): List<String> {
        return (0 until template.length-1).map {
            template[it].toString() + template[it+1]
        }
    }
}