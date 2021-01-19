fun sherlockAndAnagrams(s: String): Int {
    var count = 0
    val anagrams = mutableMapOf<String, Int>()
    for (i in s.indices) {
        for (j in i + 1..s.length) {
            val substring = s.substring(i, j).toCharArray().sorted().joinToString("")
            count += anagrams.getOrPut(substring) { 0 }
            anagrams.merge(substring, 1, Int::plus)
        }
    }
    return count
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
