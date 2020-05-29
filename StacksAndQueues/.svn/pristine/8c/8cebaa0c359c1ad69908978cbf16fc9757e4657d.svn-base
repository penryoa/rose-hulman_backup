package queues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the growable array queue.
 * 
 * @author Matt Boutell. Created Nov 30, 2013.
 */
public class GrowableCircularArrayQueueTest {

	private static int points = 0;
	private GrowableCircularArrayQueue<Integer> intQueue;
	private GrowableCircularArrayQueue<String> stringQueue;

	/**
	 * Creates some test cases.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.intQueue = new GrowableCircularArrayQueue<Integer>(Integer.class);
		this.stringQueue = new GrowableCircularArrayQueue<String>(String.class);
	}

	// Helper method
	private void addItemsToEachQueue() {
		this.intQueue.enqueue(15);
		this.intQueue.enqueue(10);
		this.intQueue.enqueue(8);
		this.intQueue.enqueue(23);

		this.stringQueue.enqueue("nice");
		this.stringQueue.enqueue("job");
		this.stringQueue.enqueue("so");
		this.stringQueue.enqueue("far");
		this.stringQueue.enqueue("keep");
		this.stringQueue.enqueue("up");
		this.stringQueue.enqueue("the");
		this.stringQueue.enqueue("great");
		this.stringQueue.enqueue("work");
	}

	/**
	 * Test method for
	 * {@link GrowableCircularArrayQueue#enqueue(java.lang.Object)}.
	 */
	@Test
	public void testEnqueueUsingSize() {
		assertEquals(0, this.intQueue.size());
		this.intQueue.enqueue(15);
		assertEquals(1, this.intQueue.size());
		this.intQueue.enqueue(10);
		assertEquals(2, this.intQueue.size());
		this.intQueue.enqueue(8);
		assertEquals(3, this.intQueue.size());
		this.intQueue.enqueue(23);
		assertEquals(4, this.intQueue.size());

		assertEquals(0, this.stringQueue.size());
		this.stringQueue.enqueue("nice");
		assertEquals(1, this.stringQueue.size());
		this.stringQueue.enqueue("job");
		assertEquals(2, this.stringQueue.size());
		this.stringQueue.enqueue("so");
		assertEquals(3, this.stringQueue.size());
		this.stringQueue.enqueue("far");
		assertEquals(4, this.stringQueue.size());
		this.stringQueue.enqueue("keep");
		assertEquals(5, this.stringQueue.size());
		this.stringQueue.enqueue("up");
		assertEquals(6, this.stringQueue.size());
		this.stringQueue.enqueue("the");
		assertEquals(7, this.stringQueue.size());
		this.stringQueue.enqueue("great");
		assertEquals(8, this.stringQueue.size());
		this.stringQueue.enqueue("work");
		assertEquals(9, this.stringQueue.size());
		
		points += 1;
	}

