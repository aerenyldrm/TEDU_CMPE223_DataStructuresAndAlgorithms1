class Node
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

class BST
{
    private Node root;

    // getters and setters
    public Node getRoot() { return this.root; }

    public void setRoot(Node root) { this.root = root; }
}

class Solution
{
    /*
    PSEUDOCODE
    Recursive helper function is utilized.
    If node is null or between key of p and q (as this is a BST), then return node.
    Apply this recursive helper function to left and right.
    If left or right is null, return other.
    So, it does not matter if both are null, returns null anyway.
    But if common ancestor is detected on 1 hand and null on other, returns common ancestor.
    Finally, common ancestor is returned as result. 
    */

    public Node lowestCommonAncestor(Node root, Node p, Node q)
    {
        return lowestCommonAncestorHelper(root, p, q);
    }

    public Node lowestCommonAncestorHelper(Node node, Node p, Node q)
    {
        if(node == null || (node.getKey() > p.getKey() && node.getKey() < q.getKey())) return node; // base case

        // recursive calls
        Node left = lowestCommonAncestorHelper(node.getLeft(), p, q);
        Node right = lowestCommonAncestorHelper(node.getRight(), p, q);

        if(left == null) return right;
        
        return left;
    }
}

public class lcaBST
{
    public static void main(String[] args)
    {
        BST binarySearchTree = new BST();

        binarySearchTree.setRoot(new Node());
        binarySearchTree.getRoot().setKey(6);
        
        binarySearchTree.getRoot().setLeft(new Node());
        binarySearchTree.getRoot().getLeft().setKey(2);

        binarySearchTree.getRoot().getLeft().setLeft(new Node());
        binarySearchTree.getRoot().getLeft().getLeft().setKey(0);

        binarySearchTree.getRoot().getLeft().setRight(new Node());
        binarySearchTree.getRoot().getLeft().getRight().setKey(4);

        binarySearchTree.getRoot().getLeft().getRight().setLeft(new Node());
        binarySearchTree.getRoot().getLeft().getRight().getLeft().setKey(3);

        binarySearchTree.getRoot().getLeft().getRight().setRight(new Node());
        binarySearchTree.getRoot().getLeft().getRight().getRight().setKey(5);

        binarySearchTree.getRoot().setRight(new Node());
        binarySearchTree.getRoot().getRight().setKey(8);

        binarySearchTree.getRoot().getRight().setLeft(new Node());
        binarySearchTree.getRoot().getRight().getLeft().setKey(7);

        binarySearchTree.getRoot().getRight().setRight(new Node());
        binarySearchTree.getRoot().getRight().getRight().setKey(9);

        Solution solution = new Solution();

        Node p = binarySearchTree.getRoot().getLeft();
        Node q = binarySearchTree.getRoot().getRight();

        System.out.println(solution.lowestCommonAncestor(binarySearchTree.getRoot(), p, q).getKey());

        p = binarySearchTree.getRoot().getLeft().getRight().getLeft();
        q = binarySearchTree.getRoot().getRight().getRight();

        System.out.println(solution.lowestCommonAncestor(binarySearchTree.getRoot(), p, q).getKey());

        p = binarySearchTree.getRoot().getLeft().getRight().getLeft();
        q = binarySearchTree.getRoot().getLeft().getRight().getRight();

        System.out.println(solution.lowestCommonAncestor(binarySearchTree.getRoot(), p, q).getKey());
    }
}
