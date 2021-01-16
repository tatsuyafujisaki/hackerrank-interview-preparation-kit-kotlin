import java.util.Collections

fun rotLeft(a: List<Int>, d: Int): List<Int> {
    Collections.rotate(a, -1 * d)
    return a
}

fun main() {
    val d = readLine().orEmpty().split(' ').map { it.toInt() }[1]
    val a = readLine().orEmpty().split(' ').map { it.toInt() }
    rotLeft(a, d).forEach { print("$it ") }
}
