import kotlin.math.max

fun maxRegion(grid: Array<BooleanArray>): Int {
    fun count(row: Int, column: Int): Int = if (row < 0 || column < 0 || row >= grid.size || column >= grid.first().size || !grid[row][column]) {
        0
    } else {
        grid[row][column] = false
        count(row - 1, column - 1) + count(row - 1, column) + count(row - 1, column + 1) + count(row, column - 1) + 1 + count(row, column + 1) + count(row + 1, column - 1) + count(row + 1, column) + count(row + 1, column + 1)
    }

    var result = 0
    for (row in 1..grid.size - 2) {
        for (column in 1..grid.first().size - 2) result = max(result, count(row, column))
    }
    return result
}

fun main() {
    val n = readLine().orEmpty().toInt()
    readLine() // Read and discard
    println(maxRegion(Array(n) { readLine().orEmpty().split(' ').map { it == "1" }.toBooleanArray() }))
}
