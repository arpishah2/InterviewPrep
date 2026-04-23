package Tree.BinartyTree;

public class TreeNode {

    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
        data = 0;
        left = null;
        right = null;
    }

    public TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
