fun countingValleys(path: String): Int {
    var level = 0
    var valleys = 0
    for (step in path) {
        if (step == 'U') {
            level++
        } else {
            if (level == 0) valleys++
            level--
        }
    }
    return valleys
}

fun main() {
    readLine() // Read and discard
    println(countingValleys(readLine().orEmpty()))
}
