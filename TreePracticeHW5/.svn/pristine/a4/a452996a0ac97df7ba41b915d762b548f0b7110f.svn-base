import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @author Matt Boutell
 */
public class Testing {

	private static int totalPoints = 0;
	private static int makeTreePoints = 0;
	private static int sumOfHeightsPoints = 0;

	@Test
	public void testFullTreeWithDepthEmpty() {
		BinarySearchTree b = new BinarySearchTree(-1);
		assertEquals("", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthRootOnly() {
		BinarySearchTree b = new BinarySearchTree(0);
		assertEquals("[0]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfOne() {
		BinarySearchTree b = new BinarySearchTree(1);
		assertEquals("[[1]0[1]]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfTwo() {
		BinarySearchTree b = new BinarySearchTree(2);
		assertEquals("[[[2]1[2]]0[[2]1[2]]]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfThree() {
		BinarySearchTree b = new BinarySearchTree(3);
		assertEquals("[[[[3]2[3]]1[[3]2[3]]]0[[[3]2[3]]1[[3]2[3]]]]",
				b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testSumOfHeightsRoot() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(20);
		assertEquals(0, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
	}

	@Test
	public void testSumOfHeights3() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(20);
		assertEquals(0, b.getSumOfHeights());
		b.insert(10);
		assertEquals(1, b.getSumOfHeights());
		b.insert(30);
		assertEquals(1, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
		b.insert(40);
		assertEquals(3, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
		b.insert(38);
		assertEquals(6, b.getSumOfHeights());
		sumOfHeightsPoints += 3;
		b.insert(5);
		assertEquals(7, b.getSumOfHeights());
		b.insert(15);
		assertEquals(7, b.getSumOfHeights());
		b.insert(25);
		assertEquals(7, b.getSumOfHeights());
		b.insert(50);
		assertEquals(7, b.getSumOfHeights());
		b.insert(2);
		assertEquals(9, b.getSumOfHeights());
		b.insert(22);
		assertEquals(10, b.getSumOfHeights());
		sumOfHeightsPoints += 4;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("1. makeFullTree points: %d/10]\n", makeTreePoints);
		System.out.printf("2. sumHeights points: %d/10\n", sumOfHeightsPoints);
		totalPoints = sumOfHeightsPoints + makeTreePoints;
		System.out.printf("Points: %d/20\n", totalPoints);
	}
}