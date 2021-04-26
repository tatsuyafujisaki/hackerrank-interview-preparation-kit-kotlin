val cache = mutableMapOf<Int, Int>()

fun stepPerms(n: Int): Int = when (n) {
    1 -> 1
    2 -> 2
    3 -> 4
    else -> cache.getOrPut(n) {
        stepPerms(n - 1) + stepPerms(n - 2) + stepPerms(n - 3)
    }
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(stepPerms(readLine().orEmpty().toInt()))
    }
}
