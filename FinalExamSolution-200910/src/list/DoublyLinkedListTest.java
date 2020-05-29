package list;

import static org.junit.Assert.*;

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
		this.forward.removeFirst();
		assertTrue(this.forward.isEmpty());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#min()}.
	 */
	@Test
	public void testMin1() {
		assertEquals("Hulman", this.forward.min());
		assertEquals("Hulman", this.reverse.min());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#min()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testMin2() {
		this.empty.min();
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("[]", this.empty.toString());
		assertEquals("[Rose,Hulman]", this.forward.toString());
		assertEquals("[Hulman,Rose]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse1() {
		DoublyLinkedList<String> list = this.empty.reverse();
		assertTrue(list != this.empty);
	}
	
	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse2() {
		DoublyLinkedList<String> list = this.forward.reverse();
		assertTrue(list != this.forward);
	}
	
	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse3() {
		DoublyLinkedList<String> list = this.empty.reverse();
		assertEquals("[]", this.empty.toString());
		assertEquals("[]", list.toString());
	}
	
	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse4() {
		DoublyLinkedList<String> list = this.forward.reverse();
		assertEquals("[Rose,Hulman]", this.forward.toString());
		assertEquals("[Hulman,Rose]", list.toString());
	}
	
	/**
	 * Test method for {@link list.DoublyLinkedList#reverse()}.
	 */
	@Test
	public void testReverse5() {
		DoublyLinkedList<String> list = this.reverse.reverse();
		assertEquals("[Hulman,Rose]", this.reverse.toString());
		assertEquals("[Rose,Hulman]", list.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst1() {
		assertEquals("Rose", this.forward.removeFirst());
		assertEquals("[Hulman]", this.forward.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst2() {
		this.forward.removeFirst();
		assertEquals("Hulman", this.forward.removeFirst());
		assertEquals("[]", this.forward.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst3() {
		assertEquals("Hulman", this.reverse.removeFirst());
		assertEquals("[Rose]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test
	public void testRemoveFirst4() {
		this.reverse.removeFirst();
		assertEquals("Rose", this.reverse.removeFirst());
		assertEquals("[]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#removeFirst()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst5() {
		this.empty.removeFirst();
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#addInOrder(Comparable)}.
	 */
	@Test
	public void testAddInOrder1() {
		this.empty.addInOrder("Rockin'");
		assertEquals("[Rockin']", this.empty.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#addInOrder(Comparable)}.
	 */
	@Test
	public void testAddInOrder2() {
		this.forward.addInOrder("Rockin'");
		assertEquals("[Rockin',Rose,Hulman]", this.forward.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#addInOrder(Comparable)}.
	 */
	@Test
	public void testAddInOrder3() {
		this.reverse.addInOrder("Rockin'");
		assertEquals("[Hulman,Rockin',Rose]", this.reverse.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#addInOrder(Comparable)}.
	 */
	@Test
	public void testAddInOrder4() {
		this.empty.addInOrder("A");
		this.empty.addInOrder("C");
		this.empty.addInOrder("B");
		assertEquals("[A,B,C]", this.empty.toString());
	}

	/**
	 * Test method for {@link list.DoublyLinkedList#addInOrder(Comparable)}.
	 */
	@Test
	public void testAddInOrder5() {
		this.empty.addInOrder("C");
		this.empty.addInOrder("A");
		this.empty.addInOrder("B");
		assertEquals("[A,B,C]", this.empty.toString());
	}

}
