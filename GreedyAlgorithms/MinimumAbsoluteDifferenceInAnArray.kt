import kotlin.math.abs

fun minimumAbsoluteDifference(arr: List<Int>) = arr.sorted().zipWithNext { a, b -> abs(a - b) }.min()!!

fun main() {
    readLine() // Read and discard
    println(minimumAbsoluteDifference(readLine().orEmpty().split(' ').map(String::toInt)))
}
