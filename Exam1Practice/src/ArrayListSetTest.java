import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ArrayListSet implementation.
 * 
 * @author Matt Boutell. Created Sep 28, 2013.
 */
public class ArrayListSetTest {

	private ArrayListSet<Integer> als0;
	private ArrayListSet<Integer> als1;
	private ArrayListSet<Integer> als2;
	private ArrayListSet<Integer> als3;
	private ArrayListSet<Integer> als4;
	private static int points = 0;
	private static int pointsPossible = 40;
	private static boolean addAllCorrect = false;

	/**
	 * Creates test sets
	 */
	@Before
	public void setUp() {
		this.als0 = new ArrayListSet<Integer>();
		this.als1 = makeIntSet(new int[] { 3, 5, 7, 2, 3 });
		this.als2 = makeIntSet(new int[] { 1, 4, 7, 3, 9 });
		this.als3 = makeIntSet(new int[] { 0, 2, 3, 6, 7 });
		this.als4 = makeIntSet(new int[] { 5, 2, 3, 7 });
	}

	/**
	 * Displays points.
	 */
	@AfterClass
	public static void displayScore() {
		System.out.printf("You earned %d of %d points.\n", points,
				pointsPossible);
		if (addAllCorrect) {
			System.out.println("You are eligible to earn 5 more points if addAll meets efficiency requirements");
		} else {
			System.out.println("Since your addAll method is incorrect, this will be your final score");
		}
	}

	/**
	 * Test method for {@link ArrayListSet#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddDuplicate() {
		assertTrue(this.als0.add(5));
		assertFalse(this.als0.add(5));
		assertEquals(1, this.als0.size());
		points += 2;
	}

	/**
	 * Test method for {@link ArrayListSet#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddAndSize() {
		assertTrue(this.als0.add(5));
		assertTrue(this.als0.add(8));
		assertTrue(this.als0.add(3));
		assertTrue(this.als0.add(7));
		assertTrue(this.als0.add(1));
		assertEquals(5, this.als0.size());
		points += 2;
	}

	/**
	 * Test method for {@link ArrayListSet#add(java.lang.Comparable)}.
	 */
	@Test
	public void testAddAndSizeByIntSet() {
		assertEquals(4, this.als1.size());
		assertEquals(5, this.als2.size());
		assertEquals(5, this.als3.size());
		assertEquals(4, this.als4.size());
		points += 2;
	}

	/**
	 * Test method for {@link ArrayListSet#addAll(SimpleSet)}.
	 */
	@Test
	public void testAddAll() {
		this.als1.addAll(this.als2);
		// Should give 1, 2, 3, 4, 5, 7, 9
		assertEquals(7, this.als1.size());
		assertEquals(makeIntSet(new int[] { 1, 2, 3, 4, 5, 7, 9 }), this.als1);

		// Should give 0, 2, 3, 5, 6, 7
		this.als3.addAll(this.als4);
		assertEquals(6, this.als3.size());
		assertEquals(makeIntSet(new int[] { 0, 2, 3, 5, 6, 7 }), this.als3);
		points += 5;
		addAllCorrect = true;
	}

	/**
	 * Test method for {@link ArrayListSet#clear()}.
	 */
	@Test
	public void testClear() {
		this.als0.clear();
		assertTrue(this.als0.isEmpty());
		this.als1.clear();
		assertTrue(this.als1.isEmpty());
		this.als2.clear();
		assertTrue(this.als2.isEmpty());
		this.als3.clear();
		assertTrue(this.als3.isEmpty());
		this.als4.clear();
		assertTrue(this.als4.isEmpty());
		points += 1;
	}

	/**
	 * Test method for {@link ArrayListSet#contains(java.lang.Comparable)}.
	 */
	@Test
	public void testContains() {
		assertTrue(this.als1.contains(2));
		assertTrue(this.als1.contains(3));
		assertTrue(this.als1.contains(5));
		assertTrue(this.als1.contains(7));
		assertFalse(this.als1.contains(0));
		assertFalse(this.als1.contains(1));
		assertFalse(this.als1.contains(4));
		assertFalse(this.als1.contains(10));
		assertFalse(this.als1.contains(100));
		points += 1;
	}

