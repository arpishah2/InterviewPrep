package Graph.BidirectionalGraph;

import java.util.*;

/**
 * Find if Path Exists in Graph
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * <p>
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * <p>
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * <p>
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 */
public class PathInAGraph {

    //This is a graph traversal problem, where we have to start traversal from one node and check if we can reach the other one.
    //There exist two methods, breadth-first search (BFS) and depth-first search (DFS) for graph traversals.

    public static void main(String[] args) {
        PathInAGraph solution = new PathInAGraph();

        // Test Case 1: Simple connected graph
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}};
        int source1 = 0, destination1 = 2;
        printResult(1, solution.validPathBFSIteration(n1, edges1, source1, destination1), true);

        // Test Case 2: Disconnected components
        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source2 = 0, destination2 = 5;
        printResult(2, solution.validPathDFSIterative(n2, edges2, source2, destination2), false);

        // Test Case 3: Single node (Source is Destination)
        int n3 = 1;
        int[][] edges3 = {};
        int source3 = 0, destination3 = 0;
        printResult(3, solution.validPathDFSRecurssive(n3, edges3, source3, destination3), true);

        // Test Case 4: Larger linear path
        int n4 = 4;
        int[][] edges4 = {{0, 1}, {1, 2}, {2, 3}};
        int source4 = 0, destination4 = 3;
        printResult(4, solution.validPathBFSIteration(n4, edges4, source4, destination4), true);
    }

    private static void printResult(int testNum, boolean actual, boolean expected) {
        String status = (actual == expected) ? "PASS ✅" : "FAIL ❌";
        System.out.printf("Test Case %d: %s%n", testNum, status);
        System.out.printf(" Expected: %b, Actual: %b%n", expected, actual);
        System.out.println("------------------------------------");
    }

    /**
     * Time complexity: O(n+m)
     * <p>
     * In a typical BFS search, the time complexity is O(V+E) where V is the number of vertices and E is the number of edges. There are n nodes and m edges in this problem.
     * We build adjacent list of all m edges in graph which takes O(m).
     * Each node is added to the queue and popped from queue once, it takes O(n) to handle all nodes.
     * The time complexity is O(n+m).
     * Space complexity: O(n+m)
     * <p>
     * We used a hash map neighbors to store all edges, which requires O(m) space for m edges.
     * We use seen, either a hash set or an array to record the visited nodes, which takes O(n) space.
     * There may be up to n nodes stored in queue and O(n) space is required.
     * Therefore, the space complexity is O(n+m).
     */
    public boolean validPathBFSIteration(int n, int[][] edges, int source, int destination) {

        //Store all edges in the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();

        //Store all edges in 'graph'
        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];
            graph.computeIfAbsent(nodeA, val -> new ArrayList<>()).add(nodeB);
            graph.computeIfAbsent(nodeB, val -> new ArrayList<>()).add(nodeA);
            // {0: 1}, {1: 2}, {2: 4, 3}, {3: 0}, {4: 3}
        }

        Queue<Integer> nodeList = new LinkedList<>();
        nodeList.add(source);
        // 0

        boolean[] seen = new boolean[n];
        seen[source] = true;
        // [true, false, false]

        while (!nodeList.isEmpty()) {
            Integer currNode = nodeList.poll(); //0; 1; 2; 4

            if (currNode.equals(destination)) {
                return true;
            }
            List<Integer> neighbours = graph.get(currNode); //1; 2; 4,3; 3

            for (Integer neighbour : neighbours) {
                if (!seen[neighbour]) {
                    seen[neighbour] = true; //0, 1, 2, 4, 3,
                    nodeList.offer(neighbour); //1; 2, 4
                }

            }
        }

        return false;
    }

    /**
     * Algorithm
     * 1. Initialize an empty stack stack to store the nodes to be visited.
     * 2. Use one bool array seen to mark all visited nodes and a hash map graph to store all edges.
     * 3. Add the starting node source to stack and mark it as visited.
     * 4. While stack has nodes, get the top node curr_node from stack.
     * 5. If curr_node equals destination, return true.
     * 6. Otherwise, add unvisited neighbor nodes of curr_node to stack and mark them as visited and repeat step 4.
     * 7. If we finish the iteration without finding destination, return false.
     */
    public boolean validPathDFSIterative(int n, int[][] edges, int source, int destination) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, val -> new ArrayList<>()).add(a);
        }

        boolean[] seen = new boolean[n];
        seen[source] = true;

        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {
            int currNode = stack.pop();
            if (currNode == destination) {
                return true;
            }
            // Add all unvisited neighbors of the current node to stack'
            // and mark it as visited.
            for (int nextNode : graph.get(currNode)) {
                if (!seen[nextNode]) {
                    seen[nextNode] = true;
                    stack.push(nextNode);
                }
            }
        }

        return false;

    }

    public boolean validPathDFSRecurssive(int n, int[][] edges, int source, int destination) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] seen = new boolean[n];

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, val -> new ArrayList<>()).add(a);
        }

        return dfs(graph, seen, source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] seen, int currNode, int destination) {
        if (currNode == destination) {
            return true;
        }
        seen[currNode] = true;
        for (int nextNode : graph.get(currNode)) {
            if (!seen[nextNode]) {  // Only call dfs if not seen
                if (dfs(graph, seen, nextNode, destination)) {
                    return true;
                }
            }
        }
        return false;
    }

}
