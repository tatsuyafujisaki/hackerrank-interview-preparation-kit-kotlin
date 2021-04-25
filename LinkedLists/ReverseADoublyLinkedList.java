/** 
 * I fall back to Java instead of Kotlin for this problem because the unmodifiable code in the editor for Kotlin is incorrect.
 * It is expected to print each answer for each test case on a separate line, but it prints all the answers on the same line.
 */
static DoublyLinkedListNode reverse(DoublyLinkedListNode node) {
    if (node == null) return null;
    DoublyLinkedListNode temp = node.next;
    node.next = node.prev;
    node.prev = temp;
    if (node.prev == null) return node;
    return reverse(node.prev);
}
