import kotlin.math.ceil

fun minTime(machines: Iterable<Long>, goal: Long): Long {
    val sortedMachines = machines.sorted()
    val goalPerMachine = ceil(goal.toDouble() / sortedMachines.size).toLong()
    var minDays = goalPerMachine * sortedMachines.first()
    var maxDays = goalPerMachine * sortedMachines.last()
    var result = 0L
    while (minDays <= maxDays) {
        val days = (minDays + maxDays) / 2
        if (sortedMachines.map { days / it }.sum() < goal) {
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
    val machines = readLine().orEmpty().split(' ').map(String::toLong)
    println(minTime(machines, goal))
}
