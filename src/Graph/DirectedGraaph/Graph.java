package Graph.DirectedGraaph;//Store Graph.DirectedGraaph.Graph as a list of Vertexes

import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Vertex> vertexes;

    Graph() {
        this.vertexes = new ArrayList<Vertex>();
    }

    public void printGraph() {
        System.out.println("Tree.Node(visited)---weight(Graph.DirectedGraaph.Edge)---> OtherNode");
        System.out.println();
        for (Vertex v : vertexes) {
            System.out.print(v);
        }
    }
	/*given arraylist of [from, to] generate graph
	public Graph.DirectedGraaph.Graph generateGraph(){
		//see method //public Graph.DirectedGraaph.Graph createGraph(ArrayList<Graph.DirectedGraaph.Ticket> tickets) //from  class Graph.DirectedGraaph.FlightItineraryProblem
	}*/

    public Vertex getVertex(String valueToSearch) {
        Vertex vtx = new Vertex();
        for (Vertex v : this.vertexes) {
            if (v.value == valueToSearch)
                return v;
        }
        return vtx;
    }
}
