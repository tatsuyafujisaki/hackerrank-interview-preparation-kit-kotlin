fun getMinimumCost(k: Int, c: Iterable<Int>) = c.sortedDescending().reduceIndexed { i, acc, cost -> acc + (i / k + 1) * cost }

fun main() {
    println(getMinimumCost(readLine().orEmpty().split(' ').map(String::toInt)[1], readLine().orEmpty().split(' ').map(String::toInt)))
}
