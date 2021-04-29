val String.digitSum get() = map { Character.getNumericValue(it).toLong() }.sum() // TODO: Use Char.digitToInt() in Kotlin 1.4.30+.

fun superDigit(n: String, k: Int): Int {
    tailrec fun solve(x: Long): Int = if (x < 10) x.toInt() else solve(x.toString().digitSum)
    return solve(n.digitSum * k)
}

fun main() {
    val (n, k) = readLine().orEmpty().split(' ')
    println(superDigit(n, k.toInt()))
}
