fun String.substrings() = sequence {
    for (i in indices) {
        for (j in i + 1..length) {
            yield(substring(i, j))
        }
    }
}

fun sherlockAndAnagrams(s: String) = s.substrings().map { it.toCharArray().sorted() }.groupingBy { it }.eachCount().map { it.value }.sumBy { (it * (it - 1)) / 2 }

fun main() {
    repeat(readLine().orEmpty().toInt()) {
        println(sherlockAndAnagrams(readLine().orEmpty()))
    }
}
