package Graph.DirectedGraaph;

import java.util.ArrayList;

/*
 * http://algs4.cs.princeton.edu/41graph/AdjMatrixGraph.java.html
 *
 * The standard adjacency matrix stores a matrix as a 2-D array with each slot in
 * A[i][j]
 * = 1, if there is an edge from vertex i to vertex j
 * = 0, otherwise
 *
 * --Positives of using Adjacency matrix representation over Adjacency list ->
 * Good for dense graphs
 *
 * --Negatives of using Adjacency matrix representation over Adjacency list ->
 * a) Iterate through neighbors of a vertex
 * 		= scan all vertices to find all the edges incident to a vertex  = Theta(V)
 * b) In a relatively sparse graph, using an adjacency matrix
 * would be very inefficient running Dijkstra's algorithm
 *
 * --Memory = Theta(V2)
 * -- Check for edge(i,j) = Theta(1)
 *
 * watch: https://www.youtube.com/watch?v=WQ2Tzlxl_Xo
 */

/*
 *  each slot in A[i][j]
 *  - being a 1 if there is an edge from vertex i to vertex j,
 *  - or storing a 0 otherwise
 *
 *  		     A
 * 			    / \
 *             /   \________
 * 			  B    C        |
 *           /\    /\       |
 *          /  \  /  \      |
 *         D    E F   G     |
 *                |_________|
 *
 *
 *   Adjacency Matrix =
 *
 *   				  A B C D E F G
 *                   ________________
 *                A | 0 1 1 0 0 0 0
 *                B | 1 0 0 1 1 0 0
 *                C | 1 0 0 0 0 1 1
 *                D | 0 1 0 0 0 0 0
 *                E | 0 1 0 0 0 0 0
 *                F | 0 0 1 0 0 0 0
 *                G | 0 0 1 0 0 0 0
 *
 *             Note: 1 = true,  0 = false
 *
 *
 *
 */
public class GraphAdjacencyMatrix {

    private int noOfVertex;
    private int noOfEdge;
    private boolean adjMat[][];

    //empty graph with 0 edges and 0 vertices
    GraphAdjacencyMatrix() {
        this.noOfEdge = 0;
        this.noOfVertex = 0;
        this.adjMat = new boolean[this.noOfVertex][this.noOfVertex];
    }

    //graph with v vertex and no edges
    GraphAdjacencyMatrix(int v) throws Exception {
        if (v < 0)
            throw new Exception("Cant be negative no of vertices in the graph");
        this.noOfEdge = 0;
        this.noOfVertex = v;
        this.adjMat = new boolean[this.noOfVertex][this.noOfVertex];
    }

    //graph with v vertex and e edges. e<v
    GraphAdjacencyMatrix(int v, int e) throws Exception {
        if (v < 0) throw new Exception("Cant be negative no of vertices in the graph");
        if (e < 0) throw new Exception("Cant be negative no of edges in the graph");
        if (e > v * v) throw new Exception("Cant be that many edges");
        this.noOfVertex = v;
        this.noOfEdge = e;
        this.adjMat = new boolean[this.noOfVertex][this.noOfVertex];
    }

    public static void main(String[] args) {

        System.out.println("Enter no of vertex and no of edge");
        int v = Integer.parseInt(args[0]);
        int e = Integer.parseInt(args[1]);

        try {
            GraphAdjacencyMatrix gr = new GraphAdjacencyMatrix(v, e);
            gr.addEdge(0, 1);
            gr.addEdge(0, 2);
            gr.addEdge(1, 0);
            gr.addEdge(1, 3);
            gr.addEdge(1, 4);
            gr.addEdge(2, 0);
            gr.addEdge(2, 5);
            gr.addEdge(2, 6);
            gr.addEdge(3, 1);
            gr.addEdge(4, 1);
            gr.addEdge(5, 2);
            gr.addEdge(6, 2);
            gr.printGrpahMatrix();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //return no of vertex
    public int countOfVertex() {
        return this.noOfVertex;
    }

    //return no of edges
    public int countOfEdges() {
        return this.noOfEdge;
    }

    //check if edge exists between 2 nodes
    public boolean edgeExists(int v1, int v2) {
        return this.adjMat[v1][v2];
    }

    //add undirected edge from vertex v1-v2
    public void addEdge(int v1, int v2) throws Exception {
        //check one transpose will also be checked eg pos[1,2] and pos[2,1]
        this.adjMat[v1][v2] = true;
        this.adjMat[v2][v1] = true;
    }

    //return neighbors of a vertex v1
    public int[] getNeighbours(int v1) {
        ArrayList<Integer> neb = new ArrayList<Integer>();
        int vertexPos = v1;
        for (int i = 0; i < this.noOfVertex; i++) {
            if (this.adjMat[vertexPos][i])
                neb.add(i);
        }
        //Convert Integer to int
        int[] nebs = new int[neb.size()];
        int i = 0;
        for (Integer n : neb)
            nebs[i++] = n;
        return nebs;
    }

    public void printGrpahMatrix() {
        for (int i = 0; i < this.noOfVertex; i++) {
            for (int j = 0; j < this.noOfVertex; j++) {
                if (this.adjMat[i][j])
                    System.out.print("T");
                else
                    System.out.print("F");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
