package recursion;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit tests for methods in Sentence.
 * 
 * @author Curt Clifton. Created Nov 14, 2009.
 */
public class SentenceTest {
	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram1() {
		Sentence s1 = new Sentence("");
		Sentence s2 = new Sentence("");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram2() {
		Sentence s1 = new Sentence("X");
		Sentence s2 = new Sentence("x");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram3() {
		Sentence s1 = new Sentence("X");
		Sentence s2 = new Sentence("Y");
		assertFalse(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram4() {
		Sentence s1 = new Sentence("XX");
		Sentence s2 = new Sentence("XY");
		assertFalse(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram5() {
		Sentence s1 = new Sentence("YX");
		Sentence s2 = new Sentence("XY");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram6() {
		Sentence s1 = new Sentence("X");
		Sentence s2 = new Sentence("x");
		assertTrue(s2.isAnagram(s1));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram7() {
		Sentence s1 = new Sentence("X");
		Sentence s2 = new Sentence("Y");
		assertFalse(s2.isAnagram(s1));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram8() {
		Sentence s1 = new Sentence("XX");
		Sentence s2 = new Sentence("XY");
		assertFalse(s2.isAnagram(s1));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram9() {
		Sentence s1 = new Sentence("YX");
		Sentence s2 = new Sentence("XY");
		assertTrue(s2.isAnagram(s1));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram10() {
		Sentence s1 = new Sentence("Curtis Clifton");
		Sentence s2 = new Sentence("Friction Cults");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram11() {
		Sentence s1 = new Sentence("Matt Boutell");
		Sentence s2 = new Sentence("Tuba Met Toll");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram12() {
		Sentence s1 = new Sentence("Rose Hulman");
		Sentence s2 = new Sentence("Numerals Ho");
		assertTrue(s1.isAnagram(s2));
	}


	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram13() {
		Sentence s1 = new Sentence("Rose Hulman");
		Sentence s2 = new Sentence("namluhesor");
		assertTrue(s1.isAnagram(s2));
	}

	/**
	 * Test method for {@link recursion.Sentence#isAnagram(Sentence)}.
	 */
	@Test
	public void testIsAnagram14() {
		Sentence s1 = new Sentence("Happy Thanksgiving");
		Sentence s2 = new Sentence("Hang Path Viking Spy");
		assertTrue(s1.isAnagram(s2));
	}
}
