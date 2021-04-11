import kotlin.math.max

fun maxRegion(grid: Array<BooleanArray>): Int {
    fun count(row: Int, column: Int): Int = if (row < 0 || column < 0 || row >= grid.size || column >= grid.first().size || !grid[row][column]) {
        0
    } else {
        grid[row][column] = false
        count(row - 1, column - 1) + count(row - 1, column) + count(row - 1, column + 1) +
            count(row, column - 1) + 1 + count(row, column + 1) +
            count(row + 1, column - 1) + count(row + 1, column) + count(row + 1, column + 1)
    }

    var result = 0
    for (row in grid.indices) {
        for (column in grid.first().indices) result = max(result, count(row, column))
    }
    return result
}

fun main() {
    val n = readLine().orEmpty().toInt()
    readLine() // Read and discard
    println(
        maxRegion(
            Array(n) {
                readLine()
                    .orEmpty()
                    .split(' ')
                    .map { it == "1" }
                    .toBooleanArray()
            }
        )
    )
}
