fun <T> Stack<T>.peekOrElse(item: T) = lastOrNull() ?: item

/** Remove this function and use the built-in removeLast() after Kotlin 1.4 */
fun MutableList<*>.removeLast() {
    removeAt(lastIndex)
}

fun findSmallerLeft(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    val left = IntArray(xs.size)
    for (i in xs.indices) {
        while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
        left[i] = stack.peekOrElse(-1)
        stack.push(i)
    }
    return left
}

fun findSmallerRight(xs: List<Int>): IntArray {
    val stack = Stack<Int>()
    val right = IntArray(xs.size)
    for (i in xs.lastIndex downTo 0) {
        while (stack.isNotEmpty() && xs[stack.peek()] >= xs[i]) stack.removeLast()
        right[i] = stack.peekOrElse(xs.size)
        stack.push(i)
    }
    return right
}

fun riddle(arr: List<Int>): List<Int> {
    val left = findSmallerLeft(arr)
    val right = findSmallerRight(arr)
    val result = IntArray(arr.size + 1)
    for (i in arr.indices) {
        val windowSize = right[i] - left[i] - 1
        result[windowSize] = max(result[windowSize], arr[i])
    }
    for (i in arr.lastIndex downTo 0) result[i] = max(result[i], result[i + 1])
    return result.drop(1)
}

fun main() {
    readLine() // Read and discard
    println(riddle(readLine().orEmpty().split(' ').map(String::toInt)).joinToString(" "))
}
