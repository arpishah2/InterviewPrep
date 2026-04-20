package Tree.BinartyTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 */
public class SerializeDeserializeBinaryTree {


    public String serializeBFSIterative(BTNode root) {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            result.append(" null ");
            return result.toString();
        }

        Queue<BTNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);

        while (!bfsQueue.isEmpty()) {
            BTNode node = bfsQueue.poll();

            if (node == null) {
                result.append(" None ,");
            } else {
                result.append(node.data);
                result.append(" , ");

                bfsQueue.add(root.left);
                bfsQueue.add(root.right);
            }

        }

        return result.toString();

    }

    /**
     * Encodes a tree to a single string.
     * The preorder DFS traverse follows recursively the order of root -> left subtree -> right subtree.
     */
    public String serializeDFSPreOrder(BTNode root) {
        return reserialize(root, "");
    }

    // Encodes a tree to a single string.
    private String reserialize(BTNode root, String result) {
        if (root == null) {
            return result + "null";
        } else {
            result += root.data + " , ";
            reserialize(root.left, result);
            reserialize(root.right, result);
        }
        return result;
    }


    // Decodes your encoded data to tree.
    private BTNode deserializePreOrder(String input) {
        String[] data_array = input.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return redeserialize(data_list);
    }

    public BTNode redeserialize(List<String> dataList) {
        // Recursive deserialization.
        if (dataList.get(0).equals("null")) {
            dataList.remove(0);
            return null;
        } else {
            BTNode root = new BTNode(Integer.valueOf(dataList.get(0)));
            dataList.remove(0);
            root.left = redeserialize(dataList);
            root.right = redeserialize(dataList);
            return root;
        }
    }
}

