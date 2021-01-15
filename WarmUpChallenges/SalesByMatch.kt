fun sockMerchant(ar: Collection<Int>) = ar.groupingBy { it }.eachCount().values.map { it / 2 }.sum()

fun main() {
    readLine() // Read and discard
    println(sockMerchant(readLine().orEmpty().split(' ').map { it.toInt() }))
}