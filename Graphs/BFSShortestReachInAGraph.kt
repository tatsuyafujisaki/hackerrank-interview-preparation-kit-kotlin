fun main() {
    val edgeCost = 6
    repeat(readLine().orEmpty().toInt()) {
        val (n, m) = readLine().orEmpty().split(' ').map(String::toInt)
        val graph = Array(n) { mutableSetOf<Int>() }
        repeat(m) {
            val (u, v) = readLine().orEmpty().split(' ').map(String::toInt).map { it - 1 } // to zero-based
            graph[u].add(v)
            graph[v].add(u)
        }
        val s = readLine().orEmpty().trim().toInt() - 1 // to zero-based
        val distances = IntArray(n) {
            if (it == s) 0 else -1
        }
        val nodesToVisit = mutableListOf(s)
        while (nodesToVisit.isNotEmpty()) {
            val currentNode = nodesToVisit.removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
            graph[currentNode]
                .filter {
                    distances[it] == -1
                }
                .forEach {
                    distances[it] = distances[currentNode] + edgeCost
                    nodesToVisit.add(it)
                }
        }
        println(distances.filterIndexed { i, _ -> i != s }.joinToString(" "))
    }
}
