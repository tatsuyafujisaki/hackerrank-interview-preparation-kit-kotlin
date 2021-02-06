fun candies(arr: IntArray): Long {
    val candies = LongArray(arr.size).also { it[0] = 1 }
    for (i in 0 until arr.lastIndex) candies[i + 1] = if (arr[i] < arr[i + 1]) candies[i] + 1 else 1
    for (i in arr.lastIndex downTo 1) {
        if (arr[i - 1] > arr[i] && candies[i - 1] <= candies[i]) candies[i - 1] = candies[i] + 1
    }
    return candies.sum()
}

fun main() {
    val n = readLine().orEmpty().toInt()
    val arr = IntArray(n)
    for (i in 0 until n) arr[i] = readLine().orEmpty().toInt()
    println(candies(arr))
}
