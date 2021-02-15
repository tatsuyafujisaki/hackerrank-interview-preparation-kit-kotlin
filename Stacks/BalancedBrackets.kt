import java.util.Stack

fun Stack<*>.popOrNull() = if (empty()) null else pop()

fun isBalanced(s: String) = with(Stack<Char>()) {
    for (c in s) {
        when (c) {
            '{' -> push('}')
            '[' -> push(']')
            '(' -> push(')')
            else -> if (c != popOrNull()) return "NO"
        }
    }
    if (empty()) "YES" else "NO"
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(isBalanced(readLine().orEmpty()))
    }
}
