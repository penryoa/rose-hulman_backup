package pathstring;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int pathStringPoints = 0;

	// Helper method to shorten tests. Just loops through the string, inserting
	// one character at a time into the BST.
	private void insertManyChars(BinarySearchTree t, String string) {
		for (int i = 0; i < string.length(); i++) {
			t.insert(string.charAt(i));
		}
	}


	@Test
	public void testPathStringEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals("", t.pathString('x'));
		pathStringPoints += 1;
	}

	@Test
	public void testPathStringNotFound() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals("", t.pathString('r'));
		insertManyChars(t, "metabolic");
		assertEquals("", t.pathString('x'));
		pathStringPoints += 2;
	}

	@Test
	public void testPathString1AnyOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "METABOLIC");
		String actual = t.pathString('L');
		String expectedInThisOrder = "ELM";
		testIfStringsContainSameCharacters(expectedInThisOrder, actual);
		pathStringPoints += 2;
	}

	@Test
	public void testPathString1AscendingOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "METABOLIC");
		String actual = t.pathString('L');
		String expectedInThisOrder = "ELM";
		// Full credit if in order
		assertEquals(expectedInThisOrder, actual);
		pathStringPoints += 1;
	}

	
	@Test
	public void testPathString2AnyOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "ISOGRAM");
		// An isogram is a word with no repeated characters, which is
		// nice for these unit tests.
		// https://en.wikipedia.org/wiki/Isogram
		testIfStringsContainSameCharacters("AGI", t.pathString('A'));
		testIfStringsContainSameCharacters("IORS", t.pathString('R'));
		testIfStringsContainSameCharacters("IMOS", t.pathString('M'));
		pathStringPoints += 2;
	}

	@Test
	public void testPathString2AscendingOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "ISOGRAM");
		assertEquals("AGI", t.pathString('A'));
		assertEquals("IORS", t.pathString('R'));
		assertEquals("IMOS", t.pathString('M'));
		pathStringPoints += 1;
	}

	@Test
	public void testPathString3AnyOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "UNCOPYRIGHTABLES");
		
		testIfStringsContainSameCharacters("ACNU", t.pathString('A'));
		testIfStringsContainSameCharacters("NOU", t.pathString('O'));
		testIfStringsContainSameCharacters("CILNU", t.pathString('L'));
		testIfStringsContainSameCharacters("NOPRSTU", t.pathString('S'));
		pathStringPoints += 4;
	}

	@Test
	public void testPathString3AscendingOrder() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyChars(t, "UNCOPYRIGHTABLES");

		assertEquals("ACNU", t.pathString('A'));
		assertEquals("NOU", t.pathString('O'));
		assertEquals("CILNU", t.pathString('L'));
		assertEquals("NOPRSTU", t.pathString('S'));
		pathStringPoints += 2;
	}

	private void testIfStringsContainSameCharacters(String expected, String actual) {
		Set<Character> expectedChars = new HashSet<>();
		for (int i = 0; i < expected.length(); i++) {
			expectedChars.add(expected.charAt(i));
		}	
		Set<Character> actualChars = new HashSet<>();
		for (int i = 0; i < actual.length(); i++) {
			actualChars.add(actual.charAt(i));
		}
		assertTrue(expectedChars.containsAll(actualChars));
		assertTrue(actualChars.containsAll(expectedChars));
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("2. pathString unit test points:           %2d/15\n", pathStringPoints);
		System.out.printf("   (10 points efficiency will be checked by the instructor)\n");
	}
}
