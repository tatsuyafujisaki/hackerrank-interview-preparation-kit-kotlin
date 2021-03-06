fun jumpingOnClouds(clouds: List<Int>): Int {
    var jumps = 0
    var i = 0
    while (i < clouds.lastIndex) {
        i += if (i + 2 < clouds.size && clouds[i + 2] == 0) 2 else 1
        jumps++
    }
    return jumps
}

fun main() {
    readLine() // Read and discard
    println(jumpingOnClouds(readLine().orEmpty().split(' ').map(String::toInt)))
}
