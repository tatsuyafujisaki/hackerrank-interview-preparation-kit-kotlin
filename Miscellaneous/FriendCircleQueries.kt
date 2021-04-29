class DisjointSets(n: Int) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)
    private val people = IntArray(n) { 1 }
    var maxCircle = Int.MIN_VALUE

    /** Using the technique "path compression" */
    private fun find(x: Int): Int {
        if (parents[x] == x) return x
        parents[x] = find(parents[x])
        return parents[x]
    }

    /** Using the technique "union by rank" */
    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        when {
            ranks[xRoot] < ranks[yRoot] -> {
                parents[xRoot] = yRoot
                tryUpdateMaxCircle(xRoot, yRoot)
            }
            ranks[xRoot] > ranks[yRoot] -> {
                parents[yRoot] = xRoot
                tryUpdateMaxCircle(xRoot, yRoot)
            }
            xRoot != yRoot -> {
                parents[yRoot] = xRoot
                ranks[xRoot]++
                tryUpdateMaxCircle(xRoot, yRoot)
            }
        }
    }

    private fun tryUpdateMaxCircle(xRoot: Int, yRoot: Int) {
        val sum = people[xRoot] + people[yRoot]
        people[xRoot] = sum
        people[yRoot] = sum
        if (maxCircle < sum) maxCircle = sum
    }
}

fun maxCircle(queries: List<List<Int>>): List<Int> {
    val compress = queries.flatten().toSet().mapIndexed { i, x -> x to i }.toMap()
    val disjointSets = DisjointSets(compress.size)
    val maxCircles = mutableListOf<Int>()
    for ((a, b) in queries) {
        disjointSets.union(compress[a]!!, compress[b]!!)
        maxCircles.add(disjointSets.maxCircle)
    }
    return maxCircles
}

fun main() {
    val queries = List(readLine().orEmpty().toInt()) {
        readLine()
            .orEmpty()
            .split(' ')
            .map(String::toInt)
    }
    maxCircle(queries).forEach {
        println(it)
    }
}
