package dev.claudio.adventofcode2021

fun main() {
    Day21Part2().main()
}

private class Day21Part2 {
    val cacheP1Start: MutableMap<GameState, GameState> = mutableMapOf()
    val cacheP2Start: MutableMap<GameState, GameState> = mutableMapOf()

    fun main() {
        val gameState = GameState(4, 5)
        runGame(gameState)
        println("p1SubUniverseWins: " + gameState.p1SubUniverseWins)
        println("p2SubUniverseWins: " + gameState.p2SubUniverseWins)
    }

    fun runGame(gameState: GameState, p2Start: Boolean = false) {
        val cache = if (p2Start) cacheP2Start else cacheP1Start
        val key = gameState.copy(rolls = gameState.rolls.toMutableList())
        if (!cache.containsKey(key)) {
            if (p2Start) {
                takeTurnP2(gameState)
            }
            while (!gameState.isGameCompleted()) {
                takeTurnP1(gameState)
                if (!gameState.isGameCompleted()) {
                    takeTurnP2(gameState)
                }
            }
            gameState.incrementWinner()
            cache[key] = gameState
        }
        val gameStateCached: GameState = cache[key]!!
        gameState.p1SubUniverseWins = gameStateCached.p1SubUniverseWins
        gameState.p2SubUniverseWins = gameStateCached.p2SubUniverseWins
    }

    fun rollDieWithUniverseSplit(gameState: GameState, p2Start: Boolean = false) {
        while (gameState.rolls.size < 3) {
            (1L .. 2L).forEach {
                val subGame = gameState.copy(rolls = gameState.rolls.plus(it).toMutableList(), p1SubUniverseWins = 0L, p2SubUniverseWins = 0L)
                runGame(subGame, p2Start)
                gameState.p1SubUniverseWins += subGame.p1SubUniverseWins
                gameState.p2SubUniverseWins += subGame.p2SubUniverseWins
            }
            gameState.rolls.add(3L)
        }
    }

    private fun takeTurnP1(gameState: GameState) {
        rollDieWithUniverseSplit(gameState)
        gameState.p1Position += gameState.rolls.sum()
        gameState.p1Position %= 10
        if (gameState.p1Position == 0L) gameState.p1Position = 10
        gameState.p1Score += gameState.p1Position
        gameState.rolls.clear()
    }

    private fun takeTurnP2(gameState: GameState) {
        rollDieWithUniverseSplit(gameState, true)
        gameState.p2Position += gameState.rolls.sum()
        gameState.p2Position %= 10
        if (gameState.p2Position == 0L) gameState.p2Position = 10
        gameState.p2Score += gameState.p2Position
        gameState.rolls.clear()
    }

    data class GameState(
        var p1Position: Long,
        var p2Position: Long,
        var p1Score: Long = 0,
        var p2Score: Long = 0,
        var rolls: MutableList<Long> = mutableListOf(),
        var p1SubUniverseWins: Long = 0,
        var p2SubUniverseWins: Long = 0,
    ) {
        fun isGameCompleted() : Boolean {
            return p1Score >= 21 || p2Score >= 21
        }
        fun incrementWinner() {
            if (p1Score >= 21) {
                p1SubUniverseWins++
            } else {
                p2SubUniverseWins++
            }
        }
    }
}