package DirectedGraaph;/*
 * 
 * DirectedGraaph.DepthFirstSearch(DirectedGraaph.Vertex c)
		If c is the goal
			Exit
		Else
			Mark c "Visit In Progress"
			Foreach neighbor n of c
				If n "Unvisited"
					Depth-First-Search( n )
				Mark c "Visited"
 * 
 * 
 */

import java.util.List;

public class DepthFirstSearch {


    public static void main(String[] args) {

        DepthFirstSearch dfs = new DepthFirstSearch(); //create obj of curr class
        GraphCreation gcr = new GraphCreation(); //create sample graph
        Graph graph1 = gcr.createGraph(); //store sample graph
        dfs.DFS(graph1.vertexes.get(0), new Vertex("E")); //call method
    }

    /**
     * @param root   - node to search against,
     * @param search - node to search for
     * @about - searches a node in a directed graph using depth first search
     */
    public void DFS(Vertex root, Vertex search) {

        //if the value of currNode and node to search for matches
        if (root.value == search.value && root.visited != true) {
            root.visited = true;
            System.out.println("Found:- " + root.value);
            return;

        } else if (root.value != search.value && root.visited != true) {

            root.visited = true; //visit node
            List<Edge> edgs = root.edges;

            for (Edge e : edgs) {
                DFS(e.to, search);
            }
        }
    }
}
