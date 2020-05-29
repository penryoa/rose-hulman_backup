package heap;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;

// Test class
public class MinIndexOfHeapRootTest {

	private static int minIndexOfHeapRootPoints = 0;


	@Test
	public void testMinIndexOfHeapRootSimple() {
		int[] arr =         {5,1,2,2,3,3,3,3,4,4,4,4,4,4,4,4};
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // All are heaps, so min is 1
		arr[0] = 0;  // now {0,1,2,2,3,3,3,3,4,4,4,4,4,4,4,4}
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // 0th is ignored, so same result
		arr[3] = 0;  // now {0,1,2,0,3,3,3,3,4,4,4,4,4,4,4,4};
		assertEquals(2,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // root no longer heap, so goes to 2
		arr[3] = 2;
		arr[2] = -3; // now {0,1,-3,2,3,3,3,3,4,4,4,4,4,4,4,4};    
		assertEquals(2,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // root no longer heap, so goes to 2
		arr[10] = -1; // now {0,1,-3,2,3,3,3,3,4,4,-1,4,4,4,4,4};
		assertEquals(3,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // L subtree also no longer heap, so goes to 3
		arr[8] = -1;
		arr[9] = -1;
		arr[11] = -1;
		arr[4] = -2;
		arr[5] = -2; // now {0,1,-3,2,-2,-2,3,3,-1,-1,-1,-1,4,4,4,4};
		assertEquals(2,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // L subtree is a heap again
		arr[1] = -4; // now {0,-4,-3,2,-2,-2,3,3,-1,-1,-1,-1,4,4,4,4};
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // root is a heap again
		minIndexOfHeapRootPoints += 6;
	}
	

	@Test
	public void testMinIndexOfHeapRootEqualsAndRuns() {
		int[] arr =         {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; // full tree height 4
		int lastIndex = arr.length - 1;
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr)); // All are heaps, so min is 1
		for (int i = 1; i <= lastIndex / 2; i++) { // only up to final parent
			arr[i] = 2;  // now {0,2,2,…,2,1,1,…,1}
			             //              ^
						 //				 i
			assertEquals(i+1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr));
		}
		
		minIndexOfHeapRootPoints += 4;
	}
	

	@Test
	public void testMinIndexOfHeapRootNotFullTree() {
		int[] arr1 =         {0,1,2,2,3,3,3,3,4,4,4,4,4,4,4};		 // 7 out of 8 possible leaves in last level
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr1)); // All are heaps, so min is 1
		int[] arr2 =         {0,4,3,3,2,2,2,2,1,1,1,1,1,1,1};		 
		assertEquals(8,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr2)); // Only leaves are heaps, so min is first leaf
		int[] arr3 =         {0,4,3,3,2,2,2,0,1,1,1,1,1,1,1};		 
		assertEquals(7,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr3)); // Final parent is min index
		int[] arr4 =         {0,4,3,-1,2,2,0,0,1,1,1,1,1,1,1};		 
		assertEquals(3,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr4)); // Root's R is min index
		int[] arr5 =         {0,4,-1,3,0,0,2,0,1,1,1,1,1,1,1};		 
		assertEquals(2,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr5)); // Root's L is min index

		int[] arr6 =         {0,1,2,2,3,3,3,3,4,4,4};		 		 // 3 out of 8 possible leaves in last level
		assertEquals(1,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr6)); // All are heaps, so min is 1
		int[] arr7 =         {0,4,3,3,2,2,2,2,1,1,1};		 		 
		assertEquals(6,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr7)); // Only leaves are heaps, so min is first leaf
		int[] arr8 =         {0,4,3,3,2,0,2,2,1,1,1};		 		 
		assertEquals(5,MinIndexOfHeapRoot.minIndexOfHeapRoot(arr8)); // Final parent is min index
		
		minIndexOfHeapRootPoints += 4;
	}
	


	@Test
	public void testMinIndexOfHeapRootRandom() {
		int[] arr =         {0,6,5,5,4,4,4,4,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
							 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; // full tree height 5
		
		for (int i = 0; i < 200; i++) { 					  // run this random test many times
			int[] newarr = Arrays.copyOf(arr, arr.length);    // make a copy of the full tree height 5
			Random rand = new Random();
			int randIndex = rand.nextInt(arr.length - 1) + 1; // choose a random integer in range [1, arr.length-1]
			makeSubHeapAt(newarr, randIndex, 0);  			  // make a subheap at that index
			int firstLeafIndex = (arr.length - 1) / 2 + 1;
			int minIHR = (randIndex <  firstLeafIndex) ? 	  // min heap-root index will be either the index chosen,
					randIndex : firstLeafIndex;				  // or index of the first leaf if chosen index was a leaf										  
			assertEquals(minIHR,MinIndexOfHeapRoot.minIndexOfHeapRoot(newarr));
		}
		minIndexOfHeapRootPoints += 6;
	}
	
	// makes a sub-heap at index i, with the start value given, and values increasing by 1 with each increment in depth
	private void makeSubHeapAt(int[] arr, int i, int startVal) {
		if (i >= arr.length) {
			return;
		}
		arr[i] = startVal;
		makeSubHeapAt(arr, 2*i, startVal + 1);
		makeSubHeapAt(arr, 2*i + 1, startVal + 1);
	}
	


	@AfterClass
	public static void displayPoints() {
		System.out.printf("2. minIndexOfHeapRoot TOTAL POINTS:	 		 %2d/20\n", minIndexOfHeapRootPoints);
	}
	

	
}
