package Tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Binary search tree: Used for searching. A binary tree where the left child contains only nodes with values less than the parent node,
 * and where the right child only contains nodes with values greater than or equal to the parent.
 *
 * generate the following tree:
 * use Tree.BinarySearchTree.BSTNode class
 *
 * 87 -> 50 -> 27 -> 111 -> 99 -> 42 -> 90 -> 105 -> 58 -> 32 -> 68 ->
 *          43 -> 60 -> 70 -> 51 -> 1 -> 11
 *
 *
 * 			                                  87
 * 			  								 / \
 *           							    /   \
 *          							   /     \
 *                                        /       \
 *                                       /         \
 *                                     50   		111
 *                                     / \  		/
 *          					    27     58      99
 *      						    /\	   /\      /\
 *                                 /  \   /  \    /  \
 *    						      1   42  51 68  90 105
 *    						      \   /\     /\
 *                                11 32 43  60 70
 *
 */

public class BST {

    BSTNode root;

    //method -> Inserting node in Tree.BinarySearchTree.BST
    // If node's value is less than root node's value -> go left else -> go right. Do the same

    public static void main(String args[]) {

        BST obj = new BST();

        //insertNodeBST(Tree.BinarySearchTree.BSTNode root, Tree.BinarySearchTree.BSTNode newNode)
        // 87 -> 50 -> 27 -> 111 -> 99 -> 42 -> 90 -> 105 -> 58 -> 32 -> 68 ->
        //      43 -> 60 -> 70 -> 51 -> 1 -> 11

        //create tree
        obj.root = null;
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(87));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(50));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(27));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(111));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(99));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(42));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(90));
        BSTNode n = new BSTNode(105);
        obj.root = obj.insertNodeBST(obj.root, n);
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(58));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(32));
        BSTNode p = new BSTNode(68);
        obj.root = obj.insertNodeBST(obj.root, p);
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(43));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(60));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(70));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(51));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(1));
        obj.root = obj.insertNodeBST(obj.root, new BSTNode(11));

        //print tree
        System.out.println("\nTree.Tree:");
        obj.printBST(obj.root, 0);

        //in-order traversal
        System.out.println("\nIn Order Traversal: ");
        obj.inOrderTraversal(obj.root);

        System.out.println("\n");

        //pre-order traversal
        System.out.println("\nPre Order Traversal: ");
        obj.preOrderTraversal(obj.root);

        System.out.println("\n");

        //post-order traversal
        System.out.println("\nPost Order Traversal: ");
        obj.postOrderTraversal(obj.root);

        //depth first search
        System.out.println("\n\nElement present in tree(DFS)? \t" + obj.depthFirstSearch(obj.root, 105));


        //breadth first search
        System.out.println("\nElement present in tree(BFS)? \t" + obj.breadthFirstSearch(obj.root, 99, new LinkedList<BSTNode>()));

        //Remove leaf

        obj.root = obj.deleteNodeBST(obj.root, 105);
        obj.printBST(obj.root, 0);


        //return depth of a node 105
        int d[] = new int[]{0};
        obj.depthOfNode(obj.root, p, d);
        System.out.println("Depth of the ndoe is:" + d[0]);


    } //end of main

    public BSTNode insertNodeBST(BSTNode root, BSTNode newNode) {
        if (root == null)
            root = newNode;
        else {
            int newVal = newNode.data;
            if (newVal < root.data)
                root.left = insertNodeBST(root.left, newNode);
            else
                root.right = insertNodeBST(root.right, newNode);
        }
        return root;
    }//end of insertNodeBST()

    public BSTNode deleteNodeBST(BSTNode root, int val) {
        BSTNode newNode = returnNode(root, val);
        if (root == null) {
            System.out.println("Tree.Tree is already empty !! Cannot delete !!");
        } else if (newNode.left != null && newNode.right != null && root != null) {
            //if both the children are present
            //place predecessor of the node at its place and delete the value
            //predecessor of a node = rightmost value in the left subtree

            BSTNode currNode = newNode.left;
            while (currNode != null)                //find predec
                currNode = currNode.right;
            if (currNode.left == null)             //if predec is a leaf, just go on
                newNode.data = currNode.data;
            else {                                //if predec is not a leaf, then we have to take care of its left child
                newNode.data = currNode.data;
                deleteNodeBST(root, newNode.data);
            }
        } else if (newNode.left != null || newNode.right != null) { //if there is only 1 child
            //make the child as the node
            if (newNode.left != null)
                newNode = newNode.left;
            else
                newNode = newNode.right;
        } else {                                    //it is leaf
            System.out.println(newNode.data + " is a leaf node. Let us delete it ");
            newNode = null;
        }
        return root;
    }//end of insertNodeBST()

    public BSTNode returnNode(BSTNode root, int val) {
        BSTNode node = root;
        if (root.data == val)
            return node;
        else if (val < root.data)
            node = returnNode(root.left, val);
        else if (val > root.data)
            node = returnNode(root.right, val);
        return node;
    }

    public String depthOfNode(BSTNode cur, BSTNode search, int[] depth) {
        if (cur == null)
            return "Not Found";
        if (cur == search)
            return "Found";
        int temp = depth[0];
        depth[0] += 1;
        String found = depthOfNode(cur.left, search, depth);
        if (found == "Not Found") {
            depth[0] = temp;
            depth[0] += 1;
            found = depthOfNode(cur.right, search, depth);
            if (found == "Found")
                return "Found";
        } else {
            return "Found";
        }
        return "Not Found";
    }

    public String nthHighest(BSTNode cur, int N, int[] indx) {

        if (cur == null)
            return "Not Done";

        return null;
    }

    public boolean depthFirstSearch(BSTNode root, int val) {
        boolean found = false;
        if (root.data == val) {
            found = true;
            return found;
        } else if (val < root.data)
            return depthFirstSearch(root.left, val);
        else if (val > root.data)
            return depthFirstSearch(root.right, val);
        return found;
    }
	
	
