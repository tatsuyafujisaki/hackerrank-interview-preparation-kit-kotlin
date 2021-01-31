fun <T> MutableMap<T, Int>.increment(key: T, value: Int = 1) {
    merge(key, value, Int::plus)
}

fun <T> MutableMap<T, Int>.decrement(key: T, value: Int = 1) {
    merge(key, value, Int::minus)
}

fun reverseShuffleMerge(s: String): String {
    val remainingForA = s.groupingBy { it }.eachCount().mapValues { it.value / 2 }.toMutableMap().withDefault { 0 }
    val remainingNotForA = remainingForA.toMutableMap().withDefault { 0 }
    val a = mutableListOf<Char>()
    s.reversed().forEach {
        if (remainingForA.getValue(it) > 0) {
            while (a.isNotEmpty() && a.last() > it && remainingNotForA.getValue(a.last()) > 0) {
                val removed = a.removeAt(a.lastIndex) // removeLast() in Kotlin 1.4+
                remainingForA.increment(removed)
                remainingNotForA.decrement(removed)
            }
            a.add(it)
            remainingForA.decrement(it)
        } else {
            remainingNotForA.decrement(it)
        }
    }
    return a.joinToString("")
}

fun main() {
    println(reverseShuffleMerge(readLine().orEmpty()))
}
