package chess;
import java.util.*;

/**
 * 
 * @author Parth Shorey
 * @author Rusit Soni
 * 
 *
 */

public abstract class Piece {
	
	public static final char white = 'w';
	public static final char black = 'b';
	
	char color;
	Position p;
	
	public char getColor(){
		return this.color;
	}
	
	public Position getPosition(){
		return this.p;
	}
	
	public void move(Position pos){
		this.p = pos;
	}
	
	abstract public boolean isValid(Position pos);
	
	abstract public boolean validCapture(Position pos);
	
	abstract public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c);
	
	abstract public String toString();
	
	//public boolean equals 
	
}
