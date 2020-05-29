package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;


public class Testing {

	private static int internalNodeSumPoints = 0;

	@Test
	public void testInternalNodeSumEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals(0, t.internalNodeSum());
		internalNodeSumPoints += 1;
	}

	@Test
	public void testInternalNodeSumRoot() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		assertEquals(0, t.internalNodeSum());
		internalNodeSumPoints += 1;
	}

	@Test
	public void testInternalNodeSumOneLeaf() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		assertEquals(20, t.internalNodeSum());

		t = new BinarySearchTree();
		t.insert(10);
		t.insert(20);
		assertEquals(10, t.internalNodeSum());
		internalNodeSumPoints += 1;
	}

	@Test
	public void testInternalNodeSumSimple() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		assertEquals(70, t.internalNodeSum());
		internalNodeSumPoints += 1;
	}

	@Test
	public void testInternalNodeSumTreeInSpecification() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertEquals(523, t.internalNodeSum());
		internalNodeSumPoints += 6;
	}
	
	@Test
	public void testInternalNodeSumLarger() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(100);
		t.insert(50);
		t.insert(75);
		t.insert(25);
		t.insert(150);
		t.insert(175);
		t.insert(160);
		t.insert(155);
		t.insert(12);
		t.insert(37);
		t.insert(6);
		t.insert(8);
		t.insert(10);
		t.insert(11);
		assertEquals(696, t.internalNodeSum());
		internalNodeSumPoints += 6;
	}

	private static int attachMinMaxPoints = 0;

	@Test
	public void testAttachMinMaxEmptyReturned() {
		BinarySearchTree t = new BinarySearchTree();
		assertFalse(t.attachPathMinAndMaxToNode(10));
		attachMinMaxPoints += 1;
	}

	@Test
	public void testAttachMinMaxRoot() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(17);
		assertFalse(t.attachPathMinAndMaxToNode(10));//not found, tree unchanged.
		assertTrue(t.attachPathMinAndMaxToNode(17));
		assertEquals("171717", t.toString());
		attachMinMaxPoints += 1;
	}

	private BinarySearchTree insertValsIntoTree(int[] vals) {
		BinarySearchTree t = new BinarySearchTree();
		for (int val : vals) {
			t.insert(val);
		}
		return t;
	}
	
	@Test
	public void testAttachMinMaxSimple() {
		int[] treeVals = new int[] {50, 20, 30, 60};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertTrue(t.attachPathMinAndMaxToNode(30));
		assertEquals("202030505060", t.toString());
		
		// re-build so we start with a fresh tree.
		t = insertValsIntoTree(treeVals); 
		assertFalse(t.attachPathMinAndMaxToNode(40));
		attachMinMaxPoints += 1;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification1() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertFalse(t.attachPathMinAndMaxToNode(100)); // not found
		assertTrue(t.attachPathMinAndMaxToNode(40)); // found
		assertEquals("-10884080508090120180190", t.toString());
		attachMinMaxPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification2() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		// Same tree, different values to find.
		// These next tests all use this tree.
		assertFalse(t.attachPathMinAndMaxToNode(-300)); 
		assertTrue(t.attachPathMinAndMaxToNode(48));
		assertEquals("-10830404584880508090120180190", t.toString());
		attachMinMaxPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification3() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertFalse(t.attachPathMinAndMaxToNode(46));
		assertTrue(t.attachPathMinAndMaxToNode(45));
		assertEquals("-108304084580508090120180190", t.toString());
		attachMinMaxPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification4() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertFalse(t.attachPathMinAndMaxToNode(51));
		assertTrue(t.attachPathMinAndMaxToNode(50));
		assertEquals("-108850808090120180190", t.toString());
		attachMinMaxPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification5() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertFalse(t.attachPathMinAndMaxToNode(119));
		assertTrue(t.attachPathMinAndMaxToNode(120));
		assertEquals("-10830404548508080120120", t.toString());
		attachMinMaxPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification6() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertFalse(t.attachPathMinAndMaxToNode(81));
		assertTrue(t.attachPathMinAndMaxToNode(80));
		assertEquals("808080", t.toString());
		attachMinMaxPoints += 2;
	}
	
	
	private static int attachMinMaxIgnoreReturnPoints = 0;

	// Tests that ignore boolean return value, to make it easier to score 
	// partial credit for solutions that have incorrect return values.
	@Test
	public void testAttachMinMaxRootIgnoreReturn() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(17);
		assertFalse(t.attachPathMinAndMaxToNode(10));//not found, tree unchanged.
		assertTrue(t.attachPathMinAndMaxToNode(17));
		assertEquals("171717", t.toString());
		attachMinMaxIgnoreReturnPoints += 1;
	}

	@Test
	public void testAttachMinMaxSimpleIgnoreReturn() {
		int[] treeVals = new int[] {50, 20, 30, 60};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(30);
		assertEquals("202030505060", t.toString());
		attachMinMaxIgnoreReturnPoints += 1;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification1IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(100);
		t.attachPathMinAndMaxToNode(40);
		assertEquals("-10884080508090120180190", t.toString());
		attachMinMaxIgnoreReturnPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification2IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		// Same tree, different values to find.
		// These next tests all use this tree.
		t.attachPathMinAndMaxToNode(-300); 
		t.attachPathMinAndMaxToNode(48);
		assertEquals("-10830404584880508090120180190", t.toString());
		attachMinMaxIgnoreReturnPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification3IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(46);
		t.attachPathMinAndMaxToNode(45);
		assertEquals("-108304084580508090120180190", t.toString());
		attachMinMaxIgnoreReturnPoints += 2;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification4IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(51);
		t.attachPathMinAndMaxToNode(50);
		assertEquals("-108850808090120180190", t.toString());
		attachMinMaxIgnoreReturnPoints += 1;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification5IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(119);
		t.attachPathMinAndMaxToNode(120);
		assertEquals("-10830404548508080120120", t.toString());
		attachMinMaxIgnoreReturnPoints += 1;
	}

	@Test
	public void testAttachMinMaxTreeInSpecification6IgnoreReturn() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		t.attachPathMinAndMaxToNode(81);
		t.attachPathMinAndMaxToNode(80);
		assertEquals("808080", t.toString());
		attachMinMaxIgnoreReturnPoints += 1;
	}

	
	private static int listWithinTolerancePoints = 0;

	@Test
	public void testListWithinToleranceEmpty() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals("[]", t.listWithinToleranceOfTarget(50, 50).toString());
		assertEquals("[]", t.listWithinToleranceOfTarget(10, 0).toString());
		listWithinTolerancePoints += 1;
	}

	@Test
	public void testListWithinToleranceRoot() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		assertEquals("[50]", t.listWithinToleranceOfTarget(50, 40).toString());
		assertEquals("[]", t.listWithinToleranceOfTarget(100, 49).toString());
		assertEquals("[]", t.listWithinToleranceOfTarget(0, 49).toString());
		listWithinTolerancePoints += 2;
	}

	@Test
	public void testListWithinToleranceSimple() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		assertEquals("[50]", t.listWithinToleranceOfTarget(50, 5).toString());
		assertEquals("[30, 50]", t.listWithinToleranceOfTarget(40, 15).toString());
		assertEquals("[20, 30, 50]", t.listWithinToleranceOfTarget(35, 20).toString());
		assertEquals("[20, 30]", t.listWithinToleranceOfTarget(25, 10).toString());
		assertEquals("[20, 30, 50, 60]", t.listWithinToleranceOfTarget(40, 25).toString());
		assertEquals("[20]", t.listWithinToleranceOfTarget(20, 5).toString());
		listWithinTolerancePoints += 2;
	}

	@Test
	public void testListWithinToleranceBoundary() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(50);
		t.insert(20);
		t.insert(30);
		t.insert(60);
		assertEquals("[50]", t.listWithinToleranceOfTarget(47, 3).toString());
		assertEquals("[50]", t.listWithinToleranceOfTarget(52, 2).toString());
		assertEquals("[30, 50]", t.listWithinToleranceOfTarget(40, 10).toString());
		assertEquals("[20, 30, 50]", t.listWithinToleranceOfTarget(35, 15).toString());
		assertEquals("[20, 30]", t.listWithinToleranceOfTarget(25, 5).toString());
		assertEquals("[20, 30, 50, 60]", t.listWithinToleranceOfTarget(40, 20).toString());
		assertEquals("[20]", t.listWithinToleranceOfTarget(20, 0).toString());
		listWithinTolerancePoints += 2;
	}
	
	@Test
	public void testListWithinToleranceTreeInSpecification() {
		int[] treeVals = new int[] {80, 8, 120, -10, 50, 90, 180, 40, 190, 30, 45, 48};
		BinarySearchTree t = insertValsIntoTree(treeVals);
		assertEquals("[45, 48, 50, 80, 90]", t.listWithinToleranceOfTarget(80, 35).toString());
		assertEquals("[40, 45, 48, 50, 80, 90, 120]", t.listWithinToleranceOfTarget(80, 40).toString());
		assertEquals("[8, 30]", t.listWithinToleranceOfTarget(20, 14).toString());
		assertEquals("[50, 80, 90, 120]", t.listWithinToleranceOfTarget(110, 60).toString());
		assertEquals("[]", t.listWithinToleranceOfTarget(110, 5).toString());
		listWithinTolerancePoints += 4;
	}

	@Test
	public void testListWithinToleranceLarger() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(100);
		t.insert(50);
		t.insert(75);
		t.insert(25);
		t.insert(150);
		t.insert(175);
		t.insert(160);
		t.insert(155);
		t.insert(12);
		t.insert(37);
		t.insert(6);
		t.insert(8);
		t.insert(10);
		t.insert(11);
		assertEquals("[10, 11, 12]", t.listWithinToleranceOfTarget(11, 1).toString());
		assertEquals("[100, 150]", t.listWithinToleranceOfTarget(125, 25).toString());
		assertEquals("[25, 37, 50]", t.listWithinToleranceOfTarget(37, 13).toString());
		assertEquals("[8, 10, 11, 12, 25, 37, 50, 75]", t.listWithinToleranceOfTarget(50, 42).toString());
		assertEquals("[150, 155]", t.listWithinToleranceOfTarget(152, 3).toString());
		assertEquals("[]", t.listWithinToleranceOfTarget(93, 5).toString());
		listWithinTolerancePoints += 4;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("%2d/16 internalNodeSum correctness points\n", internalNodeSumPoints);

		System.out.printf("%2d/15 attachMinMax correctness points\n", attachMinMaxPoints);
		System.out.printf("(%2d/11 correctness points if return value ignored)\n", attachMinMaxIgnoreReturnPoints);

		System.out.printf("      --- efficiency will be checked by the instructor\n");

		System.out.printf("%2d/15 listWithinToleranceOfTarget correctness points\n", listWithinTolerancePoints);
		System.out.printf("      --- efficiency will be checked by the instructor\n");
	}
}
