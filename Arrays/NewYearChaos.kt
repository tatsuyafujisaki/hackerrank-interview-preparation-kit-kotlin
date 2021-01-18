fun minimumBribes(q: List<Int>) {
    var expected1 = 1
    var expected2 = 2
    var expected3 = 3
    var bribes = 0
    for (p in q) {
        when (p) {
            expected1 -> {
                expected1 = expected2
                expected2 = expected3
                expected3++
            }
            expected2 -> {
                expected2 = expected3
                expected3++
                bribes++
            }
            expected3 -> {
                expected3++
                bribes += 2
            }
            else -> {
                println("Too chaotic")
                return
            }
        }
    }
    println(bribes)
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        readLine() // Read and discard
        minimumBribes(readLine().orEmpty().split(' ').map { it.toInt() })
    }
}
