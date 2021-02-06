fun abbreviation(a: String, b: String): String {
    val dp = Array(a.length + 1) { BooleanArray(b.length + 1) }.also { it[0][0] = true }
    var i = 0
    while (i < a.length && a[i].isLowerCase()) dp[1 + i++][0] = true
    for (ai in a.indices) {
        for (bi in b.indices) dp[ai + 1][bi + 1] = dp[ai][bi] && a[ai].toUpperCase() == b[bi] || dp[ai][bi + 1] && a[ai].isLowerCase()
    }
    return if (dp[a.length][b.length]) "YES" else "NO"
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(abbreviation(readLine().orEmpty(), readLine().orEmpty()))
    }
}
