import java.util.Stack
import kotlin.math.max

// https://www.geeksforgeeks.org/find-the-maximum-of-minimums-for-every-window-size-in-a-given-array/
fun riddle(arr: List<Int>): IntArray {
    val left = IntArray(arr.size).apply {
        val stack = Stack<Int>()
        for (i in arr.indices) {
            // TODO: Instead of pop(), use removeLast() in Kotlin 1.4+ because removeLast() does not bother to return the removed value we don't use.
            while (stack.isNotEmpty() && arr[stack.peek()] >= arr[i]) stack.pop()
            this[i] = stack.lastOrNull() ?: -1
            stack.push(i)
        }
    }
    val right = IntArray(arr.size).apply {
        val stack = Stack<Int>()
        for (i in arr.lastIndex downTo 0) {
            // TODO: Instead of pop(), use removeLast() in Kotlin 1.4+ because removeLast() does not bother to return the removed value we don't use.
            while (stack.isNotEmpty() && arr[stack.peek()] >= arr[i]) stack.pop()
            this[i] = stack.lastOrNull() ?: arr.size
            stack.push(i)
        }
    }
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
