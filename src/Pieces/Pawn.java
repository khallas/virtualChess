package Pieces;

public class Pawn extends AbstractPiece{

	public Pawn(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String draw() {
		// TODO Auto-generated method stub
		String unicode;
		if(isWhite)
			unicode = "\u2659";
		else 
			unicode = "\u265F";
		return unicode;
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		//=====================RE-USING OLD CODE=================
				//if ((chessboard_main2[srcCol][srcRow] == pieces.Chessmen.W_P)||(chessboard_main2[srcCol][srcRow] == pieces.Chessmen.B_P))
				//above condition is for checking if we are dealing with a pawn. 
				//{
					/*if(destCol-srcCol == 1 || srcCol-destCol==1){
						//if we are moving diagonally - 
						if(chessboard_main2[destCol][destRow] == pieces.Chessmen.EMPTY){ 
							//can only move if we make a kill 
							return false;}
					}				
					else*/ if (destCol!=srcCol){
						//condition to check that we don't move diagonally. 
						System.out.println("Pawn move error!!");
						return false;
					}
					if ((isWhite && srcRow != 1)||(!isWhite && srcRow != 6)){
						//Check if it isn't the first turn.
						if ((isWhite && (destRow-srcRow != 1)) || !isWhite && (srcRow - destRow != 1)){
							//If not the first move, only allow piece to move forward by one. 
							System.out.println("Pawn move error!");
							return false;
						}
					}
					else{
						//If it is the first turn,
						if (!((isWhite && (destRow-srcRow == 2)) || (!isWhite && (srcRow - destRow == 2))
								|| (isWhite && (destRow-srcRow == 1)) || (!isWhite && (srcRow - destRow == 1))))				
						{
							//Only allow piece to move forward by one or two squares.
							System.out.println("Pawn move error");
							return false;
						}
					}
			
				//}//end dealing with pawns
		
			return true;
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 1;
	}
	

}
