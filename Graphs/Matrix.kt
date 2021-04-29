class DisjointSets(n: Int, machines: List<Int>) {
    private val parents = IntArray(n) { it }
    private val ranks = IntArray(n)

    /** Using the technique "path compression" */
    fun find(x: Int): Int {
        if (parents[x] != x) parents[x] = find(parents[x])
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

    val isInMachineComponent = BooleanArray(n).apply {
        for (machine in machines) this[machine] = true
    }
}

fun minTime(roads: List<List<Int>>, machines: List<Int>): Int {
    var totalTime = 0
    val disjointSets = DisjointSets(roads.size + 1 /* nodes = roads + 1 */, machines)
    roads.sortedByDescending { it[2] /* time */ }.forEach {
        val root1 = disjointSets.find(it[0])
        val root2 = disjointSets.find(it[1])
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
    val roads = List(n - 1) {
        readLine().orEmpty().split(' ').map(String::toInt)
    }
    println(minTime(roads, List(k) { readLine().orEmpty().toInt() }))
}
