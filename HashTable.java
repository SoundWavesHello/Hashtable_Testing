/*
 * 		Author: Kevin Lane
 * 		Lab 9
 * 		Last Modified: 11/28/16
 * 
 * 		This class contains methods to do the following:
 * 
 * 		1) 	Construct a chained HashTable class object when 
 * 			given the number of items and the load factor.
 * 
 * 		2)	Construct a chained HashTable class object when 
 * 			given the number of items and the size of the 
 * 			table.
 * 
 * 		3)	Insert an entry into the HashTable.
 * 
 * 		4)	Find an entry in the HashTable when given its
 * 			key.
 * 
 * 		5)	Remove an entry in the HashTable when given
 * 			its key.
 * 
 * 		6)	Compress an entry's hash code.
 * 
 * 		7)	Return whether or not the HashTable is empty.
 * 
 * 		8)	Calculate the variance in chain length.
 * 
 * 		9)	Return the load factor of the HashTable.
 * 
 * 		10)	Return the number of buckets.
 * 
 */

import java.util.ArrayList;

public class HashTable {
	
	private ArrayList<Entry>[] table;
	private int n;
	private int numBuckets;
	private double loadFactor;
	
	
	
	// Purpose: Construct a HashTable
	// Parameters: The number of items and the
	//			load factor
	// Return: None
	
	public HashTable(int numItems, double loadFactor){
		this.loadFactor = loadFactor;
		
		// don't adjust n, as that gets incremented
		// every time an item is added to the HashTable
		
		// need to cast double so that integer division
		// does not occur
		double temp = (double)numItems / loadFactor;
		numBuckets = (int)temp;
		
		// create the hash table
		table = new ArrayList[numBuckets];
		
		// fill the hash table with ArrayLists
		for (int i = 0; i < table.length; i ++){
			table[i] = new ArrayList();
		}
	}
	
	
	// Purpose: Construct a HashTable
	// Parameters: The number of buckets
	// Return: None
	
	public HashTable(int numBuckets){
		n = 0;
		this.numBuckets = numBuckets;
		
		// cast n and numBuckets as doubles so
		// that integer division doesn't take place
		loadFactor = (double)n / (double)numBuckets;
		
		// create the hash table
		table = new ArrayList[numBuckets];
		
		// fill the hash table with ArrayLists
		for (int i = 0; i < table.length; i ++){
			table[i] = new ArrayList();
		}
	}
	
	
	
	// Purpose: Insert an entry into the HashTable
	// Parameters: The board and its value
	// Return: None
	
	public void insert(GameBoard board, int boardValue){
		
		Entry entry = new Entry(board, boardValue);
		
		// get the compressed hashCode
		int index = compress(entry.getBoard().hashCode());
		
		// insert the value into the table at that index
		// if there is a collision, it just gets added
		// to the end of the chain there
		
		table[index].add(entry);
		
		// adjust n and loadFactor
		
		n ++;
		loadFactor = (double)n / (double) numBuckets;
	}
	
	
	
	// Purpose: Find an entry into the HashTable
	// Parameters: The board
	// Return: The entry
	
	public Entry find(GameBoard target){
		
		// compress the hashCode
		int index = compress(target.hashCode());
		
		// look at that index to see if anything is there
		
		if (table[index].size() == 0){
			// if there is nothing at that index
			return null;
		}
		
		else{
			
			// if there is something here, iterate through
			// the chain at this index
			
			for (int i = 0; i < table[index].size(); i ++){

				// if the targetGameBoard is equal to the
				// GameBoard in a specific entry, return
				// the entry
				if(target.equals(table[index].get(i).getBoard())){
					return table[index].get(i);
				}
			}
		}
		
		// if we go through the entire chain and there's
		// nothing there
		return null;
	}
	
	
	
	// Purpose: Find and remove an entry from
	//			the HashTable
	// Parameters: The board
	// Return: The entry
	
	public Entry remove(GameBoard target){
		
		// compress the hashCode
		int index = compress(target.hashCode());
		
		// look at that index to see if anything is there
		
		if (table[index].size() == 0){
			// if there is nothing at that index
			return null;
		}
		
		else{
			// if there is something here, iterate through
			// the chain at this index
			
			for (int i = 0; i < table[index].size(); i ++){

				// if the targetGameBoard is equal to the
				// GameBoard in a specific entry, return
				// and remove the entry
				if(target.equals(table[index].get(i).getBoard())){
					
					// adjust numBuckets and loadFactor
					
					n --;
					loadFactor = n / numBuckets;
					
					return table[index].remove(i);
				}
			}
		}
		
		// if we go through the entire chain and there's
		// nothing there
		return null;		
	}
	
	
	
	// Purpose: Compress a hash code
	// Parameters: The hash code
	// Return: A compressed hash code
	
	public int compress(int hashCode) {
		 return (((13 * hashCode) + 37) 
				 % 16908799) % numBuckets;
	}
	
	
	
	// Purpose: Determine whether the hash table
	//			is empty or not
	// Parameters: None
	// Return: If it is empty
	
	public boolean isEmpty(){
		
		// if there are more than 0 items
		if (n > 0){
			return false;
		}
		
		return true;
	}

	
	
	// Purpose: Calculate the variance in chain
	//			length
	// Parameters: None
	// Return: The variance
	
	public double calcVariance(){
		
		// keep track of the total in
		// the summation
		double varTotal = 0;
		
		for(int i = 0; i < numBuckets; i ++){
			
			double curr = table[i].size() - loadFactor;
			curr = Math.pow(curr, 2);
			
			varTotal += curr;
		}
		
		double variance = (1.0 / (double)numBuckets) 
				* varTotal;
		
		return variance;
	}
	
	
	
	// Purpose: Get the load factor
	// Parameters: None
	// Return: The load factor
	
	public double getLoadFactor(){
		return loadFactor;
	}
	
	
	
	// Purpose: Get the number of buckets
	// Parameters: None
	// Return: The number of buckets
	
	public int getNumBuckets(){
		return numBuckets;
	}
	

}
