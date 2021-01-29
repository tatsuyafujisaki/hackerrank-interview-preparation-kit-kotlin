import kotlin.math.min

/**
 * https://www.geeksforgeeks.org/count-special-palindromes-in-a-string/
 * Note there are no nested loops.
 */
fun substrCount(s: String): Int {
    val counts = mutableListOf<Pair<Char, Int>>()
    var count = 0
    var previous = 0.toChar()
    for (c in s) {
        if (previous == c) {
            count++
        } else {
            counts.add(previous to count)
            previous = c
            count = 1
        }
    }
    counts.add(previous to count)
    // Sum of n! -> n(a_1+a_n)/2 -> n(n+1)/2
    // e.g. "aaa" -> "aaa", "aa", "aa", "a", "a", "a" -> 3(3+1)/2
    var total = counts.map { it.second }.sumBy { it * (it + 1) / 2 }
    for (i in 1 until counts.lastIndex) {
        if (counts[i].second == 1 && counts[i - 1].first == counts[i + 1].first) {
            total += min(counts[i - 1].second, counts[i + 1].second)
        }
    }
    return total
}

fun main() {
    readLine() // Read and discard
    println(substrCount(readLine().orEmpty()))
}
