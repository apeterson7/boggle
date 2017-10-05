import java.util.*;


public class MyTrie extends AbstractSet<String>{

    /**
     * indicates with any trie node represents the end of a string
     */
    private boolean isWord;
    
    /**
     * int representing size of trie
     */
    private int size;
    
    /**
     * array of children 0-25 associated with alphabet
     */
    private MyTrie[] children;
    
    /**
     * constructor for MyTrie with an array of children of
     * length 26 and a size of 0
     */
    public MyTrie() {
	children = new MyTrie[26];
	isWord = false;
	size = 0;
    }
    
    /**
     * 
     * @return an iterator from the arrayList generated from toList();
     */
    public Iterator<String> iterator() {
	// TODO Auto-generated method stub
	return this.toList().iterator();
    }
    
    /**
     * 
     * @return the size of the trie
     */
    public int size() {
	// TODO Auto-generated method stub
	return size;
    }
    
    //What is this method?
    public boolean containsEmptyString() {
	return isWord;
    }
    
    public boolean containsPrefix(String prefix) {
	MyTrie bubble = this;
	for (int i = 0; i < prefix.length(); i++) {
	    bubble = bubble.children[prefix.charAt(i) - 'a'];
	}
	if (bubble == null) {
	    return false;
	} else {
	    for (int j = 0; j < 26; j++) {
		if (bubble.children[j] != null) {
		    return true;
		}
	    }
	    return false;
	}
    }
	
	
    
    /**
     * 
     * @param string to test membership
     * @return whether the string is a member of the trie
     */
    public boolean contains(String string) {
	if(string.length() == 0) {
	    return containsEmptyString();
	}else {
	    if(children[string.charAt(0) - 'a'] != null) {
		return children[string.charAt(0) - 'a'].contains(string.substring(1));
	    }else{
		return false;
	    }
	}
    }
    
    /**
     * 
     * @param string 
     * if string does not exist in trie, it is added
     * if not, return false
     * @return true if add was successful
     */
    public boolean add(String string) {
	
	if(string.length() == 1) {
	    if(children[string.charAt(0)- 'a'] != null) {
		if(children[string.charAt(0)- 'a'].isWord) {
		    return false;
		}else {
		    children[string.charAt(0)- 'a'].isWord = true;
		    children[string.charAt(0)- 'a'].size++;
		    size++;
		    return true;
		}
	    }else {
		children[string.charAt(0)- 'a'] = new MyTrie();
		children[string.charAt(0)- 'a'].isWord = true;
		children[string.charAt(0)- 'a'].size++;
		size++;
		return true;
	    }
	}else {
	    if(children[string.charAt(0)- 'a'] == null) {
		children[string.charAt(0)- 'a'] = new MyTrie();
		if(children[string.charAt(0)- 'a'].add(string.substring(1))) {
		    size++;
		    return true;
		}else {
		    return false;
		}
	    }else{
		if(children[string.charAt(0)- 'a'].add(string.substring(1))) {
		    size++;
		    return true;
		}else {
		    return false;
		}
	    }
	}
    }
    /**
     * 
     * @return true if trie is empty, false if otherwise
     */
    public boolean isEmpty() {
	if(size == 0) {
	    return true;
	}else {
	    return false;
	}
    }
    /**
     * helper method to convert integer into a 1 letter String
     * @param int i to be converted
     * @return associated alphabet letter as a string
     */
    private String intToChar(int i) {
	String s = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
	Scanner scr = new Scanner(s);
	String letter = "";
	for(int j = 0; j<=i; j++) {
	    letter = scr.next();
	}
	return letter;
    }
    
    /**
     * helper method to recursively add all items in trie to a list
     * @param necessarily an empty string at the beginning
     * @return an arraylist of all existing strings
     */
    private ArrayList<String> toListHelper(String prefix) {
	ArrayList<String> list = new ArrayList<String>();
	if(this.isWord) {
	    list.add(prefix);
	}
	for(int i = 0; i < 26; i++) {
	    if(children[i] != null) {
		list.addAll(children[i].toListHelper(prefix + intToChar(i)));
	    }
	}
	return list;
    }
    
    /**
     * gives helper method an empty string
     * @return an arraylist of all existing strings
     */
    private ArrayList<String> toList(){
	return toListHelper("");
    }
    
    /**
     * uses toList method to create a string of contents of trie
     * @return string of contents of trie
     */
    public String toString() {
	ArrayList<String> strings = this.toList();
	String s = "";
	for(int i = 0; i < strings.size(); i++) {
	    s = s + strings.get(i) + " ";
	}
	return s;
    }
    

}
