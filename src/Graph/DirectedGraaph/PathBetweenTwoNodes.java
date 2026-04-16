package Graph.DirectedGraaph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathBetweenTwoNodes {

    boolean isFound;
    Queue<Vertex> vtx = new LinkedList<Vertex>();

    public static void main(String args[]) {
        GraphCreation gc = new GraphCreation();
        Graph graph = gc.createGraph();
        PathBetweenTwoNodes pth = new PathBetweenTwoNodes();

        Vertex a = graph.getVertex("A");
        Vertex f = graph.getVertex("F");
        System.out.println("Path between the 2 nodes exist??:-> " + pth.isThereAPath(a, f));

    }

    /**
     *
     * @param vt1 - Staring vertex from where path is to be checked
     * @param vt2 - Ending vertex to check if a path exists to this vertex from vt1
     * @return true: path exists; false: path does not exist
     */
    public boolean isThereAPath(Vertex vt1, Vertex vt2) {

        isFound = false;
        vtx.add(vt1); // add vertex to queue of vertex

        while (!vtx.isEmpty()) { //while queue is not empty

            Vertex v = vtx.peek(); //get the first vertex from queue
            if (v == vt2 && !v.visited) { //if v equals end vertex; return true - path exists
                isFound = true;
                return isFound;
            }
            List<Edge> edgs = v.edges;
            for (Edge e : edgs) {
                vtx.add(e.to); //add neighbors of current vertex to queue
            }
            vtx.remove(); //remove vertex from queue
        }
        return isFound;
    }

}
