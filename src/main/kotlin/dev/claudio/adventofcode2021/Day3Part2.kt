package dev.claudio.adventofcode2021

import org.apache.commons.lang3.StringUtils

fun main() {
    Day3Part2().main()
}

private class Day3Part2 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("day3-input.txt")
        var transposedInput: List<String> = Support.stringTranspose(inputList)
        var current = transposedInput[0]
        var index = 0
        while (current.length > 1) {
            val bitCriteria: Char = bitCriteria(current, 0, false)
            transposedInput = dropEntries(transposedInput, bitCriteria, index)
            index = (index + 1) % transposedInput.size
            current = transposedInput[index]
        }
        println(transposedInput)
        println(Integer.parseInt(transposedInput.joinToString(""),2))

        var transposedInput2: List<String> = Support.stringTranspose(inputList)
        current = transposedInput2[0]
        index = 0
        while (current.length > 1) {
            println(Support.stringTranspose(transposedInput2))
            val bitCriteria: Char = bitCriteria(current, 0, true)
            transposedInput2 = dropEntries(transposedInput2, bitCriteria, index)
            index = (index + 1) % transposedInput2.size
            current = transposedInput2[index]
        }
        println(transposedInput2)
        println(Integer.parseInt(transposedInput2.joinToString(""),2))
        val oxygen = transposedInput.joinToString("")
        val co2 = transposedInput2.joinToString("")
        println(Integer.parseInt(oxygen,2)*Integer.parseInt(co2,2)) // 586920 answer too low
    }

    private fun dropEntries(transposedInput: List<String>, bitCriteria: Char, index: Int): List<String> {
        val findCharIndexes = findCharIndexes(transposedInput[index], bitCriteria)
        return transposedInput.map {
            var newStr = ""
            val charArray: CharArray = it.toCharArray()
            findCharIndexes.forEach { charIndex ->
                newStr += charArray[charIndex]
            }
            newStr
        }
    }

    fun findCharIndexes(textString: String, c: Char): List<Int> {
        val indexes: MutableList<Int> = ArrayList()
        var index = 0
        while (index != -1) {
            index = textString.indexOf(c, index)
            if (index != -1) {
                indexes.add(index++)
            }
        }
        return indexes
    }

    fun bitCriteria(str: String, index: Int, reverse: Boolean): Char {
        val target: Char = if (reverse) {
            if (StringUtils.countMatches(str, '0') <= (str.length.toDouble() / 2)) {
                '0'
            } else {
                '1'
            }
        } else {
            if(StringUtils.countMatches(str, '1') >= (str.length.toDouble() / 2)) {
                '1'
            } else {
                '0'
            }
        }
        return target
    }
}