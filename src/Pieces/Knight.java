package Pieces;

public class Knight extends AbstractPiece{

	public Knight(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public String draw() {
		String unicode;
		if(isWhite)
			unicode = "\u2658";
		else 
			unicode = "\u265E";
		return unicode;
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		int delta_x = Math.abs(destRow-srcRow);
		int delta_y = Math.abs(destCol-srcCol);			
		if(!((delta_x==2 && delta_y==1)||(delta_x==1 && delta_y==2))){
			System.out.println("Knight Error!");
			return false;
		}

		return true;
	}

	@Override
	public int relativeValue() {
		return 3;
	}
	

}
