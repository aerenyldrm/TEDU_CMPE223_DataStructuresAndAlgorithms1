package HOA8;

class Node
{
    int data;
    Node left;
    Node right;
}

class BinarySearchTree
{
    Node root;
}

public class ib
{
    /*
    CONCEPT
    Balance information is gathered from bottom to top.
    A helper function returning integer gets things easy.
    Otherwise, quite complicated solution.
    */

    public boolean isBalanced(Node root)
    {
        if(root == null) return false;
        if(determineHeightHelper(root) == -1) return false;

        return true;
    }

    private int determineHeightHelper(Node node)
    {
        if(node == null) return 0;

        int leftHeight = determineHeightHelper(node.left);
        int rightHeight = determineHeightHelper(node.right);

        if(leftHeight == -1 || rightHeight == -1) return -1;

        if(Math.abs(leftHeight - rightHeight) > 1) return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
