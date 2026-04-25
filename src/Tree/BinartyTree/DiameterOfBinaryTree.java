package Tree.BinartyTree;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 * <p>
 * Input: root = [1,2]
 * Output: 1
 *
 */
public class DiameterOfBinaryTree {

    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;

    }

    public int calculateHeight(TreeNode currNode) {
        if (currNode == null) return 0;

        int leftHeight = calculateHeight(currNode.left);
        int rightHeight = calculateHeight(currNode.right);
        int height = Math.max(leftHeight, rightHeight) + 1;

        // Update the global maximum diameter
        // (sum of left and right heights represents the path through this node)
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return height;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree solver = new DiameterOfBinaryTree();

        // --- Example 1: [1,2,3,4,5] ---
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        System.out.println("Tree Example 1:");
        printTree(root1, "", true);
        System.out.println("Diameter: " + solver.diameterOfBinaryTree(root1)); // Output: 3

        System.out.println("\n----------------------------\n");

        // --- Example 2: [1,2] ---
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        System.out.println("Tree Example 2:");
        printTree(root2, "", true);
        System.out.println("Diameter: " + solver.diameterOfBinaryTree(root2)); // Output: 1
    }

    // Pretty print helper method to visualize tree structure
    public static void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
