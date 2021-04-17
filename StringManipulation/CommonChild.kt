import kotlin.math.max

fun commonChild(s1: String, s2: String) = List(s1.length + 1) { IntArray(s2.length + 1) }.also {
    for (i in s1.indices) {
        for (j in s2.indices) it[i + 1][j + 1] = if (s1[i] == s2[j]) it[i][j] + 1 else max(it[i + 1][j], it[i][j + 1])
    }
}[s1.length][s2.length]

fun main() {
    println(commonChild(readLine().orEmpty(), readLine().orEmpty()))
}
