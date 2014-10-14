package Pieces;

public class BishopMoves extends AbstractPiece{

	public BishopMoves(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		int delta_x = destRow-srcRow;
		int delta_y = destCol-srcCol;			
		if(Math.abs(delta_x) != Math.abs(delta_y)){
			System.out.println("Bishop Error!");
			return false;
		}		
		return true;
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
