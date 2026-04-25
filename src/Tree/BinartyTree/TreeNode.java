package Tree.BinartyTree;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
        val = 0;
        left = null;
        right = null;
    }

    public TreeNode(int data) {
        this.val = data;
        left = null;
        right = null;
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.val = data;
        this.left = left;
        this.right = right;
    }

}
