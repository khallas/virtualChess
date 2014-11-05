package Pieces;

public class King extends AbstractPiece{

	public King(boolean isWhite) {
		super(isWhite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String draw() {
		String unicode;
		if(isWhite)
			unicode = "\u2654";
		else 
			unicode = "\u265A";
		return unicode;
	}

	@Override
	public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
		int delta_x = Math.abs(destRow-srcRow);
		int delta_y = Math.abs(destCol-srcCol);			
		
		if(delta_x > 1 || delta_y > 1)
			//only allowing there to be a change of 1 in the x and y direction for the King. 
			{
				System.out.println("King Error!");
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
