fun insertNodeAtPosition(head: SinglyLinkedListNode?, data: Int, position: Int): SinglyLinkedListNode? {
    val node = SinglyLinkedListNode(data)
    if (position == 0) {
        node.next = head
        return node
    }
    return head?.apply {
        next = insertNodeAtPosition(next, data, position - 1)
    }
}