	/**
	 * Test method for {@link ArrayListSet#containsAll(SimpleSet)}.
	 */
	@Test
	public void testContainsAll() {
		ArrayListSet<Integer> als2copy = makeIntSet(new int[] { 9, 7, 4, 3, 1 });
		assertTrue(this.als2.containsAll(als2copy));
		assertTrue(als2copy.containsAll(this.als2));
		ArrayListSet<Integer> subsetOfAls2 = makeIntSet(new int[] { 1, 7, 4 });
		assertTrue(this.als2.containsAll(subsetOfAls2));
		assertFalse(subsetOfAls2.containsAll(this.als2));
		points += 5;
	}

	/**
	 * Test method for {@link ArrayListSet#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		ArrayListSet<Integer> als2copy = makeIntSet(new int[] { 9, 7, 4, 3, 1 });
		assertEquals(this.als2, als2copy);
		assertEquals(this.als1, this.als4);
		points += 5;
	}

	/**
	 * Test method for {@link ArrayListSet#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(this.als0.isEmpty());
		assertFalse(this.als1.isEmpty());
		assertFalse(this.als2.isEmpty());
		assertFalse(this.als3.isEmpty());
		assertFalse(this.als4.isEmpty());
		points += 1;
	}

	/**
	 * Test method for {@link ArrayListSet#iterator()}.
	 */
	@Test
	public void testIterator() {
		TreeSet<Integer> sortedSet = new TreeSet<Integer>();
		// Uses the iterator
		for (Integer i : this.als2) {
			sortedSet.add(i);
		}
		assertEquals("[1, 3, 4, 7, 9]", sortedSet.toString());
		points += 1;
	}

	/**
	 * Test method for {@link ArrayListSet#remove(java.lang.Comparable)}.
	 */
	@Test
	public void testRemove() {

		assertTrue(this.als2.remove(4));
		assertFalse(this.als2.remove(5));
		assertTrue(this.als2.remove(7));
		assertFalse(this.als2.remove(7));
		assertTrue(this.als2.remove(1));
		assertTrue(this.als2.remove(9));
		assertTrue(this.als2.remove(3));
		assertTrue(this.als2.isEmpty());
		points += 2;
	}

	/**
	 * Test method for {@link ArrayListSet#toArray()}.
	 */
	@Test
	public void testToArray() {
		Integer[] als2Array = this.als2.toArray();
		Arrays.sort(als2Array);
		Integer[] expected = new Integer[] { 1, 3, 4, 7, 9 };
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], als2Array[i]);
		}
		points += 3;
	}

	/**
	 * Test method for {@link ArrayListSet#toString()}.
	 */
	@Test
	public void testToString() {
		assertSetFormat(new Integer[] {}, this.als0.toString());
		assertSetFormat(new Integer[] {3,5,7,2}, this.als1.toString());
		assertSetFormat(new Integer[] {1,4,7,3,9}, this.als2.toString());
		assertSetFormat(new Integer[] {0,2,3,6,7}, this.als3.toString());
		assertSetFormat(new Integer[] {5,2,3,7}, this.als4.toString());
		points += 5;
	}

	private void assertSetFormat(Integer[] content, String s) {
		assertTrue(s.startsWith("{"));
		assertTrue(s.endsWith("}"));
		for (Integer i : content) {
			assertTrue(s.contains(Integer.toString(i)));
		}

		int nCommas = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',') {
				nCommas++;
			}
		}

		if (content.length == 0) {
			assertEquals(0, nCommas);
		} else {
			assertEquals(content.length - 1, nCommas);
		}

		// Visual check
		System.out.println(s);
	}

	private static ArrayListSet<Integer> makeIntSet(int[] nums) {
		ArrayListSet<Integer> als = new ArrayListSet<Integer>();
		for (int n : nums)
			als.add(n);
		return als;
	}

}
