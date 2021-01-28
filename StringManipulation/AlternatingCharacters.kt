fun alternatingCharacters(s: String): Int {
    var count = 0
    for (i in 0 until s.length - 1) {
        if (s[i] == s[i + 1]) count++
    }
    return count
}

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(alternatingCharacters(readLine().orEmpty()))
    }
}
