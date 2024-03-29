fun insertNodeAtPosition(head: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode = if (head == null || position == 0) {
    SinglyLinkedListNode(data).apply {
        next = head
    }
} else {
    head.apply {
        next = insertNodeAtPosition(next, data, position - 1)
    }
}
