import java.util.Stack
import kotlin.math.max

fun <T> Stack<T>.peekOrElse(item: T) = lastOrNull() ?: item

/** Remove this function and use the built-in removeLast() after Kotlin 1.4 */
fun MutableList<*>.removeLast() {
    removeAt(lastIndex)
}

fun findSmallerLeft(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.indices) {
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
            this[i] = stack.peekOrElse(-1)
            stack.push(i)
        }
    }
}

fun findSmallerRight(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    return IntArray(xs.size).apply {
        for (i in xs.lastIndex downTo 0) {
            while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
            this[i] = stack.peekOrElse(xs.size)
            stack.push(i)
        }
    }
}

fun riddle(arr: List<Int>): List<Int> {
    val left = findSmallerLeft(arr)
    val right = findSmallerRight(arr)
    return with(IntArray(arr.size + 1)) {
        for (i in arr.indices) {
            val windowSize = right[i] - left[i] - 1
            this[windowSize] = max(this[windowSize], arr[i])
        }
        for (i in arr.lastIndex downTo 1) this[i] = max(this[i], this[i + 1])
        drop(1)
    }
}

fun main() {
    readLine() // Read and discard
    println(riddle(readLine().orEmpty().split(' ').map(String::toInt)).joinToString(" "))
}
