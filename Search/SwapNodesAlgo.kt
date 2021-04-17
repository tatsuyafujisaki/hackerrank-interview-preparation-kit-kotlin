data class Node(var left: Int, var right: Int) {
    fun swapChildren() {
        val temp = left
        left = right
        right = temp
    }
}

fun swapSubtrees(nodes: List<Node>, k: Int, h: Int = 1, index: Int = 1) {
    if (index == -1) return
    val node = nodes[index - 1]
    if (h % k == 0) node.swapChildren()
    swapSubtrees(nodes, k, h + 1, node.left)
    swapSubtrees(nodes, k, h + 1, node.right)
}

fun traverseInOrder(nodes: List<Node>, index: Int = 1): List<Int> = if (index == -1) {
    emptyList()
} else traverseInOrder(nodes, nodes[index - 1].left) + index + traverseInOrder(
    nodes,
    nodes[index - 1].right
)

fun swapNodes(indices: Iterable<IntArray>, queries: Iterable<Int>): List<List<Int>> {
    val nodes = mutableListOf<Node>()
    for (index in indices) nodes.add(Node(index[0], index[1]))
    return queries.map {
        swapSubtrees(nodes, it)
        traverseInOrder(nodes)
    }
}

fun main() {
    val indices = List(readLine().orEmpty().toInt()) {
        readLine().orEmpty().split(' ').map(String::toInt).toIntArray()
    }
    val queries = List(readLine().orEmpty().toInt()) {
        readLine().orEmpty().toInt()
    }
    for (nodes in swapNodes(indices, queries)) println(nodes.joinToString(" "))
}
