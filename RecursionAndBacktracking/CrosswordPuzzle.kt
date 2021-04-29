fun List<CharArray>.deepCopy() = map { it.clone() }

fun fillWordHorizontally(grid: List<CharArray>, word: String, row: Int, column: Int): List<CharArray>? {
    for (i in word.indices) {
        if (grid[row][column + i] == '-' || grid[row][column + i] == word[i]) {
            grid[row][column + i] = word[i]
        } else {
            return null
        }
    }
    return grid
}

fun fillWordVertically(grid: List<CharArray>, word: String, row: Int, column: Int): List<CharArray>? {
    for (i in word.indices) {
        if (grid[row + i][column] == '-' || grid[row + i][column] == word[i]) {
            grid[row + i][column] = word[i]
        } else {
            return null
        }
    }
    return grid
}

fun crosswordPuzzle(grid: List<CharArray>, initialWords: List<String>): List<CharArray>? {
    if (initialWords.isEmpty()) return grid
    val words = initialWords.toMutableList()
    val word = words.removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
    for (row in grid.indices) {
        for (column in 0..(grid.size - word.length)) {
            fillWordHorizontally(grid.deepCopy(), word, row, column)?.let { newGrid ->
                crosswordPuzzle(newGrid, words)?.let {
                    return it
                }
            }
        }
    }
    for (column in grid.first().indices) {
        for (row in 0..(grid.first().size - word.length)) {
            fillWordVertically(grid.deepCopy(), word, row, column)?.let { newGrid ->
                crosswordPuzzle(newGrid, words)?.let {
                    return it
                }
            }
        }
    }
    return null
}

fun main() {
    crosswordPuzzle(
        List(10) { readLine().orEmpty().toCharArray() },
        readLine().orEmpty().split(';')
    )?.forEach {
        println(it.joinToString(""))
    }
}
