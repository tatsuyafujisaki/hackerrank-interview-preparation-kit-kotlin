import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun minimumPasses(m0: Long, w0: Long, p: Long, n: Long): Long {
    var m = m0
    var w = w0
    var candies = 0L
    var passes = 0L
    var minPasses = Long.MAX_VALUE
    var pass: Long
    while (candies < n) {
        if (p <= candies) {
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
            pass = 1
        } else {
            pass = max(1, (((p - candies) / m.toDouble()) / w).toLong())
        }
        passes += pass
        if (Long.MAX_VALUE / w < pass * m) return passes
        candies += pass * m * w
        minPasses = min(minPasses, passes + ceil((n - candies).toDouble() / (m * w)).toLong())
    }
    return minPasses
}

fun main() {
    val (m, w, p, n) = readLine().orEmpty().split(' ').map(String::toLong)
    println(minimumPasses(m, w, p, n))
}
