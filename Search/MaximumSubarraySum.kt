/**
 * https://www.quora.com/What-is-the-logic-used-in-the-HackerRank-Maximise-Sum-problem
 * https://stackoverflow.com/q/31113993/10867055
 */
fun maximumSum(a: Iterable<Long>, m: Long): Long {
    val prefixSums = sortedSetOf(0L)
    var prefixSum = 0L
    return a.map { x ->
        prefixSum = (prefixSum + x) % m
        prefixSums.add(prefixSum)
        prefixSums.higher(prefixSum)?.let {
            (prefixSum - it + m) % m
        } ?: prefixSum
    }.max()!!
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val m = readLine().orEmpty().split(' ').map(String::toLong)[1]
        val a = readLine().orEmpty().split(' ').map(String::toLong)
        println(maximumSum(a, m))
    }
}
