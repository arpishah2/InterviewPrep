package Tree.BinartyTree;

import java.util.ArrayDeque;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * <p>
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
public class SameTree {

    private static boolean validateNodes(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return true;
    }

    public static void main(String[] args) {
        SameTree sol = new SameTree();

        // TC1: Identical
        TreeNode p1 = new TreeNode(10);
        p1.left = new TreeNode(5);
        p1.right = new TreeNode(15);
        TreeNode q1 = new TreeNode(10);
        q1.left = new TreeNode(5);
        q1.right = new TreeNode(15);

        // TC2: Structural Mismatch (Mirror shapes)
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        // TC3: Value Mismatch
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        // Header
        System.out.println(String.format("%-25s | %-18s | %-18s | %-10s | %-10s",
                "TEST CASE", "TREE P", "TREE Q", "RECURSE", "ITERATE"));
        System.out.println("-".repeat(95));

        runTest("TC1: Identical", p1, q1, sol, true);
        runTest("TC2: Structure Fail", p2, q2, sol, false);
        runTest("TC3: Value Fail", p3, q3, sol, false);
        runTest("TC4: Empty Roots", null, null, sol, true);
    }

    private static void runTest(String name, TreeNode p, TreeNode q, SameTree sol, boolean expected) {
        boolean resRec = sol.isSameTreeRecursion(p, q);
        boolean resIter = sol.isSameTreeIteration(p, q);

        // A test passes if both algorithms match the expected boolean result
        boolean testPassed = (resRec == expected) && (resIter == expected);

        System.out.println(String.format("%-22s | %-16s | %-16s | %-10s | %-10s | %-10s",
                name,
                treeToString(p),
                treeToString(q),
                resRec ? "MATCH ✅" : "DIFF ❌",
                resIter ? "MATCH ✅" : "DIFF ❌",
                testPassed ? "PASS 🏆" : "FAIL ⚠️"));
    }

    private static String treeToString(TreeNode root) {
        if (root == null) return "null";
        String l = (root.left != null) ? String.valueOf(root.left.val) : "n";
        String r = (root.right != null) ? String.valueOf(root.right.val) : "n";
        return "[" + root.val + "," + l + "," + r + "]";
    }

    /**
     * Recursively checks if two binary trees are identical in structure and values.
     *
     * <h3>🌳 MATCHED EXAMPLE TRACE (Tree P: [10, 5, 15] | Tree Q: [10, 5, 15])</h3>
     * <pre>
     * | Step | Node P | Node Q | Action / Logic Check                | Result          |xzl
     * |------|--------|--------|-------------------------------------|-----------------|
     * | 1    | 10     | 10     | Root Match (10 == 10)               | Proceed Left    |
     * | 2    | 5      | 5      | Left Child Match (5 == 5)           | Proceed Children|
     * | 3    | null   | null   | Base Case: Both Null                | True ✅         |
     * | 4    | null   | null   | Base Case: Both Null                | True ✅         |
     * | 5    | (5)    | (5)    | Combine Results (True && True)      | Subtree Match ✅ |
     * | 6    | 15     | 15     | Right Child Match (15 == 15)        | Proceed Children|
     * | 7    | null   | null   | Base Case: Both Null                | True ✅         |
     * | 8    | null   | null   | Base Case: Both Null                | True ✅         |
     * | 9    | (15)   | (15)   | Combine Results (True && True)      | Subtree Match ✅ |
     * | 10   | [ROOT] | [ROOT] | Final: Left(True) && Right(True)    | IDENTICAL 🏆    |
     * </pre>
     *
     * <h3>📍 LOGIC SUMMARY</h3>
     * <ul>
     *   <li><b>Both Null:</b> Success! Reached the same leaf end point.</li>
     *   <li><b>One Null:</b> Failure! Structural mismatch (one tree is larger).</li>
     *   <li><b>Data Mismatch:</b> Failure! Same shape but different values.</li>
     * </ul>
     *
     * @param p Root of the first tree
     * @param q Root of the second tree
     * @return true if trees are identical, false otherwise
     * <p>
     * Time complexity : O(N),
     * Space complexity : O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.
     */
    public boolean isSameTreeRecursion(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTreeRecursion(p.left, q.left) && isSameTreeRecursion(p.right, q.right);
    }

    /**
     * Checks if two binary trees are identical using an iterative Breadth-First Search (BFS) approach.
     * This method uses a Queue (ArrayDeque) to compare nodes level by level.
     *
     * <h3>🔄 ITERATIVE MATCH TRACE (Level-Order)</h3>
     * <pre>
     * | Step | Node P | Node Q | Action / Queue Logic                | Result          |
     * |------|--------|--------|-------------------------------------|-----------------|
     * | 1    | 10     | 10     | Roots match. Add children to Deque. | Proceed ✅      |
     * | 2    | 5      | 5      | Pop (5,5). Match. Children are null.| Proceed ✅      |
     * | 3    | 15     | 15     | Pop (15,15). Match. Add nulls check.| Proceed ✅      |
     * | 4    | null   | null   | validateNodes(null, null)           | True ✅         |
     * | 5    | [DONE] | [DONE] | Deque is empty.                     | IDENTICAL 🏆    |
     * </pre>
     *
     * <h3>📍 IMPLEMENTATION DETAILS</h3>
     * <ul>
     *   <li><b>ArrayDeque:</b> Used as a Queue. <i>Note: Does not allow null elements.</i></li>
     *   <li><b>validateNodes:</b> Helper method to check both structural and value equality.</li>
     *   <li><b>Guard:</b> <code>if (p.left != null)</code> ensures we only add non-null nodes to the Deque.</li>
     * </ul>
     *
     * @param p Root of the first tree
     * @param q Root of the second tree
     * @return true if trees are identical, false otherwise
     * @complexity Time: O(N) where N is the number of nodes.
     * @complexity Space: O(W) where W is the maximum width of the tree.
     */
    public boolean isSameTreeIteration(TreeNode p, TreeNode q) {

        if (!validateNodes(p, q)) return false;
        if (p == null) return true;

        ArrayDeque<TreeNode> pDeque = new ArrayDeque<>();
        pDeque.addLast(p);

        ArrayDeque<TreeNode> qDeque = new ArrayDeque<>();
        qDeque.addLast(q);

        while (!pDeque.isEmpty()) {

            p = pDeque.removeFirst();
            q = qDeque.removeFirst();

            if (!validateNodes(p, q)) return false;

            // 1. Check Left Children
            if (!validateNodes(p.left, q.left)) return false;
            if (p.left != null) {
                pDeque.addLast(p.left);
                qDeque.addLast(q.left);
            }

            // 2. Check Right Children
            if (!validateNodes(p.right, q.right)) return false;
            if (p.right != null) {
                pDeque.addLast(p.right);
                qDeque.addLast(q.right);
            }
        }
        return true;
    }
}
