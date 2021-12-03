package dev.claudio.adventofcode2021

fun main() {
    val inputList: List<String> = Support.readFileAsListString("day2-input.txt")
    val commands: List<Command> = inputList.map {
        val split = it.split(" ")
        Command(Direction.valueOf(split[0]), Integer.valueOf(split[1]))
    }
    var horizontal = 0
    var depth = 0
    commands.forEach{
        when (it.direction) {
            Direction.forward -> horizontal += it.unit
            Direction.up -> depth -= it.unit
            Direction.down -> depth += it.unit
        }
    }
    println("forward: $horizontal")
    println("yAxis: $depth")
    println("multiply: ${horizontal * depth}") //-1693300
}

data class Command(val direction: Direction, val unit: Int)

enum class Direction {
    forward,
    up,
    down
}
