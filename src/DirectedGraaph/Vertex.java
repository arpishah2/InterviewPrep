package DirectedGraaph;

import java.util.ArrayList;
import java.util.List;


public class Vertex {

    String value;
    List<Edge> edges;
    boolean visited;

    Vertex() {
        this.value = "";
        this.edges = new ArrayList<Edge>();
        this.visited = false;
    }

    Vertex(String val) {
        this.value = val;
        this.edges = new ArrayList<Edge>();
        this.visited = false;
    }

    Vertex(String val, List<Edge> edg) {
        this.value = val;
        this.edges = edg;
        this.visited = false;
    }

    Vertex(String val, List<Edge> edg, boolean vis) {
        this.value = val;
        this.edges = edg;
        this.visited = vis;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : this.edges) {
            sb.append("[");
            sb.append(this.value);
            sb.append("(" + this.visited + ")");
            sb.append("--");
            sb.append(e.weight + "--> ");
            sb.append(e.to.value);
            sb.append("]");
            sb.append(",\t");
        }
        return sb.toString();
    }

}
