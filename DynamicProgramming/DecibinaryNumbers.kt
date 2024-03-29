// https://allhackerranksolutionsbykaira.blogspot.com/2020/04/decibinary-numbers-hackerrank-solution.html
const val maxD = 19 // See the editorial for details.
const val maxS = 300000 // See the editorial for details.

fun pow2(n: Int) = 1 shl n

/** Equivalent of f(d, s) in the editorial */
fun createF() = List(maxD + 1) { LongArray(maxS + 1) }.apply {
    this[0][0] = 1
    for (d in 1..maxD) {
        for (s in 0..maxS) {
            for (i in 0..9) {
                val previousS = s - i * pow2(d - 1)
                if (previousS >= 0) this[d][s] += this[d - 1][previousS]
            }
        }
    }
}

/** Equivalent of "c" in the editorial */
fun createC(f: List<LongArray>) = LongArray(maxS + 1).apply {
    this[0] = f[maxD][0]
    for (s in 1 until size) this[s] = this[s - 1] + f[maxD][s]
}

/**
 * @param x as in xth decibinary
 * @return the index of [x] if [x] is found. If not, the index of the max element that is less than [x]. Note that the index is "s" in the editorial.
 */
fun findS(c: LongArray, x: Long): Int {
    val s = c.binarySearch(x)
    return if (s >= 0) s else -s - 1
}

fun decibinaryNumbers(f: List<LongArray>, c: LongArray, x: Long): Long {
    var s = findS(c, x)
    var g = if (s > 0) x - c[s - 1] else x
    var decibinaryDigits = ""
    for (d in maxD downTo 1) {
        var i = 0
        var decibinaryCount = 0L
        var temp = 0L
        while (decibinaryCount < g) {
            temp = decibinaryCount
            decibinaryCount += f[d - 1][s - i++ * pow2(d - 1)]
        }
        i--
        decibinaryCount = temp
        decibinaryDigits += i
        s -= i * pow2(d - 1)
        g -= decibinaryCount
    }
    return decibinaryDigits.toLong()
}

fun main() {
    val f = createF()
    val c = createC(f)
    repeat(readLine().orEmpty().toInt()) {
        println(decibinaryNumbers(f, c, readLine().orEmpty().toLong()))
    }
}
