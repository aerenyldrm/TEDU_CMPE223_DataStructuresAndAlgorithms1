// Title: Homework 3 Question 2
// Author: Arif Eren YILDIRIM
// ID: 10001521
// Section: 2
// Assignment: 3

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/*
CONCEPT
First child next sibling approach.
*/

class Node
{
    public String name;
    public int salary;
    public Node firstChild;
    public Node nextSibling;

    public Node(String name, int salary)
    {
        this.name = name;
        this.salary = salary;
    }
}

/*
CONCEPT
Class for n-ary tree.
*/

class NTree
{
    public Node head;

    /*
    CONCEPT
    This is for constructing tree from text file.
    */

    public void constructFromFile(String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;

        boolean firstLine = true;
 
        while((line = reader.readLine()) != null)
        {
            line = line.trim();

            if(line.isEmpty()) continue;
 
            String[] parts = line.split(",");
            String[] parentParts = parts[0].trim().split(" ");
            String[] childParts = parts[1].trim().split(" ");
 
            String parentName = parentParts[0];
            int parentSalary = Integer.parseInt(parentParts[1]);

            String childName = childParts[0];
            int childSalary = Integer.parseInt(childParts[1]);
 
            if(firstLine)
            {
                this.head = new Node(parentName, parentSalary);

                firstLine = false;
            }
 
            Node parentNode = this.search(parentName);
            if(parentNode == null) parentNode = new Node(parentName, parentSalary);
 
            Node childNode = this.search(childName);
            if(childNode == null) childNode = new Node(childName, childSalary);
 
            this.insert(parentNode, childNode);
        }
 
        reader.close();
    }

    /*
    CONCEPT
    This method of insert(Node parent, Node child) is required to create n-ary tree from input.
    During insertion, n-ary tree is structured via first-child-next-sibling approach.
    So, created n-ary tree is in form of similar to binary tree.
    Think of as such: instead of left child, there is first child; instead of right child, there is next sibling.
    Nevertheless, there is quite an important difference.
    Whereas node is ancestor of right in binary tree, node is not ancestor of next sibling in this structure.
    But similar to left child of node, first child is further a child.
    It is important to realize that for implementation of described functions in homework.
    In light of these, whereas recursive approach is required to tackle first child, iterative approach is suitable to tackle next sibling.
    */

    public void insert(Node parent, Node child)
    {
        if(parent.firstChild == null) parent.firstChild = child;
        else
        {
            Node current = parent.firstChild;

            while(current.nextSibling != null)
            {
                current = current.nextSibling;
            }

            current.nextSibling = child;
        }
    }

    /*
    CONCEPT
    Search method with recursive helper search method is a basis to tackle upcoming functions.
    It, for instance, tackles recursive part of printSubordinates.
    */

    public Node search(String name)
    {
        return this.searchHelper(this.head, name);
    }

    public Node searchHelper(Node node, String name)
    {
        if(node == null) return node;
        if(name.equals(node.name)) return node;

        Node firstChild = searchHelper(node.firstChild, name);
        Node nextSibling = searchHelper(node.nextSibling, name);

        if(firstChild == null) return nextSibling;

        return firstChild;
    }

    /*
    CONCEPT
    This is for PRINT SUBORDINATES.
    This method of printSubordinates/String name) utilizes Search(String name) method to tackle recursive part.
    Rest is tackle iterative part to iterate over siblings.
    This underscores, due to difference between binary tree and first-child-next-sibling approach, combination of recursive and iterative approach to solve problem.
    */

    public void printSubordinates(String name)
    {
        if(this.search(name) == null) System.out.println("Employee not found.");
        else
        {
            Node parent = this.search(name);
            
            if(parent.firstChild == null) System.out.println(parent.name + " has no subordinates.");
            else
            {
                System.out.print("Subordinates of " + name + ": ");

                Node currentSubordinate = parent.firstChild;

                while(currentSubordinate != null)
                {
                    if(currentSubordinate.nextSibling != null) System.out.print(currentSubordinate.name + ", ");
                    else System.out.print(currentSubordinate.name + "\n");

                    currentSubordinate = currentSubordinate.nextSibling;
                }
            }
        }
    }

    /*
    CONCEPT
    This lca implementation is exact example of fundamental concept of first-child-next-sibling approach.
    For this concept, key is think recursive for first child and think iterative for next sibling.
    This is exactly what this lca(String name, String otherName) in combination with lcaHelper(Node node, String name, String otherName) do.
    */

    public Node lca(String name, String otherName)
    {
        return this.lcaHelper(this.head, name, otherName);
    }

    public Node lcaHelper(Node node, String name, String otherName)
    {
        if(node == null) return node;
        if(node.name.equals(name) || node.name.equals(otherName)) return node;

        Node child = node.firstChild;

        int detectionCount = 0; Node finalDetection = null;

        while(child != null)
        {
            Node result = lcaHelper(child, name, otherName);

            if(result != null)
            {
                detectionCount++;

                finalDetection = result;
            }

            child = child.nextSibling;
        }
        
        if(detectionCount == 2)
        {
            return node;
        }

        return finalDetection;
    }

    /*
    CONCEPT
    This is for FIND COMMON MANAGER.
    This method highly depends on lca method that is explained in detail above.
    Furthermore, search method is utilized to tackle edge cases.
    */

    public void findCommmonManager(String name, String otherName)
    {
        Node aName = this.search(name);
        Node anotherName = this.search(otherName);

        if(aName == null || anotherName == null) System.out.println("Employee not found.");
        else
        {
            System.out.println("Common Manager: " + this.lca(name, otherName).name);
        }
    }

