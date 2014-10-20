package Pieces;

public abstract class AbstractPiece {
	boolean isWhite;
	/**
	* If piece is white , set true, false otherwise.
	* @param isWhite
	*/
	public AbstractPiece(boolean isWhite){
	this.isWhite = isWhite;
	}
	/**
	* Returns true if white, false otherwise.
	* return
	*/
	public boolean isWhite ( ) {
	return isWhite ;
	}

	/**
 	* Draws given piece into the console.
	 * @return 
	*/
	public abstract String draw();
	
	/**
	* Checks whether a given move is valid. Returns true if valid, false otherwise .
	*/
	public abstract boolean isMoveValid (int srcRow , int srcCol ,
	int destRow , int destCol );
	
	/**
	* Returns relative chess piece value of this chess-man.
	* @return
	*/
	public abstract int relativeValue();
	
}
