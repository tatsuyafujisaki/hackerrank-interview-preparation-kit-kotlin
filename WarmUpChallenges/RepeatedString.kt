fun repeatedString(s: String, n: Long): Long {
    val repeat = n / s.length
    return s.count { it == 'a' } * repeat + s.take((n - s.length * repeat).toInt()).count { it == 'a' }
}

fun main() {
    val s = readLine().orEmpty()
    val n = readLine()?.toLong() ?: 0
    println(repeatedString(s, n))
}
