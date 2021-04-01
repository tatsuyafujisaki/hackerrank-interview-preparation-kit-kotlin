fun poisonousPlants(p: List<Int>): Int {
    val lists = mutableListOf(mutableListOf(p.first())).apply {
        for (i in 1 until p.size) {
            val list = last()
            if (p[i] <= list.last()) list.add(p[i]) else add(mutableListOf(p[i]))
        }
    }
    var days = 0
    while (lists.size > 1) {
        for (i in 1 until lists.size) lists[i].removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
        lists.removeIf {
            it.isEmpty()
        }
        do {
            var merged = false
            for (i in 0 until lists.lastIndex) {
                if (lists[i].last() >= lists[i + 1].first()) {
                    lists[i].addAll(lists[i + 1])
                    lists.removeAt(i + 1)
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
