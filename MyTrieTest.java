import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class MyTrieTest {

    @Test
    public void testContainsPrefix() {
	MyTrie test = new MyTrie();
	test.add("apple");
	test.add("hello");
	test.add("app");
	test.add("hellos");
	assertTrue(test.containsPrefix("a"));
	assertTrue(test.containsPrefix(""));
	assertTrue(test.containsPrefix("z")== false);
    }
    
    @Test
    public void testSize() {
	MyTrie test = new MyTrie();
	test.add("apple");
	test.add("hello");
	test.add("app");
	test.add("hellos");
	assertTrue(test.size() == 4);

    }

    @Test
    public void testIsEmpty() {
	MyTrie test = new MyTrie();
	assertTrue(test.isEmpty());
    }

   
    @Test
    public void testIterator() {
	MyTrie test = new MyTrie();
	test.add("apple");
	test.add("hello");
	test.add("app");
	test.add("hellos");
	Iterator<String> itr = test.iterator();
	assertTrue(itr.next().equals("app"));
    }

   
    @Test
    public void testContainsString() {
	MyTrie test = new MyTrie();
	test.add("apple");
	test.add("hello");
	test.add("app");
	test.add("hellos");
	assertTrue(test.contains("apple"));
	assertTrue(test.contains("hellos"));
	assertTrue(test.contains("app"));
    }

    @Test
    public void testAddString() {
	MyTrie test = new MyTrie();

	assertTrue(test.add("test"));
    }

}
