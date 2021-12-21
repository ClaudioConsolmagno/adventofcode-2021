package dev.claudio.adventofcode2021

import kotlin.math.min

fun main() {
    Day21().main()
}

private class Day21 {

    fun main() {
        val gameState = GameState(4, 5, 0, 0)

        while (!gameState.isGameCompleted()) {
            takeTurnP1(gameState)
            if (!gameState.isGameCompleted()) {
                takeTurnP2(gameState)
            }
        }
        println(gameState)
        println(gameState.puzzleOutput())
    }

    private fun takeTurnP1(gameState: GameState) {
        gameState.rolls += 3
        val rolls = mutableListOf<Int>()
        repeat(3) {
            gameState.dieCounter++
            if (gameState.dieCounter>100) gameState.dieCounter %= 100
            rolls.add(gameState.dieCounter)
        }
        gameState.p1Position += rolls.sum()
        gameState.p1Position %= 10
        if (gameState.p1Position == 0) gameState.p1Position = 10
        gameState.p1Score += gameState.p1Position
        println("Player 1 rolls ${rolls} and moves to space ${gameState.p1Position} for a total score of ${gameState.p1Score}.")
    }

    private fun takeTurnP2(gameState: GameState) {
        gameState.rolls += 3
        val rolls = mutableListOf<Int>()
        repeat(3) {
            gameState.dieCounter++
            if (gameState.dieCounter>100) gameState.dieCounter %= 100
            rolls.add(gameState.dieCounter)
        }
        gameState.p2Position += rolls.sum()
        gameState.p2Position %= 10
        if (gameState.p2Position == 0) gameState.p2Position = 10
        gameState.p2Score += gameState.p2Position
        println("Player 1 rolls ${rolls} and moves to space ${gameState.p2Position} for a total score of ${gameState.p2Score}.")
    }

    data class GameState(
        var p1Position: Int,
        var p2Position: Int,
        var p1Score: Int,
        var p2Score: Int,
        var dieCounter: Int = 0,
        var rolls: Int = 0,
    ) {
        fun isGameCompleted() : Boolean {
            return p1Score >= 1000 || p2Score >= 1000
        }
        fun puzzleOutput() : Int{
            return rolls * min(p1Score, p2Score)
        }
    }
}