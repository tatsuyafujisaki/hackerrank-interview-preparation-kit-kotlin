val Long.isEven get() = this % 2 == 0L

data class Vertex(val ancestors: List<Int>, val children: List<Int>, val subtreeSum: Long)

fun convertGraphToTree(graph: List<List<Int>>, data: List<Int>, root: Int): List<Vertex> {
    val ancestors = Array<List<Int>>(graph.size) { emptyList() }
    val vertices = Array<Vertex?>(graph.size) { null }

    fun visitVertex(id: Int, parent: Int) {
        ancestors[id] = listOf(parent) + ancestors[parent]
        val children = graph[id].filter { parent != it }
        for (child in children) visitVertex(child, id)
        val subtreeData = data[id] + children.map { vertices[it]!!.subtreeSum }.sum()
        vertices[id] = Vertex(ancestors[id], children, subtreeData)
    }

    for (child in graph[root]) visitVertex(child, root)
    val subtreeData = data[root] + graph[root].map { vertices[it]!!.subtreeSum }.sum()
    vertices[root] = Vertex(ancestors[root], graph[root], subtreeData)
    return vertices.filterNotNull()
}

fun <T : Comparable<T>> List<T?>.count(element: T): Int {
    val i = binarySearch(element)
    if (i < 0) return 0
    var j = i
    var k = i
    var count = 1
    while (j-- > 0 && this[j] == element) count++
    while (k++ < lastIndex && this[k] == element) count++
    return count
}

fun balancedForest(graph: List<List<Int>>, c: List<Int>): Long {
    var minExtra = Long.MAX_VALUE

    fun tryUpdateMinExtra(twinLargerTree: Long, smallerTree: Long) {
        if (smallerTree <= twinLargerTree) {
            val candidate = twinLargerTree - smallerTree
            if (candidate < minExtra) minExtra = candidate
        }
    }

    val vertices = convertGraphToTree(graph, c, 0)
    val subtreeSums = vertices.map { it.subtreeSum }.sorted()
    val totalSum = vertices.first().subtreeSum

    for (i in 1 until vertices.size) {
        val ancestors = vertices[i].ancestors.map { vertices[it].subtreeSum }
        if (3 * vertices[i].subtreeSum < totalSum) {
            val smallerTree = vertices[i].subtreeSum
            if ((totalSum - smallerTree).isEven) {
                val twinLargerTree = (totalSum - smallerTree) / 2
                if (ancestors.binarySearch(twinLargerTree + smallerTree) >= 0 ||
                    subtreeSums.count(twinLargerTree).let {
                        it >= 2 || it >= 1 && ancestors.binarySearch(twinLargerTree) < 0
                    }
                ) {
                    tryUpdateMinExtra(twinLargerTree, smallerTree)
                }
            }
        } else {
            val twinLargerTree = vertices[i].subtreeSum
            val smallerTree = totalSum - 2 * twinLargerTree
            if (ancestors.binarySearch(2 * twinLargerTree) >= 0 ||
                ancestors.binarySearch(twinLargerTree + smallerTree) >= 0 ||
                subtreeSums.count(twinLargerTree) >= 2
            ) {
                tryUpdateMinExtra(twinLargerTree, smallerTree)
            }
        }
    }

    return if (minExtra == Long.MAX_VALUE) -1 else minExtra
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val n = readLine().orEmpty().toInt()
        val c = readLine().orEmpty().split(' ').map(String::toInt)
        val graph = List(n) { mutableListOf<Int>() }
        repeat(n - 1) {
            val (v1, v2) = readLine()
                .orEmpty()
                .split(' ')
                .map(String::toInt)
                .map { it - 1 } // converts to zero-based numbering.
            graph[v1].add(v2)
            graph[v2].add(v1)
        }
        println(balancedForest(graph, c))
    }
}
