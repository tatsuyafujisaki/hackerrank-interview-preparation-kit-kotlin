import kotlin.math.ceil

fun minTime(machines: LongArray, goal: Long): Long {
    machines.sort()
    val goalPerMachine = ceil(goal.toDouble() / machines.size).toLong()
    var minDays = goalPerMachine * machines.first()
    var maxDays = goalPerMachine * machines.last()
    var result = 0L
    while (minDays <= maxDays) {
        val days = (minDays + maxDays) / 2
        if (machines.map { days / it }.sum() < goal) {
            minDays = days + 1
        } else {
            maxDays = days - 1
            result = days
        }
    }
    return result
}

fun main() {
    val goal = readLine().orEmpty().split(' ').map(String::toLong)[1]
    val machines = readLine().orEmpty().split(' ').map(String::toLong).toLongArray()
    println(minTime(machines, goal))
}
