package Tree.BinartyTree;

public class BTNode {

    int data;
    BTNode left;
    BTNode right;

    public BTNode() {
        data = 0;
        left = null;
        right = null;
    }

    public BTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public BTNode(int data, BTNode left, BTNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
