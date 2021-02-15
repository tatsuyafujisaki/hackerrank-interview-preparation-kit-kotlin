import java.util.Stack

fun Stack<*>.popOrNull() = if (empty()) null else pop()

fun isBalanced(s: String): String {
    val stack = Stack<Char>()
    for (c in s) {
        when (c) {
            '(' -> stack.push(')')
            '[' -> stack.push(']')
            '{' -> stack.push('}')
            else -> if (c != stack.popOrNull()) return "NO"
        }
    }
    return if (stack.empty()) "YES" else "NO"
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(isBalanced(readLine().orEmpty()))
    }
}
