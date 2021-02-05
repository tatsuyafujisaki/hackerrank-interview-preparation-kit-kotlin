import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun minimumPasses(m0: Long, w0: Long, p: Long, n: Long): Long {
    var candies = 0L
    var totalPasses = 0L
    var minTotalPasses = Long.MAX_VALUE
    var passes: Long
    var m = m0
    var w = w0
    while (candies < n) {
        if (p <= candies || (p - candies) / w < m) { // The second condition is a variant of (p - candies < m * w) for avoiding an integer overflow.
            // You reach here if one of the following is true.
            // 1. You have enough candies to add at least one machine or worker.
            // 2. You don't have enough candies to add at least one machine or worker right now but will get enough candies in this pass to do so.
            val mw = candies / p + m + w // (candies / p) is an integer and the sum of machines and workers you can add if you invest as many candies as possible.
            val half = ceil(mw / 2.0).toLong()
            if (m < w) {
                w = max(w, half)
                m = mw - w
            } else {
                m = max(m, half)
                w = mw - m
            }
            candies %= p // How many candies are left after you invest as many candies as possible.
            passes = 1
        } else {
            passes = if (Long.MAX_VALUE / w < m) 0 else (p - candies) / (m * w)
        }
        totalPasses += passes
        if (Long.MAX_VALUE / w < passes * m) {
            candies = Long.MAX_VALUE
        } else {
            candies += passes * m * w
            minTotalPasses = min(minTotalPasses, totalPasses + ceil((n - candies).toDouble() / (m * w)).toLong())
        }
    }
    // min(...) is necessary in case you reach here through an integer overflow pass.
    // In the case, minTotalPasses is unchanged from Long.MAX_VALUE.
    return min(minTotalPasses, totalPasses)
}

fun main() {
    val (m, w, p, n) = readLine().orEmpty().split(' ').map(String::toLong)
    println(minimumPasses(m, w, p, n))
}
