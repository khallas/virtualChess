package virtualChess;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class Unicode_Test {
	public static void main(String[] args) throws UnsupportedEncodingException{
		String unicodeMessage = 
				"\u2654" + // whi t e k ing
				"\u2655" + // whi t e queen
				"\u2656" + // whi t e rook
				"\u2657" + // whi t e b i shop
				"\u2658" + // whi t e kni g h t
				"\u2659" + // whi t e pawn
				"\n" +
				"\u265A" + // b l a c k k ing
				"\u265B" + // b l a c k queen
				"\u265C" + // b l a c k rook
				"\u265D" + // b l a c k b i shop
				"\u265E" + // b l a c k kni g h t
				"\u265F" + // b l a c k pawn
				"\n" ;
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		out.println(unicodeMessage);
	}
}
