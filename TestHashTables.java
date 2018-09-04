/*  
 * 		Author: Kevin Lane
 * 		Lab 9
 * 		Last Modified: 11/28/16
 * 
 * 		This class contains methods to do the following:
 * 
 * 		1)	A main method to test the HashTable class by 
 * 			timing how long it takes to run the same number
 *			of searches on HashTables with the same number
 *			of items and different load factors.  It also
 *			tests the insertion, find, remove, and isEmpty
 *			methods in the HashTable class.
 *
 *		2)	Fill an empty HashTable with random entries.
 *
 *		3)	Check to see if all of GameBoards in an array
 *			of four GameBoards are different.
 */


public class TestHashTables {

	// Constants for Part 1
	private static final int PART_1_MAX_LOAD_FACTOR = 40;
	private static final int PART_1_LOAD_FACTOR_DECREMENT = 4;
	private static final double NUM_NANOS_IN_SEC = 1000000000.0;

	// Constants for Part 2
	private static final int PART_2_HASH_TABLE_SIZE = 100;
	private static final int PART_2_NUM_BOARDS = 4;


	public static void main(String[] args) {

		int numBoards = 100000;
		int numSearches = 10000000;
		System.out.println(numBoards + " GameBoards inserted; " 
				+ numSearches + " searches\n");
		
		long startTime, endTime;
		double seconds;

		// OUTPUT: PART 1
		// --------------
		//
		// *** Create required table of statistics with varying load factors.


		// iterate through possible load factors
		
		for (int decrease = PART_1_MAX_LOAD_FACTOR;
				decrease > 0; 
				decrease -= PART_1_LOAD_FACTOR_DECREMENT){
			
			// create a table with that load factor
			
			HashTable currTable = new HashTable(numBoards, decrease);
			initTable(currTable, numBoards);
			
			// start timing
			startTime = System.nanoTime();	
			
			// run the numSearches
			
			GameBoard board = new GameBoard();
			
			for (int i = 0; i < numSearches; i ++){
				
				currTable.find(board);
			}
			
			// end timing
			endTime = System.nanoTime();
			seconds = (endTime - startTime) / NUM_NANOS_IN_SEC;
			
			System.out.printf("%5d buckets 		LF = %2d		var = %6f		time = %10f %n",
					currTable.getNumBuckets(), (int)currTable.getLoadFactor(), currTable.calcVariance(), seconds);
			
		}
		




		// OUTPUT: PART 2
		// --------------

		// *** Make a hash table called "table" of size PART_2_HASH_TABLE_SIZE.
		
		HashTable table = new HashTable(PART_2_HASH_TABLE_SIZE);
		
		// Make an array of PART_2_NUM_BOARDS GameBoards, all of which are different.
		GameBoard[] boards = new GameBoard[PART_2_NUM_BOARDS];
		for (int i = 0; i < PART_2_NUM_BOARDS; ++i) {
			boards[i] = new GameBoard();
		}
		while (!allDiff(boards)) {
			for (int i = 0; i < PART_2_NUM_BOARDS; ++i) {
				boards[i] = new GameBoard();
			}
		}

		// *** Insert the first two boards into the hash table.

		table.insert(boards[0], boards[0].boardValue());
		table.insert(boards[1], boards[1].boardValue());

		System.out.println("ADDED BOARDS 1 AND 2 TO THE HASH TABLE\n");
		System.out.println("SEARCHES AFTER INSERTIONS BUT BEFORE REMOVALS\n");
		
		System.out.println("Searching for Board 1 (is in the hash table).  Board 1:\n");
		boards[0].print();
		Entry e = table.find(boards[0]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Searching for Board 2 (is in the hash table).  Board 2:\n");
		boards[1].print();
		e = table.find(boards[1]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Searching for Board 3 (is NOT in the hash table).  Board 3:\n");
		boards[1].print();
		e = table.find(boards[2]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}


		System.out.println("Searching for Board 4 (is NOT in the hash table).  Board 4:\n");
		boards[1].print();
		e = table.find(boards[3]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("REMOVALS\n");
		System.out.println("Removing Board 1 (which is in the hash table):\n");
		boards[0].print();
		e = table.remove(boards[0]); 
		System.out.println("Here's what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Removing Board 2 (which is in the hash table):\n");
		boards[1].print();
		e = table.remove(boards[1]);
		System.out.println("Here's what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Removing Board 3 (which is NOT in the hash table):\n");
		boards[2].print();
		e = table.remove(boards[2]);
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Removing Board 4 (which is NOT in the hash table):\n");
		boards[3].print();
		e = table.remove(boards[3]);
		System.out.println("Here's what was returned from the remove:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("SEARCHES AFTER REMOVALS\n");

		System.out.println("Searching for Board 1 (no longer in the hash table).  Board 1:\n");
		boards[0].print();
		e = table.remove(boards[0]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}
		
		System.out.println("Searching for Board 2 (no longer in the hash table).  Board 2:\n");
		boards[1].print();
		e = table.remove(boards[1]);
		System.out.println("Here's what was returned from the find:\n");
		if (e != null) {
			e.getBoard().print();
		}
		else {
			System.out.println("Entry is not in the hash table.");
		}

		System.out.println("Call to isEmpty function returns:  " + table.isEmpty() + "\n");


	}




	// Purpose: Create and insert specified number of GameBoards
	//          into hash table.
	// Parameters: The hash table and the number of GameBoards to
	//             insert.
	// Return Value: None.
	//
	// NOTE: The value associated with each board is computed by 
	//       the boardValue function in the GameBoard class.
	//
	public static void initTable(HashTable table, int numBoards) {

		for (int i = 0; i < numBoards; i++) {
			GameBoard gameBoard = new GameBoard();
			table.insert(gameBoard, gameBoard.boardValue());
		}

	}


	// Purpose: To check if all the GameBoards in a given array of
	//          GameBoards are different.
	// Parameters: The array of GameBoards.
	// Return Value: True, if they are all different; otherwise,
	//               false.
	//
	public static boolean allDiff(GameBoard[] boards) {

		return
				!boards[0].equals(boards[1]) &&
				!boards[0].equals(boards[2]) &&
				!boards[0].equals(boards[3]) &&
				!boards[1].equals(boards[2]) &&
				!boards[1].equals(boards[3]) &&
				!boards[2].equals(boards[3]);


	}




}


