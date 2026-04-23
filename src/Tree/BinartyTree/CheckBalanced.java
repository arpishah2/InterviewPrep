package Tree.BinartyTree;//Check if given Tree.BinartyTree.BT is balanced
// A BTree is balanced when height of each 2 subtress differ at most by 1


public class CheckBalanced {

    public static void main(String args[]) {
        BT bt = new BT();
        bt.createSampleTree();
        TreeNode root = bt.root; //contains root of sample Binary Tree.Tree
        CheckBalanced chk = new CheckBalanced();
        System.out.println(chk.isBalanced(root));

    }

    public int height(TreeNode root) { //returns height of a node in tree
        if (root == null)
            return 0;
        else {
            return Math.max(height(root.left), height(root.right)) + 1;
            //Recursively calculate height of left and right subtrees of a node
            //and return max of the heights of two children plus 1
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;
        return false;
    }
}
