package trees;

import java.util.Scanner;

public class BinaryTree {

    /*
     class Node 
     int data;
     Node left;
     Node right;
     */
    static Node root; // head of list 

    /* Linked list Node*/
    static private class Node {

        int data;
        Node left;
        Node right;

        Node(int element) {// constructor
            data = element;
            left = null;
            right = null;
        }
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (leftHeight > rightHeight) {
            return 1 + height(node.left);
        }

        return 1 + height(node.right);
    }

    public static boolean checkBST(Node node) {
        if (node == null) {
            return true;
        }
        if (node.left != null && node.left.data > node.data) {
            return false;
        }
        if (node.right != null && node.right.data < node.data) {
            return false;
        }
        return checkBST(node.left) && checkBST(node.right);
    }

    public static Node findLCA(Node node, int first, int second) {
        if (node == null) {
            return null;
        }
        
        Node lastNode = null;
        // treat each node like a root of a tree

        if (find(node, first) != null && find(node, second) != null) {

            lastNode = node;

            // check left node
            Node foundLeft = findLCA(node.left, first, second);
            if (foundLeft != null) {
                return foundLeft;

            }
            // check right node
            Node foundRight = findLCA(node.right, first, second);
            if (foundRight != null) {
                return foundRight;
                
            }

        }

        return lastNode;
    }

    public static Node find(Node node, int data) {
        if (node == null) {

            return null;
        }

        if (data == node.data) {
            return node;
        }

        Node foundLeft = find(node.left, data);
        if (foundLeft != null) {
            return foundLeft;
        }

        Node foundRight = find(node.right, data);
        if (foundRight != null) {
            return foundRight;
        }

        return null;
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    /**
     * The main class
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        boolean isBST = checkBST(root);
        System.out.println(isBST);
        Node node = findLCA(root, 15, 11);
        System.out.println(node.data);
    }
}
