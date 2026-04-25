package Tree.BinartyTree;

import java.util.*;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorOfBT {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return root;

        Map<TreeNode, TreeNode> parent = new HashMap<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        parent.put(root, null);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode currentNode = stack.pop();

            if (currentNode.left != null) {
                parent.put(currentNode.left, currentNode);
                stack.push(currentNode.left);
            }

            if (currentNode.right != null) {
                parent.put(currentNode.right, currentNode);
                stack.push(currentNode.right);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();

        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBT solver = new LowestCommonAncestorOfBT();

        // Constructing the tree: [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Define nodes to search for
        TreeNode p = root.left;       // Node 5
        TreeNode q = root.left.right.right; // Node 4

        System.out.println("Tree Structure:");
        printTree(root, "", true);

        System.out.println("\nFinding LCA for Node " + p.val + " and Node " + q.val + "...");
        TreeNode lca = solver.lowestCommonAncestor(root, p, q);

        System.out.println("Result: The LCA is Node " + lca.val);
    }

    // Pretty print helper method
    public static void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.val);
            printTree(node.left, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
