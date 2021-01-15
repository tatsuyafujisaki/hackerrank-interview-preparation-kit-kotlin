fun countingValleys(path: String): Int {
    var level = 0
    var valleyCount = 0

    for (step in path) {
        if (step == 'U') {
            level++
        } else {
            if (level == 0) {
                valleyCount++
            }
            level--
        }
    }

    return valleyCount
}

fun main() {
    readLine() // Read and discard
    println(countingValleys(readLine().orEmpty()))
}
