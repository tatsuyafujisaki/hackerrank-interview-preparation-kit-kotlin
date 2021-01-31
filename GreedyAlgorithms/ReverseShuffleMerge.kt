val Char.alphabeticalIndex: Int get() = this - 'a'

fun reverseShuffleMerge(s: String): String {
    val remainingForA = IntArray(26).apply { for (c in s) this[c.alphabeticalIndex]++ }.map { it / 2 }.toIntArray()
    val remainingNotForA = remainingForA.clone()
    val a = mutableListOf<Char>()
    s.reversed().forEach {
        if (remainingForA[it.alphabeticalIndex] > 0) {
            while (a.isNotEmpty() && a.last() > it && remainingNotForA[a.last().alphabeticalIndex] > 0) {
                val removed = a.removeAt(a.lastIndex).alphabeticalIndex // Instead, use removeLast() in Kotlin 1.4+
                remainingForA[removed]++
                remainingNotForA[removed]--
            }
            a.add(it)
            remainingForA[it.alphabeticalIndex]--
        } else {
            remainingNotForA[it.alphabeticalIndex]--
        }
    }
    return a.joinToString("")
}

fun main() {
    println(reverseShuffleMerge(readLine().orEmpty()))
}
