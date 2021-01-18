fun twoStrings(s1: String, s2: String) = if (s1.toSet().intersect(s2.toSet()).isNotEmpty()) "YES" else "NO"

fun main() {
    val t = readLine().orEmpty().toInt()
    val ss = mutableListOf<Pair<String, String>>()
    repeat(t) {
        val s1 = readLine().orEmpty()
        val s2 = readLine().orEmpty()
        ss.add(s1 to s2)
    }
    for(s in ss) {
        println(twoStrings(s.first, s.second))
    }
}
