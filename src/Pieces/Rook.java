package Pieces;

public class Rook extends AbstractPiece{

	public Rook(boolean isWhite) {
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
	public String draw() {
		String unicode;
		if(isWhite)
			unicode = "\u2656";
		else 
			unicode = "\u265C";
		return unicode;
	}
	@Override
	public int relativeValue() {
		// TODO Auto-generated method stub
		return 6;
	}

}
