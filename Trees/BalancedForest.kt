data class Vertex(val ancestors: List<Int>, val children: List<Int>, val subtreeData: Long) {
    val isRoot get() = ancestors.isEmpty()
}

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
    val n = graph.size
    val ancestors = Array<List<Int>>(n) { emptyList() }
    val vertices = Array<Vertex?>(n) { null }

    fun visitVertex(id: Int, parent: Int) {
        ancestors[id] = if (parent == -1) emptyList() else listOf(parent) + ancestors[parent]
        val children = graph[id].filter { it != root && ancestors[it].isEmpty() }
        for (child in children) visitVertex(child, id)
        val subtreeData = data[id] + children.map { vertices[it]!!.subtreeData }.sum()
        vertices[id] = Vertex(ancestors[id], children.sortedBy { vertices[it]!!.subtreeData }, subtreeData)
    }

    visitVertex(root, -1)
    return vertices.filterNotNull()
}

fun findLCA(ancestors: List<List<Int>>, vertex1: Int, vertex2: Int): Int {
    var v1 = vertex1
    var v2 = vertex2
    while (ancestors[v1].size != ancestors[v2].size) {
        if (ancestors[v1].size < ancestors[v2].size) {
            v2 = ancestors[v2].first()
        } else {
            v1 = ancestors[v1].first()
        }
    }
    while (v1 != v2) {
        v1 = ancestors[v1].first()
        v2 = ancestors[v2].first()
    }
    return v1
}

fun balancedForest(graph: List<Set<Int>>, c: List<Int>): Long {
    val root = findRootOfMinHeightTree(graph).first()
    val vertices = convertGraphToTree(graph, c, root)
    val ancestors = vertices.map { it.ancestors }
    var dataToAdd = Long.MAX_VALUE
    val totalData = vertices[root].subtreeData

    fun tryUpdateDataToAdd(candidate: Long) {
        if (candidate < dataToAdd) dataToAdd = candidate
    }

    fun tryUpdateDataToAdd(data1: Long, data2: Long, data3: Long) {
        if (data1 == data2 && data3 <= data1) {
            tryUpdateDataToAdd(data1 - data3)
        } else if (data1 == data3 && data2 <= data1) {
            tryUpdateDataToAdd(data1 - data2)
        } else if (data2 == data3 && data1 <= data2) {
            tryUpdateDataToAdd(data2 - data1)
        }
    }

    for (i in smallerTrees.indices.filter { it != root }) {
        val (smallerTree, largerTree) = if (vertices[i].subtreeData * 2 < totalData) {
            vertices[i].subtreeData to totalData - vertices[i].subtreeData
        } else if (totalData < vertices[i].subtreeData * 2) {
            totalData - vertices[i].subtreeData to vertices[i].subtreeData
        } else {
            tryUpdateDataToAdd(vertices[i].subtreeData)
            continue
        }

        if (
            smallerTree * 2 < largerTree && largerTree % 2 == 1L ||
            dataToAdd != Long.MAX_VALUE && (smallerTree + dataToAdd) * 3 < totalData
        ) continue

        for (j in i + 1 until vertices.size) {
            when (findLCA(ancestors, i, j)) {
                i -> {
                    val data1 = vertices[i].subtreeData - vertices[j].subtreeData
                    val data2 = vertices[j].subtreeData
                    val data3 = totalData - vertices[i].subtreeData
                    tryUpdateDataToAdd(data1, data2, data3)
                }
                j -> {
                    val data1 = vertices[i].subtreeData
                    val data2 = vertices[j].subtreeData - vertices[i].subtreeData
                    val data3 = totalData - vertices[j].subtreeData
                    tryUpdateDataToAdd(data1, data2, data3)
                }
                else -> {
                    val data1 = vertices[i].subtreeData
                    val data2 = vertices[j].subtreeData
                    val data3 = totalData - data1 - data2
                    tryUpdateDataToAdd(data1, data2, data3)
                }
            }
        }
    }
    return if (dataToAdd == Long.MAX_VALUE) -1 else dataToAdd
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
        println(balancedForest(graph.map { it.toSet() }, c))
    }
}
