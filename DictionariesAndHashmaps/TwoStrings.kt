fun twoStrings(s1: String, s2: String) = if (s1.toSet().intersect(s2.toSet()).isEmpty()) "NO" else "YES"

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(twoStrings(readLine().orEmpty(), readLine().orEmpty()))
    }
}
