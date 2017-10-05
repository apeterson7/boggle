import java.io.FileNotFoundException;


public class test {



    public static void main(String[] args) throws FileNotFoundException {
	
	Boggle boggle = new Boggle("enable.txt");
	boggle.board[0][0] = new Square(0,0,"i");
	boggle.board[0][1] = new Square(0,1,"h");
	boggle.board[0][2] = new Square(0,2,"r");
	boggle.board[0][3] = new Square(0,3,"b");
	boggle.board[1][0] = new Square(1,0,"o");
	boggle.board[1][1] = new Square(1,1,"t");
	boggle.board[1][2] = new Square(1,2,"c");
	boggle.board[1][3] = new Square(1,3,"g");
	boggle.board[2][0] = new Square(2,0,"t");
	boggle.board[2][1] = new Square(2,1,"x");
	boggle.board[2][2] = new Square(2,2,"i");
	boggle.board[2][3] = new Square(2,3,"p");
	boggle.board[3][0] = new Square(3,0,"k");
	boggle.board[3][1] = new Square(3,1,"r");
	boggle.board[3][2] = new Square(3,2,"g");
	boggle.board[3][3] = new Square(3,3,"e");
	boggle.fillFoundWords();
	System.out.println(boggle.squaresForWord("grig"));
	System.out.println(boggle.toString());
	
    }
}
