import kotlin.math.pow

val Int.bits get() = toString(2).padStart(Int.SIZE_BITS, '0').map { it - '0' }

class TrieNode<T> {
    val children = Array<TrieNode<T>?>(2) { null }
}

fun maxXor(arr: List<Int>, queries: List<Int>): List<Int> {
    val root = TrieNode<Int>()
    for (x in arr) {
        var current = root
        for (bit in x.bits) {
            if (current.children[bit] == null) current.children[bit] = TrieNode()
            current = current.children[bit]!!
        }
    }
    return queries.map {
        var current = root
        val bits = it.bits
        var result = 0
        for (i in 0 until Int.SIZE_BITS) {
            val bit = bits[i]
            val inverted = (bit + 1) % 2
            if (current.children[inverted] != null) {
                result += 2.0.pow(Int.SIZE_BITS - 1 - i).toInt()
                current = current.children[inverted]!!
            } else {
                current = current.children[bit]!!
            }
        }
        result
    }
}

fun main() {
    readLine() // Read and discard.
    maxXor(
        readLine().orEmpty().split(' ').map(String::toInt),
        List(readLine().orEmpty().toInt()) {
            readLine().orEmpty().toInt()
        }
    ).forEach(::println)
}
