fun maximumSum(a: List<Long>, m: Long): Long {
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
