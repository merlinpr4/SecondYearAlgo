import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Merlin Prasad
 *  @version 09/10/18 11:13:22
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */

class DoublyLinkedList<T extends Comparable<T>>
{
	public int size = 0 ;
	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;



		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;

		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost:Theta(1)
	 *
	 * Justification:
	 *  This function will always have a constant running time.Each basic operation may be assumed to take Theta(1) time to execute 
	 */
	public boolean isEmpty()
	{	
		if (head == null) 
		{
			return true;
		}
		return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the	
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Big O of N
	 *
	 * Justification:
	 *  Determining where to place the the new node will depend on amount of nodes already created in linked list and the postion of the current node
	 *   would have to be placed.This will cost O(n) .Insertation of the node would be O(1).We can also assume all other each basic operation may be assumed to take Theta(1) time to execute
	 *    O = 1+1+1+1+1... + N = O(N)
	 */
	public void insertBefore( int pos, T data ) 
	{
		DLLNode newNode = new DLLNode(data,null,null); 
		if(isEmpty()) 
		{
			newNode.prev = null; 
			newNode.next=null;
			head = newNode; 
			tail = newNode;
			size = size + 1;
			return; 
		}
		else if(pos<= 0)//insert at head
		{
			newNode.prev = null; 
			newNode.next=head;
			head.prev = newNode ;
			head = newNode; //only update head and tail at the end
			size = size + 1;
			return ;
		}
		else if(pos>= size)//insert at tail
		{
			newNode.prev = tail; 
			newNode.next=null;
			tail.next = newNode ;
			tail = newNode; 
			size = size + 1;
			return ;
		}
		else
		{
			DLLNode currentpt = head; 
			for(int i=0 ; i< pos ; i ++) 
			{
				currentpt = currentpt.next ;  
			}
			newNode.prev = currentpt.prev;
			newNode.next = currentpt;
			currentpt.prev.next = newNode ; 
			currentpt.prev = newNode ;	
			size ++ ;
			return;
		}
	}
	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Big O of N
	 *
	 * Justification:
        Every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list until it reaches position of element we are looking for, and therefore the total cost of this method will be =O(n).
	 *
	 */
	public T get(int pos) 
	{
		if (pos>=0)
		{
			if (pos<size)
			{
				DLLNode currentpt = head; 
				for(int i=0 ; i< pos ; i ++) 
				{
					currentpt = currentpt.next ;  
				}
				return currentpt.data ;
			}
		}
			return null;
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost:  Big O of N
	 *
	 * Justification:
	 *  *  Determining the node to delete will depend on amount of nodes already created in linked list and the postion of the current node
	 *   would have to be deleted.This will cost O(n) .Deletion of the node would be O(1).We can also assume all other each basic operation may be assumed to take Theta(1) time to execute
	 *    O = 1+1+1+1+1... + N = O(N)
	 */
	public boolean deleteAt(int pos) 
	{
		if(isEmpty())
		{
			return false;
		}
		if(pos >= 0)
		{
			if(pos <size)
			{
				DLLNode currentpt = head; 
				for(int i=0 ; i< pos ; i ++) 
				{
					currentpt = currentpt.next ;  
				}
				if (pos == 0)//first node
				{
					head = currentpt.next ;

					if(head != null)//to deal with only one node
					{
						head.prev = null;
					}

					currentpt = null ;
					size--;
					return true;
				}
				else if (pos == size-1)//last node
				{
					tail = currentpt.prev ;
					tail.next = null ;
					currentpt = null;
					size--;
					return true;
				}
				else
				{			
					currentpt.prev.next = currentpt.next;
					currentpt.next.prev = currentpt.prev;
					currentpt = null ; 
					size--;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Big O of N
	 *
	 * Justification:Amount of nodes to be reversed in linked list would depend on amount of nodes already in the linked list which is N.
	 *  To traverse through the list  will cost O(n).
	 *  Each assignment operation would cost O(1) so O = 1+1+1+1+1+1+1+1+1+1+1+1+...+N = O(N)
	 *  
	 */
	public void reverse()
	{
		if(!isEmpty())
		{
			if(tail !=head)
			{
				DLLNode currentpt = head; 
				currentpt.prev =head.next ;
				tail = currentpt;
				currentpt = currentpt.next ;		

				while(currentpt.next!= null)
				{
					currentpt.prev = currentpt.next ; 
					currentpt.next = head;
					head = head.next ;
					currentpt = currentpt.prev ;
				}
				currentpt.next = currentpt.prev;
				currentpt.prev = null;
				tail.next = null;
				head = currentpt ;
			}
		}
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements unique.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: N^5 WRONG ITS N^3 ( GET AND delete are sequential so its N + N + N = N * N^2 of for loops to be = N^3
	 *
	 * Justification:The amount of elements that need to be traversed in the list is N. The two nested for loops means N*N = N^2
	 *  The get fuctions have O(n) and the two get functions mean (N^2).The delete function has O(n).So O = N *N * N * N *N= O(N^5)
	 */
	public void makeUnique()
	{
		if(!isEmpty())
		{
			if(tail !=head)
			{
				for(int i =0 ; i<size ; i++)
				{
					for(int j =i+1 ; j<size ; j++)
					{
						if(get(i) == get(j))
						{
							deleteAt(j);
							j--;
						}
					}
				}
			}
		}

	}


	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost:Big O of 1
	 *
	 * Justification:
	 * Inseration of node will always take O(1).Function will have constant running time and we assume all basic operation of assignment is Theta(1).
	 * The inertBefore function has O(1) as item is always added to head and this wont change depending on size of DLL.So O(1).
	 */
	public void push(T item) 
	{	
		insertBefore(0, item);
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Big O of 1
	 *
	 * Justification:
	 *  The functions get and delete both have constant time to execute of Theta(1) as it is always the first element being popped.This will not change even as stack increases in size.
	 *  O = 1 +1 =2 = O (1) This function will have constant running time
	 */ 
	public T pop() 
	{
		T element = get(0);
		deleteAt(0);	
		return element;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Big O of 1
	 *
	 * Justification:
	 * Inseration of node will always take O(1).Function will have constant running time and we assume all basic operation of assignment is Theta(1).
	 * The insertBefore function has O(1) as item is always added to tail and this wont change depending on size of DLL.So O(1).
	 */
	public void enqueue(T item) 
	{
		insertBefore(size, item);
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an equeue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Big O of 1
	 *
	 * Justification:
	 *  The functions get and delete both have constant time to execute of Theta(1) as it is always the first element being popped.This will not change even as stack increases in size.
	 *  O = 1 +1 =2 = O (1) This function will have constant running time
	 */
	public T dequeue() 
	{
		T element = get(0);
		deleteAt(0);	
		return element;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}


}




