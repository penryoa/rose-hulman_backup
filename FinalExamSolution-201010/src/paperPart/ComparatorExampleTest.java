package paperPart;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests sortByLength.
 *
 * @author cclifton.
 *         Created Nov 14, 2009.
 */
public class ComparatorExampleTest {

	/**
	 * Test method for {@link paperPart.ComparatorExample#sortByLength(java.lang.String[])}.
	 */
	@Test
	public void testSortByLength() {
		String[] ar = {"elephant", "dog", "aardvarks", "cat"};
		new ComparatorExample().sortByLength(ar);
		String[] ex = {"dog", "cat", "elephant", "aardvarks"};
		assertArrayEquals(ex, ar);
	}

}
