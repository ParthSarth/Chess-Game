/**
 * 
 * @author Rusit Soni
 * 
 *
 */

package chess;

import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop(char c, Position p){
		this.color = c;
		this.p = p;
	}
	
	@Override
	public boolean isValid(Position pos) {
		int xdiff = this.p.checkVertical(pos);
		int ydiff = this.p.checkHorizontal(pos);
		if (Math.abs(xdiff) == Math.abs(ydiff)){
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
		return "" + this.color + "B";
	}

	@Override
	public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c) {
		int directionx = this.p.getX()>pos.getX() ? -1 : 1;
		int directiony = this.p.getY()>pos.getY() ? -1 : 1;
		for (int i = 1; i<Math.abs(this.p.getX() - pos.getX()); i++){
			if (board[this.p.getX() + (i*directionx)][this.p.getY() + (i*directiony)] != null){
				if(c){
					if(!board[this.p.getX() + (i*directionx)][this.p.getY() + (i*directiony)].toString().substring(1).equals("K")){
						return false;
					}
				}
				else{
					return false;
				}
			}
			if (s != null){
				s.add(new Position(this.p.getX()+(i*directionx), this.p.getY() + (i*directiony)));
			}
		}
		return true;
	}

}
