package Graph.DirectedGraaph;/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

*********Note: For input to be non-cyclic - you can use Hashmap
 */

import java.util.*;

class Ticket {
    String from;
    String to;

    Ticket() {
    }

    Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }
}

public class FlightItineraryProblem {


    public static void main(String[] args) {
        FlightItineraryProblem obj = new FlightItineraryProblem(); //Create obj
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		/*
		tickets.add(new Graph.DirectedGraaph.Ticket("MUC", "LHR"));
		tickets.add(new Graph.DirectedGraaph.Ticket("JFK", "MUC"));
		tickets.add(new Graph.DirectedGraaph.Ticket("SFO", "SJC"));
		tickets.add(new Graph.DirectedGraaph.Ticket("LHR", "SFO"));
		//Return ["JFK", "MUC", "LHR", "SFO", "SJC"]
		 */
        //cycles
        tickets.add(new Ticket("JFK", "SFO"));
        tickets.add(new Ticket("JFK", "ATL"));
        tickets.add(new Ticket("SFO", "ATL"));
        tickets.add(new Ticket("ATL", "JFK"));
        tickets.add(new Ticket("ATL", "SFO"));
        //Return ["JFK","ATL","JFK","SFO","ATL","SFO"]
        //Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

        //get startpoint
        String startPoint = obj.findStartPoint(tickets);
        System.out.println(startPoint);
        Graph gr = obj.createGraph(tickets);
    }

    /**
     * @param tickets - array list of input tickets
     * @return start point of the itinerary
     */
    public String findStartPoint(ArrayList<Ticket> tickets) {
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        //loop over arraylist, for each element [from,to] add from,+1 in hmap and to,-1 in hmap. Next time compare values and do -1 or +1 accordingly
        for (Ticket t : tickets) {
            String from = t.from;
            String to = t.to;
            boolean isPresentTo = hmap.containsKey(to);
            boolean isPresentFrom = hmap.containsKey(from);
            if (isPresentFrom)
                hmap.put(from, hmap.get(from) + 1);
            else
                hmap.put(from, 1);

            if (isPresentTo)
                hmap.put(to, hmap.get(to) - 1);
            else
                hmap.put(to, -1);
        }

        Iterator<Map.Entry<String, Integer>> it = hmap.entrySet().iterator();
        String startPoint = ""; //to store start point if all the degrees are <=0, that means no clear start point, choose any

        while (it.hasNext()) {
            Map.Entry<String, Integer> curr = it.next();
            String key = curr.getKey();
            int value = curr.getValue();

            if (value > 0)
                return key;                //if there is a clear start point
            else if (value == 0)
                startPoint = key;   //if there is no clear start point, return one with degree 0
        }
        return startPoint;
    }

    /**
     * @param arraylist of Graph.DirectedGraaph.Ticket[from,to] from input
     *                  generate a directed cyclic graph from arraylist of input tickets
     */
    public Graph createGraph(ArrayList<Ticket> tickets) {

        Graph gr = new Graph();
        gr.vertexes = new ArrayList<Vertex>();
        Set<String> nodeValues = new HashSet<String>();

        for (Ticket t : tickets) {
            String from = t.from;
            String to = t.to;
            boolean isPresentFrom = nodeValues.contains(from);
            boolean isPresentTo = nodeValues.contains(to);
            Vertex vtFrom, vtTo;

            if (!isPresentFrom) {
                vtFrom = new Vertex(from);
                nodeValues.add(from);
                gr.vertexes.add(vtFrom);
            }
            if (!isPresentTo) {
                vtTo = new Vertex(to);
                nodeValues.add(to);
                gr.vertexes.add(vtTo);
            }

            vtFrom = gr.getVertex(from);
            vtTo = gr.getVertex(to);
            vtFrom.edges.add(new Edge(0, vtTo));
        }
        gr.printGraph();
        return gr;
    }
}


/* Solution ->
   1) Create a HashMap of given pair of tickets.  Let the created 
   HashMap be 'dataset'. Every entry of 'dataset' is of the form 
   "from->to" like "Chennai" -> "Banglore"

   2) Find the starting point of itinerary.
     a) Create a reverse HashMap.  Let the reverse be 'reverseMap'
        Entries of 'reverseMap' are of the form "to->form". 
        Following is 'reverseMap' for above example.
        "Banglore"-> "Chennai" 
        "Delhi"   -> "Bombay" 
        "Chennai" -> "Goa"
        "Goa"     ->  "Delhi"
 
     b) Traverse 'dataset'.  For every key of dataset, check if it
        is there in 'reverseMap'.  If a key is not present, then we 
        found the starting point. In the above example, "Bombay" is
        starting point.

   3) Start from above found starting point and traverse the 'dataset' to print itinerary.
   All of the above steps require O(n) time so overall time complexity is O(n).
*/
