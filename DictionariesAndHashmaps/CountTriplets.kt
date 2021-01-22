fun <T> MutableMap<T, Long>.increment(key: T, value: Long = 1) {
    merge(key, value, Long::plus)
}

fun countTriplets(arr: List<Long>, r: Long): Long {
    val singlets = mutableMapOf<Long, Long>()
    val doublets = mutableMapOf<Long, Long>()
    var triplets = 0L
    for (i in arr.indices) {
        triplets += doublets.getOrDefault(arr[i], 0)
        doublets.increment(arr[i] * r, singlets.getOrDefault(arr[i], 0))
        singlets.increment(arr[i] * r)
    }
    return triplets
}

fun main() {
    val r = readLine().orEmpty().split(' ').map { it.toLong() }[1]
    val arr = readLine().orEmpty().split(' ').map { it.toLong() }
    println(countTriplets(arr, r))
}