/* print the already created Tree.BinarySearchTree.BST
 * 
 * 										     111
 * 											 /
 *                                   		/              
 *          					       	  99
 *      					              /\
 *                                       /  \
 *    						           90   105
 *    
 *    
 *    
 *    AS -------------------------------->
 *    
 *    111
	   		99
			 	90
					null
					null
			 	105
					null
					null
		  	null
 *    
 *    
 *    
 *    
 */

    public boolean breadthFirstSearch(BSTNode root, int val, Queue<BSTNode> q) {

        boolean found = false;
        if (root.data == val) {
            found = true;
            return found;
        } else if (root != null) {
            if (q.peek() != null) //if value does not match, remove that root from queue
                q.remove();
            if (root.left != null)        //add the child of the removed Tree.BinarySearchTree.BSTNode(root) to queue
                q.add(root.left);
            if (root.right != null)
                q.add(root.right);
            if (q.isEmpty())            //if queue is empty anyway no processing can be done
                return false;
            else
                return breadthFirstSearch(q.peek(), val, q);
        }
        return found;
    }


    // Left -> Root -> Right
    // 1 11 27 32 42 43 50 51 58 60 68 70 87 90 99 105 111

    public void printBST(BSTNode root, int count) {
        if (root == null) {
            for (int i = 0; i < count; i++) {
                System.out.print("\t");
            }
            System.out.println("null");
        } else {

            for (int i = 0; i < count; i++) {
                System.out.print("\t");
            }
            System.out.println(root.data);

            int temp = count + 1;
            printBST(root.left, temp);
            printBST(root.right, temp);
        }
    }//end of printBST()

    // Root -> Left -> right
    // 87 50 27 1 null 11 42 32 43 58 51 68 60 70 111 99 90 105

    public void inOrderTraversal(BSTNode root) {
        if (root == null)
            System.out.print("");
        else {
            inOrderTraversal(root.left);
            System.out.print(root.data + " -> ");
            inOrderTraversal(root.right);
        }
    }

    //left -> right -> data
    //11 -> 1 -> 32 -> 43 -> 42 -> 27 -> 51 ->
    //60 -> 70 -> 68 -> 58 -> 50 -> 90 -> 105 -> 99 -> 111 -> 87

    public void preOrderTraversal(BSTNode root) {
        if (root == null)
            System.out.print("");
        else {
            System.out.print(root.data + " -> ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public void postOrderTraversal(BSTNode root) {
        if (root == null)
            System.out.print("");
        else {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " -> ");
        }
    }

}//end of class
