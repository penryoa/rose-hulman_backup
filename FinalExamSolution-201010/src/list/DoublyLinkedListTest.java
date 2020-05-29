package list;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link DoublyLinkedList}.
 * 
 * @author Curt Clifton
 */
public class DoublyLinkedListTest {

	private DoublyLinkedList<String> empty;
	private DoublyLinkedList<String> forward;
	private DoublyLinkedList<String> reverse;
	private DoublyLinkedList<Integer> fromArray;

	/**
	 * Sets up some lists.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.empty = new DoublyLinkedList<String>();

		this.forward = new DoublyLinkedList<String>();
		this.forward.addLast("Rose");
		this.forward.addLast("Hulman");

		this.reverse = new DoublyLinkedList<String>();
		this.reverse.addFirst("Rose");
		this.reverse.addFirst("Hulman");

		this.fromArray = DoublyLinkedList.fromArray(new Integer[] { 1, 2, 3 });
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse0() {
		assertEquals("[]", this.empty.reverse().toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse1() {
		assertEquals("[Hulman,Rose]", this.forward.reverse().toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse2() {
		assertEquals("[Rose,Hulman]", this.reverse.reverse().toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse3() {
		assertEquals("[3,2,1]", this.fromArray.reverse().toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isInOrder()}.
	 */
	@Test
	public void testIsInOrder0() {
		// Ordered by string length
		Comparator<String> lengthComp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
		assertTrue(this.empty.isInOrder(lengthComp));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isInOrder()}.
	 */
	@Test
	public void testIsInOrder1() {
		// Ordered by string length
		Comparator<String> lengthComp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
		assertTrue(this.forward.isInOrder(lengthComp));
		assertFalse(this.reverse.isInOrder(lengthComp));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isInOrder()}.
	 */
	@Test
	public void testIsInOrder2() {
		// Ordered by natural order
		Comparator<String> naturalComp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		assertFalse(this.forward.isInOrder(naturalComp));
		assertTrue(this.reverse.isInOrder(naturalComp));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isInOrder()}.
	 */
	@Test
	public void testIsInOrder3() {
		// Ordered by natural order
		Comparator<Integer> naturalComp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		assertTrue(this.fromArray.isInOrder(naturalComp));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isInOrder()}.
	 */
	@Test
	public void testIsInOrder4() {
		// Ordered by natural order
		Comparator<Integer> naturalComp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};
		DoublyLinkedList<Integer> outOfOrder = DoublyLinkedList
				.fromArray(new Integer[] { 1, 2, 4, 3 });
		assertFalse(outOfOrder.isInOrder(naturalComp));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf0() {
		assertEquals(-1, this.empty.indexOf(""));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf1() {
		assertEquals(0, this.forward.indexOf("Rose"));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf2() {
		assertEquals(1, this.forward.indexOf("Hulman"));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf3() {
		assertEquals(-1, this.forward.indexOf("Purdue"));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf4() {
		assertEquals(0, this.fromArray.indexOf(1));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf5() {
		assertEquals(1, this.fromArray.indexOf(2));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf6() {
		assertEquals(2, this.fromArray.indexOf(3));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#indexOf()}.
	 */
	@Test
	public void testIndexOf7() {
		assertEquals(-1, this.fromArray.indexOf(4));
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst1() {
		assertEquals("Rose", this.forward.removeFirst());
		assertEquals("[Hulman]", this.forward.toString());
		assertEquals("Hulman", this.reverse.removeFirst());
		assertEquals("[Rose]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst2() {
		this.empty.removeFirst();
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeLast()}.
	 */
	@Test
	public void testRemoveLast1() {
		assertEquals("Hulman", this.forward.removeLast());
		assertEquals("[Rose]", this.forward.toString());
		assertEquals("Rose", this.reverse.removeLast());
		assertEquals("[Hulman]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeLast()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast2() {
		this.empty.removeLast();
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(this.empty.isEmpty());
		assertFalse(this.forward.isEmpty());
		this.forward.removeFirst();
		assertFalse(this.forward.isEmpty());
		this.forward.removeLast();
		assertTrue(this.forward.isEmpty());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("[]", this.empty.toString());
		assertEquals("[Rose,Hulman]", this.forward.toString());
		assertEquals("[Hulman,Rose]", this.reverse.toString());

		assertEquals("[1,2,3]", this.fromArray.toString());
	}

}
