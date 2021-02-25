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
 *  @author Merlin Prasad
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

	@Test
	public void testget()
	{
		//test empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();	
		assertNull(testDLL.get(2));		
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);		
		//test non -empty list for position
		assertEquals( "Checking get to a list containing 3 elements at position 2","3", testDLL.get(2).toString());
		assertNull(testDLL.get(-2));	
	}

	@Test
	public void testdeleteAt()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,5);

		//test non-empty list

		//delete last node
		testDLL.deleteAt(2); 
		assertEquals( "Checking deleteAt to a list containing 3 elements at position 2", "1,2", testDLL.toString());


		//delete first node
		testDLL.deleteAt(0);
		assertEquals( "Checking deleteAt to a list containing 2 elements at position 0", "2", testDLL.toString());

		//check deleting only node
		assertTrue(testDLL.deleteAt(0));

		//delete node in empty list
		assertFalse(testDLL.deleteAt(1));

		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);

		//delete middle node
		assertTrue(testDLL.deleteAt(1));
		testDLL.insertBefore(1,2);
		testDLL.deleteAt(1); 
		assertEquals( "Checking deleteAt to a list containing 3 elements at position 1", "1,3", testDLL.toString());

		//delete node beyond size of list
		assertFalse(testDLL.deleteAt(7));
		
		//delete node beyond size of list
		assertFalse(testDLL.deleteAt(-7));

	}

	@Test
	public void testpush()
	{
		//test empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();

		testDLL.push(1);
		assertEquals( "Checking push to a list containing 0 elements at position 0", "1", testDLL.toString());

		//test non-empty list
		testDLL.push(3);
		assertEquals( "Checking push to a list containing 1 elements at position 0", "3,1", testDLL.toString());
	}


	@Test
	public void testpop()
	{

		//test empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.pop();
		assertEquals( "Checking pop to a list containing 0 elements at position 0", "", testDLL.toString());

		//test non empty list
		testDLL.push(3);
		testDLL.push(1);
		testDLL.push(7);
		testDLL.pop();
		assertEquals( "Checking pop to a list containing 3 elements at position 0", "1,3", testDLL.toString());
		testDLL.pop();
		assertEquals( "Checking pop to a list containing 2 elements at position 0", "3", testDLL.toString());

		testDLL.push(7);
		assertEquals( "Checking get to a list containing 3 elements at position 0","7", testDLL.pop().toString());

	}

	@Test
	public void testenqueue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//test empty list
		testDLL.enqueue(1);
		assertEquals( "Checking enqueue to a list containing 0 elements ", "1", testDLL.toString());

		//test non-empty list
		testDLL.enqueue(6);
		assertEquals( "Checking enqueue to a list containing 1 elements ", "1,6", testDLL.toString());		
	}

	@Test
	public void testdequeue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//test empty list
		testDLL.dequeue();
		assertEquals( "Checking enqueue to a list containing 0 elements ", "", testDLL.toString());

		testDLL.enqueue(1);
		testDLL.enqueue(6);
		//test non-empty list
		testDLL.dequeue();
		assertEquals( "Checking enqueue to a list containing 0 elements ", "6", testDLL.toString());
	}

	@Test
	public void testreverse()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//test empty list
		testDLL.reverse();
		assertEquals( "Checking reverse to a list containing 0 elements ", "", testDLL.toString());

		// test non-empty list
		testDLL.insertBefore(0,1);
		//test one element
		testDLL.reverse();
		assertEquals( "Checking reverse to a list containing 1 elements ", "1", testDLL.toString());

		//test multiple elements
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.reverse();
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "3,2,1", testDLL.toString());

		testDLL.insertBefore(0,5);
		testDLL.reverse();
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "1,2,3,5", testDLL.toString());
	}

	@Test
	public void testmakeUnique()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//test empty list
		testDLL.makeUnique();
		assertEquals( "Checking reverse to a list containing 0 elements ", "", testDLL.toString());

		// test non-empty list
		testDLL.insertBefore(0,1);
		//test one element
		testDLL.makeUnique();
		assertEquals( "Checking makeUnique to a list containing 1 elements ", "1", testDLL.toString());

		//test multiple elements no duplicates
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.makeUnique();
		assertEquals( "Checking makeUnique to a list containing 3 elements", "1,2,3", testDLL.toString());

		//test multiple elements 1 duplicates
		testDLL.insertBefore(2,3);
		testDLL.makeUnique();
		assertEquals( "Checking makeUnique to a list containing 4 elements ", "1,2,3", testDLL.toString());

		//test multiple duplicates
		testDLL.push(2);
		testDLL.push(1);
		testDLL.push(3);
		testDLL.insertBefore(3,3);
		testDLL.makeUnique();
		assertEquals( "Checking makeUnique to a list containing 7 elements", "3,1,2", testDLL.toString());

	}
}
