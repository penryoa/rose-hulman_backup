package list;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Matchers class.
 *
 * @author Curt Clifton
 */
public class MatchersTest {

	private DoublyLinkedList<String> empty;
	private DoublyLinkedList<String> one;
	private DoublyLinkedList<String> three;
	private DoublyLinkedList<String> ten;

	/**
	 * Creates some test lists.
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.empty = new DoublyLinkedList<String>();
		
		this.one = new DoublyLinkedList<String>();
		this.one.addFirst("ABCD");
		
		this.three = new DoublyLinkedList<String>();
		this.three.addFirst("ABC");
		this.three.addFirst("DEFG");
		this.three.addFirst("HIJKL");
		
		this.ten = new DoublyLinkedList<String>();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<10; i++) {
			sb.append(i);
			this.ten.addFirst(sb.toString());
		}
	}
	
	/**
	 * Tests {@link Matchers#longStringMatcher()}.
	 */
	@Test
	public void testLongStringMatcher1() {
		assertNotNull(Matchers.longStringMatcher());
	}
	
	/**
	 * Tests {@link Matchers#longStringMatcher()}.
	 */
	@Test
	public void testLongStringMatcher2() {
		ListItemMatcher<String> m = Matchers.longStringMatcher();
		assertEquals(0, this.empty.countMatches(m));
	}
	
	/**
	 * Tests {@link Matchers#longStringMatcher()}.
	 */
	@Test
	public void testLongStringMatcher3() {
		ListItemMatcher<String> m = Matchers.longStringMatcher();
		assertEquals(1, this.one.countMatches(m));
	}

	
	/**
	 * Tests {@link Matchers#longStringMatcher()}.
	 */
	@Test
	public void testLongStringMatcher4() {
		ListItemMatcher<String> m = Matchers.longStringMatcher();
		assertEquals(2, this.three.countMatches(m));
	}
	
	/**
	 * Tests {@link Matchers#longStringMatcher()}.
	 */
	@Test
	public void testLongStringMatcher5() {
		ListItemMatcher<String> m = Matchers.longStringMatcher();
		assertEquals(7, this.ten.countMatches(m));
	}
}
