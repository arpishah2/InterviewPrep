package DirectedGraaph;/*Some basic points
 *
 * Note: traverses over directed graph
 *
 * NOT WORKS ON WEIGHTED GRAPH
 * WORK on unweighed directed and undirected graphs
 *
 * 1. BFS - it expands on every level
 * 2. Add first level objects to a queue
 * 3. Pop the object from queue one by one, if the popped object satisfies your search, exit
 * 4. If not add the neighbor of popped object in the queue.
 * 5. Loop over the queue
 * 6. If queue becomes empty at the end and you do not find the desired object, then print "Nothing found"
 *
 */

/*
 * Eugene BFS - https://www.youtube.com/watch?v=9J9jME3CC3c&feature=youtu.be
 */

/*
 *Algo ->
 * breadthFirst(start)
	q ← empty queue
	q.enqueue(start)
	while (q.hasElements())
		node ← q.dequeue()
		if node.visited != Visited
			visit(node)
			node.visited = Visited
			for each adjacentNode in node.edges
				q.enqueue(adjacentNode)
 * 
 */

/*
 * Add - prints out the path from start to goal when it finds it.
 */

import java.util.*;

public class BreadthFirstSearch {

    /**
     * @about: Main method
     */
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(); //creating obj of current class
        GraphCreation gcr = new GraphCreation(); //to create a sample graph
        Graph graph1 = gcr.createGraph(); //storing sample graph
        Queue<Vertex> qu1 = new LinkedList<Vertex>(); //BFS requires a queue to work on
        qu1.add(graph1.vertexes.get(0));
        HashMap<Object, Object> m = new HashMap<Object, Object>();
        m.put(graph1.vertexes.get(0).value, "null"); //put the initial root and say that it has been introduced by null
        m = bfs.getBFSPath(qu1, new Vertex("M"), graph1.vertexes.get(0), m); //find best path
        bfs.BFS(qu1, new Vertex("E"), graph1.vertexes.get(0)); //send empty queue, vertex to search, vertex to start from
        LinkedList<Object> ll = new LinkedList<Object>(); //LinkedList.LL to save path
        ll = bfs.getLLBFSPath(m, "E", ll); //saves path in LinkedList.LL
        bfs.printBFSPath(ll); //prints path from LinkedList.LL
    }

    /**
     * @ABOUT gets the bfs path from the hashmap<DirectedGraaph.Vertex,from>
     */
    public HashMap<Object, Object> getBFSPath(Queue<Vertex> q, Vertex search, Vertex root, HashMap<Object, Object> hmap) {
        if (root.value == search.value)
            return hmap; //return map if value is found
        else {
            q.remove();
            for (Edge e : root.edges) {
                Vertex neib = e.to;
                if (!q.contains(neib) && neib.visited == false) {
                    q.add(neib); //add neighbors to the queue
                    if (!hmap.containsKey(neib.value))
                        hmap.put(neib.value, root.value); //add vertex and where it is introduced from
                }
            }
            root.visited = true; //set the flag in order to avoid loops and infinite calls
            if (!q.isEmpty())
                hmap = getBFSPath(q, search, q.peek(), hmap); //Recursion - call for top value in queue
        }
        return hmap;
    }

    /**
     * @ABOUT gets the bfs path from the hashmap<DirectedGraaph.Vertex,from> and saves it in linked list
     */
    public LinkedList<Object> getLLBFSPath(HashMap<Object, Object> hmap, Object search, LinkedList<Object> ll) {
        if (hmap.containsKey(search) && search != null) {
            ll = this.getLLBFSPath(hmap, hmap.get(search), ll);
            ll.add(search);
        }
        return ll;
    }

    /**
     * @ABOUT prints the bfs path from LinkedList.LL
     */
    public void printBFSPath(LinkedList<Object> ll) {
        ListIterator<Object> listIterator = ll.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + "\t->\t");
        }
    }

    public void BFS(Queue<Vertex> q, Vertex search, Vertex root) {

        q.add(root); //Add current node to queue
        int count = 2;

        while (count != 0) {
            Vertex currNode = q.remove(); //remove the node form queue
            currNode.visited = true; //set visited = true

            if (currNode.value == search.value) { //if the currNode equals node to search; return
                System.out.println("Value Found !! " + currNode.value);
                return;
            } else {
                List<Edge> currEdges = currNode.edges; //get edges of a node to get to neighbours
                for (Edge e : currEdges) {
                    q.add(e.to); //add neighbor node to queue
                }
            }
        }//end of while
    }//end of bfs method
}//end of class
