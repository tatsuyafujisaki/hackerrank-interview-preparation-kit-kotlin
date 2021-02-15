import java.util.Stack
import kotlin.math.max

fun main() {
    val stack = Stack<Int>()
    repeat(readLine().orEmpty().toInt()) {
        val query = readLine().orEmpty().split(' ').map(String::toInt)
        when (query[0]) {
            1 -> stack.push(if (stack.isEmpty()) query[1] else max(stack.peek(), query[1]))
            2 -> stack.pop()
            else -> println(stack.peek())
        }
    }
}
