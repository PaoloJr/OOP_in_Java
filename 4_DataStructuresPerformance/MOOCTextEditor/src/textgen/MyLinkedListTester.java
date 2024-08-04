/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 20;
	private static final int LIST1_LENGTH = 50;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		emptyList = new MyLinkedList<Integer>();

		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		shortList.add("C");
		
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		
		list1 = new MyLinkedList<Integer>();
		for (int i = 0; i < LIST1_LENGTH; i++) {
			list1.add(i);
		}
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet() {
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		assertEquals("Check third", "C", shortList.get(2));
		
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 0, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)1, list1.get(0));
		assertEquals("Remove: check size is correct ", 49, list1.size());
		
		// TODO: Add more tests here
		int b = list1.remove(1);
		assertEquals("Remove: check element at index b ", 2, b);
		assertEquals("Remove: check new element at 1", (Integer)3, list1.get(1));
		assertEquals("Remove: check size is updated ", 48, list1.size());
		
		// lower bound
		try {
			list1.remove(-1);
			fail("remove at -1 failed");
		} 
		catch (IndexOutOfBoundsException e) {
			
		}
		// higher bound
		try {
			list1.remove(list1.size() + 1);
			fail("remove at index > size");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd() {
        // TODO: implement this test
		boolean first = shortList.add("D");
		boolean second = shortList.add("E");
		boolean third = shortList.add("F");
		
//		assertEquals("Add at end first: ", true, first);
//		assertEquals("Add at end second: ", true, second);
//		assertEquals("Add at end third: ", true, third);
		assertTrue(first);
		assertTrue(second);
		assertTrue(third);
		
		// add null
		try {
			shortList.add(null);
			fail("failed to add a null value");
		}
		catch (NullPointerException e) {
			
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize() {
		// TODO: implement this test
		int emptyListSize = emptyList.size();
		int shortListSize = shortList.size();
		int longListSize = longerList.size();
		int list1Size = list1.size();
		
		assertEquals("Test size; emptyList ", 0, emptyListSize);
		assertEquals("Test size; shortList ", 3, shortListSize);
		assertEquals("Test size; longerList ", 20, longListSize);
		assertEquals("Test size; list1List ", 50, list1Size);
		
	}	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex() {
        // TODO: implement this test
		// at at the end
		shortList.add(3, "New Node");
		shortList.add(4, "Another Node");
		
		// add at index
		shortList.add(0, "addition Node");
		shortList.add(1, "and another addition");
		
//		System.out.println(shortList);
		
		assertEquals("Short List add at index 0: ", "addition Node", shortList.get(0));
		assertEquals("Short List add at index 1: ", "and another addition", shortList.get(1));
		assertEquals("Short List add at index 5: ", "New Node", shortList.get(5));
		assertEquals("Short List add at index 6: ", "Another Node", shortList.get(6));
		
		// lower bound
		try {
			shortList.add(-1, "new at -1");
			fail("failed at index -1");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// upper bound
		try {
			shortList.add(shortList.size() + 1, "new at size + 1");
			fail("failed at index size + 1");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// add null
		try {
			shortList.add(0, null);
			fail("failed to add null-value at index 0");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet() {
	    // TODO: implement this test
		shortList.set(0, "new replacement");
		assertEquals("Short List set at index 0: ", "new replacement", shortList.get(0));
		shortList.set(2, "index 2 replace");
		assertEquals("Short List set at index 2: ", "index 2 replace", shortList.get(2));
		
		// lower bound
		try {
			shortList.set(-1, "some lower-bound index");
			fail("set failed at -1");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// higher bound
		try {
			shortList.set(shortList.size() + 1, "some higher-bound index");
			fail("set failed at shortList size + 1");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// set null value
		try {
			shortList.set(1, null);
			fail("failed to set value at index 1 to null");
		}
	    catch (NullPointerException e) {
	    	
	    }
	}
	
}
