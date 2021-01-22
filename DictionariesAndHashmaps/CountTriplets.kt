fun MutableMap<Long, Long>.increment(key: Long, value: Long = 1) {
    merge(key, value, Long::plus)
}

fun countTriplets(arr: List<Long>, r: Long): Long {
    val singlets = mutableMapOf<Long, Long>().withDefault { 0 }
    val doublets = mutableMapOf<Long, Long>().withDefault { 0 }
    var triplets = 0L
    for (i in arr.indices) {
        triplets += doublets.getValue(arr[i])
        doublets.increment(arr[i] * r, singlets.getValue(arr[i]))
        singlets.increment(arr[i] * r)
    }
    return triplets
}

fun main() {
    val r = readLine().orEmpty().split(' ').map(String::toLong)[1]
    val arr = readLine().orEmpty().split(' ').map(String::toLong)
    println(countTriplets(arr, r))
}
