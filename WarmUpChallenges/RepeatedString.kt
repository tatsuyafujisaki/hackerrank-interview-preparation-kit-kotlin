fun repeatedString(s: String, n: Long) =
    with(n / s.length) {
        s.count { it == 'a' } * this + s.take((n - s.length * this).toInt()).count { it == 'a' }
    }

fun main() {
    val s = readLine().orEmpty()
    val n = readLine()?.toLong() ?: 0
    println(repeatedString(s, n))
}
