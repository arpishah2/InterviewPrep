package Graph.BidirectionalGraph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 */
public class CloneAGraph {

    // Hash map to save the visited node and it's respective clone as key and value respectively. This helps to avoid cycles.
    private HashMap<CloneNode, CloneNode> visitedDFS = new HashMap<>();

    public CloneNode cloneGraphDFS(CloneNode node) {

        if (node == null) {
            return null;
        }

        if (visitedDFS.containsKey(node)) {
            return visitedDFS.get(node);
        }

        CloneNode newNode = new CloneNode(node.val);
        visitedDFS.put(node, newNode);

        for (CloneNode neighbour : node.neighbors) {
            newNode.neighbors.add(cloneGraphDFS(neighbour));
        }

        return newNode;
    }

    public CloneNode cloneGraphBFS(CloneNode node) {

        if (node == null) {
            return null;
        }

        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<CloneNode, CloneNode> visited = new HashMap();

        // Put the first node in the queue
        LinkedList<CloneNode> queue = new LinkedList<>();
        queue.add(node);

        // create clone of the node
        CloneNode newNode = new CloneNode(node.val);
        visited.put(node, newNode);

        while (!queue.isEmpty()) {

            CloneNode currentNode = queue.pop();

            //List<CloneNode> newNeighbours = new ArrayList<>();

            for (CloneNode n : currentNode.neighbors) {

                if (!visited.containsKey(n)) {
                    //create new neighbour node and it to list of new neighbours
                    CloneNode newNeighbour = new CloneNode(n.val, new ArrayList<>());
                    visited.put(n, newNeighbour);

                    queue.add(n);
                }

                visited.get(currentNode).neighbors.add(visited.get(n));
            }

        }

        // Return the clone of the node from visited.
        return visited.get(node);
    }
}

class CloneNode {
    public int val;
    public List<CloneNode> neighbors;

    public CloneNode(int val) {
        this.val = val;
    }

    public CloneNode(int val, List<CloneNode> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }

}
