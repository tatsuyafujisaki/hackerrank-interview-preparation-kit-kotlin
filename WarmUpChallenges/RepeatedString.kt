fun repeatedString(s: String, n: Long) =
    with(n / s.length) {
        s.count { it == 'a' } * this + s.take((n - s.length * this).toInt()).count { it == 'a' }
    }

fun main() {
    println(repeatedString(readLine().orEmpty(), readLine()?.toLong() ?: 0))
}
