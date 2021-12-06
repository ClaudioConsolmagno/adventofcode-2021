package dev.claudio.adventofcode2021

fun main() {
    Day4().main()
}

private class Day4 {
    fun main() {
        val inputList: List<String> = Support.readFileAsListString("day4-input.txt").filter { it != "" }
        val marks: List<Int> = inputList[0].split(",").map { Integer.valueOf(it) }
        var start = 1
        val boards = mutableListOf<Board>()
        while(start < inputList.size) {
            val boardList = (0 until 5).map {
                inputList[start + it].split(" ").filter { it2 -> it2 != "" }.map { it2 -> Integer.valueOf(it2) }
            }
            boards.add(Board(boardList))
            start += 5
        }
        marks.forEach{ mark ->
            boards.forEach{ board -> board.hit(mark) }
            val winner: Board? = boards.firstOrNull { it.isWin() }
            if (winner != null) {
                println(winner.winLossNumbers().second.sum() * mark)
                return
            }
        }
    }

    data class Board(
        val board: List<List<Int>>,
        private var marks: MutableList<Int> = mutableListOf(),
    ) {

        fun hit(number: Int) {
            marks.add(number)
        }

        fun isWin(): Boolean {
            (board.indices).forEach { index ->
                val horiz = board[index].all { marks.contains(it) }
                val vert = board[index].indices.map { index2 ->
                    board[index2][index]
                }.all { marks.contains(it) }
                if (horiz || vert) return true
            }
            return false
        }

        fun winLossNumbers(): Pair<List<Int>, List<Int>> {
            val allNumbers: List<Int> = board.flatten()
            return Pair(allNumbers.filter { marks.contains(it) },
                allNumbers.filterNot { marks.contains(it) })
        }
    }
}