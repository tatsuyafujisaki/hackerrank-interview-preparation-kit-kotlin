fun maxMin(k: Int, arr: IntArray): Int {
    arr.sort()
    return (0..arr.size - k).map { arr[it + k - 1] - arr[it] }.min()!!
}

fun main() {
    val n = readLine().orEmpty().toInt()
    val k = readLine().orEmpty().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) arr[i] = readLine().orEmpty().toInt()
    println(maxMin(k, arr))
}
