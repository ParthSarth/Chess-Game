package chess;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int checkVertical(Position pos){
		return this.x - pos.getX();
	}
	
	public int checkHorizontal(Position pos){
		return this.y - pos.getY();
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public String toString(){
		return ""+ (char)(this.x + 97) + this.y;
	}
	public boolean equals(Position p){
		if (this.getX() == p.getX() && this.getY() == p.getY()){
			return true;
		}
		else{
			return false;
		}
	}
}
