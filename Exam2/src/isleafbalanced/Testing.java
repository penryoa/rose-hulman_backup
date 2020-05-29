package isleafbalanced;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
	private static int leafBalancePoints = 0;

	// Helper method to shorten tests. Just loops through the array, 
	// inserting one number at a time into the BST.
	private void insertManyInts(BinarySearchTree t, int[] nums) {
		for (int num : nums) {
			t.insert(num);
		}
	}

	@Test
	public void testLeafBalanceSmall() {
		BinarySearchTree t = new BinarySearchTree();
		assertTrue(t.isLeafBalanced());
		t.insert(10);
		assertTrue(t.isLeafBalanced());
		t.insert(15);
		assertTrue(t.isLeafBalanced());
		t.insert(12);
		assertTrue(t.isLeafBalanced());
		t.insert(20);
		assertFalse(t.isLeafBalanced());		
		leafBalancePoints += 5;
	}

	@Test
	public void testLeafBalance() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyInts(t, new int[] {10, 5, 3, 1, 20, 15, 25});
		assertTrue(t.isLeafBalanced());
		t.insert(13);
		t.insert(17);
		assertFalse(t.isLeafBalanced());
		leafBalancePoints += 5;
	}

	@Test
	public void testLeafBalanceWithImbalanceDeepWithinTree() {
		// TODO: Write
		BinarySearchTree t = new BinarySearchTree();
		insertManyInts(t, new int[] {60, 20, 80});
		assertTrue(t.isLeafBalanced());
		
		// Adding more values to the tree we already made above.
		insertManyInts(t, new int[] {10, 30, 70, 90});
		assertTrue(t.isLeafBalanced());
		// Etc.
		insertManyInts(t, new int[] {25, 40, 65, 75, 85, 95});
		assertTrue(t.isLeafBalanced());
		// Etc.
		insertManyInts(t, new int[] {35, 50, 45, 55});
		assertFalse(t.isLeafBalanced());
		leafBalancePoints += 5;
	}

	@Test
	public void testLeafBalanceAlternatingBalancedImbalanced() {
		// TODO: Write
		BinarySearchTree t = new BinarySearchTree();
		insertManyInts(t, new int[] {60, 20, 80, 10, 30, 70, 90, 25, 40, 65, 75, 85, 95});
		assertTrue(t.isLeafBalanced());
		
		// Adding more values to the tree we already made above.
		insertManyInts(t, new int[] {35, 50});
		assertFalse(t.isLeafBalanced());
		insertManyInts(t, new int[] {5, 15});
		assertTrue(t.isLeafBalanced());
		insertManyInts(t, new int[] {2, 8});
		assertFalse(t.isLeafBalanced());
		insertManyInts(t, new int[] {82, 88});
		assertTrue(t.isLeafBalanced());
		insertManyInts(t, new int[] {94, 96});
		assertFalse(t.isLeafBalanced());
		insertManyInts(t, new int[] {74, 76});
		assertTrue(t.isLeafBalanced());
		insertManyInts(t, new int[] {34, 36});
		assertFalse(t.isLeafBalanced());
		leafBalancePoints += 5;
	}

	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("2. isLeafBalanced unit test points:           %2d/20\n", leafBalancePoints);
		System.out.printf("   (10 points efficiency will be checked by the instructor)\n");
	}
}
