import java.util.Stack
import kotlin.math.max

/** Remove this function and use the built-in removeLast() after Kotlin 1.4 */
fun MutableList<*>.removeLast() {
    removeAt(lastIndex)
}

fun findSmallerLeft(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.indices) {
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
            this[i] = stack.lastOrNull() ?: -1
            stack.push(i)
        }
    }
}

fun findSmallerRight(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.lastIndex downTo 0) {
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
            this[i] = stack.lastOrNull() ?: xs.size
            stack.push(i)
        }
    }
}

// https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/
fun riddle(arr: List<Int>): List<Int> {
    val left = findSmallerLeft(arr)
    val right = findSmallerRight(arr)
    return MutableList(arr.size) { 0 }.apply {
        for (i in arr.indices) {
            val windowSize = right[i] - left[i] - 1
            this[windowSize - 1] = max(this[windowSize - 1], arr[i])
        }
        for (i in lastIndex - 1 downTo 0) this[i] = max(this[i], this[i + 1])
    }
}

fun main() {
    readLine() // Read and discard
    println(riddle(readLine().orEmpty().split(' ').map(String::toInt)).joinToString(" "))
}
