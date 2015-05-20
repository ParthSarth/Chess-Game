package chess;
import java.util.ArrayList;

/**
 * 
 * @author Parth Shorey
 * @author Rusit Soni
 * 
 *
 */


public class ChessBoard {
	Piece[][] board;
	boolean bTurn = false;
	static Piece wk, bk;
	
	public ChessBoard() {
		this.board = new Piece [8][8];
		
		
		this.board[0][0] = new Rook('b', new Position(0,0));
		this.board[0][1] = new Knight('b', new Position(0,1));
		this.board[0][2] = new Bishop('b', new Position(0,2));
		this.board[0][3] = new Queen('b', new Position(0,3));
		this.board[0][4] = new King('b', new Position(0,4));
		this.board[0][5] = new Bishop('b', new Position(0,5));
		this.board[0][6] = new Knight('b', new Position(0,6));
		this.board[0][7] = new Rook('b', new Position(0,7));
		
		
		this.board[7][0] = new Rook('w', new Position(7,0));
		this.board[7][1] = new Knight('w', new Position(7,1));
		this.board[7][2] = new Bishop('w', new Position(7,2));
		this.board[7][3] = new Queen('w', new Position(7,3));
		this.board[7][4] = new King('w', new Position(7,4));
		this.board[7][5] = new Bishop('w', new Position(7,5));
		this.board[7][6] = new Knight('w', new Position(7,6));
		this.board[7][7] = new Rook('w', new Position(7,7));
		
		this.board[1][0] = new Pawn('b', new Position(1,0));
		this.board[1][1] = new Pawn('b', new Position(1,1));
		this.board[1][2] = new Pawn('b', new Position(1,2));
		this.board[1][3] = new Pawn('b', new Position(1,3));
		this.board[1][4] = new Pawn('b', new Position(1,4));
		this.board[1][5] = new Pawn('b', new Position(1,5));
		this.board[1][6] = new Pawn('b', new Position(1,6));
		this.board[1][7] = new Pawn('b', new Position(1,7));
		this.board[6][0] = new Pawn('w', new Position(6,0));
		this.board[6][1] = new Pawn('w', new Position(6,1));
		this.board[6][2] = new Pawn('w', new Position(6,2));
		this.board[6][3] = new Pawn('w', new Position(6,3));
		this.board[6][4] = new Pawn('w', new Position(6,4));
		this.board[6][5] = new Pawn('w', new Position(6,5));
		this.board[6][6] = new Pawn('w', new Position(6,6));
		this.board[6][7] = new Pawn('w', new Position(6,7));
		
		wk = this.board[7][4];
		bk = this.board[0][4];
	}
	
	
	public boolean whoseTurn(){
		return bTurn;
	}
	
	public void nextTurn(){
		this.bTurn = !bTurn;
	}
	
	public Piece getPieceAtPosition(Position pos){
		return this.board[pos.getX()][pos.getY()];
	}
	
	public void setPieceAtPosition(Position pos, Piece p){
		 this.board[pos.getX()][pos.getY()] = p;
	}
	
	public boolean action(Position s, Position d){
		Piece p = this.getPieceAtPosition(s);
		Piece q = this.getPieceAtPosition(d);
		
		if (q == null){
			if (p.isValid(d) && p.clearPath(d, this.board, null, false)){
				this.setPieceAtPosition(p.getPosition(), null);
				p.move(d);
				this.setPieceAtPosition(p.getPosition(), p);
				return true;
			}
			else{
				//System.out.println();
				System.out.println("Illegal move, try again");
				System.out.println();
				return false;
			}
		}
		else{
			if (q.getColor() == p.getColor()){
				// print lineq
				//System.out.println();
				System.out.println("Illegal move, try again");
				System.out.println();
				return false;
			}
			else if (p.validCapture(d) && p.clearPath(d, this.board, null, false)) {
				this.setPieceAtPosition(p.getPosition(), null);
				p.move(d);
				this.setPieceAtPosition(p.getPosition(), p);
				return true;
			}
			else{
				//System.out.println();
				System.out.println("Illegal move, try again");
				System.out.println();
				return false;
			}
		}
	}
	
