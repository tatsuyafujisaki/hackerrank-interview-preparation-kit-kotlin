import java.util.Stack
import kotlin.math.max

fun findSmallerLeft(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.indices) {
            // TODO Instead of pop(), use removeLast() in Kotlin 1.4+ because removeLast() does not bother to return the removed value we don't use.
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.pop()
            this[i] = stack.lastOrNull() ?: -1
            stack.push(i)
        }
    }
}

fun findSmallerRight(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.lastIndex downTo 0) {
            // TODO Instead of pop(), use removeLast() in Kotlin 1.4+ because removeLast() does not bother to return the removed value we don't use.
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.pop()
            this[i] = stack.lastOrNull() ?: xs.size
            stack.push(i)
        }
    }
}

// https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/
fun riddle(arr: List<Int>): IntArray {
    val left = findSmallerLeft(arr)
    val right = findSmallerRight(arr)
    return IntArray(arr.size).apply {
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
