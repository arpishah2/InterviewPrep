package Hash;/*
 * HashTable ->
 * 1. Good for modeling relationship from one item to other 
 * 2. good for catching duplicates
 * 3. Very fast search, insert, delete
 * 4. Idea -> Hash function produces index for a key where value is to be stored
 * 
 * Resize when load factor > .75
 * Load factor = total values in HT / Total slots in HT
 * 
 * HashFunction consideration ->
 * 1. Same index for same key
 * 2. Different index for different key (though there can be collision)
 * 3. Index within the size of hashtable
 * 
 * HashTable Applications ->
 * 1. Phone contact lookup
 * 2. DNS Resolution (web address -> ip address)
 * 3. Caching
 */

/*
 * Hash function to be used is the remainder of division by size.
 */
class HashEntry{
	private int key;
	private int value;
	
	HashEntry(){}
	HashEntry(int k, int v){
		key = k;
		value = v;
	}
	public int getKey(){
		return key;
	}
	public int getValue(){
		return value;
	}
}

public class HashImpl {
	int size;
	HashEntry[] table;
	
	public HashImpl() {
		size = 10;
		table = new HashEntry[size];
	}
	
	public HashImpl(int size) {
		this.size = size;
		table = new HashEntry[size];
	}
	
	
	//get value corresponding to a given key
	public int get(int key){
		int hash = key%size; //remainder - index location
		while(table[hash]!=null){ 
			if(table[hash].getKey() == key)	 //Same key is present at desired index
				return table[hash].getValue();
			++hash;							//Otherwise, find the desired location
			hash = hash%size;
		}
		return table[hash].getValue();		//return the value at location
	}
	
	//get index for key, place value at that index in table[]
	
	public void put(int key, int val){
		int hash = key%size; 		//remainder - index location
		while(table[hash]!=null){ 	//Collision
			++hash;
			hash = hash%size;
		}
		table[hash] = new HashEntry(key,val);
	}

}
