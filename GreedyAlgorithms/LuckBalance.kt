fun luckBalance(k: Int, contests: Iterable<IntArray>) = with(contests.partition { it[1] == 1 }) {
    with(first.map { it.first() }.sortedDescending()) {
        take(k).sum() - drop(k).sum()
    } + second.map { it.first() }.sum()
}

fun main() {
    val (n, k) = readLine().orEmpty().split(' ').map(String::toInt)
    val contests = List(n) {
        readLine().orEmpty().split(' ').map(String::toInt).toIntArray()
    }
    println(luckBalance(k, contests))
}
