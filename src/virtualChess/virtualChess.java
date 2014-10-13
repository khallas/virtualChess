package virtualChess;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class virtualChess {

	public static final int boardsize = 8;
	public enum Chessmen{
		W_Ki,
		W_Q,
		W_R,
		W_B,
		W_Kn,
		W_P,
		B_Ki,
		B_Q,
		B_R,
		B_B,
		B_Kn,
		B_P,
		EMPTY
		}
	
	public static boolean whiteTurn = true; //this is a public counter boolean to keep track of the turn
	static Chessmen[][] chessboard_main = new Chessmen[boardsize][boardsize];

	public static void main (String[] args) throws UnsupportedEncodingException {
		chessboard_main = blankPopulate(chessboard_main);
		chessboard_main = initiateBoard(chessboard_main);
		print(chessboard_main);
		while(kingCheck(chessboard_main)){
			chessboard_main = move(chessboard_main);
			print(chessboard_main);
		}
		}
	
	public static Chessmen[][] blankPopulate(Chessmen[][] board){
		for (int i = 0; i<board.length; i++){
			for (int j = 0; j<board[i].length; j++){
				board[i][j] = Chessmen.EMPTY;
			}
			}
		return board;
		}
	
	public static void print(Chessmen[][] board) throws UnsupportedEncodingException{
	for(int i = board.length-1; i>=0; i--){
		int row_num = i+1;
		System.out.printf(row_num + "\t");
		for(int j = 0; j<board[i].length; j++){
			if (board[j][i] != Chessmen.EMPTY){
				PrintStream out = new PrintStream(System.out, true, "UTF-8");
				out.printf(printUnicode(board[j][i]) + "\t");
				//System.out.printf(board[j][i] + "\t");
			}
			else System.out.printf("\t");
			}
		System.out.println("\n");
		}
	System.out.println("\t a\t b\t c\t d\t e\t f\t g\t h");
	}
	
	public static Chessmen[][] initiateBoard(Chessmen[][] board){
	board[0][0] = Chessmen.W_R;
	board[1][0] = Chessmen.W_Kn;
	board[2][0] = Chessmen.W_B;
	board[3][0] = Chessmen.W_Q;
	board[4][0] = Chessmen.W_Ki;
	board[5][0] = Chessmen.W_B;
	board[6][0] = Chessmen.W_Kn;
	board[7][0] = Chessmen.W_R;
	
	board[0][7] = Chessmen.B_R;
	board[1][7] = Chessmen.B_Kn;
	board[2][7] = Chessmen.B_B;
	board[3][7] = Chessmen.B_Q;
	board[4][7] = Chessmen.B_Ki;
	board[5][7] = Chessmen.B_B;
	board[6][7] = Chessmen.B_Kn;
	board[7][7] = Chessmen.B_R;
	
	for(int i = 0; i<boardsize;  i++){
	board[i][1] = Chessmen.W_P;
	board[i][6] = Chessmen.B_P;
	}
	
	return board;
	}
	
	public static Chessmen[][] move(Chessmen[][] board){
		//Stating whose turn it is - 
		if (whiteTurn) System.out.println("White turn - ");
		else System.out.println("Black turn - ");
			
		//Getting input from the user in the standard chess lingo
		Scanner sc = new Scanner(System.in);
		System.out.println("Which square to move from?");
		String from = sc.next();
		System.out.println("Which square to move to?");
		String to = sc.next();
		
		//converting this to array address for chessboard
		char letterFrom = from.charAt(0);
		char letterTo = to.charAt(0);
		int numFrom = Character.getNumericValue(from.charAt(1))-1;
		int numTo = Character.getNumericValue(to.charAt(1))-1;
		int[] from_int = new int[2];
		int[] to_int = new int[2];
		from_int[1] = numFrom;
		to_int[1] = numTo;
		
		switch(letterFrom){
		case 'a': from_int[0] = 0; break;
		case 'b': from_int[0] = 1; break;
		case 'c': from_int[0] = 2; break;
		case 'd': from_int[0] = 3; break;
		case 'e': from_int[0] = 4; break;
		case 'f': from_int[0] = 5; break;
		case 'g': from_int[0] = 6; break;
		case 'h': from_int[0] = 7; break;
		default : from_int[0] = 10;
		}
		switch(letterTo){
		case 'a': to_int[0] = 0; break;
		case 'b': to_int[0] = 1; break;
		case 'c': to_int[0] = 2; break;
		case 'd': to_int[0] = 3; break;
		case 'e': to_int[0] = 4; break;
		case 'f': to_int[0] = 5; break;
		case 'g': to_int[0] = 6; break;
		case 'h': to_int[0] = 7; break;
		default : to_int[0] = 10;
		}
		
		//This is where I am making the move after checking that it doesn't violate rules
		if (rules(board, from_int, to_int)){
			board[to_int[0]][to_int[1]] = board[from_int[0]][from_int[1]];
			board[from_int[0]][from_int[1]] = Chessmen.EMPTY;
			whiteTurn = !whiteTurn;
			}		
		
		//check for pawnTransformation 
		board = pawnTransform(board, to_int, from_int);
		return board;
	}

	public static boolean kingCheck(Chessmen[][] board) 
	{
		/* This is a method which checks whether both kings are still on the board.
		 * If that is the case, it returns true and lets the game go on. If not, 
		 * it checks which one is missing and declares the other the 
		 * victor and ends the game. 
		 */
		int whiteCounter = 0;
		int blackCounter = 0;
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[i].length; j++)
			{
				if(board[i][j].equals(Chessmen.W_Ki)) 
					whiteCounter++;
				if (board[i][j].equals(Chessmen.B_Ki))
					blackCounter++;
			}
		}
		if ((blackCounter + whiteCounter) == 2) return true;
		else if (blackCounter == 0){
			System.out.println("White wins!!!");
			return false;
		}
		else {
			System.out.println("Black wins!!!");
			return false;
		}		
	}
	
	public static boolean rules (Chessmen[][] board, int[] from, int to[]){
		
		//Making lists of white and black pieces --
		List<Chessmen> whitePieces = new ArrayList<Chessmen>();
		whitePieces.add(Chessmen.W_Ki);
		whitePieces.add(Chessmen.W_Q);
		whitePieces.add(Chessmen.W_R);
		whitePieces.add(Chessmen.W_Kn);
		whitePieces.add(Chessmen.W_B);
		whitePieces.add(Chessmen.W_P);		
		List<Chessmen> blackPieces = new ArrayList<Chessmen>();
		blackPieces.add(Chessmen.B_Ki);
		blackPieces.add(Chessmen.B_Q);
		blackPieces.add(Chessmen.B_R);
		blackPieces.add(Chessmen.B_Kn);
		blackPieces.add(Chessmen.B_B);
		blackPieces.add(Chessmen.B_P);
		// ended making groups
		
		//Rule 0 - the 'From' and 'To' are within bounds 
		if (from[0]<0 || from[0]>7 || from[1]<0 || from[1]>7 || to[0]<0 || to[0]>7 || to[1]<0 || to[1]>7){
			System.out.println("Move out of bounds!!");
			return false;
		}
			
		
		// Rule 1 - Initial space can't be labeled empty
		if (board[from[0]][from[1]] == Chessmen.EMPTY){
			System.out.println("Intial square is empty!!");
			return false;
		}
		
		//Rule 2 - Check whether if player is killing his own piece - 
		if ((whitePieces.contains(board[from[0]][from[1]]) && whitePieces.contains(board[to[0]][to[1]]) || 
				(blackPieces.contains(board[from[0]][from[1]]) && blackPieces.contains(board[to[0]][to[1]]))))
				{
			System.out.println("Can't kill your own piece!!");
			return false;			
				}
		
		//Rule 3 - Check if the move is according to the turn - 
		if ((whiteTurn && blackPieces.contains(board[from[0]][from[1]])) || !whiteTurn && whitePieces.contains(board[from[0]][from[1]])){
			System.out.println("Please play according to turn!!");
			return false;
		}
			
		//Rule 4 ------------------------------- PAWN RULES!!! ----------------------------------
		/*Note - this is still work in progress. Currently the only rule is that pawns can move
		 * forward by 1 space. 
		 * There are rules yet to be included 
		 * 1. Allow double move when first move - done! 
		 * 2. Restrict move if there is something in front - might do this in a general way for all pieces
		 * except knights.
		 * 3. Allow killing diagonally one square if available - done!!
		 * 4. Convert to another piece when reach the other side of the board - this is done properly in a method 
		 * called pawnTransform.
		 */
		if ((board[from[0]][from[1]] == Chessmen.W_P)||(board[from[0]][from[1]] == Chessmen.B_P))
		//above condition is for checking if we are dealing with a pawn. 
		{
			if(to[0]-from[0] == 1 || from[0]-to[0]==1){
				//if we are moving diagonally - 
				if(board[to[0]][to[1]] == Chessmen.EMPTY){ 
					//can only move if we make a kill 
					return false;}
			}				
			else if (to[0]!=from[0]){
				//condition to check that we don't move diagonally. 
				System.out.println("Pawn move error!!");
				return false;
			}
			if (from[1] != 1){
				//Check if it isn't the first turn.
				if ((whiteTurn && (to[1]-from[1] != 1)) || !whiteTurn && (from[1] - to[1] != 1)){
					//If not the first move, only allow piece to move forward by one. 
					System.out.println("Pawn move error!");
					return false;
				}
			}
			else{
				//If it is the first turn,
				if (!((whiteTurn && (to[1]-from[1] == 2)) || !whiteTurn && (from[1] - to[1] == 2)
						|| (whiteTurn && (to[1]-from[1] == 1)) || !whiteTurn && (from[1] - to[1] == 1)))				
				{
					//Only allow piece to move forward by one or two squares.
					System.out.println("Pawn move error");
					return false;
				}
			}
	
		}//end dealing with pawns
		
		
		//Rule 5 ------------------------King Rules---------------------------
		if ((board[from[0]][from[1]] == Chessmen.W_Ki)||(board[from[0]][from[1]] == Chessmen.B_Ki))
		//above condition checks if we are making a move with a king
		{
			if(to[0]-from[0] > 1 || to[1]-from[1] > 1 || to[0]-from[0] < (-1) || to[1]-from[1] < (-1) )
			//only allowing there to be a change of 1 in the x and y direction for the King. 
			{
				System.out.println("King Error!");
				return false;
			}
		}
		
		//Rule 6 ------------------------Bishop Rules---------------------------
		if ((board[from[0]][from[1]] == Chessmen.W_B)||(board[from[0]][from[1]] == Chessmen.B_B))
		//above condition checks if we are making a move with a bishop
		{		
			int delta_x = to[0]-from[0];
			int delta_y = to[1]-from[1];			
			if(Math.abs(delta_x) != Math.abs(delta_y)){
				System.out.println("Bishop Error!");
				return false;
			}
		}
		
		//Rule 7 ------------------------Rook Rules---------------------------
		if ((board[from[0]][from[1]] == Chessmen.W_R)||(board[from[0]][from[1]] == Chessmen.B_R))
		//above condition checks if we are making a move with a rook
		{		
			int delta_x = to[0]-from[0];
			int delta_y = to[1]-from[1];			
			if(!(delta_x==0 || delta_y==0)){
				System.out.println("Rook Error!");
				return false;
			}
		}

		//Rule 8 ------------------------Knight Rules---------------------------
		if ((board[from[0]][from[1]] == Chessmen.W_Kn)||(board[from[0]][from[1]] == Chessmen.B_Kn))
		//above condition checks if we are making a move with a knight
		{		
			int delta_x = Math.abs(to[0]-from[0]);
			int delta_y = Math.abs(to[1]-from[1]);			
			if(!((delta_x==2 && delta_y==1)||(delta_x==1 && delta_y==2))){
				System.out.println("Knight Error!");
				return false;
			}
		}
		
		//Rule 8 ------------------------Queen Rules---------------------------
				if ((board[from[0]][from[1]] == Chessmen.W_Q)||(board[from[0]][from[1]] == Chessmen.B_Q))
				//above condition checks if we are making a move with a queen
				{		
					int delta_x = Math.abs(to[0]-from[0]);
					int delta_y = Math.abs(to[1]-from[1]);			
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
				}

		//Rule 4 - Check if there is something in the way of the move (except Knight)
		//if ((board[from[0]][from[1]] != Chessmen.W_Kn)||(board[from[0]][from[1]] != Chessmen.B_Kn))
			
			
			
			
		
		return true;
	}
	
	public static String printUnicode(Chessmen piece){
		String unicode = "";
		switch(piece){
		case W_Ki : unicode = "\u2654"; break;
		case W_Q : unicode = "\u2655"; break;
		case W_R : unicode = "\u2656"; break;
		case W_B : unicode = "\u2657"; break;
		case W_Kn : unicode = "\u2658"; break;
		case W_P : unicode = "\u2659"; break;
		case B_Ki : unicode = "\u265A"; break;
		case B_Q : unicode = "\u265B"; break;
		case B_R : unicode = "\u265C"; break;
		case B_B : unicode = "\u265D"; break;
		case B_Kn : unicode = "\u265E"; break;
		case B_P : unicode = "\u265F"; break;
		case EMPTY : unicode = "\t"; break;
		}
		return unicode;
		
	}
	
	public static Chessmen[][] pawnTransform(Chessmen[][] board, int[] to, int[] from){
		if (((board[from[0]][from[1]] == Chessmen.W_P)&&(to[1]==7))||((board[from[0]][from[1]] == Chessmen.B_P))&&(to[1]==0)){
				//If we reach end of board, call a method which changes to another piece.
				System.out.println("Which piece do you want to convert to? Enter serial number - \n "
				+ "1. Queen \n"
				+ "2. Knight \n "
				+ "3. Rook \n"
				+ "4. Bishop \n"
				+ "5. Pawn \n");
				Scanner sc = new Scanner(System.in);
				int option = sc.nextInt();
				
				
				
				if(whiteTurn){
				switch (option){
				case 1 : board[to[0]][to[1]] = Chessmen.W_Q;
				case 2 : board[to[0]][to[1]] = Chessmen.W_Kn;
				case 3 : board[to[0]][to[1]] = Chessmen.W_R;
				case 4 : board[to[0]][to[1]] = Chessmen.W_B;
				case 5 : board[to[0]][to[1]] = Chessmen.W_P;
				}}
				else{
					switch (option){
					case 1 : board[to[0]][to[1]] = Chessmen.B_Q;
					case 2 : board[to[0]][to[1]] = Chessmen.B_Kn;
					case 3 : board[to[0]][to[1]] = Chessmen.B_R;
					case 4 : board[to[0]][to[1]] = Chessmen.B_B;
					case 5 : board[to[0]][to[1]] = Chessmen.B_P;
				}

				}
			}

		
	return board;		
	}
	
	}
			