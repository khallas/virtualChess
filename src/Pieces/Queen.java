package Pieces;

public class Queen extends AbstractPiece{

	public Queen(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String draw() {
		// TODO Auto-generated method stub
		String unicode;
		if(isWhite)
			unicode = "\u2655";
		else 
			unicode = "\u265B";
		return unicode;
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		int delta_x = Math.abs(destRow-srcRow);
		int delta_y = Math.abs(destCol-srcCol);			
		if(delta_x == 0 || delta_y==0)
		{
			if(!(delta_x==0 || delta_y==0)){
				System.out.println("Queen Error!");
				return false;
			}}
		else if(delta_x!=delta_y){
			System.out.println("Queen Error!");
			return false;
		}
		return true;
	}

	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 9;
	}
	

}
