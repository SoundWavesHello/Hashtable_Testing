/* 
 * Stephen Majercik
 * 16 November 2016
 *
 * Objects of this class contain a <GameBoard, value-of-GameBoard> 
 * pair to be inserted into a hash table
 *
 */


public class Entry {
	
	// The <GameBoard, value-of-GameBoard> items.
	private GameBoard board;
	private int value;


	// Default is null GameBoard and 0 value.
	//
	public Entry() {
		board = null;
		value = 0;
	}

	// Set instance variables to parameter values.
	//
	public Entry(GameBoard board, int value) { 
		this.board = board; 
		this.value = value; 
	}


	// Getters for the instance variables.
		
	GameBoard getBoard() { 
		return board; 
	}

	int getValue() { 
		return value; 
	} 


}
