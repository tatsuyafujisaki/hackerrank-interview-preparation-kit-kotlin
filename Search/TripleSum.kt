fun readIntegers() = readLine().orEmpty().split(' ').map(String::toInt)

fun triplets(a: List<Int>, b: List<Int>, c: List<Int>): Long {
    // Instead, Iterable<T>.toSortedSet() causes a Time Limit Exceeded in the test case 2, 3, 4, 7, and 8.
    val setA = a.distinct().sorted()
    val setB = b.distinct().sorted()
    val setC = c.distinct().sorted()
    var triplets = 0L
    var i = 0
    var j = 0
    for (q in setB) {
        while (i < setA.size && setA[i] <= q) i++
        while (j < setC.size && setC[j] <= q) j++
        // "i" must be converted to Long before it is multiplied by "j", or the test case 4 fails because of an integer overflow.
        triplets += i.toLong() * j
    }
    return triplets
}

fun main() {
    readLine() // Read and discard
    println(triplets(readIntegers(), readIntegers(), readIntegers()))
}
