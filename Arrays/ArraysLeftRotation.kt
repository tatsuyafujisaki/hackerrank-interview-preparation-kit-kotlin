fun rotLeft(a: Collection<Int>, d: Int) = a.drop(d) + a.take(d)

fun main() {
    val d = readLine().orEmpty().split(' ').map(String::toInt)[1]
    val a = readLine().orEmpty().split(' ').map(String::toInt)
    rotLeft(a, d).forEach { print("$it ") }
}
