package Hash;

import Graph.DirectedGraaph.FlightItineraryProblem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/*
 * Find Itinerary from a given list of ticket
 * Given a list of tickets, find itinerary in order using the given list.
 * 
 * Input:
 * "Chennai" -> "Banglore"
 * "Bombay" -> "Delhi"
 * "Goa"    -> "Chennai"
 * "Delhi"  -> "Goa"
 * 
Output: 
Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,

Note: For input use to be non cyclic - use Hashtable. Go to Hash Source folder
It may be assumed that the input list of tickets is not cyclic and there is one ticket from every city except final destination.
*
*/

public class FilghtIternaryProblem {

	public void getItinerary(HashMap<String,String> hmap){

		if(hmap.size()==0){
			System.out.println("Sorry, please provide tickets");
			return;
		}
		String startPoint  = "";
		HashMap<String,String> reverseMap = new HashMap<String,String>(); //create a reverse map
		Iterator<Entry<String, String>> it = hmap.entrySet().iterator(); //iterator for input hmap
		
		while(it.hasNext()){ //iterate over hmap to populate reverseMap
			Entry<String,String> ticket = it.next();
			reverseMap.put(ticket.getValue(), ticket.getKey());
		}
		it = hmap.entrySet().iterator(); 	//reset iterator
		
		//for every key in hmap, see if reverseMap has the same key (to check if there is a path), if not that is the starting location
		while(it.hasNext()){
			Entry<String,String> ticket = it.next();
			if(! reverseMap.containsKey(ticket.getKey()))
				startPoint = ticket.getKey();
		}
		
		//now we have starting point, traverse the map and print results
		while(hmap.containsKey(startPoint)){
			System.out.println(startPoint+"->"+hmap.get(startPoint));
			startPoint = hmap.get(startPoint);
		}
	}
	
	public static void main(String[] args){
		FlightItineraryProblem obj = new FlightItineraryProblem(); //Create obj
		HashMap<String,String> hmap = new HashMap<String,String>(); //input in form of Hash map
		hmap.put("Chennai","Banglore");
		hmap.put("Bombay", "Delhi");
		hmap.put("Goa", "Chennai");
		hmap.put("Delhi", "Goa");	
		//obj.getItinerary(hmap);
	}

}
