fun twoStrings(s1: String, s2: String) = if (s1.toSet().intersect(s2.toSet()).isEmpty()) "NO" else "YES"

fun main() {
    val ss = mutableListOf<Pair<String, String>>()
    repeat(readLine().orEmpty().toInt()) {
        ss.add(readLine().orEmpty() to readLine().orEmpty())
    }
    for (s in ss) {
        println(twoStrings(s.first, s.second))
    }
}
