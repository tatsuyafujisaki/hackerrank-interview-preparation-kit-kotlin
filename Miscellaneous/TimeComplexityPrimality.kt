import kotlin.math.sqrt

val Int.isPrime
    get() = when {
        this == 2 -> true
        this < 2 || this % 2 == 0 -> false
        else -> (3..sqrt(toDouble()).toInt() step 2).none { this % it == 0 }
    }

fun primality(n: Int) = if (n.isPrime) "Prime" else "Not prime"

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(primality(readLine().orEmpty().toInt()))
    }
}
