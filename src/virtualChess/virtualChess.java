package virtualChess;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import Pieces.*;

public class virtualChess {

	public static final int boardsize = 8;

	public static boolean whiteTurn = true; //this is a public counter boolean to keep track of the turn
	static Object[][] chessboard_main = new Object[boardsize][boardsize];

	public static void main (String[] args) throws UnsupportedEncodingException {
		chessboard_main = blankPopulate(chessboard_main);
		chessboard_main = initiateBoard(chessboard_main);
		print(chessboard_main);
		while(kingCheck(chessboard_main)){
			chessboard_main = move(chessboard_main);
			print(chessboard_main);
		}
		}
	
	public static Object[][] blankPopulate(Object[][] chessboard_main2){
		for (int i = 0; i<chessboard_main2.length; i++){
			for (int j = 0; j<chessboard_main2[i].length; j++){
				chessboard_main2[i][j] = pieces.Chessmen.EMPTY;
			}
			}
		return chessboard_main2;
		}
	
	public static void print(Object[][] chessboard_main2) throws UnsupportedEncodingException{
	for(int i = chessboard_main2.length-1; i>=0; i--){
		int row_num = i+1;
		System.out.printf(row_num + "\t");
		for(int j = 0; j<chessboard_main2[i].length; j++){
			if (chessboard_main2[j][i] != pieces.Chessmen.EMPTY){
				PrintStream out = new PrintStream(System.out, true, "UTF-8");
				out.printf(((AbstractPiece) chessboard_main2[j][i]).draw() + "\t");
			}
			else System.out.printf("\t");
			}
		System.out.println("\n");
		}
	System.out.println("\t a\t b\t c\t d\t e\t f\t g\t h");
	}
	
	public static Object[][] initiateBoard(Object[][] chessboard_main2){
	chessboard_main2[0][0] = new Rook(true);//pieces.Chessmen.W_R;
	chessboard_main2[1][0] = new Knight(true);//pieces.Chessmen.W_Kn;
	chessboard_main2[2][0] = new Bishop(true);//pieces.Chessmen.W_B;
	chessboard_main2[3][0] = new Queen(true);//pieces.Chessmen.W_Q;
	chessboard_main2[4][0] = new King(true);//pieces.Chessmen.W_Ki;
	chessboard_main2[5][0] = new Bishop(true);//pieces.Chessmen.W_B;
	chessboard_main2[6][0] = new Knight(true);//pieces.Chessmen.W_Kn;
	chessboard_main2[7][0] = new Rook(true);//pieces.Chessmen.W_R;
	
	chessboard_main2[0][7] = new Rook(false);//pieces.Chessmen.B_R;
	chessboard_main2[1][7] = new Knight(false);//pieces.Chessmen.B_Kn;
	chessboard_main2[2][7] = new Bishop(false);//pieces.Chessmen.B_B;
	chessboard_main2[3][7] = new Queen(false);//pieces.Chessmen.B_Q;
	chessboard_main2[4][7] = new King(false);//pieces.Chessmen.B_Ki;
	chessboard_main2[5][7] = new Bishop(false);//pieces.Chessmen.B_B;
	chessboard_main2[6][7] = new Knight(false);//pieces.Chessmen.B_Kn;
	chessboard_main2[7][7] = new Rook(false);//pieces.Chessmen.B_R;
	
	for(int i = 0; i<boardsize;  i++){
	chessboard_main2[i][1] = new Pawn(true);//pieces.Chessmen.W_P;
	chessboard_main2[i][6] = new Pawn(false);//pieces.Chessmen.B_P;
	}
	
	return chessboard_main2;
	}
	
	public static Object[][] move(Object[][] chessboard_main2){
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
		if (rules(chessboard_main2, from_int, to_int)){
			chessboard_main2[to_int[0]][to_int[1]] = chessboard_main2[from_int[0]][from_int[1]];
			chessboard_main2[from_int[0]][from_int[1]] = pieces.Chessmen.EMPTY;
			whiteTurn = !whiteTurn;
			}		
		
		//check for pawnTransformation 
		//chessboard_main2 = pawnTransform(chessboard_main2, to_int, from_int);
		return chessboard_main2;
	}

	public static boolean kingCheck(Object[][] chessboard_main2) 
	{
		/* This is a method which checks whether both kings are still on the board.
		 * If that is the case, it returns true and lets the game go on. If not, 
		 * it checks which one is missing and declares the other the 
		 * victor and ends the game. 
		 */
		/*int whiteCounter = 0;
		int blackCounter = 0;
		//King WK = new King(true);
		//King BK = new King(false);
		for(int i = 0; i<chessboard_main2.length; i++){
			for(int j = 0; j<chessboard_main2[i].length; j++)
			{
				if(chessboard_main2[i][j]==pieces.King)) 
					whiteCounter++;
				if (chessboard_main2[i][j].equals(Pieces.pieces.Chessmen.B_Ki))
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
		}*/
		return true;
	}
	
