/** 
 * I use Java for this problem because the unmodifiable code in the editor for Kotlin is incorrect.
 * It is expected to print each answer for each test case on a separate line, but it prints all the answers on the same line.
 */
static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
    DoublyLinkedListNode node = new DoublyLinkedListNode(data);
    if (head == null) return node;
    if (data <= head.data) {
        node.next = head;
        head.prev = node;
        return node;
    }
    DoublyLinkedListNode tail = sortedInsert(head.next, data);
    head.next = tail;
    tail.prev = head;
    return head;
}
