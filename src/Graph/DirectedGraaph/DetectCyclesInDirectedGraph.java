package Graph.DirectedGraaph;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Problem:
 * Given a directed graph, check whether the graph contains a cycle or not.
 * Your function should return true if the given graph contains at least one cycle, else return false.
 */

/*
 * Solution:
 * Depth First Traversal can be used to detect cycle in a Graph.DirectedGraaph.Graph.
 * DFS for a connected graph produces a tree.
 * There is a cycle in a graph only if there is a back edge present in the graph.
 * A back edge is an edge that is from a node to itself (selfloop) or one of its ancestor in the tree produced by DFS.
 *
 * white set - not visited
 * grey set - visiting
 * black set - visited - will contain vertex whose all children have also been visited
 *
 *  * 			 A
 * 			   1/ \ 2
 *             /   \________
 * 			  B    C        |
 *         3 /\4 5 /\6      |
 *          /  \  /  \      |7
 *         D    E F   G     |
 *                |_________|
 */

public class DetectCyclesInDirectedGraph {

    private Set<Vertex> unvisited;
    private Set<Vertex> visiting;
    private Set<Vertex> visited;

    public DetectCyclesInDirectedGraph() {
        this.unvisited = new LinkedHashSet<Vertex>();
        this.visiting = new HashSet<Vertex>();
        this.visited = new HashSet<Vertex>();
        this.unvisited = null;
    }

    public DetectCyclesInDirectedGraph(Set<Vertex> vtx) {

        this.unvisited = new LinkedHashSet<Vertex>(vtx);
        this.visiting = new HashSet<Vertex>();
        this.visited = new HashSet<Vertex>();
    }

    public static void main(String args[]) {
        GraphCreation obj = new GraphCreation();
        Graph gr = obj.createGraph();
        Set<Vertex> vtxs = new LinkedHashSet<Vertex>(gr.vertexes);
        DetectCyclesInDirectedGraph dc = new DetectCyclesInDirectedGraph(vtxs);
        dc.detectCycle(gr.getVertex("A"));
    }

    public void detectCycle(Vertex v) {

        boolean inVisiting = this.visiting.contains(v);
        boolean inVisited = this.visited.contains(v);

        if (!inVisiting && !inVisited) {
            this.unvisited.remove(v);
            this.visiting.add(v);

            if (v.edges == null) {
                this.visiting.remove(v);
                this.visited.add(v);
                return;
            } else {
                for (Edge e : v.edges) {
                    detectCycle(e.to);
                }
            }
        } else if (inVisiting) {
            System.out.println("Has cycles");
        }

    }
}
