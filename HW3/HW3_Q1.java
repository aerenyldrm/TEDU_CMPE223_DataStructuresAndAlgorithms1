// Title: Homework 3 Question 1
// Author: Arif Eren YILDIRIM
// ID: 10001521
// Section: 2
// Assignment: 3

import java.util.Scanner;

class BinaryTree
{
    public Node root;

    public class Node
    {
        public String name;
        public int age;
        public Node left;
        public Node right;

        public Node(String name, int age)
        {
            this.name = name;
            this.age = age;
        }
    }

    /*
    CONCEPT
    Birth is exactly similar to insert a node to binary search tree.
    Actually, it is even easier as notica that it is provided in homework that individuals are provided in order from oldest to youngest.
    Recursive helper function is utilized.
    */

    public void birth(String name, int age)
    {
        this.root = birthHelper(this.root, name, age);
    }

    private Node birthHelper(Node node, String name, int age)
    {
        if(node == null) return new Node(name, age);

        if(age < node.age) node.left = birthHelper(node.left, name, age);
        else if(age > node.age) node.right = birthHelper(node.right, name, age);

        return node;
    }

    /*
    CONCEPT
    Married resembles determining lowest common ancestor in binary tree conceptually.
    If name is detected in existing tree for marriage, return its parent.
    And carry this information from down to top until arriving to root.
    This is fundamental to understand, rest is just noticing special cases and constraints.
    Recursive helper function is utilized.
    */

    public void married(String name, String otherName, int otherAge)
    {
        Node commonAncestorOrSpouse = marriedHelper(this.root, name, otherName, otherAge);

        Node spouse = new Node(otherName, otherAge);

        if(commonAncestorOrSpouse.name.equals(this.root.name)) // This is for special case.
        {
            if(commonAncestorOrSpouse.right == null) commonAncestorOrSpouse.right = spouse;
            else throw new IllegalStateException("This is Illegal State Exception!");
        }
        else
        {
            if(commonAncestorOrSpouse.left == null) commonAncestorOrSpouse.left = spouse;
            else if(commonAncestorOrSpouse.right == null) commonAncestorOrSpouse.right = spouse;
            else throw new IllegalStateException("This is Illegal State Exception!");
        }
    }

    private Node marriedHelper(Node node, String name, String otherName, int otherAge)
    {
        if(node == null || node.name.equals(name)) return node;
        
        Node left = marriedHelper(node.left, name, otherName, otherAge);
        Node right = marriedHelper(node.right, name, otherName, otherAge);

        if(left != null && left.name.equals(name)) return node;
        if(right != null && right.name.equals(name)) return node;

        if(left == null) return right;

        return left;
    }

    /*
    CONCEPT
    This is required for correct formatting.
    It is about inorder traverse tree and print.
    Recursive helper function is utilized.
    */

    public void printInorder()
    {
        printInorderHelper(this.root);
    }

    private void printInorderHelper(Node node)
    {
        if(node == null) return;

        printInorderHelper(node.left);

        System.out.print(node.name + " ");

        printInorderHelper(node.right);
    }
}

public class HW3_Q1
{
    public static void main(String[] args)
    {
        BinaryTree binaryTree = new BinaryTree();

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        scanner.nextLine(); // To tackle with nextInt(), which is about Java.

        for(int i = 0; i < n; i++)
        {
            String line = scanner.nextLine();
            String[] stringArray = line.split(" ");

            if(stringArray[0].equals("BIRTH"))
            {
                int age = Integer.parseInt(stringArray[2]);
                
                binaryTree.birth(stringArray[1], age);
            }

            else if(stringArray[0].equals("MARRIED"))
            {
                int otherAge = Integer.parseInt(stringArray[3]);

                binaryTree.married(stringArray[1], stringArray[2], otherAge);
            }
        }

        binaryTree.printInorder();

        scanner.close();
    }
}
