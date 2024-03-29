class TrieNode {
    val children = Array<TrieNode?>(2) { null }
}

val Int.bits get() = toString(2).padStart(Int.SIZE_BITS, '0').map { it - '0' }

fun maxXor(arr: List<Int>, queries: List<Int>): List<Int> {
    val root = TrieNode()
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
            val inverted = 1 - bit
            if (current.children[inverted] != null) {
                result += 1 shl (Int.SIZE_BITS - 1 - i)
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
