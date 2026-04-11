package Tree.BinartyTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Tree.Tree :->
5 
	10 
		20 
			40 
				null
				null
			null
		25 
			null
			null
	15 
		30 
			null
			null
		35 
			null
			null



In order traversal: 
40	20	10	25	5	30	15	35	

Pre order traversal: 
5	10	20	40	25	15	30	35	

Ppst order traversal: 
40	20	25	10	30	35	15	5	

 */


public class BT {

    BTNode root;

    public BT() {
        root = new BTNode(0);
    }

    public BT(int data) {
        //create root with value 0
        root = new BTNode(data);
    }

    public BT(int data, BTNode left, BTNode right) {
        //create root with value 0
        root = new BTNode(data, left, right);
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
        obj.bredthFirstTraversal(obj.root);
    }

    public void createSampleTree() {

        BTNode g = new BTNode(40);
        BTNode c = new BTNode(20, g, null);
        BTNode d = new BTNode(25);
        BTNode e = new BTNode(30);
        BTNode f = new BTNode(35);
        BTNode a = new BTNode(10, c, d);
        BTNode b = new BTNode(15, e, f);
        this.root.data = 5;
        this.root.left = a;
        this.root.right = b;
    }

    public void inOrderTraversal(BTNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.data + "\t");
        inOrderTraversal(root.right);
    }

    public void preOrderTraversal(BTNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + "\t");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //DFS - inorder,preorder,postorder

    //BFS - This level-by-level traversal is called a breadth-first traversal because we explore the breadth,
    //i.e., full width of the tree at a given level, before going deeper.
    //Use a queue

    public void postOrderTraversal(BTNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + "\t");
    }

    public void bredthFirstTraversal(BTNode root) {
        Queue<BTNode> q = new LinkedList<BTNode>();  //Take a Empty Queue.
        if (root == null) return;        //If root is empty, no further processing
        q.add(root);                    //Start from the root, insert the root into the Queue

        while (!q.isEmpty()) {            //Now while Queue is not empty
            BTNode n = q.remove();        //Extract the node from the Queue
            System.out.print(n.data + " "); //Print the extracted node.
            if (n.left != null)            // insert all its chil­dren into the Queue
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    public void printTree(BTNode rootNode, int count) {

        for (int i = 0; i < count; i++) {
            System.out.print("\t");
        }
        count++;
        if (rootNode == null) {
            System.out.println("null");
            return;
        }
        System.out.print(rootNode.data + " \n");
        printTree(rootNode.left, count);
        printTree(rootNode.right, count);

    }

}
