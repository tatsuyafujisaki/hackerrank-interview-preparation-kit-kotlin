fun isValid(s: String) = with(IntArray(26).apply { for (c in s) this[c - 'a']++ }.filter { it > 0 }.groupingBy { it }.eachCount()) {
    when (size) {
        1 -> "YES"
        2 -> {
            val (more, less) = entries.sortedBy { -it.key }
            if (more.key - less.key == 1 && more.value == 1 || less.key == 1 && less.value == 1) "YES" else "NO"
        }
        else -> "NO"
    }
}

fun main() {
    println(isValid(readLine().orEmpty()))
}
