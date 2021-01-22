fun MutableMap<Int, Int>.increment(key: Int) {
    merge(key, 1, Int::plus)
}

fun MutableMap<Int, Int>.decrement(key: Int) {
    merge(key, 1, Int::minus)
}

fun freqQuery(queries: List<List<Int>>): List<Int> {
    val counts = mutableMapOf<Int, Int>().withDefault { 0 }
    val countCounts = mutableMapOf<Int, Int>().withDefault { 0 }
    val answers = mutableListOf<Int>()
    for (query in queries) {
        val (t, x) = query
        when (t) {
            1 -> {
                countCounts.decrement(counts.getValue(x))
                counts.increment(x)
                countCounts.increment(counts.getValue(x))
            }
            2 -> if (counts.getValue(x) > 0) {
                countCounts.decrement(counts.getValue(x))
                counts.decrement(x)
                countCounts.increment(counts.getValue(x))
            }
            else -> answers.add(if (countCounts.getValue(x) > 0) 1 else 0)
        }
    }
    return answers
}

fun main() {
    val queries = mutableListOf<List<Int>>()
    repeat(readLine().orEmpty().toInt()) {
        queries.add(readLine().orEmpty().split(' ').map { it.toInt() })
    }
    freqQuery(queries).forEach(::println)
}
