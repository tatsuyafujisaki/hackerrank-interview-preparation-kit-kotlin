/**
 * @param workspace is to reuse a temporary array not to recreate temporary arrays to avoid Time Limit Exceeded in the test case 5.
 */
fun median(counts: IntArray, d: Int, workspace: IntArray): Double {
    fun median2(onMiddleIndex: (IntArray, Int) -> Double): Double {
        val middleIndex = d / 2
        var i = 0
        counts.forEachIndexed { j, x ->
            repeat(x) {
                workspace[i] = j
                if (i == middleIndex) return onMiddleIndex(workspace, i)
                i++
            }
        }
        error("Unexpected to reach here.")
    }
    return if (d % 2 == 0) {
        median2 { xs, i -> (xs[i - 1] + xs[i]) / 2.0 }
    } else {
        median2 { xs, i -> xs[i].toDouble() }
    }
}

fun activityNotifications(expenditures: List<Int>, d: Int): Int {
    var notifications = 0
    val counts = IntArray(201)
    for (i in 0 until d - 1) {
        counts[expenditures[i]]++
    }
    val indexOfMedian = d / 2
    val workspace = IntArray(indexOfMedian + 1)
    for (i in 0 until expenditures.size - d) {
        counts[expenditures[i + d - 1]]++
        if (median(counts, d, workspace) * 2 <= expenditures[i + d]) notifications++
        counts[expenditures[i]]--
    }
    return notifications
}

fun main() {
    val d = readLine().orEmpty().split(' ').map(String::toInt)[1]
    val expenditures = readLine().orEmpty().split(' ').map(String::toInt)
    println(activityNotifications(expenditures, d))
}
