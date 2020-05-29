import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings("javadoc")
public class StringLinkedListTest {

	
	private StringLinkedList makeABC() {
		StringLinkedList list = new StringLinkedList();
		list.addFirst("C");
		list.addFirst("B");
		list.addFirst("A");
		return list;
	}
	
	private StringLinkedList makeNumberList(int maxNum) {
		StringLinkedList list = new StringLinkedList();
		for(int i = maxNum; i > 0; i--) {
			list.addFirst(Integer.toString(i));
		}
		return list;
	}
	
	@Test
	public void testLimitToSize() {
		StringLinkedList list = makeABC();
		list.limitToSize(3);
		Assert.assertEquals("C", list.removeLast());
		Assert.assertEquals("B", list.removeLast());
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		list = makeABC();
		list.limitToSize(2);
		Assert.assertEquals("B", list.removeLast());
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		list = makeABC();
		list.limitToSize(1);
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());
		list = makeABC();
		list.limitToSize(0);
		Assert.assertEquals(null, list.removeLast());
		list = makeNumberList(100);
		list.limitToSize(10);
		Assert.assertEquals("10", list.removeLast());
	}

	@Test
	public void testIsSorted() {
		Assert.assertTrue(makeABC().isSorted());
		Assert.assertTrue(makeNumberList(9).isSorted());
		StringLinkedList list = makeABC();
		list.addFirst("Z");
		Assert.assertFalse(list.isSorted());
		list = new StringLinkedList();
		list.addFirst("apple");
		list.addFirst("cat");
		list.addFirst("dog");
		list.addFirst("ant");
		Assert.assertFalse(list.isSorted());
		list = new StringLinkedList();
		list.addFirst("ninja");
		list.addFirst("dave");
		list.addFirst("dog");
		list.addFirst("ant");
		Assert.assertFalse(list.isSorted());
		list = new StringLinkedList();
		list.addFirst("ninja");
		list.addFirst("dog");
		list.addFirst("dave");
		list.addFirst("ant");
		Assert.assertTrue(list.isSorted());
	}
	
	@Test
	public void testExplodeStrings() {
		StringLinkedList list = makeABC();
		list.explodeStrings();
		Assert.assertEquals("C", list.removeLast());
		Assert.assertEquals("B", list.removeLast());
		Assert.assertEquals("A", list.removeLast());
		Assert.assertEquals(null, list.removeLast());

		list = new StringLinkedList();
		list.addFirst("xy");
		list.addFirst("foo");
		list.addFirst("bar");
		list.explodeStrings();

		Assert.assertEquals("y", list.removeLast());
		Assert.assertEquals("x", list.removeLast());
		Assert.assertEquals("o", list.removeLast());
		Assert.assertEquals("o", list.removeLast());
		Assert.assertEquals("f", list.removeLast());
		Assert.assertEquals("r", list.removeLast());
		Assert.assertEquals("a", list.removeLast());
		Assert.assertEquals("b", list.removeLast());
		Assert.assertEquals(null, list.removeLast());

		list = new StringLinkedList();
		list.addFirst("a");
		list.addFirst("");
		list.addFirst("xy");
		list.explodeStrings();

		Assert.assertEquals("a", list.removeLast());
		Assert.assertEquals("", list.removeLast());
		Assert.assertEquals("y", list.removeLast());
		Assert.assertEquals("x", list.removeLast());
		Assert.assertEquals(null, list.removeLast());

		list = new StringLinkedList();
		list.explodeStrings();
		Assert.assertEquals(null, list.removeLast());
	}

}
