val Long.isOdd get() = this % 2 == 1L
fun <K, V : Comparable<V>> Map<K, V>.sortedByValue() = toList().sortedBy { it.second }.toMap()

data class Vertex(val ancestors: List<Int>, val children: List<Int>, val subtreeSum: Long)

fun findRootOfMinHeightTree(graph: List<Set<Int>>): Set<Int> {
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

fun convertGraphToTree(graph: List<Set<Int>>, data: List<Int>, root: Int): List<Vertex> {
    val ancestors = Array<List<Int>>(graph.size) { emptyList() }
    val vertices = Array<Vertex?>(graph.size) { null }

    fun visitVertex(id: Int, parent: Int) {
        ancestors[id] = if (parent == -1) emptyList() else listOf(parent) + ancestors[parent]
        val children = graph[id].filter { it != root && ancestors[it].isEmpty() }
        for (child in children) visitVertex(child, id)
        val subtreeData = data[id] + children.map { vertices[it]!!.subtreeSum }.sum()
        vertices[id] = Vertex(ancestors[id], children.sortedBy { vertices[it]!!.subtreeSum }, subtreeData)
    }

    visitVertex(root, -1)
    return vertices.filterNotNull()
}

fun balancedForest(graph: List<Set<Int>>, c: List<Int>): Long {
    val root = findRootOfMinHeightTree(graph).first()
    val vertices = convertGraphToTree(graph, c, root)
    val sortedAncestors = vertices.map { it.ancestors.sorted() }
    var extraMin = Long.MAX_VALUE
    val totalSum = vertices[root].subtreeSum

    fun tryUpdateMinExtra(candidate: Long) {
        if (candidate < extraMin) extraMin = candidate
    }

    fun tryUpdateMinExtra(tree1: Long, tree2: Long, tree3: Long) {
        if (tree1 == tree2 && tree3 <= tree1) {
            tryUpdateMinExtra(tree1 - tree3)
        } else if (tree1 == tree3 && tree2 <= tree1) {
            tryUpdateMinExtra(tree1 - tree2)
        } else if (tree2 == tree3 && tree1 <= tree2) {
            tryUpdateMinExtra(tree2 - tree1)
        }
    }

    for (i in vertices.indices.filter { it != root }) {
        val subtreeSum = vertices[i].subtreeSum
        val complementSum = totalSum - subtreeSum

        if (2 * subtreeSum == totalSum) {
            tryUpdateMinExtra(subtreeSum)
            continue
        }

        val (smallerTree, largerTree) = if (2 * subtreeSum < totalSum) {
            subtreeSum to complementSum
        } else {
            complementSum to subtreeSum
        }

        if (smallerTree * 2 < largerTree && largerTree.isOdd || extraMin < largerTree / 2 - smallerTree) continue

        for (j in i + 1 until vertices.size) {
            val subtreeSum2 = vertices[j].subtreeSum
            when {
                sortedAncestors[i].binarySearch(j) >= 0 ->
                    tryUpdateMinExtra(
                        subtreeSum,
                        subtreeSum2 - subtreeSum,
                        totalSum - subtreeSum2
                    )
                sortedAncestors[j].binarySearch(i) >= 0 ->
                    tryUpdateMinExtra(
                        subtreeSum - subtreeSum2,
                        subtreeSum2,
                        totalSum - subtreeSum
                    )
                else -> tryUpdateMinExtra(
                    subtreeSum,
                    subtreeSum2,
                    totalSum - subtreeSum - subtreeSum2
                )
            }
        }
    }
    return if (extraMin == Long.MAX_VALUE) -1 else extraMin
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val n = readLine().orEmpty().toInt()
        val c = readLine().orEmpty().split(' ').map(String::toInt)
        val graph = List(n) { mutableSetOf<Int>() }
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
