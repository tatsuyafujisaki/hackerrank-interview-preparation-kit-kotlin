fun poisonousPlants(p: List<Int>): Int {
    val descents = mutableListOf(mutableListOf(p.first())).apply {
        for (i in 1 until p.size) {
            val descent = last()
            if (p[i] <= descent.last()) descent.add(p[i]) else add(mutableListOf(p[i]))
        }
    }
    var days = 0
    while (descents.size > 1) {
        for (i in 1 until descents.size) descents[i].removeAt(0) // TODO: Use removeFirst() in Kotlin 1.4+.
        descents.removeIf {
            it.isEmpty()
        }
        do {
            var merged = false
            for (i in 0 until descents.lastIndex) {
                if (descents[i].last() >= descents[i + 1].first()) {
                    descents[i].addAll(descents[i + 1])
                    descents.removeAt(i + 1)
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
