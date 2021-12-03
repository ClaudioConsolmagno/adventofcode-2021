package dev.claudio.adventofcode2021

class Support {
    companion object {
        fun readFileAsListInt(fileName: String) : List<Int> = readFileAsListString(fileName).map { Integer.valueOf(it) }

        fun readFileAsListString(fileName: String) : List<String>
                = object {}.javaClass.classLoader.getResourceAsStream(fileName)!!
            .bufferedReader()
            .readLines()
    }
}