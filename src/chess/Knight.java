package chess;

import java.util.ArrayList;

public class Knight extends Piece {
	
	public Knight(char c, Position p){
		this.color = c;
		this.p = p;
	}

	@Override
	public boolean isValid(Position pos) {
		int xdiff = this.p.checkVertical(pos);
		int ydiff = this.p.checkHorizontal(pos);
		if ((Math.abs(xdiff) == 1 && Math.abs(ydiff) == 2) || (Math.abs(xdiff) == 2 && Math.abs(ydiff) == 1)){
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

	@Override
	public String toString() {
		return "" + this.color + "N";
	}

	@Override
	public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c) {
		// TODO Auto-generated method stub
		return true;
	}

}
