/**
 * When c_lib <= c_road, the answer is c_lib * n.
 * When c_road < c_lib, the answer is c_lib * number_of_components + c_road * (n - number_of_components).
 * You need "long" only in the final calculation. Until then, "int" should be big enough.
 * If you fail the test case 4,5,6,8,9,10, ensure that the following mock test case below returns 5.
```
1
4 3 2 1
1 3
2 4
1 2
```
 */
class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    /** Using the technique "path compression" */
    private fun findRoot(x: Int): Int {
        if (parents[x] != x) parents[x] = findRoot(parents[x])
        return parents[x]
    }

    /** Using the technique "union by rank" */
    fun union(x: Int, y: Int) {
        val xRoot = findRoot(x)
        val yRoot = findRoot(y)
        when {
            ranks[xRoot] < ranks[yRoot] -> parents[xRoot] = yRoot
            ranks[xRoot] > ranks[yRoot] -> parents[yRoot] = xRoot
            xRoot != yRoot -> {
                parents[yRoot] = xRoot
                ranks[xRoot]++
            }
        }
    }

    val components: Collection<List<Int>>
        get() {
            // Make each parent the direct child of its root ancestor.
            for (i in parents.indices) parents[i] = findRoot(parents[i])
            return parents
                .mapIndexed { i, x -> x to i }
                .groupBy({ it.first }, { it.second })
                .values
        }
}

fun roadsAndLibraries(n: Int, cLib: Int, cRoad: Int, pairs: Collection<Pair<Int, Int>>): Long {
    if (cLib <= cRoad) return cLib.toLong() * n
    val disjointSets = DisjointSets(n)
    for (pair in pairs) disjointSets.union(pair.first, pair.second)
    val componentCount = disjointSets.components.size.toLong()
    return cLib * componentCount + cRoad * (n - componentCount)
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val (n, m, cLib, cRoad) = readLine().orEmpty().split(' ').map(String::toInt)
        val cities = List(m) {
            readLine()
                .orEmpty()
                .split(' ')
                .map(String::toInt)
                .map { it - 1 /* Converts to zero-based numbering */ }
                .let {
                    it[0] to it[1]
                }
        }
        println(roadsAndLibraries(n, cLib, cRoad, cities))
    }
}
