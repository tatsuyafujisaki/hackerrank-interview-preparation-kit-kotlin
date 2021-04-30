val pow2 = intArrayOf(
    1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024,
    2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576,
    2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824
)

val Int.bits
    get() = toString(2)
        .padStart(Int.SIZE_BITS, '0')
        .toCharArray()
        .map { it - '0' }

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
                result += pow2[Int.SIZE_BITS - 1 - i]
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
