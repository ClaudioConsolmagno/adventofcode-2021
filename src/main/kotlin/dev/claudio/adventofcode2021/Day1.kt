package dev.claudio.adventofcode2021

import org.apache.commons.collections4.iterators.PeekingIterator

fun main() {
    Day1().main()
}

private class Day1 {
    fun main() {
        val inputList: List<Int> = Support.readFileAsListInt("day1-input.txt")
        val iterator = PeekingIterator.peekingIterator(inputList.iterator())
        var increased = 0
        while (iterator.hasNext()) {
            val current = iterator.next()
            val next = iterator.peek()
            if (next != null && next > current) increased++
        }
        println(increased)
    }
}