    /*
    CONCEPT
    This is for CALCULATE TOTAL SALARY
    Fundamental concept relevant to first-child-next-sibling approach is adopted again.
    Recursive approach for first child i.e., for moving lateral.
    Iterative approach for next sibling i.e., for moving vertical.
    */

    public void calculateTotalSalary(String name)
    {
        Node node = this.search(name);

        if(node == null) System.out.println("Employee not found");
        else
        {
            System.out.println("Total salary under " + name + ": " + this.calculateTotalSalaryHelper(node));
        }
    }

    public int calculateTotalSalaryHelper(Node node)
    {
        if(node == null) return 0;

        int total = node.salary;

        Node child = node.firstChild;

        while(child != null)
        {
            total += calculateTotalSalaryHelper(child);

            child = child.nextSibling;
        }

        return total;
    }

    /*
    CONCEPT
    This is for CHECK MANAGER.
    A is manager of B if A is an ancestor of B.
    Determine path from root to B, then decide if A is located on that path.
    */
 
    public void checkManager(String nameA, String nameB)
    {
        Node nodeA = this.search(nameA);
        Node nodeB = this.search(nameB);
 
        if(nodeA == null || nodeB == null)
        {
            System.out.println("Employee not found.");

            return;
        }
 
        Stack<Node> pathToB = this.findPath(nameB);
 
        boolean isManager = false;
 
        for(Node node : pathToB)
        {
            if(node.name.equals(nameA))
            {
                isManager = true;

                break;
            }
        }
 
        if(isManager) System.out.println(nameA + " is a manager of " + nameB + ".");
        else System.out.println(nameA + " is not a manager of " + nameB + ".");
    }

    /*
    CONCEPT
    This is for FIND PATH.
    findPath(String target) returns a stack containing path from root to target node.
    Root is at bottom of stack, target is at top.
    findPathHelper(Node current, String target, Stack<Node> path) is the recursive DFS that pushes on descent and pops on backtrack.
    Similar to overall concept, recursive for firstChild i.e., moving vertical, iterative for nextSibling i.e., moving lateral.
    */
 
    public Stack<Node> findPath(String target)
    {
        Stack<Node> path = new Stack<>();

        findPathHelper(this.head, target, path);

        return path;
    }
 
    public boolean findPathHelper(Node current, String target, Stack<Node> path)
    {
        if(current == null) return false;
 
        path.push(current);
 
        if(current.name.equals(target)) return true;

        Node child = current.firstChild;
 
        while(child != null)
        {
            if(findPathHelper(child, target, path)) return true;

            child = child.nextSibling;
        }
 
        path.pop();

        return false;
    }
 
    public void printPath(String name)
    {
        Node node = this.search(name);
 
        if(node == null)
        {
            System.out.println("Employee not found.");

            return;
        }
 
        Stack<Node> path = this.findPath(name);
 
        Node[] array = path.toArray(new Node[0]);
 
        StringBuilder sb = new StringBuilder("Path: ");
 
        for(int i = 0; i < array.length; i++)
        {
            sb.append(array[i].name);

            if(i < array.length - 1) sb.append(" -> ");
        }
 
        System.out.println(sb.toString());
    }
}

public class HW3_Q2
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        NTree nTree = new NTree();

        System.out.print("Enter filename: ");

        String filename = scanner.next();
 
        try
        {
            nTree.constructFromFile(filename);
        }
        catch(IOException e)
        {
            System.out.println("File not found: " + filename);
        }

        /*
        CONCEPT
        This is menu implementation.
        */
        System.out.println("Menu:");
        System.out.println("1. Print Subordinates");
        System.out.println("2. Find Common Manager");
        System.out.println("3. Calculate Total Salary");
        System.out.println("4. Check Manager");
        System.out.println("5. Find Path");
        System.out.println("6. Quit");

        boolean keepGoing = true;

        while(keepGoing)
        {
            System.out.println();

            System.out.print("Enter your choice: ");
            int selection = scanner.nextInt();
            System.out.println(selection);

            switch(selection)
            {
                case 1:
                    System.out.print("Enter employee name: ");
                    String employeeName = scanner.next();
                    System.out.println(employeeName);
 
                    nTree.printSubordinates(employeeName);

                    break;
                case 2:
                    System.out.print("Enter first employee name: ");
                    String firstName = scanner.next();
                    System.out.println(firstName);
 
                    System.out.print("Enter second employee name: ");
                    String secondName = scanner.next();
                    System.out.println(secondName);

                    nTree.findCommmonManager(firstName, secondName);

                    break;
                case 3:
                    System.out.print("Enter employee name: ");
                    String salaryName = scanner.next();
                    System.out.println(salaryName);
 
                    nTree.calculateTotalSalary(salaryName);
 
                    break;
                case 4:
                    System.out.print("Enter first employee name: ");
                    String managerName = scanner.next();
                    System.out.println(managerName);
 
                    System.out.print("Enter second employee name: ");
                    String subordinateName = scanner.next();
                    System.out.println(subordinateName);
 
                    nTree.checkManager(managerName, subordinateName);
 
                    break;
                case 5:
                    System.out.print("Enter employee name: ");
                    String pathName = scanner.next();
                    System.out.println(pathName);
 
                    nTree.printPath(pathName);
 
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    
                    keepGoing = false;
 
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }
}
