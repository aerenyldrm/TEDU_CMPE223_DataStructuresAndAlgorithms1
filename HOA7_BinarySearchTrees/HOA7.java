public class HOA7
{
    public static void main(String[] args)
    {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.setRoot(binarySearchTree.new Node());
        binarySearchTree.getRoot().setKey(60);
        
        binarySearchTree.getRoot().setLeft(binarySearchTree.new Node());
        binarySearchTree.getRoot().getLeft().setKey(20);

        binarySearchTree.getRoot().getLeft().setLeft(binarySearchTree.new Node());
        binarySearchTree.getRoot().getLeft().getLeft().setKey(10);

        binarySearchTree.getRoot().getLeft().setRight(binarySearchTree.new Node());
        binarySearchTree.getRoot().getLeft().getRight().setKey(40);

        binarySearchTree.getRoot().getLeft().getRight().setLeft(binarySearchTree.new Node());
        binarySearchTree.getRoot().getLeft().getRight().getLeft().setKey(30);

        binarySearchTree.getRoot().getLeft().getRight().setRight(binarySearchTree.new Node());
        binarySearchTree.getRoot().getLeft().getRight().getRight().setKey(50);

        binarySearchTree.getRoot().setRight(binarySearchTree.new Node());
        binarySearchTree.getRoot().getRight().setKey(70);

        System.out.println(binarySearchTree.determineMinimum());
        binarySearchTree.printAscending();
        System.out.println(binarySearchTree.determineHeight());
    }
}
