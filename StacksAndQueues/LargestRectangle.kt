import java.util.Stack
import kotlin.math.max

fun Stack<Int>.safePeek() = if (empty()) -1 else peek()

// https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
fun largestRectangle(h: List<Int>): Int {
    var maxArea = 0
    val stack = Stack<Int>()
    var i = 0
    while (i < h.size) {
        if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
            stack.add(i++)
        } else {
            maxArea = max(maxArea, h[stack.pop()] * (i - 1 - stack.safePeek()))
        }
    }
    while (stack.isNotEmpty()) maxArea = max(maxArea, h[stack.pop()] * (h.lastIndex - stack.safePeek()))
    return maxArea
}

fun main() {
    readLine() // Read and discard
    println(largestRectangle(readLine().orEmpty().split(' ').map(String::toInt)))
}
