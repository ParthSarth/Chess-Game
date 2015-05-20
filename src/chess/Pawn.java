package chess;

import java.util.ArrayList;

/**
 * 
 * @author Parth Shorey
 * @author Rusit Soni
 * 
 *
 */

public class Pawn extends Piece {
	
	public Pawn(char c, Position p){
		this.color = c;
		this.p = p;
	}
	
	
	@Override
	public boolean isValid(Position pos){
		int diff = Math.abs(this.p.checkVertical(pos));
		if (this.p.getX() == 1 || this.p.getX() == 6){
			if (diff <= 2){
				return true;
			}
		}
		else if (diff == 1){
			return true;
		}
		return false;
	}
	

	@Override
	public boolean validCapture(Position pos) {
		int xdiff = this.p.checkVertical(pos);
		int ydiff = this.p.checkHorizontal(pos);
		if (Math.abs(xdiff) == 1 && Math.abs(ydiff) == 1){
			return true;
		}
		return false;
	}


	@Override
	public String toString() {
		return "" + this.color + "p";
	}


	@Override
	public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c) {
		// TODO Auto-generated method stub
		return true;
	}

}
