/* 
 * Stephen Majercik
 * 16 November 2016
 *
 * This class allows the user to create simple "game boards" that
 * are 2-dimensional arrays of integers. Note that the class provides
 * functions to both:
 *
 * 		1) calculate the "value" of a game board, and
 * 		2) calculate the hash code of a game board
 *
 * The first one is the value that is associated with the game board
 * in the <key, value> pair that is going to be put into the hash table.
 * The second one is the hash code that is compressed to find the index
 * where the <key,value> pair will go when it it put into the hash table.
 *
 *
 */
 
 
import java.util.Random;

public class GameBoard {

	// GameBoards are 4x4 arrays of integers.
	private static final int DIMENSION = 4;
	private int[][] board = new int[DIMENSION][DIMENSION];

	// To generate random board entries.
	private static Random rand = new Random();

	
	// Create a GameBoard by assigning random values [0..2]
	// inclusive to the board array.
	//
	public GameBoard() {
		
		for (int row = 0; row < DIMENSION; ++row) {
			for (int col = 0; col < DIMENSION; ++col) {
				board[row][col] = rand.nextInt(3);
			}
		}
	}


	// Purpose: Return a value at the specified spot in the
	//          GameBoard..
	// Parameters: The row and column of the value to be returned.
	// Return Value: The value at that row and column.
	//
	int elementAt(int row, int col) {
		return board[row][col];
	}


	// Purpose: Compare GameBoard parameter to GameBoard
	//          in object.
	// Parameters: The GameBoard to be compared to the
	//             GameBoard in the object.
	// Return Value: True if the GameBoard passed in is 
	//               the same as the board in this GameBoard 
	//               object; otherwise, false.
	//
	public boolean equals(GameBoard board) {

		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				if (this.board[r][c] != board.elementAt(r, c))
					return false;
			}
		}

		return true;
	}


	// Purpose: Computes the value of the GameBoard.
	// Parameters: None.
	// Return Value: The value of the GameBoard.
	//
	// NOTE: The value is defined to be the number of 
	//       1s in the array minus the number of 2s in 
	//       the array.
	//
	public int boardValue() {

		int numOnes = 0;
		int numTwos = 0;
		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				if (board[r][c] == 1) {
					++numOnes;
				}
				else if (board[r][c] == 2) {
					++numTwos;
				}
			}
		}

		return numOnes - numTwos;
	}


	// Purpose: Compute the hash code of the GameBoard.
	// Parameters: None.
	// Return Value: The hash code.
	//
	public int hashCode() {

		int hashVal = 0;

		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				hashVal += ((127 * hashVal) + board[r][c]) % 16908799;
			}
		}

		return hashVal;
	}


	// Purpose: Print the GameBoard.
	// Parameters: None.
	// Return Value: None.
	//
	public void print() {

		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}




}
