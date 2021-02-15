import java.util.Stack

fun isBalanced(s: String) = with(Stack<Char>()) {
    for (c in s) {
        when (c) {
            '{' -> push('}')
            '[' -> push(']')
            '(' -> push(')')
            else -> if (isEmpty() || c != pop()) return "NO"
        }
    }
    if (empty()) "YES" else "NO"
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(isBalanced(readLine().orEmpty()))
    }
}
