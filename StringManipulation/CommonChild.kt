import kotlin.math.max

fun commonChild(s1: String, s2: String): Int {
    val matrix = Array(s1.length + 1) { IntArray(s2.length + 1) }
    for (i in s1.indices) {
        for (j in s2.indices) matrix[i + 1][j + 1] = if (s1[i] == s2[j]) matrix[i][j] + 1 else max(matrix[i + 1][j], matrix[i][j + 1])
    }
    return matrix[s1.length][s2.length]
}

fun main() {
    println(commonChild(readLine().orEmpty(), readLine().orEmpty()))
}
