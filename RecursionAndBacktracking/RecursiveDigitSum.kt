val String.digitSum get() = map { (it - '0').toLong() }.sum()

fun superDigit(n: String, k: Int): Int {
    tailrec fun solve(x: Long): Int = if (x < 10) x.toInt() else solve(x.toString().digitSum)
    return solve(n.digitSum * k)
}

fun main() {
    val (n, k) = readLine().orEmpty().split(' ')
    println(superDigit(n, k.toInt()))
}
