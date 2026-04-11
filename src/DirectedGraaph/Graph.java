package DirectedGraaph;//Store DirectedGraaph.Graph as a list of Vertexes

import java.util.ArrayList;
import java.util.List;

public class Graph {

    List<Vertex> vertexes;

    Graph() {
        this.vertexes = new ArrayList<Vertex>();
    }

    public void printGraph() {
        System.out.println("Tree.Node(visited)---weight(DirectedGraaph.Edge)---> OtherNode");
        System.out.println();
        for (Vertex v : vertexes) {
            System.out.print(v);
        }
    }
	/*given arraylist of [from, to] generate graph
	public DirectedGraaph.Graph generateGraph(){
		//see method //public DirectedGraaph.Graph createGraph(ArrayList<DirectedGraaph.Ticket> tickets) //from  class DirectedGraaph.FlightItineraryProblem
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
