package dev.claudio.adventofcode2021

fun main() {
    Day3().main()
}

private class Day3 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("day3-input.txt")
        val listSize = inputList.size
        val listSizeHalf = listSize / 2
        val recordSize = inputList[0].length
        val onesCount = mutableListOf<Int>()
        (0 until recordSize).forEach{
            onesCount.add(0)
        }
        inputList.forEach { reading ->
            (0 until recordSize).forEach { index ->
                if (reading[index] == '1') {
                    onesCount[index] = onesCount[index] + 1
                }
            }
        }
        var gamma = ""
        var epsilon = ""
        onesCount.forEach {
            if ((listSize - it) < listSizeHalf) {
                gamma += "1"
                epsilon += "0"
            } else {
                gamma += "0"
                epsilon += "1"
            }
        }
        println(onesCount)
        println(gamma)
        println(epsilon)
        println(Integer.parseInt(gamma,2)*Integer.parseInt(epsilon,2))
    }
}