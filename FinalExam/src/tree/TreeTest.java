package tree;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

public class TreeTest {
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
		assertEquals(0, t.countExactlyBalancedNodes());
		t.insert(10);
		assertEquals(1, t.countExactlyBalancedNodes());
		t.insert(15);
		assertEquals(1, t.countExactlyBalancedNodes());
		t.insert(8);
		assertEquals(3, t.countExactlyBalancedNodes());
		t.insert(20);
		assertEquals(2, t.countExactlyBalancedNodes());
		leafBalancePoints += 3;
	}

	@Test
	public void testLeafBalanceExampleFromSpecs() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyInts(t, new int[] {50, 40, 60, 20, 70, 10, 30, 80});
		assertEquals(5, t.countExactlyBalancedNodes());
		leafBalancePoints += 3;
	}

	@Test
	public void testLeafBalanceOnFullTreeAndBeyond() {
		BinarySearchTree t = new BinarySearchTree();
		insertManyInts(t, new int[] {40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55, 65, 75});
		assertEquals(15, t.countExactlyBalancedNodes());
		// Adding more values to the tree we already made above destroys the balance of some nodes.
		t.insert(2);
		t.insert(37);
		assertEquals(12, t.countExactlyBalancedNodes());
		leafBalancePoints += 4;
	}



	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("1. countExactlyBalancedNodes unit test points:           %2d/10\n", leafBalancePoints);
		System.out.printf("   (10 points efficiency will be checked by the instructor)\n");
	}
}
