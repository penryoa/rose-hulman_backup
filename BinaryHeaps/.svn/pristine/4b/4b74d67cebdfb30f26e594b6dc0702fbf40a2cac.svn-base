import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 */
public class BinaryHeapTest {

	/**
	 * Simple test method for insert, delete, toString, and sort. Enforces the
	 * method signatures.
	 */
	@Test
	public void testSimple() {
		// TODO: Implement the BinaryHeap class according to the spec.

		/*
		 * CONSIDER: If you are choosing to implement the heap's internal
		 * storage using an array (and not an ArrayList), like we did on
		 * GrowableCircularArrayQueue from the StacksAndQueues assignment, then
		 * you'll need to pass in to the constructor a parameter that tells what
		 * class it is. That is, use the second version (currently commented
		 * out) below, and see StacksAndQueues for more examples. You can modify
		 * the tests below as well to match.
		 */
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		// BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Integer.class);
		
		// deleteMin returns null if it has no elements.
		assertNull(heap.deleteMin());
		heap.insert(21);
		assertEquals("[null, 21]", heap.toString());
		int min = heap.deleteMin();
		assertEquals(21, min);
		assertEquals("[null]", heap.toString());
		String[] csLegends = new String[] { "Edsger Dijkstra", "Grace Hopper", "Donald Knuth", "Ada Lovelace",
				"Claude Shannon", "Alan Turing" };

		BinaryHeap<String> csLegendsHeap = new BinaryHeap<String>();
		csLegendsHeap.sort(csLegends);
		assertEquals("[Ada Lovelace, Alan Turing, Claude Shannon, Donald Knuth, Edsger Dijkstra, Grace Hopper]",
				Arrays.toString(csLegends));
	}

	// TODO: Add unit tests for each method until you are satisfied your code is
	// correct. I will test your code with more complex tests. Since this
	// assignment is short, I'd like you to give the tests some thought, rather
	// than just always relying on someone else's tests. Professional developers
	// usually write their own unit tests.

}
