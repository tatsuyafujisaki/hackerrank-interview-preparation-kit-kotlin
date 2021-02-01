fun pairs(k: Int, arr: Iterable<Int>) = arr.count { contains(it + k) } }

fun main() {
    println(pairs(readLine().orEmpty().split(' ').map(String::toInt)[1], readLine().orEmpty().split(' ').map(String::toInt)))
}
