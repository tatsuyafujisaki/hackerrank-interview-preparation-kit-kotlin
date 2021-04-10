fun whatFlavors(cost: List<Int>, money: Int) {
    val flavors = mutableMapOf<Int, Int>()
    cost.forEachIndexed { i, c ->
        flavors[money - c]?.let {
            println("$it ${i + 1}")
            return
        }
        flavors[c] = i + 1
    }
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        val money = readLine().orEmpty().toInt()
        readLine() // Read and discard
        // Costs in all the non-sample test cases contain a trailing space, which will causes a parsing error.
        whatFlavors(readLine().orEmpty().trim().split(' ').map(String::toInt), money)
    }
}
