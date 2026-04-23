public class BinaryTree
{
    private Node root;

    public class Node
    {
        private int key;
        private Node left;
        private Node right;

        // getters and setters
        public int getKey() { return this.key; }
        public Node getLeft() { return this.left; }
        public Node getRight() { return this.right; }

        public void setKey(int key) { this.key = key; }
        public void setLeft(Node left) { this.left = left; }
        public void setRight(Node right) { this.right = right; }
    }

    // getters and setters
    public Node getRoot() { return this.root; }

    public void setRoot(Node root) { this.root = root; }

    /*
    PSEUDOCODE
    Recursive Java solution to determine minimum key in Binary Tree.
    This method is a method of BinaryTree class.
    Concept is, every node questions its children what is minimum key.
    Information accumulates from leafs towards root i.e., from down to up.
    Recursive helper function is utilized.
    */

    // to determine minimum key in binary tree
    public int determineMinimum()
    {
        return determineMinimumHelper(this.root);
    }

    private int determineMinimumHelper(Node node) // recursive helper function to utilize in recursive function
    {
        if(node == null) return Integer.MAX_VALUE;
        if(node.left == null && node.right == null) return node.key;

        int leftMinimum = determineMinimumHelper(node.left);
        int rightMinimum = determineMinimumHelper(node.right);

        return determineMinimumOf3(leftMinimum, rightMinimum, node.key);
    }

    private int determineMinimumOf3(int leftMinimum, int rightMinimum, int key)
    {
        int minimumKey = leftMinimum;

        if(rightMinimum < minimumKey) minimumKey = rightMinimum;
        if(key < minimumKey) minimumKey = key;

        return minimumKey;
    }

    /*
    PSEUDOCODE
    Recursive Java solution to determine total number of keys in Binary Tree.
    This method is a method of BinaryTree class.
    Concept is, every node questions its children what is total number of keys.
    Information accumulates from leafs towards root i.e., from down to up.
    Recursive helper function is utilized.
    */

    // to determine total number of keys in binary tree
    public int determineTotalNumber()
    {
        return determineTotalNumberHelper(this.root);
    }

    private int determineTotalNumberHelper(Node node)
    {
        if(node == null) return 0;

        int leftTotalNumber = determineTotalNumberHelper(node.left);
        int rightTotalNumber = determineTotalNumberHelper(node.right);

        return 1 + leftTotalNumber + rightTotalNumber;
    }

    /*
    PSEUDOCODE
    Recursive Java solution to determine total number of keys in Binary Tree.
    This method is a method of BinaryTree class.
    Concept is, every node questions its children about what is height.
    Information accumulates from leafs towards root i.e., from down to up.
    Recursive helper function is utilized.
    */

    // to determine height of binary tree
    public int determineHeight()
    {
        return determineHeightHelper(this.root);
    }

    private int determineHeightHelper(Node node)
    {
        if(node == null) return 0;

        int leftHeight = determineHeightHelper(node.left);
        int rightHeight = determineHeightHelper(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
    PSEUDOCODE
    Recursive Java solution to determine sum of keys in Binary Tree.
    This method is a method of BinaryTree class.
    Concept is, every node questions its children about what is sum of keys.
    Information accumulates from leafs towards root i.e., from down to up.
    Recursive helper function is utilized.
    */

    // to determine sum of keys in binary tree
    public int determineSum()
    {
        return determineSumHelper(this.root);
    }

    private int determineSumHelper(Node node)
    {
        if(node == null) return 0;

        int leftSum = determineSumHelper(node.left);
        int rightSum = determineSumHelper(node.right);

        return node.key + leftSum + rightSum;
    }
}
