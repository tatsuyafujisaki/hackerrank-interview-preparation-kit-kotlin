import java.io.Closeable
import java.io.PrintWriter
import java.util.StringTokenizer
import kotlin.math.min

/** @return the number of the given element in a sorted array */
fun LongArray.count(element: Long): Int {
    var i = binarySearch(element)
    if (i < 0) return 0
    var count = 1
    var j = i
    while (getOrNull(--i) == element) count++
    while (getOrNull(++j) == element) count++
    return count
}

fun balancedForest(graph: List<List<Int>>, c: IntArray): Long {
    var minExtra = Long.MAX_VALUE

    fun tryUpdateMinExtra(twinTree: Long, thirdTree: Long) {
        if (thirdTree <= twinTree) minExtra = min(minExtra, twinTree - thirdTree)
    }

    val ancestors = Array<List<Int>>(graph.size) { emptyList() }
    val subtreeSums = LongArray(graph.size)

    fun visitVertex(i: Int, parent: Int) {
        ancestors[i] = listOf(parent) + ancestors[parent]
        val children = graph[i].filter { parent != it }
        for (child in children) visitVertex(child, i)
        subtreeSums[i] = c[i] + children.map { subtreeSums[it] }.sum()
    }

    graph.first().forEach { visitVertex(it, 0) }
    subtreeSums[0] = c[0] + graph[0].map { subtreeSums[it] }.sum()
    val totalSum = subtreeSums.first()
    val sortedSubtreeSums = subtreeSums.sortedArray() // to use binary search to avoid Time Limit Exceeded.
    for (i in 1 until graph.size) {
        val subtreeSumsOfAncestors =
            ancestors[i].map { subtreeSums[it] } // ancestors are sorted by subtreeSum by nature.
        if (3 * subtreeSums[i] < totalSum) {
            val smallerTree = subtreeSums[i]
            if ((totalSum - smallerTree) % 2 == 0L) {
                val twinLargerTree = (totalSum - smallerTree) / 2
                if (subtreeSumsOfAncestors.binarySearch(twinLargerTree + smallerTree) >= 0 ||
                    sortedSubtreeSums.count(twinLargerTree).let {
                        // If there are two vertices of the same subtreeSum, it is guaranteed that they are not in a parent-child relationship.
                        it >= 2 || it >= 1 && subtreeSumsOfAncestors.binarySearch(twinLargerTree) < 0
                    }
                ) tryUpdateMinExtra(twinLargerTree, smallerTree)
            }
        } else {
            val twinLargerTree = subtreeSums[i]
            val smallerTree = totalSum - 2 * twinLargerTree
            if (subtreeSumsOfAncestors.binarySearch(2 * twinLargerTree) >= 0 ||
                subtreeSumsOfAncestors.binarySearch(twinLargerTree + smallerTree) >= 0 ||
                // If there are two vertices of the same subtreeSum, it is guaranteed that they are not in a parent-child relationship.
                sortedSubtreeSums.count(twinLargerTree) >= 2
            ) tryUpdateMinExtra(twinLargerTree, smallerTree)
        }
    }
    return if (minExtra == Long.MAX_VALUE) -1 else minExtra
}

fun main() {
    FastScanner().use { fs ->
        PrintWriter(System.out, false /* writes at once */).use { pw ->
            repeat(fs.nextInt()) {
                val n = fs.nextInt()
                val c = fs.nextIntegers(n)
                val graph = List(n) { mutableListOf<Int>() }
                repeat(n - 1) {
                    val (v1, v2) = fs.nextZeroBasedIntegers(2)
                    graph[v1].add(v2)
                    graph[v2].add(v1)
                }
                pw.println(balancedForest(graph, c))
            }
        }
    }
}

class FastScanner : Closeable {
    private val br = System.`in`.bufferedReader()
    private var st = StringTokenizer("")

    override fun close() {
        br.close()
    }

    private fun next(): String {
        while (!st.hasMoreTokens()) st = StringTokenizer(br.readLine())
        return st.nextToken()
    }

    fun nextInt() = next().toInt()
    private fun nextZeroBasedInt() = next().toInt() - 1

    fun nextIntegers(n: Int) = IntArray(n) {
        nextInt()
    }

    fun nextZeroBasedIntegers(n: Int) = IntArray(n) {
        nextZeroBasedInt()
    }
}
