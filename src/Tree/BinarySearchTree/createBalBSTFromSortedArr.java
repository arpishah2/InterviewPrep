package Tree.BinarySearchTree;/*
 * Problem: Given a sorted array. Write a function that creates a Balanced Binary Search Tree.Tree using array elements.
 * 
 * Input:  Array {1, 2, 3}
 * Output: A Balanced Tree.BinarySearchTree.BST
     2
   /  \
  1    3 
* Input: Array {1, 2, 3, 4}
* Output: A Balanced Tree.BinarySearchTree.BST
      3
    /  \
   2    4
 /
1
 */

public class createBalBSTFromSortedArr {


    public static void main(String args[]) {
        createBalBSTFromSortedArr obj = new createBalBSTFromSortedArr();
        int[] input = {1, 2, 3, 4, 7, 8, 12};
        BSTNode root = obj.createBST(input, 0, input.length - 1);
        BST b = new BST();
        b.root = root;
        b.printBST(root, 0);

    }

    public BSTNode createBST(int[] input, int start, int end) {
        if (start > end)
            return null;
        else {
            int midIndex = (start + end) / 2;
            BSTNode root = new BSTNode(input[midIndex]);
            root.left = createBST(input, start, midIndex - 1);
            root.right = createBST(input, midIndex + 1, end);
            return root;
        }
    }

}
