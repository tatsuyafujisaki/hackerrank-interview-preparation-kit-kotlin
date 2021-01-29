fun maxMin(k: Int, arr: IntArray) = with(arr.sorted()) { (0..size - k).map { this[it + k - 1] - this[it] }.min()!! }

fun main() {
    val n = readLine().orEmpty().toInt()
    val k = readLine().orEmpty().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) arr[i] = readLine().orEmpty().toInt()
    println(maxMin(k, arr))
}
