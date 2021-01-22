fun checkMagazine(magazine: Collection<String>, note: Collection<String>) {
    val magazineWords = magazine.groupingBy { it }.eachCount()
    note.groupingBy { it }.eachCount().forEach {
        if (magazineWords.getOrDefault(it.key, 0) < it.value) {
            println("No")
            return
        }
    }
    print("Yes")
}

fun main() {
    readLine() // Read and discard
    checkMagazine(readLine().orEmpty().split(' '), readLine().orEmpty().split(' '))
}
