package Graph.DirectedGraaph;

public class Edge {

    int weight;
    Vertex to;

    Edge() {
        this.weight = 0;
        this.to = new Vertex();
    }

    Edge(int wt, Vertex toVtx) {
        this.weight = wt;
        this.to = toVtx;
    }
}
