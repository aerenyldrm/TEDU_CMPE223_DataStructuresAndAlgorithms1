public class HOA6
{
    public static void main(String[] args)
    {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.setRoot(binaryTree.new Node());
        binaryTree.getRoot().setKey(60);
        
        binaryTree.getRoot().setLeft(binaryTree.new Node());
        binaryTree.getRoot().getLeft().setKey(20);

        binaryTree.getRoot().getLeft().setLeft(binaryTree.new Node());
        binaryTree.getRoot().getLeft().getLeft().setKey(10);

        binaryTree.getRoot().getLeft().setRight(binaryTree.new Node());
        binaryTree.getRoot().getLeft().getRight().setKey(40);

        binaryTree.getRoot().getLeft().getRight().setLeft(binaryTree.new Node());
        binaryTree.getRoot().getLeft().getRight().getLeft().setKey(30);

        binaryTree.getRoot().getLeft().getRight().setRight(binaryTree.new Node());
        binaryTree.getRoot().getLeft().getRight().getRight().setKey(50);

        binaryTree.getRoot().setRight(binaryTree.new Node());
        binaryTree.getRoot().getRight().setKey(70);

        System.out.println(binaryTree.determineMinimum());
        System.out.println(binaryTree.determineTotalNumber());
        System.out.println(binaryTree.determineHeight());
        System.out.println(binaryTree.determineSum());
    }
}
