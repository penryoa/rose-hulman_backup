import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StringLinkedListTests {

	StringLinkedList list;
	
	@Before
	public void setUp() throws Exception {
		list = new StringLinkedList();
		list.addFirst("c");
		list.addFirst("b");
		list.addFirst("a");
	}

	@Test
	public void testRemoveLast() {
		Assert.assertEquals("c", list.removeLast());
		Assert.assertEquals("b", list.removeLast());
		Assert.assertEquals("a", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
	}

	@Test
	public void testAddSecond() {
		list.addSecond("x");
		Assert.assertEquals("c", list.removeLast());
		Assert.assertEquals("b", list.removeLast());
		Assert.assertEquals("x", list.removeLast());
		Assert.assertEquals("a", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		
		list = new StringLinkedList();
		list.addFirst("A");
		list.addSecond("B");
		Assert.assertEquals("B", list.removeLast());
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		
		list = new StringLinkedList();
		list.addSecond("Q");
		Assert.assertEquals("Q", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
	}

	@Test
	public void testUppercase() {
		list.uppercase();
		Assert.assertEquals("C", list.removeLast());
		Assert.assertEquals("B", list.removeLast());
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		
		list = new StringLinkedList();
		list.uppercase();
		Assert.assertEquals(null, list.removeLast());
	}

}
