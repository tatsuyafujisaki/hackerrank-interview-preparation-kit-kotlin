fun main() {
    val edgeLength = 6
    repeat(readLine().orEmpty().toInt()) {
        val (n, m) = readLine().orEmpty().split(' ').map(String::toInt)
        val graph = List(n) { mutableSetOf<Int>() }
        repeat(m) {
            val (u, v) = readLine()
                .orEmpty()
                .split(' ')
                .map(String::toInt)
                .map { it - 1 }  // converts to zero-based numbering.
            graph[u].add(v)
            graph[v].add(u)
        }
        val s = readLine().orEmpty().toInt() - 1 // converts to zero-based numbering.
        val distances = IntArray(n) { -1 }.apply {
            this[s] = 0
        }
        val nodesToVisit = mutableListOf(s)
        while (nodesToVisit.isNotEmpty()) {
            val currentNode = nodesToVisit.removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
            graph[currentNode]
                .filter {
                    distances[it] == -1
                }
                .forEach {
                    distances[it] = distances[currentNode] + edgeLength
                    nodesToVisit.add(it)
                }
        }
        println(distances.filterIndexed { i, _ -> i != s }.joinToString(" "))
    }
}
