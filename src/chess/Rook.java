package chess;

import java.util.ArrayList;

public class Rook extends Piece {
	
	public Rook(char c, Position p){
		this.color = c;
		this.p = p;
	}
	
	@Override
	public boolean isValid(Position pos) {
		int xdiff = this.p.checkVertical(pos);
		int ydiff = this.p.checkHorizontal(pos);
		if ((Math.abs(xdiff) != 0 && Math.abs(ydiff) == 0) || (Math.abs(xdiff) == 0 && Math.abs(ydiff) != 0)){
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
		return "" + this.color + "R";
	}

	@Override
	public boolean clearPath(Position pos, Piece[][] board, ArrayList<Position> s, boolean c) {
		int direction = 0;
		if (this.p.getX() == pos.getX()){
			direction = this.p.getY()>pos.getY() ? 1 : -1;
			for (int i = 1; i<Math.abs(this.p.getY()-pos.getY()); i++){
				if (board[this.p.getX()][this.p.getY()+(i*direction)] != null){ //&& !board[this.p.getX()][this.p.getY()+(i*direction)].toString().substring(1).equals("K")){
					if(c){
						if(!board[this.p.getX()][this.p.getY()+(i*direction)].toString().substring(1).equals("K")){
							return false;
						}
					}
					else{
						return false;
					}
				}
				if (s != null){
					s.add(new Position(this.p.getX(), this.p.getY()+(i*direction)));
				}
			}
			return true;
		}
		else{
			direction = this.p.getX()>pos.getX() ? -1 : 1;
			for (int i = 1; i<Math.abs(this.p.getX()-pos.getX()); i++){
				if (board[this.p.getX()+(i*direction)][this.p.getY()] != null){ //&& !board[this.p.getX()][this.p.getY()+(i*direction)].toString().substring(1).equals("K")){
					if(c){
						if(!board[this.p.getX()+(i*direction)][this.p.getY()].toString().substring(1).equals("K")){
							return false;
						}
					}
					else{
						return false;
					}
				}
				if (s != null){
					s.add(new Position(this.p.getX()+(i*direction), this.p.getY()));
				}
			}
			return true;
		}
		
		//return false;
	}

}