	public static boolean rules (Object[][] chessboard_main2, int[] from, int to[]){
		
		//Making lists of white and black pieces --
		List<pieces.Chessmen> whitePieces = new ArrayList<pieces.Chessmen>();
		whitePieces.add(pieces.Chessmen.W_Ki);
		whitePieces.add(pieces.Chessmen.W_Q);
		whitePieces.add(pieces.Chessmen.W_R);
		whitePieces.add(pieces.Chessmen.W_Kn);
		whitePieces.add(pieces.Chessmen.W_B);
		whitePieces.add(pieces.Chessmen.W_P);		
		List<pieces.Chessmen> blackPieces = new ArrayList<pieces.Chessmen>();
		blackPieces.add(pieces.Chessmen.B_Ki);
		blackPieces.add(pieces.Chessmen.B_Q);
		blackPieces.add(pieces.Chessmen.B_R);
		blackPieces.add(pieces.Chessmen.B_Kn);
		blackPieces.add(pieces.Chessmen.B_B);
		blackPieces.add(pieces.Chessmen.B_P);
		// ended making groups
		
		//Rule 0 - the 'From' and 'To' are within bounds 
		if (from[0]<0 || from[0]>7 || from[1]<0 || from[1]>7 || to[0]<0 || to[0]>7 || to[1]<0 || to[1]>7){
			System.out.println("Move out of bounds!!");
			return false;
		}
			
		
		// Rule 1 - Initial space can't be labeled empty
		if (chessboard_main2[from[0]][from[1]] == pieces.Chessmen.EMPTY){
			System.out.println("Intial square is empty!!");
			return false;
		}
		
		//Rule 2 - Check whether if player is killing his own piece - 
		if ((whitePieces.contains(chessboard_main2[from[0]][from[1]]) && whitePieces.contains(chessboard_main2[to[0]][to[1]]) || 
				(blackPieces.contains(chessboard_main2[from[0]][from[1]]) && blackPieces.contains(chessboard_main2[to[0]][to[1]]))))
				{
			System.out.println("Can't kill your own piece!!");
			return false;			
				}
		
		//Rule 3 - Check if the move is according to the turn - 
		if ((whiteTurn && blackPieces.contains(chessboard_main2[from[0]][from[1]])) || !whiteTurn && whitePieces.contains(chessboard_main2[from[0]][from[1]])){
			System.out.println("Please play according to turn!!");
			return false;
		}
		
		//Rule 4 - Check if it is following the rule of the given piece -
		if(((AbstractPiece) chessboard_main2[from[0]][from[1]]).isMoveValid(from[1],from[0],to[1],to[0])==false) return false;
		
		
		
		
		
		/*
		//Rule 5 ------------------------Pawn Rules---------------------------
		if ((chessboard_main2[from[0]][from[1]] == pieces.Chessmen.W_P)||(chessboard_main2[from[0]][from[1]] == pieces.Chessmen.B_P))
		//above condition is for checking if we are dealing with a pawn. 
		{
			if(to[0]-from[0] == 1 || from[0]-to[0]==1){
				//if we are moving diagonally - 
				if(chessboard_main2[to[0]][to[1]] == pieces.Chessmen.EMPTY){ 
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
		
	
		*/
		return true;
	}
	
	public static String printUnicode(pieces.Chessmen piece){
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
	
	public static pieces.Chessmen[][] pawnTransform(pieces.Chessmen[][] board, int[] to, int[] from){
		if (((board[from[0]][from[1]] == pieces.Chessmen.W_P)&&(to[1]==7))||((board[from[0]][from[1]] == pieces.Chessmen.B_P))&&(to[1]==0)){
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
				case 1 : board[to[0]][to[1]] = pieces.Chessmen.W_Q;
				case 2 : board[to[0]][to[1]] = pieces.Chessmen.W_Kn;
				case 3 : board[to[0]][to[1]] = pieces.Chessmen.W_R;
				case 4 : board[to[0]][to[1]] = pieces.Chessmen.W_B;
				case 5 : board[to[0]][to[1]] = pieces.Chessmen.W_P;
				}}
				else{
					switch (option){
					case 1 : board[to[0]][to[1]] = pieces.Chessmen.B_Q;
					case 2 : board[to[0]][to[1]] = pieces.Chessmen.B_Kn;
					case 3 : board[to[0]][to[1]] = pieces.Chessmen.B_R;
					case 4 : board[to[0]][to[1]] = pieces.Chessmen.B_B;
					case 5 : board[to[0]][to[1]] = pieces.Chessmen.B_P;
				}

				}
			}

		
	return board;		
	}
	
	}
			