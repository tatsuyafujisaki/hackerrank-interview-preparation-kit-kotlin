fun readIntegers() = readLine().orEmpty().split(' ').map { it.toInt() }

fun hourglassSum(arr: List<List<Int>>): Int {
    var maxHourglassSum = Int.MIN_VALUE
    for (i in 1..4) {
        for (j in 1..4) {
            val hourglassSum = arr[i - 1][j - 1] + arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j] + arr[i + 1][j - 1] + arr[i + 1][j] + arr[i + 1][j + 1]
            if (maxHourglassSum < hourglassSum) {
                maxHourglassSum = hourglassSum
            }
        }
    }
    return maxHourglassSum
}

fun main() {
    println(
        hourglassSum(
            listOf(
                readIntegers(),
                readIntegers(),
                readIntegers(),
                readIntegers(),
                readIntegers(),
                readIntegers()
            )
        )
    )
}
