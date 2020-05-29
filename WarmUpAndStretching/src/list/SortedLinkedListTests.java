package list;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Tests {@link SortedLinkedList}.
 */
public class SortedLinkedListTests {

	private static float points = 0;

	/**
	 * Tests {@link SortedLinkedList#SortedLinkedList()}.
	 */
	@Test
	public void testConstructor() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		assertEquals("[]", list.toString());
	}

	/**
	 * Tests {@link SortedLinkedList#add(Comparable)}.
	 */
	@Test
	public void testAdd1() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		list.add(10);
		assertEquals("[10]", list.toString());
		points += 1.5;
	}

	/**
	 * Tests {@link SortedLinkedList#add(Comparable)}.
	 */
	@Test
	public void testAdd2() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		list.add(20);
		list.add(10);
		assertEquals("[10,20]", list.toString());
		points += 2;
	}

	/**
	 * Tests {@link SortedLinkedList#add(Comparable)}.
	 */
	@Test
	public void testAdd3() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		list.add(10);
		list.add(5);
		list.add(20);
		list.add(15);
		list.add(18);
		list.add(25);
		assertEquals("[5,10,15,18,20,25]", list.toString());
		points += 2;
	}

	/**
	 * Tests {@link SortedLinkedList#addFirst(Comparable)}.
	 */
	@Test
	public void testAddFirst() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		try {
			list.addFirst(1);
			fail("Should have thrown exception");
		} catch (UnsupportedOperationException e) {
			points += 1.5;
		}
	}

	/**
	 * Tests {@link SortedLinkedList#addLast(Comparable)}.
	 */
	@Test
	public void testAddLast() {
		SortedLinkedList<Integer> list = new SortedLinkedList<Integer>();
		try {
			list.addLast(1);
			fail("Should have thrown exception");
		} catch (UnsupportedOperationException e) {
			points += 1.5;
		}
	}

	/**
	 * Tests {@link SortedLinkedList#SortedLinkedList(DoublyLinkedList)}.
	 */
	@Test
	public void testListConstructor() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.add(10);
		list.add(5);
		list.add(20);
		list.add(7);
		list.add(25);
		list.add(18);
		list.add(19);
		SortedLinkedList<Integer> sortedList = new SortedLinkedList<Integer>(
				list);
		assertEquals("[5,7,10,18,19,20,25]", sortedList.toString());
		points += 1.5;
	}

	@AfterClass
	public static void showPoints() {
		System.out.printf("SORTED LINKED LIST POINTS = %.1f of 10.0\n", points);
	}
}
