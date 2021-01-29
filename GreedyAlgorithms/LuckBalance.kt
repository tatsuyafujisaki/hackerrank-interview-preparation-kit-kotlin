fun luckBalance(k: Int, contests: Iterable<List<Int>>) = with(contests.partition { it[1] == 1 }) {
    with(first.map { it.first() }.sortedDescending()) {
        take(k).sum() - drop(k).sum()
    } + second.map { it.first() }.sum()
}

fun main() {
    val (n, k) = readLine().orEmpty().split(' ').map(String::toInt)
    val contests = mutableListOf<List<Int>>()
    repeat(n) {
        contests.add(readLine().orEmpty().split(' ').map(String::toInt))
    }
    println(luckBalance(k, contests))
}
