package Tree.BinartyTree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author arpi
 * @version 1
 * @about Given a Binary Tree.Tree, design an algo which creates LinkedList.LL of all nodes at each depth. If you have a tree with depth d, you will have d LLs
 *
 */

/*
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

Result:
[5]
[10, 15]
[20, 25, 30, 35]
[40]

 */


public class ListOfDepths {

    public ListOfDepths() {

    }

    public static void main(String args[]) {

        BT obj = new BT();
        obj.createSampleTree();
        ListOfDepths lod = new ListOfDepths();


        ArrayList<LinkedList<Integer>> array = new ArrayList<LinkedList<Integer>>();
        array = lod.createLLatEachdepth(obj.root, array, 0);

        for (LinkedList<Integer> ll : array) {
            System.out.println(ll.toString());
        }

    }

    public ArrayList<LinkedList<Integer>> createLLatEachdepth(TreeNode root, ArrayList<LinkedList<Integer>> arr, int depth) {


        if (root == null) {
            return arr;
        }

        //LinkedList<Integer> atPos = arr.get(depth);
        //System.out.println(atPos);
        LinkedList<Integer> atPos = null;

        if (arr.size() <= depth) {
            atPos = new LinkedList<Integer>();
            atPos.add(root.data);
            arr.add(depth, atPos); //add if no value exists
        } else {
            atPos = arr.get(depth);
            atPos.add(root.data);
            arr.set(depth, atPos); //replace already contained value with new val
        }

        arr = createLLatEachdepth(root.left, arr, depth + 1);
        arr = createLLatEachdepth(root.right, arr, depth + 1);
        return arr;
    }
}
