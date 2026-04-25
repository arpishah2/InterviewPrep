package Tree.BinartyTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 🌳 Binary Tree (BT) Implementation and Traversal
 * <p>
 * This class provides methods for creating a Binary Tree and traversing it using
 * Depth-First Search (DFS) and Breadth-First Search (BFS) strategies.
 *
 * <h3>📊 SAMPLE TREE STRUCTURE</h3>
 * <pre>
 *         5
 *       /   \
 *     10     15
 *    /  \   /  \
 *   20  25 30  35
 *  /
 * 40
 * </pre>
 *
 * <h3>🔄 TRAVERSAL COMPARISON TABLE</h3>
 * <table border="1" cellpadding="5" style="border-collapse: collapse;">
 *   <thead>
 *     <tr style="background-color: #f2f2f2;">
 *       <th>Type</th>
 *       <th>Traversal</th>
 *       <th>Logic (L=Left, R=Right, N=Node)</th>
 *       <th>Output for Sample Tree</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>DFS</td>
 *       <td><b>In-Order</b></td>
 *       <td>L &rarr; N &rarr; R</td>
 *       <td>40  20  10  25  5  30  15  35</td>
 *     </tr>
 *     <tr>
 *       <td>DFS</td>
 *       <td><b>Pre-Order</b></td>
 *       <td>N &rarr; L &rarr; R</td>
 *       <td>5  10  20  40  25  15  30  35</td>
 *     </tr>
 *     <tr>
 *       <td>DFS</td>
 *       <td><b>Post-Order</b></td>
 *       <td>L &rarr; R &rarr; N</td>
 *       <td>40  20  25  10  30  35  15  5</td>
 *     </tr>
 *     <tr>
 *       <td>BFS</td>
 *       <td><b>Level-Order</b></td>
 *       <td>Level by Level (Queue based)</td>
 *       <td>5  10  15  20  25  30  35  40</td>
 *     </tr>
 *   </tbody>
 * </table>
 *
 * @author YourName
 * @version 1.0
 */
public class BT {

    TreeNode root;

    public BT() {
        root = new TreeNode(0);
    }

    public BT(int data) {
        //create root with value 0
        root = new TreeNode(data);
    }

    /**
     * Constructor initializing root with children.
     *
     * @param data  integer value for root node
     * @param left  reference to left child node
     * @param right reference to right child node
     */
    public BT(int data, TreeNode left, TreeNode right) {
        //create root with value 0
        root = new TreeNode(data, left, right);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        BT obj = new BT();
        obj.createSampleTree();
        System.out.println("Tree.Tree :-> ");
        obj.printTree(obj.root, 0);
        System.out.println("\n\n");
        System.out.println("In order traversal: ");
        obj.inOrderTraversal(obj.root);
        System.out.println("\n");
        System.out.println("Pre order traversal: ");
        obj.preOrderTraversal(obj.root);
        System.out.println("\n");
        System.out.println("Post order traversal: ");
        obj.postOrderTraversal(obj.root);
        System.out.println("\n");
        System.out.println("BFS - Level order traversal: ");
        obj.breadthFirstTraversal(obj.root);
    }

    public void createSampleTree() {

        TreeNode g = new TreeNode(40);
        TreeNode c = new TreeNode(20, g, null);
        TreeNode d = new TreeNode(25);
        TreeNode e = new TreeNode(30);
        TreeNode f = new TreeNode(35);
        TreeNode a = new TreeNode(10, c, d);
        TreeNode b = new TreeNode(15, e, f);
        this.root.val = 5;
        this.root.left = a;
        this.root.right = b;
    }

    /**
     * Depth First Search: In-order (Left, Node, Right).
     * Used for retrieving data in sorted order in BSTs.
     *
     * @param root current node in recursion
     */
    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val + "\t");
        inOrderTraversal(root.right);
    }

    /**
     * Depth First Search: Pre-order (Node, Left, Right).
     * Useful for creating a copy of the tree or prefix expressions.
     * @param root current node in recursion
     */
    public void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * Depth First Search: Post-order (Left, Right, Node).
     * Useful for deleting trees or postfix expressions.
     * @param root current node in recursion
     */
    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val + "\t");
    }

    /**
     * Breadth-First Search: Level-order (Queue based).
     * Explores the full width of the tree level by level.
     *
     * @param root starting node (usually tree root)
     */
    public void breadthFirstTraversal(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();  //Take a Empty Queue.
        if (root == null) return;        //If root is empty, no further processing
        q.add(root);                    //Start from the root, insert the root into the Queue

        while (!q.isEmpty()) {            //Now while Queue is not empty
            TreeNode n = q.remove();        //Extract the node from the Queue
            System.out.print(n.val + " "); //Print the extracted node.
            if (n.left != null)            // insert all its chil­dren into the Queue
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    /**
     * Pretty prints the tree structure in the console with indentation.
     * @param rootNode current node to print
     * @param count depth level for indentation
     */
    public void printTree(TreeNode rootNode, int count) {

        for (int i = 0; i < count; i++) {
            System.out.print("\t");
        }
        count++;
        if (rootNode == null) {
            System.out.println("null");
            return;
        }
        System.out.print(rootNode.val + " \n");
        printTree(rootNode.left, count);
        printTree(rootNode.right, count);

    }

}
