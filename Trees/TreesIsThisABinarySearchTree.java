// Submit your solution in Python or C++ because the driver code for Java is broken.

boolean checkBST(Node root) {
    return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private boolean checkBST(Node node, int left, int right) {
    return node == null || left < node.data && node.data < right && checkBST(node.left, left, node.data) && checkBST(node.right, node.data, right);
}