	@Test
	public void testEnqueueUsingToString() {
		addItemsToEachQueue();
		assertEquals("[15, 10, 8, 23]", this.intQueue.toString());
		assertEquals("[nice, job, so, far, keep, up, the, great, work]", this.stringQueue.toString());
		points += 1;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#dequeue()}.
	 */
	@Test
	public void testDequeueUsingEnqueue() {
		addItemsToEachQueue();
		addItemsToEachQueue(); // forces a resize.
		assertEquals(15, (int)this.intQueue.dequeue());
		assertEquals(10, (int)this.intQueue.dequeue());
		assertEquals(8, (int)this.intQueue.dequeue());
		assertEquals(23, (int)this.intQueue.dequeue());

		assertEquals("nice", this.stringQueue.dequeue());
		assertEquals("job", this.stringQueue.dequeue());
		assertEquals("so", this.stringQueue.dequeue());
		assertEquals("far", this.stringQueue.dequeue());
		assertEquals("keep", this.stringQueue.dequeue());
		assertEquals("up", this.stringQueue.dequeue());
		assertEquals("the", this.stringQueue.dequeue());
		assertEquals("great", this.stringQueue.dequeue());
		assertEquals("work", this.stringQueue.dequeue());
		points += 2;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#dequeue()}.
	 */
	@Test
	public void testDequeueEmptyQueue() {
		try {
			this.intQueue.dequeue();
			fail("Should have thrown a NoSuchElementException");

		} catch (NoSuchElementException e) {
			points += 1;
		}
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#peek()}.
	 */
	@Test
	public void testPeekEmptyQueue() {
		try {
			this.intQueue.peek();
			fail("Should have thrown a NoSuchElement exception");

		} catch (NoSuchElementException e) {
			points += 1;
		}
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#peek()}.
	 */
	@Test
	public void testPeekUsingEnqueue() {
		String[] words = "No matter what is enqueued, peek returns the first item enqueued until something is dequeued"
				.split(" ");
		String firstWord = words[0];
		for (String word : words) {
			this.stringQueue.enqueue(word);
			assertEquals(firstWord, this.stringQueue.peek());
		}
		points += 1;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(this.intQueue.isEmpty());
		assertTrue(this.stringQueue.isEmpty());
		this.stringQueue.enqueue("an item");
		assertFalse(this.stringQueue.isEmpty());
		points += 1;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#clear()}.
	 */
	@Test
	public void testClearUsingSize() {
		addItemsToEachQueue();
		assertEquals(4, this.intQueue.size());
		assertEquals(9, this.stringQueue.size());
		
		this.intQueue.clear();
		this.stringQueue.clear();
		
		assertEquals(0, this.intQueue.size());
		assertEquals(0, this.stringQueue.size());

		// Make sure it can handle new items inserted.
		this.intQueue.enqueue(17);
		assertEquals(1, this.intQueue.size());
		points += 1;
	}

	/**
	 * Test method for {@link GrowableCircularArrayQueue#size()}.
	 */
	@Test
	public void testSizeEmptyQueue() {
		assertEquals(0, this.intQueue.size());
		assertEquals(0, this.stringQueue.size());
		points += 1;
	}

	/**
	 * Test method for
	 * {@link GrowableCircularArrayQueue#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		addItemsToEachQueue();
		assertTrue(this.intQueue.contains(15));
		assertTrue(this.intQueue.contains(8));
		assertTrue(this.intQueue.contains(23));
		assertFalse(this.intQueue.contains(0));
		assertFalse(this.intQueue.contains(1));
		assertFalse(this.intQueue.contains(-1));
		
		assertTrue(this.stringQueue.contains("nice"));
		assertTrue(this.stringQueue.contains("job"));
		assertTrue(this.stringQueue.contains("work"));
		assertFalse(this.stringQueue.contains("help"));
		assertFalse(this.stringQueue.contains("not"));
		assertFalse(this.stringQueue.contains("here"));
		points += 2;
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToStringEmpty() {
		assertEquals("[]", this.intQueue.toString());
		assertEquals("[]", this.stringQueue.toString());
		points += 1;
	}

	/**
	 * Test method 
	 */
	@Test
	public void testCaseFromSpecification() {
		assertEquals(0, this.stringQueue.size());
		this.stringQueue.enqueue("a");
		assertEquals(1, this.stringQueue.size());
		this.stringQueue.enqueue("b");
		this.stringQueue.enqueue("c");
		this.stringQueue.enqueue("d");
		this.stringQueue.enqueue("e");
		assertEquals("[a, b, c, d, e]", this.stringQueue.toString());
		// forces capacity to double
		this.stringQueue.enqueue("f");
		this.stringQueue.enqueue("g");
		this.stringQueue.enqueue("h");
		assertEquals(8, this.stringQueue.size());
		assertEquals("[a, b, c, d, e, f, g, h]", this.stringQueue.toString());
		assertEquals("a", this.stringQueue.dequeue());
		assertEquals("b", this.stringQueue.dequeue());
		assertEquals("c", this.stringQueue.dequeue());
		assertEquals("d", this.stringQueue.dequeue());
		assertEquals("e", this.stringQueue.dequeue());
		assertEquals("[f, g, h]", this.stringQueue.toString());
		
		this.stringQueue.enqueue("i");
		this.stringQueue.enqueue("j");
		// Now it is full. Next one will wrap around
		this.stringQueue.enqueue("k"); 
		assertEquals("[f, g, h, i, j, k]", this.stringQueue.toString());

		this.stringQueue.enqueue("L");
		this.stringQueue.enqueue("m");
		this.stringQueue.enqueue("n");
		this.stringQueue.enqueue("o");
		System.out.println(this.stringQueue.debugString()); 
		
		// Wraps around
		this.stringQueue.enqueue("p");
		// If did like suggested, will be f through p, plus capacity for 9 more elements (probably null)
		System.out.println(this.stringQueue.debugString()); 
		points += 6;
	}
	
	/**
	 * Test method 
	 */
	@Test
	public void testCaseResizeWhenWrappedAround() {
		this.stringQueue.enqueue("a");
		this.stringQueue.enqueue("b");
		this.stringQueue.enqueue("c");
		this.stringQueue.enqueue("d");
		this.stringQueue.enqueue("e");
		assertEquals("a", this.stringQueue.dequeue());
		assertEquals("b", this.stringQueue.dequeue());
		this.stringQueue.enqueue("f");
		this.stringQueue.enqueue("g");
		// Full
		assertEquals("[c, d, e, f, g]", this.stringQueue.toString());
		
		this.stringQueue.enqueue("h");
		// Should have doubled correctly.
		assertEquals("[c, d, e, f, g, h]", this.stringQueue.toString());
		
		points += 2;
		
	}	
	
	/**
	 * Displays how many points were earned.
	 */
	@AfterClass
	public static void showPoints() {
		System.out.printf("GrowableCircularArrayQueue implementation earned %d/21 points\n", points);
	}
}
