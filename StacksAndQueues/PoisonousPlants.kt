fun poisonousPlants(p: List<Int>): Int {
    val listOfDescendingPlantLists = mutableListOf(mutableListOf(p.first())).apply {
        for (i in 1 until p.size) {
            val descendingPlantList = last()
            if (p[i] <= descendingPlantList.last()) descendingPlantList.add(p[i]) else add(mutableListOf(p[i]))
        }
    }
    var days = 0
    while (listOfDescendingPlantLists.size > 1) {
        for (i in 1 until listOfDescendingPlantLists.size) listOfDescendingPlantLists[i].removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
        listOfDescendingPlantLists.removeIf {
            it.isEmpty()
        }
        do {
            var merged = false
            for (i in 0 until listOfDescendingPlantLists.lastIndex) {
                if (listOfDescendingPlantLists[i].last() >= listOfDescendingPlantLists[i + 1].first()) {
                    listOfDescendingPlantLists[i].addAll(listOfDescendingPlantLists[i + 1])
                    listOfDescendingPlantLists.removeAt(i + 1)
                    merged = true
                    break
                }
            }
        } while (merged)
        days++
    }
    return days
}

fun main() {
    readLine() // Read and discard
    println(poisonousPlants(readLine().orEmpty().split(' ').map(String::toInt)))
}
