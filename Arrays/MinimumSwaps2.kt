fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun minimumSwaps(arr: IntArray): Int {
    var swaps = 0
    var i = 0
    while (i < arr.size) {
        if (i == arr[i]) {
            i++
        } else {
            arr.swap(i, arr[i])
            swaps++
        }
    }
    return swaps
}

fun main() {
    readLine() // Read and discard
    println(minimumSwaps(readLine().orEmpty().split(' ').map { it.toInt() - 1 }.toIntArray())) // -1 is to convert values from one-based to zero-based.
}