public class BinarySearchTree
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
    Recursive Java solution to minimum key in Binary Search Tree.
    This method is a method of BinarySearchTree class.
    Concept is, due to systematic organization of Binary Search Tree, as long as there is left, left is minimum.
    Recursive helper function is utilized.
    */

    public int determineMinimum()
    {
        if(this.root == null) throw new IllegalStateException("This is illegal state exception!");

        return determineMinimumHelper(this.root);
    }

    public int determineMinimumHelper(Node node)
    {
        if(node.left == null) return node.key;

        int minimum = determineMinimumHelper(node.left);

        return minimum;
    }

    /*
    PSEUDOCODE
    Concept is, due to systematic organization of Binary Search Tree, inorder traversal and print is exactly same to print in ascending order.
    */

    public void printAscending()
    {
        printAscendingHelper(this.root);
    }

    public void printAscendingHelper(Node node)
    {
        if(node == null) return;

        printAscendingHelper(node.left);

        System.out.println(node.key);

        printAscendingHelper(node.right);

        return;
    }

    /*
    PSEUDOCODE
    Recursive Java solution to determine height of Binary Search Tree.
    This method is a method of BinaryTree class.
    Concept is, every node questions its children about what is height.
    Information accumulates from leafs towards root i.e., from down to up.
    Recursive helper function is utilized.
    Similar to post-order logic.
    */

    public int determineHeight()
    {
        return determineHeightHelper(this.root);
    }

    public int determineHeightHelper(Node node)
    {
        if(node == null) return 0;

        int leftHeight = determineHeightHelper(node.left);
        int rightHeight = determineHeightHelper(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
    PSEUDOCODE
    Recursive Java solution to delete minimum key of Binary Search Tree.
    This method is a method of BinarySeacrhTree class.
    Recursive helper function is utilized.
    Recursive helper function returns Node whereas recursive function is returns none.
    */

    public void deleteMinimum()
    {
        if(this.root == null) throw new IllegalStateException("This is illegal state exception!");

        deleteMinimumHelper(this.root);

        return;
    }

    public Node deleteMinimumHelper(Node node)
    {
        if(node.left == null) return node.right;

        node.left = deleteMinimumHelper(node.left);

        return node;
    }
}
