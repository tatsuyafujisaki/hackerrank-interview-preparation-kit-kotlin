fun repeatedString(s: String, n: Long): Long {
    val repeat = n / s.length
    return s.count { it == 'a' } * repeat + s.take((n - s.length * repeat).toInt()).count { it == 'a' }
}

fun main() {
    println(repeatedString(readLine().orEmpty(), readLine()?.toLong() ?: 0))
}
