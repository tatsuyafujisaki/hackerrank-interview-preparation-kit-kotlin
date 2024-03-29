fun arrayManipulation(n: Int, queries: Iterable<IntArray>): Long {
    val xs = LongArray(n)
    for (query in queries) {
        val a = query[0]
        val b = query[1]
        val k = query[2].toLong()
        xs[a - 1] += k
        if (b < n) {
            xs[b] -= k
        }
    }
    var sum = 0L
    var max = 0L
    for (x in xs) {
        sum += x
        if (max < sum) {
            max = sum
        }
    }
    return max
}

fun main() {
    val (n, m) = readLine().orEmpty().split(' ').map(String::toInt)
    val queries = List(m) {
        readLine().orEmpty().split(' ').map(String::toInt).toIntArray()
    }
    println(arrayManipulation(n, queries))
}
