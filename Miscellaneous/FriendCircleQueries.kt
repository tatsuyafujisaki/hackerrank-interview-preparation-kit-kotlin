import kotlin.math.max

fun maxCircle(queries: List<List<Int>>): List<Int> {
    val compress = queries.flatten().toSet().mapIndexed { i, x -> x to i }.toMap()
    val parents = IntArray(compress.size) { it }
    val people = IntArray(compress.size) { 1 }
    val maxCircles = mutableListOf<Int>()
    var maxCircle = Int.MIN_VALUE

    tailrec fun find(x: Int): Int = if (parents[x] == x) x else find(parents[x])

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        if (xRoot != yRoot) {
            parents[yRoot] = xRoot
            people[xRoot] = people[xRoot] + people[yRoot]
            maxCircle = max(maxCircle, people[xRoot])
        }
    }

    for ((a, b) in queries) {
        union(compress[a]!!, compress[b]!!)
        maxCircles.add(maxCircle)
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
