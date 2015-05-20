package chess;

import java.util.ArrayList;

/**
 * 
 * @author Rusit Soni
 * 
 *
 */

public class King extends Piece {
	
	boolean isKing = true;
	
	public King(char c, Position p){
		this.color = c;
		this.p = p;
	}
	
	@Override
	public boolean isValid(Position pos) {
		int xdiff = this.p.checkVertical(pos);
		int ydiff = this.p.checkHorizontal(pos);
		if (Math.abs(xdiff) == 1 && Math.abs(ydiff) == 1){
			return true;
		}
		else if ((Math.abs(xdiff) == 1 && ydiff == 0) || (xdiff == 0 && Math.abs(ydiff) == 1)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean validCapture(Position pos) {
		return this.isValid(pos);
	}
	
	// loop through entire board and call isValid() on every piece to the king's current location to see if in check
	// loop again through king's possible moves to see if in checkmate
	
	@Override
	public String toString() {
		return "" + this.color + "K";
	}

	@Override
	public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c) {
		// TODO Auto-generated method stub
		return true;
	}
}
