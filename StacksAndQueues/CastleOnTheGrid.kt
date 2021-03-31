fun minimumMoves(grid: Array<BooleanArray>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
    val n = grid.size
    val moves = Array(n) { IntArray(n) { Int.MAX_VALUE } }.apply {
        this[startX][startY] = 0
    }
    var visited = mutableListOf(startX to startY)
    do {
        val toVisit = mutableListOf<Pair<Int, Int>>()
        while (visited.isNotEmpty()) {
            val (visitedX, visitedY) = visited.removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+ for simplicity.

            fun visit(x: Int, y: Int) {
                if (moves[x][y] == Int.MAX_VALUE) {
                    moves[x][y] = moves[visitedX][visitedY] + 1
                    toVisit.add(x to y)
                }
            }

            fun moveLeftUntilBlocked(startX: Int, y: Int) {
                var x = startX
                while (x - 1 >= 0 && grid[x - 1][y]) visit(--x, y)
            }

            fun moveRightUntilBlocked(startX: Int, y: Int) {
                var x = startX
                while (x + 1 < n && grid[x + 1][y]) visit(++x, y)
            }

            fun moveUpUntilBlocked(x: Int, startY: Int) {
                var y = startY
                while (y - 1 >= 0 && grid[x][y - 1]) visit(x, --y)
            }

            fun moveDownUntilBlocked(x: Int, startY: Int) {
                var y = startY
                while (y + 1 < n && grid[x][y + 1]) visit(x, ++y)
            }

            moveLeftUntilBlocked(visitedX, visitedY)
            moveRightUntilBlocked(visitedX, visitedY)
            moveUpUntilBlocked(visitedX, visitedY)
            moveDownUntilBlocked(visitedX, visitedY)
        }
        visited = toVisit
    } while (visited.isNotEmpty())
    return moves[goalX][goalY]
}

fun main() {
    val n = readLine().orEmpty().toInt()
    val grid = Array(n) { BooleanArray(n) }
    for (i in 0 until n) readLine().orEmpty().toCharArray().map { it == '.' }.toBooleanArray().copyInto(grid[i])
    val (startX, startY, goalX, goalY) = readLine().orEmpty().split(' ').map(String::toInt)
    println(minimumMoves(grid, startX, startY, goalX, goalY))
}
