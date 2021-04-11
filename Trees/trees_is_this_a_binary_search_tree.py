def checkBST(root, left = -float('inf'), right = float('inf')):
    return root is None or (left < root.data and root.data < right and checkBST(root.left, left, root.data) and checkBST(root.right, root.data, right))
