package Pieces;

public class RookMoves extends AbstractPiece{

	public RookMoves(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}
	public boolean isMoveValid (int srcRow , int srcCol ,
			int destRow , int destCol ){
		int delta_x = destRow-srcRow;
		int delta_y = destCol-srcCol;			
		if(!(delta_x==0 || delta_y==0)){
			System.out.println("Rook Error!");
			return false;
		}
				
		
		return true;
		
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