	public String endgame(){
		boolean wcheck = false, bcheck = false;
		ArrayList<Piece> wpieces = new ArrayList<Piece>();
		ArrayList<Piece> bpieces = new ArrayList<Piece>();
		Piece c = null;
		Position checker = null;
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if (this.board[x][y] != null){
					if (this.board[x][y].validCapture(wk.getPosition()) && this.board[x][y].getColor() == 'b'){
						bpieces.add(this.board[x][y]);
						wcheck = true;
					}
					
					if (this.board[x][y].validCapture(bk.getPosition()) && this.board[x][y].getColor() == 'w'){
						c = this.board[x][y];
						checker = bk.getPosition();
						wpieces.add(this.board[x][y]);
						bcheck = true;
					}
				}
			}
		}
		
		for (int x = 0; x<wpieces.size(); x++){
			if(!wpieces.get(x).clearPath(bk.getPosition(), this.board, null, false)){
				bcheck = false;
			}
		}
		
		for (int x = 0; x<bpieces.size(); x++){
			if(!bpieces.get(x).clearPath(wk.getPosition(), this.board, null, false)){
				wcheck = false;
			}
		}
		
		boolean wcheckmate = true, bcheckmate = true;
		
		ArrayList<Position> wpp = new ArrayList<Position>();
		wpp.add(new Position(wk.getPosition().getX()-1, wk.getPosition().getY()-1));
		wpp.add(new Position(wk.getPosition().getX()-1, wk.getPosition().getY()));
		wpp.add(new Position(wk.getPosition().getX()-1, wk.getPosition().getY()+1));
		
		wpp.add(new Position(wk.getPosition().getX(), wk.getPosition().getY()-1));
		wpp.add(new Position(wk.getPosition().getX(), wk.getPosition().getY()+1));
		
		wpp.add(new Position(wk.getPosition().getX()+1, wk.getPosition().getY()-1));
		wpp.add(new Position(wk.getPosition().getX()+1, wk.getPosition().getY()));
		wpp.add(new Position(wk.getPosition().getX()+1, wk.getPosition().getY()+1));
		
		ArrayList<Position> bpp = new ArrayList<Position>();
		bpp.add(new Position(bk.getPosition().getX()-1, bk.getPosition().getY()-1));
		bpp.add(new Position(bk.getPosition().getX()-1, bk.getPosition().getY()));
		bpp.add(new Position(bk.getPosition().getX()-1, bk.getPosition().getY()+1));
		
		bpp.add(new Position(bk.getPosition().getX(), bk.getPosition().getY()-1));
		bpp.add(new Position(bk.getPosition().getX(), bk.getPosition().getY()+1));
		
		bpp.add(new Position(bk.getPosition().getX()+1, bk.getPosition().getY()-1));
		bpp.add(new Position(bk.getPosition().getX()+1, bk.getPosition().getY()));
		bpp.add(new Position(bk.getPosition().getX()+1, bk.getPosition().getY()+1));
		
		int wrow, wcol, brow, bcol;
		Piece temp;
		
		for (int x = 0; x<8; x++){
			if (wcheck){
				wrow = wpp.get(x).getX();
				wcol = wpp.get(x).getY();
				if ((wrow< 8 && wrow >-1) && (wcol<8 && wcol>-1)){
					temp = this.getPieceAtPosition(wpp.get(x));
					if ((temp == null || temp.getColor() == 'b') && wk.isValid(wpp.get(x))){
						wcheckmate = this.checkmatehelper(wpp.get(x), wpieces, 'w');
						if (wcheckmate == false){
							break;
						}
					}
				}
			}
			else if (bcheck){
				brow = bpp.get(x).getX();
				bcol = bpp.get(x).getY();
				if ((brow< 8 && brow >-1) && (bcol<8 && bcol>-1)){
					temp = this.getPieceAtPosition(bpp.get(x));
					if ((temp == null || temp.getColor() == 'w') && bk.isValid(bpp.get(x))){
						bcheckmate = this.checkmatehelper(bpp.get(x), bpieces, 'b');
						if (bcheckmate == false){
							break;
						}
					}
				}
				
			}
		}
		int wcancel = 0, bcancel = 0;
		Position t = null;

		ArrayList<Position> singles = new ArrayList<Position>();
		ArrayList<Position> singles2 = new ArrayList<Position>();
		if (wpieces.size() == 1){
			singles.add(wpieces.get(0).getPosition());
			wpieces.get(0).clearPath(wk.getPosition(), board, singles, true);
		
		for (int i = 0; i<singles.size(); i++){
			for (int x = 0; x<8; x++){
				for (int y = 0; y<8; y++){
					if (this.board[x][y] != null){
						if (this.board[x][y].isValid(singles.get(i)) && this.board[x][y].getColor() == 'w'){
							wcheckmate = false;
							break;
						}
					}
				}
			}
		}
		}
		
		if (bpieces.size() == 1){
			singles2.add(bpieces.get(0).getPosition());
			bpieces.get(0).clearPath(bk.getPosition(), board, singles2, true);
		
		for (int i = 0; i<singles2.size(); i++){
			for (int x = 0; x<8; x++){
				for (int y = 0; y<8; y++){
					if (this.board[x][y] != null){
						if (this.board[x][y].isValid(singles2.get(i)) && this.board[x][y].getColor() == 'b'){
							bcheckmate = false;
							break;
						}
					}
				}
			}
		}
		}
		
		if (wcheck){
			if (wcheckmate){
				return "WCheckmate";
			}
			else if (wcheck && bTurn){
				return "WCheckmate";
			}
			else{
				return "Check";
			}
		}
		else if (bcheck){
			if (bcheckmate){
				return "BCheckmate";
			}
			else if (bcheck && !bTurn){
				return "BCheckmate";
			}
			else{
				return "Check";
			}
		}
		return "";
	}
	
	private boolean checkmatehelper(Position p, ArrayList<Piece> pieces, char color){
		for (int x = 0; x<8; x++){
			for (int y = 0; y<8; y++){
				if (this.board[x][y] != null && this.board[x][y].getColor() != color){
					if (this.board[x][y].validCapture(p) && this.board[x][y].getPosition().equals(p) == false){// && (this.getPieceAtPosition(p) == null || color != this.board[x][y].getColor())){
						if (this.board[x][y].clearPath(p, this.board, null, true)){ // add conditions for white or black king
							if (!pieces.contains(this.board[x][y])){
								pieces.add(this.board[x][y]);
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public void promotion(String p){
		String playerinput = p;
		int i;
		for (i= 0; i< 8; i++){
			if(board[0][i] != null && board[0][i].toString().charAt(1) == 'p'){
				if(playerinput.length() == 6){
					char c = playerinput.charAt(7);
					switch(c){
					case 'N':
						board[0][i] = new Knight('w', new Position(0,i));
					case 'B':
						board[0][i] = new Bishop('w', new Position(0,i));
					case 'R':
						board[0][i] = new Rook('w', new Position(0,i));
					case 'Q':
						board[0][i] = new Queen('w', new Position(0,i));
					}
				}
				else{
					board[0][i] = new Queen('w', new Position(0,i));
				}
			}
			else if (board[7][i] != null && board[7][i].toString().charAt(1) == 'p'){
				if(playerinput.length() == 6){
					char c = playerinput.charAt(7);
					switch(c){
					case 'N':
						board[7][i] = new Knight('b', new Position(7,i));
					case 'B':
						board[7][i] = new Bishop('b', new Position(7,i));
					case 'R':
						board[7][i] = new Rook('b', new Position(7,i));
					case 'Q':
						board[7][i] = new Queen('b', new Position(7,i));
					}
				}
				else{
					board[7][i] = new Queen('b', new Position(7,i));
				}
			}
		}
	}
	
	public void print(){
		int count = 8;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 9; j++){
				if (j==8){
					System.out.println(count);
					count--;
				}
				if(j<8 && this.board[i][j] == null){
					System.out.print(((j + i) % 2 == 0) ? "   " : "## ");
				}
				else if (j<8){
					System.out.print(this.board[i][j].toString()+" ");
				}
			}
		}
		
	}
	
}
