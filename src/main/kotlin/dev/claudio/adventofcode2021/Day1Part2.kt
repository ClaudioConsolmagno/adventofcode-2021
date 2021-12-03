package dev.claudio.adventofcode2021

import org.apache.commons.collections4.iterators.PeekingIterator

fun main() {
    val inputList: List<Int> = Support.readFileAsListInt("day1-input.txt")
    val threeMeasurementInput: MutableList<Int> = mutableListOf()
    var index = 0
    val size = inputList.size
    while(index < size) {
        var currentMeasurementWindow = inputList[index]
        if (index + 1 < size) {
            currentMeasurementWindow += inputList[index+1]
        }
        if (index + 2 < size) {
            currentMeasurementWindow += inputList[index+2]
        }
        threeMeasurementInput.add(currentMeasurementWindow)
        index++
    }

    val iterator = PeekingIterator.peekingIterator(threeMeasurementInput.iterator())
    var increased = 0
    while (iterator.hasNext()) {
        val current = iterator.next()
        val next = iterator.peek()
        if (next != null && next > current) increased++
    }
    println(increased)
}
