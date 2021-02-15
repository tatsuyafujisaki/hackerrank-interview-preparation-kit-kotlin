import java.util.Stack

class Queue<T> {
    private val inbox = Stack<T>()
    private val outbox = Stack<T>()

    fun enqueue(item: T) {
        inbox.push(item)
    }

    fun dequeue(): T {
        prepare()
        return outbox.pop()
    }

    fun peek(): T {
        prepare()
        return outbox.peek()
    }

    private fun prepare() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) outbox.push(inbox.pop())
        }
    }
}

fun main() {
    val queue = Queue<Int>()
    repeat(readLine().orEmpty().toInt()) {
        val query = readLine().orEmpty().split(' ').map(String::toInt)
        when (query[0]) {
            1 -> queue.enqueue(query[1])
            2 -> queue.dequeue()
            else -> println(queue.peek())
        }
    }
}
