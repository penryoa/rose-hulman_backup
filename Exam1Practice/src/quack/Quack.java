package quack;

import java.util.NoSuchElementException;

/**
 * A class to implement the Quack ADT.
 *
 * @author Nate Chenette and << Your Name Here >>.
 *         Created Dec 15, 2016.
 */

public class Quack {
	SinglyLinkedList list;  // Data must be stored here.
	boolean inStackMode;    // true if Quack in stack mode, false if in queue mode.
	
	public Quack() {
		list = new SinglyLinkedList();
		inStackMode = true; // Quack always starts in stack mode
	}
	
	
	/**
	 * Converts and sets the Quack to stack mode. If already in stack mode, stay there.
	 */
	public void toStackMode() {
		// TODO Write me!

	}
	
	/**
	 * Converts and sets the Quack to queue mode. If already in queue mode, stay there.
	 */
	public void toQueueMode() {
		// TODO Write me!
		
	}
	
	
	/**
	 * Insert an item into the Quack.
	 */
	public void insert(String newitem) {
		// TODO Write me!
		
	}


	/**
	 * Retrieve the next item from the Quack according to mode:
	 *    FIFO if in queue mode
	 *    LIFO if in stack mode
	 *    
	 *    If the Quack is empty, throws a NoSuchElementException.
	 */
	public String retrieve() {
		// TODO Write me!
		
		return null;
	}
	
	
	
	
	// ------- Code below this point should not be changed -------
	
	
	// For testing purposes
	public String toString() {
		return list.toString();
	}
	
	
	
	/**
	 * Basic singly linked list.
	 */
	public class SinglyLinkedList {
		Node head;
		
		SinglyLinkedList() {
			head = null;
		}
		
		// For testing/debugging purposes
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("[");
			Node nd = head;
			if (nd != null) {
				s.append(nd.data);
				nd = nd.next;
			}
			while (nd != null) {
				s.append(", ");
				s.append(nd.data);
				nd = nd.next;
			}
			s.append("]");
			return s.toString();
		}
	}
	

	/**
	 * Basic node.
	 */
	class Node {
		String data;
		Node next;
		
		Node(String item, Node nextNode) {
			data = item;
			next = nextNode;
		}
	}
	
}
