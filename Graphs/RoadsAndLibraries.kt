/**
 * When c_lib <= c_road, the answer is c_lib * n.
 * When c_road < c_lib, the answer is c_lib * number_of_islands + c_road * (n - number_of_islands).
 * You need "long" only in the final calculation. Until then, "int" should be big enough.
 * If you get a TLE, try discarding the input lines of city pairs to slightly speed up when c_lib <= c_road.
 * If you fail the test case 4,5,6,8,9,10, ensure that the following mock test case below returns 5.
1
4 3 2 1
1 3
2 4
1 2
 */
class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    val islands: Collection<List<Int>>
        get() {
            // Make each parent trace back to its root ancestor.
            for (i in parents.indices) parents[i] = findRoot(parents[i])
            return parents
                .mapIndexed { i, x -> x to i }
                .groupBy({ it.first }, { it.second })
                .values
        }

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
}

fun roadsAndLibraries(n: Int, cLib: Int, cRoad: Int, pairs: Set<Pair<Int, Int>>): Long {
    if (cLib <= cRoad) return cLib.toLong() * n
    val disjointSets = DisjointSets(n)
    for (pair in pairs) disjointSets.union(pair.first, pair.second)
    val islandCount = disjointSets.islands.size.toLong()
    return cLib * islandCount + cRoad * (n - islandCount)
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val (n, m, cLib, cRoad) = readLine().orEmpty().split(' ').map(String::toInt)
        val cities = mutableSetOf<Pair<Int, Int>>()
        if (cLib <= cRoad) {
            repeat(m) {
                readLine() // Read and discard
            }
        } else {
            repeat(m) {
                cities.add(readLine()
                    .orEmpty()
                    .split(' ')
                    .map(String::toInt)
                    .map { it - 1 /* Convert to zero-based numbering */ }
                    .let {
                        it[0] to it[1]
                    }
                )
            }
        }
        println(roadsAndLibraries(n, cLib, cRoad, cities))
    }
}
