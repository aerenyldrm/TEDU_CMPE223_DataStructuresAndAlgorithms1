package HOA8;

public class HOA8
{
    public static void main(String[] args)
    {
        /*
        These are test cases for Q4.
        */

        int[] array1 = { 0, 1, 2, 3, 4, 5, 6 };
        int[] array2 = { 0, 1, 2, 10, 4, 5, 1 };

        int size1 = 7;
        int size2 = 7;

        System.out.println(imh.isMinimumHeap(array1, size1));
        System.out.println(imh.isMinimumHeap(array2, size2));

        /*
        These are test cases for Q5.
        */

        System.out.println(new ib().isBalanced(null) + " (expect false)!");

        Node single = new Node();
        single.data = 1;
        System.out.println(new ib().isBalanced(single) + " (expect true)!");

        Node root3 = new Node();
        root3.data = 2;
        root3.left = new Node(); root3.left.data = 1;
        root3.right = new Node(); root3.right.data = 3;
        System.out.println(new ib().isBalanced(root3) + " (expect true)!");

        Node root4 = new Node();
        root4.data = 1;
        root4.left = new Node(); root4.left.data = 2;
        root4.left.left = new Node(); root4.left.left.data = 3;
        System.out.println(new ib().isBalanced(root4) + " (expect false)!");

        Node root5 = new Node();
        root5.data = 1;
        root5.left = new Node(); root5.left.data = 2;
        root5.right = new Node(); root5.right.data = 3;
        root5.left.left = new Node(); root5.left.left.data = 4;
        System.out.println(new ib().isBalanced(root5) + " (expect true)!");
    }
}
