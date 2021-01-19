fun String.alphabetized() = toCharArray().sorted().joinToString("")

fun MutableMap<String, Int>.increment(key: String) = merge(key, 1, Int::plus)

fun sherlockAndAnagrams(s: String): Int {
    var anagramCount = 0
    val anagrams = mutableMapOf<String, Int>()

    for (i in s.indices) {
        for (j in i + 1..s.length) {
            val substring = s.substring(i, j).alphabetized()
            anagramCount += anagrams.getOrPut(substring) { 0 }
            anagrams.increment(substring)
        }
    }

    return anagramCount
}

fun main() {
    val strings = mutableListOf<String>()

    repeat(readLine().orEmpty().toInt()) {
        strings.add(readLine().orEmpty())
    }

    for (string in strings) {
        println(sherlockAndAnagrams(string))
    }
}
