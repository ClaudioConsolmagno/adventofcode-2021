package dev.claudio.adventofcode2021

class Support {
    companion object {
        fun readFileAsListInt(fileName: String) : List<Int>
                = object {}.javaClass.classLoader.getResourceAsStream(fileName)!!
            .bufferedReader()
            .readLines()
            .map { Integer.valueOf(it) }
    }
}