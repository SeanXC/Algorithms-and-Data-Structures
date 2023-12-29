package csu22011_a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new DoublyLinkedList<Integer>();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
		testDLL.insertBefore(1,5);
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
		testDLL.insertBefore(2,6);       
		assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(-1,7);        
		assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(7,8);        
		assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
		testDLL.insertBefore(700,9);        
		assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);        
		assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10,1);        
		assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10,1);        
		assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
	}

	// be executed at least once from at least one test.

	@Test
	public void testGet()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);

		assertEquals( "Checking get position 0", "1", testDLL.get(0).toString() );
		assertEquals( "Checking get position 1", "2", testDLL.get(1).toString() );
		assertEquals( "Checking get position 2", "3", testDLL.get(2).toString() );
		assertNull( "Checking get out of bounds position",testDLL.get(3) );
		assertNull( "Checking get out of bounds position",testDLL.get(-10) );
	}
	@Test
    public void testDeleteAt()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        assertTrue( "Checking deleteAt for position 0", testDLL.deleteAt(0) );
        assertEquals( "Checking list after deletion at position 0", "2,3", testDLL.toString() );
        assertTrue( "Checking deleteAt for last valid position", testDLL.deleteAt(1) );
        assertEquals( "Checking list after deletion at last position", "2", testDLL.toString() );
        assertFalse( "Checking deleteAt for an out of bounds position", testDLL.deleteAt(2) );
        assertFalse("Accessing a position beyond the list's size", testDLL.deleteAt(5));
        assertFalse("Accessing a position beyond the list's size", testDLL.deleteAt(-6));
    }
	@Test
    public void test1NodeDeleteAt()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);

        assertTrue( "Checking deleteAt for position 0", testDLL.deleteAt(0) );
        assertEquals( "Checking list after deletion at position 0", "", testDLL.toString() );
    }
	
    @Test
    public void testReverse()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals( "Checking result after reversing the list", "", testDLL.toString());
        testDLL.insertBefore(0,1);
        testDLL.reverse();
        assertEquals( "Checking result after reversing the list", "1", testDLL.toString());
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.reverse();
        assertEquals( "Checking result after reversing the list", "3,2,1", testDLL.toString() );
    }
	
    @Test
    public void testMakeUnique2Duplicates() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,4);

        testDLL.makeUnique();
        assertEquals("Checking list with 2 duplicates", "1,2,4", testDLL.toString());
    }
    
    @Test
    public void testMakeUnique3Duplicates() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,2);
        testDLL.insertBefore(3,2);
        testDLL.insertBefore(4,4);

        testDLL.makeUnique();
        assertEquals("Checking list with 3 duplicates", "1,2,4", testDLL.toString());
    }
    
    @Test
    public void testMakeUnique0Duplicates() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.insertBefore(4,5);

        testDLL.makeUnique();
        assertEquals("Checking list with 0 duplicates", "1,2,3,4,5", testDLL.toString());
    }
    @Test 
    public void testIsEmptyOrNot() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertTrue("List is empty", testDLL.isEmpty());
        testDLL.insertBefore(0,1);
        assertFalse("List is not empty", testDLL.isEmpty());
    }

}
