class MergeSort {
    var swapped: Long = 0

    fun sort(xs: IntArray): IntArray {
        if (xs.size < 2) {
            return xs
        }
        val middle = xs.size / 2
        return merge(sort(xs.copyOfRange(0, middle)), sort(xs.copyOfRange(middle, xs.size)))
    }

    private fun merge(left: IntArray, right: IntArray): IntArray {
        var leftIndex = 0
        var rightIndex = 0
        val merged = mutableListOf<Int>()
        while (true) {
            if (left[leftIndex] <= right[rightIndex]) {
                merged.add(left[leftIndex++])
                if (leftIndex == left.size) {
                    right.copyOfRange(rightIndex, right.size).toCollection(merged)
                    break
                }
            } else {
                merged.add(right[rightIndex++])
                swapped += left.size - leftIndex
                if (rightIndex == right.size) {
                    left.copyOfRange(leftIndex, left.size).toCollection(merged)
                    break
                }
            }
        }
        return merged.toIntArray()
    }
}

fun main() {
    val d = readLine().orEmpty().toInt()
    val arrays = mutableListOf<IntArray>()
    repeat(d) {
        readLine() // Read and discard
        arrays.add(readLine().orEmpty().split(' ').map(String::toInt).toIntArray())
    }
    for (arr in arrays) {
        with(MergeSort()) {
            sort(arr)
            println(swapped)
        }
    }
}
