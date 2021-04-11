data class Node(var left: Int, var right: Int) {
    fun swapChildren() {
        val temp = left
        left = right
        right = temp
    }
}

fun MutableList<Node>.swapSubtrees(k: Int, h: Int = 1, index: Int = 1) {
    if (index == -1) return
    val node = this[index - 1]
    if (h % k == 0) node.swapChildren()
    swapSubtrees(k, h + 1, node.left)
    swapSubtrees(k, h + 1, node.right)
}

fun List<Node>.traverseInOrder(index: Int = 1): List<Int> = if (index == -1) emptyList() else traverseInOrder(this[index - 1].left) + index + traverseInOrder(this[index - 1].right)

fun swapNodes(indexes: Array<IntArray>, queries: Array<Int>): List<List<Int>> {
    val nodes = mutableListOf<Node>()
    for (index in indexes) nodes.add(Node(index[0], index[1]))
    return queries.map {
        nodes.swapSubtrees(it)
        nodes.traverseInOrder()
    }
}

fun main() {
    val indexes = Array(readLine().orEmpty().toInt()) {
        readLine().orEmpty().split(' ').map(String::toInt).toIntArray()
    }
    val queries = Array(readLine().orEmpty().toInt()) {
        readLine().orEmpty().toInt()
    }
    for (nodes in swapNodes(indexes, queries)) println(nodes.joinToString(" "))
}
