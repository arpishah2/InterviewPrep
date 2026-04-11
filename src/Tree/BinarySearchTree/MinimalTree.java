package Tree.BinarySearchTree;//Given a sorted array with unique integer elements, write an algorithm to create Tree.BinarySearchTree.BST with minimal height

public class MinimalTree {

    BSTNode root;

    public MinimalTree() {
        root = null;
    }

    public static void main(String[] args) {

        MinimalTree mt = new MinimalTree();
        int[] arr = {1, 5, 15, 20, 25, 30, 35, 80};
        mt.root = mt.createMinimalTree(arr, 0, arr.length - 1);
        BST b = new BST();
        b.printBST(mt.root, 0);

    }

    public BSTNode createMinimalTree(int[] arr, int start, int end) {

        if (start > end)
            return null;

        int midIndex = (start + end) / 2;
        int value = arr[midIndex];
        BSTNode insert = new BSTNode(value);

        insert.left = createMinimalTree(arr, start, midIndex - 1);
        insert.right = createMinimalTree(arr, midIndex + 1, end);
        return insert;
    }

}
