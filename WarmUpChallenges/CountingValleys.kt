fun countingValleys(path: String): Int {
    var level = 0
    var count = 0
    for (step in path) {
        if (step == 'U') {
            level++
        } else {
            if (level == 0) count++
            level--
        }
    }
    return count
}

fun main() {
    readLine() // Read and discard
    println(countingValleys(readLine().orEmpty()))
}
