package Graph.DirectedGraaph;

import java.util.ArrayList;
import java.util.List;

/*
 * 				 A
 * 			   1/ \ 2
 *             /   \________
 * 			  B    C        |
 *         3 /\4 5 /\6      |
 *          /  \  /  \      |7
 *         D    E F   G     |
 *                |_________|
 */


public class GraphCreation {

    Graph gr;

    public static void main(String args[]) {
        GraphCreation cr = new GraphCreation();
        cr.createGraph();
    }

    public Graph createGraph() {
        this.gr = new Graph();
        List<Vertex> vtx = new ArrayList<Vertex>();
        gr.vertexes = vtx;
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        vtx.add(a);
        vtx.add(b);
        vtx.add(c);
        vtx.add(d);
        vtx.add(e);
        vtx.add(f);
        vtx.add(g);
        Edge e1 = new Edge(1, b);
        Edge e2 = new Edge(2, c);
        Edge e3 = new Edge(3, d);
        Edge e4 = new Edge(4, e);
        Edge e5 = new Edge(5, f);
        Edge e6 = new Edge(6, g);
        Edge e7 = new Edge(7, c);

        //Edges for node A
        List<Edge> lst1 = new ArrayList<Edge>();
        lst1.add(e1);
        lst1.add(e2);
        a.edges = lst1;
        //Edges for node B
        List<Edge> lst2 = new ArrayList<Edge>();
        lst2.add(e3);
        lst2.add(e4);
        b.edges = lst2;
        //Edges for node C
        List<Edge> lst3 = new ArrayList<Edge>();
        lst3.add(e5);
        lst3.add(e6);
        c.edges = lst3;
        //Edges for node F
        List<Edge> lst4 = new ArrayList<Edge>();
        lst4.add(e7);
        f.edges = lst4;

        //gr.printGraph();
        return gr;
    }


}
