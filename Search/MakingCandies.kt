import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun minimumPasses(m0: Long, w0: Long, p: Long, n: Long): Long {
    var m = m0
    var w = w0
    var candies = 0L
    var totalPasses = 0L
    var minTotalPasses = Long.MAX_VALUE
    while (candies < n) {
        val passes = if (candies < p) {
            val mw = candies / p + m + w
            val half = ceil(mw / 2.0).toLong()
            if (w < m) {
                m = max(m, half)
                w = mw - m
            } else {
                w = max(w, half)
                m = mw - w
            }
            candies %= p
            1
        } else {
            max(1, (((p - candies) / m.toDouble()) / w).toLong())
        }
        totalPasses += passes
        if (Long.MAX_VALUE / w < passes * m) return totalPasses
        candies += passes * m * w
        minTotalPasses = min(minTotalPasses, totalPasses + ceil((n - candies).toDouble() / (m * w)).toLong())
    }
    return minTotalPasses
}

fun main() {
    val (m, w, p, n) = readLine().orEmpty().split(' ').map(String::toLong)
    println(minimumPasses(m, w, p, n))
}
