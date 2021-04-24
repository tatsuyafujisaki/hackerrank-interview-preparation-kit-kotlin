import util.SimulatedStandardInput

val Long.isEven get() = this % 2 == 0L

data class Vertex(val ancestors: List<Int>, val children: List<Int>, val subtreeSum: Long)

fun findRootOfMinHeightTree(graph: List<List<Int>>): Set<Int> {
    // Early return if the graph has only one vertex because the rest of findRootOfMinHeightTree() mistakenly returns an empty set in the scenario.
    if (graph.size == 1) return setOf(0)
    var remainingVertices = graph.size
    val degrees = graph.map { it.size }.toMutableList()
    val leaves = graph.indices.filter { graph[it].size == 1 }.toMutableList()
    while (remainingVertices > 2) {
        val leafCount = leaves.size
        remainingVertices -= leaves.size
        repeat(leafCount) {
            val leaf = leaves.removeAt(0)
            for (semiLeaf in graph[leaf]) {
                degrees[semiLeaf]--
                if (degrees[semiLeaf] == 1) leaves.add(semiLeaf)
            }
        }
    }
    return leaves.toSet()
}

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

    val root = findRootOfMinHeightTree(graph).first()
    val vertices = convertGraphToTree(graph, c, root)
    val subtreeSums = vertices.map { it.subtreeSum }.sorted()
    val totalSum = vertices[root].subtreeSum

    for (i in vertices.indices.filter { it != root }) {
        val ancestors = vertices[i].ancestors.map { vertices[it].subtreeSum }
        if (3 * vertices[i].subtreeSum < totalSum) {
            val smallerTree = vertices[i].subtreeSum
            if ((totalSum - smallerTree).isEven) {
                val twinLargerTree = (totalSum - smallerTree) / 2
                if (ancestors.binarySearch(twinLargerTree + smallerTree) >= 0) {
                    tryUpdateMinExtra(twinLargerTree, smallerTree)
                }
                val count = subtreeSums.count(twinLargerTree)
                if (count >= 2 || count >= 1 && ancestors.binarySearch(twinLargerTree) < 0) {
                    tryUpdateMinExtra(twinLargerTree, smallerTree)
                }
            }
        } else {
            val twinLargerTree = vertices[i].subtreeSum
            val smallerTree = totalSum - 2 * twinLargerTree
            if (ancestors.binarySearch(2 * twinLargerTree) >= 0) {
                tryUpdateMinExtra(twinLargerTree, smallerTree)
            }
            if (ancestors.binarySearch(twinLargerTree + smallerTree) >= 0) {
                tryUpdateMinExtra(twinLargerTree, smallerTree)
            }
            if (subtreeSums.count(twinLargerTree) >= 2) {
                tryUpdateMinExtra(twinLargerTree, smallerTree)
            }
        }
    }

    return if (minExtra == Long.MAX_VALUE) -1 else minExtra
}

fun main() {
    val stdin = SimulatedStandardInput("input05.txt")
    fun readLine() = stdin.readLine()
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
