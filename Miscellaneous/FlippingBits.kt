fun flippingBits(n: Long) = n.inv().toUInt()

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(flippingBits(readLine().orEmpty().toLong()))
    }
}
