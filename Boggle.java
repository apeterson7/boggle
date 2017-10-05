import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Boggle {
    MyTrie lex; // The dictionary, stored in a Trie
    Square[][] board; // The 4x4 board
    MyTrie foundWords; // The dictionary words on the current board
    MyTrie guesses; // The valid guesses made so far by our one player
    String[] dice; // An array of dice -- explained later!

    //done
    public Boggle(String file) throws FileNotFoundException {
	fillDice();
	board = new Square[4][4];
	guesses = new MyTrie();
	lex = new MyTrie();
	
	Scanner scr = new Scanner(new File(file));
	

	while(scr.hasNextLine()) {
	    String next = scr.nextLine();
	    lex.add(next);
	}
    }
    
    /**
     * 
     * @return the 2D array of squares ([x][y])
     */
    public Square[][] getBoard() {
	return board;
    }

    /**
     * 
     * @return return the number of guesses
     */
    public int numGuesses() {
	return guesses.size();
    }

    /**
     * 
     * @return returns a text representation of the board
     */
    public String toString() {
	String s = "";
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 4; j++) {
		s += board[i][j].toString();
	    }
	    s += "\n";
	}
	return s;
    }

    /**
     * 
     * @param word to search in contiguous words found on board
     * @return whether the word exists on the board
     */
    public boolean contains(String word) {
	return foundWords.contains(word);
    }

    /**
     * adds the user's guess to the list of guesses
     * @param guess = the user's guess
     * @return true if it wasn't there already, false if otherwise
     */
    public boolean addGuess(String guess) {
	if (foundWords.contains(guess)) {
	    guesses.add(guess);
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * rolls the virtual dice and records all possible 
     * words on the resulting board
     * @throws FileNotFoundException
     */
    public void newGame() throws FileNotFoundException {
	fillBoardFromDice();
	fillFoundWords();
    }

    /**
     * 
     * @param w the string to search for
     * @return an array list of the squares that make up the path
     */
    public ArrayList<Square> squaresForWord(String w) {
	for(int i = 0; i < 4;i++) {
	    for(int j = 0; j < 4; j++) {
		System.out.println(i + ", " + j);
		ArrayList<Square> found = squaresForWord(board[i][j],w);
		
		if(found.size() > 0) {
		    //System.out.println();
		    //System.out.println(found.toString());
		    return found;
		}
	    }
	}
	return new ArrayList<Square>();
    }

    /**
     * reads in the dice values from dice.txt
     * creates an array of each other the strings contained
     * in dice.txt
     * @throws FileNotFoundException
     */
    private void fillDice() throws FileNotFoundException {
	Scanner scr = new Scanner(new File("dice.txt"));
	dice = new String[16];
	for(int i = 0; i < 16; i++) {
	    dice[i] = scr.nextLine();
	}
	
    }

    /**
     * randomly fill the board 2D array with strings from the dice
     */
    private void fillBoardFromDice() {
	Random randint = new Random();
	ArrayList<Integer> list = new ArrayList<Integer>();
	for(int i = 0; i < 16; i++) {
	    list.add(i);
	}
	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 4; j++) {
		//find a random die and remove that die from the list of possible dice
		
		int randomDie = list.remove(randint.nextInt(list.size()));
		//get the string of a random face of the random die
		String letter = String.valueOf(dice[randomDie].charAt(randint.nextInt(6)));
		board[i][j] = new Square(i, j, letter);
		board[i][j].unmark();
	    }
	}
	System.out.println(this.toString());
    }

    /**
     * 
     * @param sq the square to recursive search
     * @param prefix = the prefix with which to continue the search
     * @return a trie of all words found on the board that exist in lex
     */
    private MyTrie search(Square sq, String prefix) {
	MyTrie found = new MyTrie();
	if(lex.contains(prefix) && prefix.length() > 0) {
	    found.add(prefix);
	}else {
	    if (lex.containsPrefix(prefix)) {
		String l = sq.toString();
		for (int i = -1; i <= 1; i++) {
		    for (int j = -1; j <= 1; j++) {
			//set the new indexes to look at;
			int searchX = sq.getX() + i;
			int searchY = sq.getY() + j;
			if (searchX >= 0 && searchY + j >= 0 && sq.getX() + i < 4 && sq.getY() + j < 4
				&& board[searchX][searchY].isMarked() == false 
				&& board[searchX][searchY] != board[sq.getX()][sq.getY()]) {
			    board[sq.getX()][sq.getY()].mark();
			    found.addAll(this.search(board[searchX][searchY],prefix + l));
			    board[sq.getX()][sq.getY()].unmark();   
			}
		    }
		}
	    }
	}
	return found;
    }
    
    /**
     * calls search(Sq, Prefix) on all the squares on the board to recursively find
     * all words contained in the board
     */
    public void fillFoundWords() {
	foundWords = new MyTrie();
	MyTrie returnedWords = new MyTrie();
	for(int i = 0; i < 4; i++) {
	    for(int j = 0; j < 4; j++) {
		returnedWords = search(board[i][j], "");
		Scanner scr = new Scanner(returnedWords.toString());
		while(scr.hasNext()) {
		    foundWords.add(scr.next());
		}
	    }
	}
	System.out.println(foundWords.toString());
    }

    /**
     * 
     * @param sq the square that is being searched for
     * @param w the string to look for
     * @return an array of a path
     */
    private ArrayList<Square> squaresForWord(Square sq, String w) {

	ArrayList<Square> found = new ArrayList<Square>();

	if (w.substring(0, 1).equals(sq.toString()) == true) {
	    found.add(sq);
	    w = w.substring(1);
	    if (w.length() == 0) {
		return found;
	    }
	    //perform a search of adjacent squares
	    for (int i = -1; i <= 1; i++) {
		for (int j = -1; j <= 1; j++) {
		    // set the new indexes to look at;
		    int searchX = sq.getX() + i;
		    int searchY = sq.getY() + j;

		
		    // if the square is h
		    if (searchX >= 0 && searchY + j >= 0 && sq.getX() + i < 4
			    && sq.getY() + j < 4 && board[searchX][searchY].isMarked() == false
			    && board[searchX][searchY] != board[sq.getX()][sq.getY()]) {

				Square search = board[searchX][searchY];
			// see if examined square matches the first char of
			// string
			if (w.substring(0, 1).equals(
				board[searchX][searchY].toString())) {
			    search.mark();

			    System.out.println("before recursion" + found);
			    found.addAll(this.squaresForWord(
				    board[searchX][searchY], w));
			
			    System.out.println("after recursion" + found);

			    search.unmark();   
			    //break out of the for loop 
			    i = 1;
			    j = 1;
			}
		    }
		}
	    }
	}
	if() {
	    
	}
	
	return found;
    }

    public static void main(String[] args) throws FileNotFoundException {
	Boggle boggle = new Boggle("enable.txt");
	BoggleFrame bFrame = new BoggleFrame(boggle);
	bFrame.pack();
	bFrame.setLocationRelativeTo(null);
	bFrame.setVisible(true);
    }

}
