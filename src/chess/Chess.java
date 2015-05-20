package chess;
import java.util.Scanner;

/**
 * 
 * @author Rusit Soni
 * 
 *
 */

public class Chess {
	
	public static void main(String[] args){
		
		ChessBoard board = new ChessBoard();
		Scanner sc = new Scanner(System.in);
		String playerinput = null;
		boolean gamestart = true;
		Position source = null;
		Position dest = null;
		int sx, sy = 0, dx, dy = 0;
		while(gamestart){
			//playerinput = sc.nextLine();
			board.print();
			System.out.println("a  b  c  d  e  f  g  h");
			System.out.println();
			if (!board.whoseTurn()){
				System.out.print("White's move: ");
			}
			else{
				System.out.print("Black's move: ");
			}
			
			playerinput = sc.nextLine();
			if (playerinput.equalsIgnoreCase("resign")){
				System.out.println();
				if (board.whoseTurn() == true){
					System.out.println("White wins");
				}
				else{
					System.out.println("Black wins");
				}
				System.exit(0);
			}
			if (playerinput.equalsIgnoreCase("draw")){
				System.out.println();
				System.out.println("Draw");
				System.exit(0);
			}
			//System.out.println();
			String str = "abcdefgh";
			int i = 0;
			System.out.println();
			boolean valid = true;
			sx = (int)playerinput.charAt(1) - 49;
			while(i<8){
				if(playerinput.charAt(0) == str.charAt(i)){
					sy = (int)playerinput.charAt(0) - 97;
					break;
				}
				else if(playerinput.charAt(0) != str.charAt(i)){
					i++;
					if (i == 8){
						System.out.println("out of file range");
						System.out.println();
						valid = false;
						break;
						//playerinput = sc.nextLine();
						

					}
				}
			}
			
			dx = (int)playerinput.charAt(4) - 49;
			i = 0;
			while(i<8){
				if(playerinput.charAt(3) == str.charAt(i)){
					dy = (int)playerinput.charAt(3) - 97;
					break;
				}
				else if(playerinput.charAt(3) != str.charAt(i)){
					i++;
					if (i == 8){
						System.out.println("out of file range");
						System.out.println();
						//System.outprint=	
						//playerinput = sc.nextLine();
						valid = false;
						break;
					}
				}
			}
			if (valid){
			sx = convert(sx);
			dx = convert(dx);
			
			source = new Position(sx, sy);
			dest = new Position(dx, dy);
			char check;
			if (board.action(source, dest)){
				board.nextTurn();
				board.promotion(playerinput);
				if (board.endgame().equals("Check")){
					System.out.println("Check");
					System.out.println();
				}
				else if (board.endgame().equals("WCheckmate") || board.endgame().equals("BCheckmate") ){
					check = board.endgame().charAt(0);
					board.print();
					System.out.println("a  b  c  d  e  f  g  h");
					System.out.println();
					System.out.println("Checkmate");
					System.out.println();
					if (check == 'B'){
						System.out.println("White wins"); 
					}
					else{ 
						System.out.println("Black wins");
					}
					gamestart = false;
				}
			}
			}
			
			
			
			
			
		}
		
	}
	
	public static int convert(int x){
		switch(x){
		case 7:
			return 0;
		case 6:
			return 1;
		case 5:
			return 2;
		case 4:
			return 3;
		case 3:
			return 4;
		case 2:
			return 5;
		case 1:
			return 6;
		case 0:
			return 7;
		default:
			return -1;
		}
	}

}
