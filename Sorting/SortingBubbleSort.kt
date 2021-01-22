fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun countSwaps(a: IntArray) {
    var swaps = 0
    var n = a.size
    while (1 < n) {
        var lastSwappedIndex = 0
        for (i in 0 until n - 1) {
            if (a[i] > a[i + 1]) {
                a.swap(i, i + 1)
                swaps++
                lastSwappedIndex = i + 1
            }
        }
        n = lastSwappedIndex
    }
    println("Array is sorted in $swaps swaps.")
    println("First Element: ${a.first()}")
    println("Last Element: ${a.last()}")
}

fun main() {
    readLine() // Read and discard
    countSwaps(readLine().orEmpty().split(' ').map(String::toInt).toIntArray())
}
