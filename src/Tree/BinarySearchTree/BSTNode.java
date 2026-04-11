package Tree.BinarySearchTree;

public class BSTNode {

    int data;
    BSTNode left;
    BSTNode right;

    BSTNode() {
        data = 0;
        left = null;
        right = null;
    }

    BSTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    BSTNode(int data, BSTNode left, BSTNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
