package buildtree;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Matt Boutell
 */
public class Testing {

	private static int totalPoints = 0;
	private static int makeTreePoints = 0;

	@Test
	public void testEmpty() {
		BinaryTree b = new BinaryTree("", "");
		assertEquals("", b.toStructuredString());
		makeTreePoints += 1;
	}

	@Test
	public void testRootOnly() {
		BinaryTree b = new BinaryTree("a", "0");
		assertEquals("a", b.toString());
		assertEquals("(a)", b.toStructuredString());
		makeTreePoints += 1;
	}

	@Test
	public void testFullHeightOne() {
		BinaryTree b = new BinaryTree("abc", "200");
		assertEquals("bac", b.toString());
		assertEquals("((b)a(c))", b.toStructuredString());
		makeTreePoints += 1;
	}

	@Test
	public void testLeftChildOnly() {
		BinaryTree b = new BinaryTree("ab", "L0");
		assertEquals("ba", b.toString());
		assertEquals("((b)a)", b.toStructuredString());
		makeTreePoints += 1;
	}

	@Test
	public void testRightChildOnly() {
		BinaryTree b = new BinaryTree("ab", "R0");
		assertEquals("ab", b.toString());
		assertEquals("(a(b))", b.toStructuredString());
		makeTreePoints += 1;
	}

	@Test
	public void testFullHeightTwoOnly() {
		BinaryTree b = new BinaryTree("abcdefg", "2200200");
		assertEquals("cbdafeg", b.toString());
		assertEquals("(((c)b(d))a((f)e(g)))", b.toStructuredString());
		makeTreePoints += 2;
	}

	
	@Test
	public void testSmallTree() {
		BinaryTree b = new BinaryTree("abc", "LL0");
		assertEquals("cba", b.toString());
		assertEquals("(((c)b)a)", b.toStructuredString());
		makeTreePoints += 2;
	}

	
	@Test
	public void testSmallZigZag() {
		BinaryTree b = new BinaryTree("abcd", "LRL0");
		assertEquals("bdca", b.toString());
		assertEquals("((b((d)c))a)", b.toStructuredString());
		makeTreePoints += 3;
	}

	@Test
	public void testZigZagFullBottom() {
		BinaryTree b = new BinaryTree("abcde", "RL200");
		assertEquals("adceb", b.toString());
		assertEquals("(a(((d)c(e))b))", b.toStructuredString());
		makeTreePoints += 3;
	}

	@Test
	public void testLarger() {
		BinaryTree b = new BinaryTree("abdegfchijkl", "2R2L00R22000");
		assertEquals("bgedfacjikhl", b.toString());
		makeTreePoints += 5;
	}
	
	
	

	@AfterClass
	public static void displayPoints() {
		totalPoints = makeTreePoints;
		System.out.printf("Points: %d/20\n", totalPoints);
	}
}