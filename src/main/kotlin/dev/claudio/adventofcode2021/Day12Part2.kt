package dev.claudio.adventofcode2021

import org.apache.commons.collections4.MultiMapUtils
import org.apache.commons.collections4.MultiValuedMap
import org.apache.commons.lang3.StringUtils

fun main() {
    Day12Part2().main()
}

private class Day12Part2 {
    val map: MultiValuedMap<String, String> = MultiMapUtils.newSetValuedHashMap()
    fun main() {
        Support.readFileAsListString("day12-input.txt").map { it.split("-") }
            .forEach{
                map.put(it[0], it[1])
                map.put(it[1], it[0])
        }
        map.remove("end")
        map.values().removeIf { it == "start" }
//        println(map)
        val paths = recurse("start", mutableSetOf("start"), listOf("start"), false)
//        paths.forEach { println(it) }
        println(paths.size)
    }

    fun recurse(cave : String, exclusions: Set<String>, final : List<String>, singleCaseTwice: Boolean) : Set<List<String>> {
        if (cave == "end") {
            return setOf(final)
        }
        return map.get(cave).filter { it !in exclusions }.flatMap {
            if (StringUtils.isAllLowerCase(it) && !singleCaseTwice) {
                recurse(it, exclusions, final + it, true) + recurse(it, exclusions + it, final + it, false)
            } else if (StringUtils.isAllLowerCase(it)) {
                recurse(it, exclusions + it, final + it, singleCaseTwice)
            } else {
                recurse(it, exclusions, final + it, singleCaseTwice)
            }
        }.toSet()
    }
}