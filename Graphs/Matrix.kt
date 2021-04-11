class DisjointSets(n: Int, machines: Array<Int>) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    val isInMachineComponent = BooleanArray(n).apply {
        for (machine in machines) this[machine] = true
    }

    /** Using the technique "path compression" */
    fun findRoot(x: Int): Int {
        if (parents[x] != x) parents[x] = findRoot(parents[x])
        return parents[x]
    }

    /** Using the technique "union by rank" */
    fun union(xRoot: Int, yRoot: Int, isMachineComponent: Boolean) {
        if (isMachineComponent) {
            isInMachineComponent[xRoot] = true
            isInMachineComponent[yRoot] = true
        }
        when {
            ranks[xRoot] < ranks[yRoot] -> parents[xRoot] = yRoot
            ranks[xRoot] > ranks[yRoot] -> parents[yRoot] = xRoot
            xRoot != yRoot -> {
                parents[yRoot] = xRoot
                ranks[xRoot]++
            }
        }
    }
}

fun minTime(roads: Array<List<Int>>, machines: Array<Int>): Int {
    var totalTime = 0
    val disjointSets = DisjointSets(roads.size + 1 /* nodes = roads + 1 */, machines)
    roads
        .sortedByDescending {
            it[2] // time
        }.forEach {
            val root1 = disjointSets.findRoot(it[0])
            val root2 = disjointSets.findRoot(it[1])
            val isCity1InMachineComponent = disjointSets.isInMachineComponent[root1]
            val isCity2InMachineComponent = disjointSets.isInMachineComponent[root2]
            if (isCity1InMachineComponent && isCity2InMachineComponent) {
                totalTime += it[2]
            } else {
                disjointSets.union(root1, root2, isCity1InMachineComponent || isCity2InMachineComponent)
            }
        }
    return totalTime
}

fun main() {
    val (n, k) = readLine().orEmpty().split(' ').map(String::toInt)
    val roads = Array(n - 1) {
        readLine().orEmpty().split(' ').map(String::toInt)
    }
    println(minTime(roads, Array(k) { readLine().orEmpty().toInt() }))
}
