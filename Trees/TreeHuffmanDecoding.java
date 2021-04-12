void decode(String s, Node root) {
    if(root == null) return;
    StringBuilder sb = new StringBuilder();
    Node node = root;
    for (int i = 0; i < s.length(); i++) {
        node = s.charAt(i) == '0' ? node.left : node.right;
        if (node.left == null && node.right == null) {
            sb.append(node.data);
            node = root;
        }
    }
    System.out.print(sb);
}
