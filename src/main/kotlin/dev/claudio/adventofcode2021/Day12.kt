package dev.claudio.adventofcode2021

import org.apache.commons.collections4.MultiMapUtils
import org.apache.commons.collections4.MultiValuedMap
import org.apache.commons.lang3.StringUtils

fun main() {
    Day12().main()
}

private class Day12 {
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
        val paths = recurse("start", mutableSetOf("start"), listOf("start"))
//        println(paths)
        println(paths.size)
    }

    fun recurse(cave : String, exclusions: Set<String>, final : List<String>) : Set<List<String>> {
        if (cave == "end") {
            return setOf(final)
        }
        return map.get(cave).filter { it !in exclusions }.flatMap {
            val newExclusions = if (StringUtils.isAllLowerCase(it)) { exclusions + it } else { exclusions }
            recurse(it, newExclusions, final + it)
        }.toSet()
    }
}