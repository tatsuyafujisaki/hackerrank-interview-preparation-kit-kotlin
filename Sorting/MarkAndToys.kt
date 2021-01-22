import kotlin.String

fun maximumToys(prices: Collection<Int>, k: Int): Int {
    var totalPrice = 0
    prices.sorted().forEachIndexed { i, price ->
        totalPrice += price
        if (totalPrice > k) {
            return i
        }
    }
    return prices.size
}

fun main() {
    val k = readLine().orEmpty().split(' ').map(String::toInt)[1]
    val prices = readLine().orEmpty().split(' ').map(String::toInt)
    println(maximumToys(prices, k))
}
