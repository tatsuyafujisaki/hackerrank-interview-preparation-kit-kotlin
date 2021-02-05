import kotlin.math.max

fun maxSubsetSum(arr: Iterable<Int>): Int {
    var a = 0
    var b = 0
    for (x in arr) {
        val temp = a
        a = max(a, b + x)
        b = temp
    }
    return a
}

fun main() {
    readLine() // Read and discard
    println(maxSubsetSum(readLine().orEmpty().split(' ').map(String::toInt)))
}
