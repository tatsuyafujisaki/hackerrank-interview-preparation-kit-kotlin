fun checkMagazine(magazine: List<String>, note: List<String>) {
    val magazineWordOccurrences = magazine.groupingBy { it }.eachCount()
    note.groupingBy { it }.eachCount().forEach {
        if (magazineWordOccurrences.getOrDefault(it.key, 0) < it.value) {
            println("No")
            return
        }
    }
    print("Yes")
}

fun main() {
    readLine() // Read and discard
    val magazine = readLine().orEmpty().split(' ')
    val note = readLine().orEmpty().split(' ')
    checkMagazine(magazine, note)
}
