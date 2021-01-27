import kotlin.math.absoluteValue

fun makeAnagram(a: String, b: String): Int {
    val counts = IntArray(26)
    for (c in a) counts[c - 'a']++
    for (c in b) counts[c - 'a']--
    return counts.sumBy { it.absoluteValue }
}

fun main() {
    println(makeAnagram(readLine().orEmpty(), readLine().orEmpty()))
}
