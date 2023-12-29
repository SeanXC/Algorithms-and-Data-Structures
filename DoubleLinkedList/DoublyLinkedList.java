package csu22011_a2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 9/10/23
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
	 */
	public boolean isEmpty()
	{
		return ((head == null)&&(tail == null));
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
	 */
	public void insertBefore( int pos, T data ) 
	{

		if (pos <= 0) 
		{
			DLLNode newNode = new DLLNode(data, null, head);
			if(!isEmpty())
			{
				head.prev = newNode;
			}
			else
			{
				tail = newNode;
			}
			head = newNode;
		}

		else if (pos>= getSize())
		{
			DLLNode insNode = new DLLNode(data, tail, null);
			if(isEmpty())
			{
				head = insNode;
			}
			else
			{
				tail.next = insNode;
			}
			tail = insNode;
		}

		else
		{
			DLLNode tmpNode = head;
			for(int i = 0; i<pos && tmpNode.next!= null; i++)
			{
				tmpNode = tmpNode.next;
			}
			DLLNode insNode = new DLLNode(data, tmpNode.prev, tmpNode);
			tmpNode.prev.next = insNode;
			tmpNode.prev = insNode;
		}
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 *
	 */
	public T get(int pos) 
	{
		if(isEmpty()||pos<0||pos>=getSize())
		{
			return null;
		}
		DLLNode getNode = head;
		for(int i = 0; i<pos; i++ )

		{
			getNode = getNode.next;
		}
		return getNode.data;
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 */
	public boolean deleteAt(int pos) 
	{
		if (isEmpty() || pos < 0 || pos >= getSize()) 
		{
			return false;
		}

		if (pos == 0) 
		{
			if (head == tail) 
			{
				head = null;
				tail = null;
			} 
			else 
			{
				head = head.next;
				head.prev = null;
			}
		} 
		else 
		{
			DLLNode tmp = head;
			for (int i = 0; i < pos; i++) 
			{
				tmp = tmp.next;
			}
			if (tmp == tail) {
				tail = tail.prev;
				tail.next = null;
			} else {
				tmp.prev.next = tmp.next;
				tmp.next.prev = tmp.prev;
			}
		}
		return true;
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 */
	public void reverse() 
	{
		if (!isEmpty()) 
		{
			DLLNode current = head;
			DLLNode tmp;
			while (current != null) 
			{
				tmp = current.next;
				current.next = current.prev;
				current.prev = tmp;
				current = tmp;
			}
			DLLNode swapTmp = head;
			head = tail;
			tail = swapTmp;
		}
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 */
	public void makeUnique() 
	{
		DLLNode currentNode = head;
		while(currentNode != null)
		{
			DLLNode checkNode = currentNode.next;
			while(checkNode != null)
			{
				if(currentNode.data.equals(checkNode.data))
				{
					if(checkNode == tail)
					{
						tail = checkNode.prev;
					}
					if(checkNode.prev != null)
					{
						checkNode.prev.next = checkNode.next;
					}
					if(checkNode.next != null)
					{
						checkNode.next.prev = checkNode.prev;
					}
					checkNode = checkNode.next;
				}
				else
				{
					checkNode = checkNode.next;
				}
			}
			currentNode = currentNode.next;
		}
	}




	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
	 *
	 *  The lines before the for loop involve 
	 *  - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
	 *  - the allocation and assignment of two variables which are constant time operations.
	 *  Thus the code before the for-loop will take Theta(1) time to run.
	 *
	 *  The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
	 *
	 *  The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
	 *  Each iteration will involve:
	 *   * The if-conditional will run:
	 *       - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
	 *       - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
	 *       - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
	 *     Therefore the if-then-else conditions will cost Theta(1) in the worst case.
	 *   * The final call to StringBuilder's append will cost again Theta(1)
	 *   * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list). 
	 *     Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
	 *  Therefore each iteration of the loop will cost Theta(1).
	 *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
	 *
	 * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
	 *
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

	public int getSize() 
	{
		int size = 0;
		for (DLLNode tmp = head; tmp != null; tmp = tmp.next) 
		{
			size++;
		}
		return size;
	}

}


