package Tree.BinartyTree;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * <p>
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 */
public class MaxDepthOfBinaryTree {


    private Queue<AbstractMap.SimpleEntry<TreeNode, Integer>> nextItems = new LinkedList<>();
    private int maxDepth = 0;

    /**
     * Time complexity: we visit each node exactly once, thus the time complexity is O(N), where N is the number of nodes.
     * Space complexity: in the worst case, the tree is completely unbalanced, e.g. each node has only left child node, the recursion call would occur N times (the height of the tree), therefore the storage to keep the call stack would be O(N)
     */
    public int maxDepthRecursion(TreeNode root) {
        if (root == null) return 0;
        int maxDepthLeft = maxDepthRecursion(root.left);
        int maxDepthRight = maxDepthRecursion(root.right);
        return 1 + Math.max(maxDepthLeft, maxDepthRight);
    }


    /**
     * Calculates the maximum depth of a binary tree using a Breadth-First Search (BFS)
     * approach implemented via tail recursion.
     *
     * <h3>🌳 SAMPLE TREE STRUCTURE</h3>
     * <pre>
     *       (5)        <-- Level 1 (maxDepth: 1)
     *      /   \
     *   (10)   (15)    <-- Level 2 (maxDepth: 2)
     *   /
     * (20)             <-- Level 3 (maxDepth: 3)
     * </pre>
     *
     * <h3>📊 STEP-BY-STEP EXECUTION TRACE</h3>
     * <pre>
     * +------+--------------+-----------+----------+----------------------------+
     * | Step | Current Node | nextLevel | maxDepth | Queue State (nextItems)    |
     * +------+--------------+-----------+----------+----------------------------+
     * |  0   | START        |    -      |    0     | [(Node 5, 0)]              |
     * |  1   | Node 5       |    1      |    1     | [(Node 10, 1), (Node 15, 1)]|
     * |  2   | Node 10      |    2      |    2     | [(Node 15, 1), (Node 20, 2)]|
     * |  3   | Node 15      |    2      |    2     | [(Node 20, 2)]             |
     * |  4   | Node 20      |    3      |    3     | [] (Empty)                 |
     * |  5   | FINISH       |    -      |    3     | Returns Final Result       |
     * +------+--------------+-----------+----------+----------------------------+
     * </pre>
     * <p>
     * Time complexity: O(N), still we visit each node once and only once.
     * <p>
     * Space complexity: O(2^(log2N−1))=O(N/2)=O(N), i.e. the maximum number of nodes at the same level (the number of leaf nodes in a full binary tree), since we traverse the tree in the BFS manner.
     */
    public int maxDepthTailRecursionWithBFS(TreeNode root) {
        if (root == null) return 0;

        nextItems.clear();
        maxDepth = 0;

        nextItems.add(new AbstractMap.SimpleEntry<>(root, 0));

        return nextMaxDepth();
    }

    private int nextMaxDepth() {
        if (nextItems.isEmpty()) {
            return maxDepth;
        }

        AbstractMap.SimpleEntry<TreeNode, Integer> nextItem = nextItems.poll();

        TreeNode nextNode = nextItem.getKey();
        int nextLevel = nextItem.getValue() + 1;

        maxDepth = Math.max(maxDepth, nextLevel);

        if (nextNode.left != null) {
            AbstractMap.SimpleEntry<TreeNode, Integer> leftNodeWithLevel = new AbstractMap.SimpleEntry<>(nextNode.left, nextLevel);
            nextItems.add(leftNodeWithLevel);
        }

        if (nextNode.right != null) {
            AbstractMap.SimpleEntry<TreeNode, Integer> rightNodeWithLevel = new AbstractMap.SimpleEntry<>(nextNode.right, nextLevel);
            nextItems.add(rightNodeWithLevel);
        }

        return nextMaxDepth();
    }

    /**
     * Pretty prints the tree structure visually
     */
    public static void displayTree(TreeNode root, int level) {
        if (root == null) {
            for (int i = 0; i < level; i++) System.out.print("\t");
            System.out.println("null");
            return;
        }
        displayTree(root.right, level + 1);
        for (int i = 0; i < level; i++) System.out.print("\t");
        System.out.println(root.val);
        displayTree(root.left, level + 1);
    }

    public static void main(String[] args) {
        MaxDepthOfBinaryTree sol = new MaxDepthOfBinaryTree();

        // TC 1: Standard Tree
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(10);
        root1.right = new TreeNode(15);
        root1.left.left = new TreeNode(20);

        // TC 2: Skewed Tree
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);

        System.out.println("=== TEST CASE 1: STANDARD TREE ===");
        displayTree(root1, 0);
        System.out.println("Result Recursion: " + sol.maxDepthRecursion(root1));
        System.out.println("Result BFS-Tail : " + sol.maxDepthTailRecursionWithBFS(root1));
        System.out.println();

        System.out.println("=== TEST CASE 2: SKEWED TREE ===");
        displayTree(root2, 0);
        System.out.println("Result Recursion: " + sol.maxDepthRecursion(root2));
        System.out.println("Result BFS-Tail : " + sol.maxDepthTailRecursionWithBFS(root2));
    }

}
