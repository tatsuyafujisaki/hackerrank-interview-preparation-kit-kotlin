/**
 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
 */
class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = LongArray(n)

    val result
        get() = parents
            .mapIndexed { i, x -> x to i }
            .groupBy({ it.first }, { it.second })
            .values.map { it.toSet() }
            .toSet()

    /** Using the technique "path compression" */
    private fun findRoot(x: Int): Int {
        if (parents[x] == x) return x
        parents[x] = findRoot(parents[x])
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
    return disjointSets.result.map {
        cLib.toLong() + cRoad * (it.size - 1)
    }.sum()
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val (n, m, cLib, cRoad) = readLine().orEmpty().split(' ').filterNot(String::isEmpty).map(String::toInt)
        val cities = mutableSetOf<Pair<Int, Int>>().apply {
            if(cLib <= cRoad) {
                repeat(m) {
                    readLine() // Read and discard
                }
            } else {
                repeat(m) {
                    add(readLine()
                        .orEmpty()
                        .split(' ')
                        .asSequence()
                        .filterNot(String::isEmpty)
                        .map(String::toInt)
                        .map { it - 1 /* Convert to zero-based numbering */ }
                        .zipWithNext()
                        .first()
                    )
                }
            }
        }
        println(roadsAndLibraries(n, cLib, cRoad, cities))
    }
}
