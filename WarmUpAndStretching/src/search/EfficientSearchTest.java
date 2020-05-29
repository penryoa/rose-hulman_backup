package search;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EfficientSearchTest {

	private int[] smallArray; 
	private static int efficiencyPoints = 0;
	private static int maxEfficiencyPoints = 6;
	private static int correctnessCount = 0;
	private static int maxCorrectnessCount = 5;
	private final Random randomizer;
	
	
	public EfficientSearchTest() {
		randomizer = new Random();
	}

	@Before
	public void setUp() throws Exception {
		this.smallArray = new int[] {4, 7, 11, 13, 20, 25, 40, 50};
	}

	// These first few tests are given just to help you debug.
	@Test
	public void testBinarySearchFirstMid() {
		// Binary search should look at the 13 first and immediately return index 3.
		assertEquals(3,  EfficientSearch.search(this.smallArray, 13));
		correctnessCount+=1;
	}

	@Test
	public void testBinarySearchSecondMid() {
		// 7 s less than 13, so it should look there next.
		assertEquals(1,  EfficientSearch.search(this.smallArray, 7));
		correctnessCount+=1;
	}
	
	@Test
	public void testBinarySearchSecondMid2() {
		// 25 is greater than 13, so it should look there after it looks at the 13.
		assertEquals(5,  EfficientSearch.search(this.smallArray, 25));
		correctnessCount+=1;
	}

	@Test
	public void testBinarySearchAllArrayElements() {
		assertEquals(0,  EfficientSearch.search(this.smallArray, 4));
		assertEquals(1,  EfficientSearch.search(this.smallArray, 7));
		assertEquals(2,  EfficientSearch.search(this.smallArray, 11));
		assertEquals(3,  EfficientSearch.search(this.smallArray, 13));
		assertEquals(4,  EfficientSearch.search(this.smallArray, 20));
		assertEquals(5,  EfficientSearch.search(this.smallArray, 25));
		assertEquals(6,  EfficientSearch.search(this.smallArray, 40));
		assertEquals(7,  EfficientSearch.search(this.smallArray, 50));
		correctnessCount+=1;
	}
	
	@Test
	public void testBinarySearchItemNotInArray() {
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 3));
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 6));
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 19));
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 26));
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 39));
		assertEquals(-1,  EfficientSearch.search(this.smallArray, 51));
		correctnessCount+=1;
	}
	
	/**
	 * The funny test name is used in conjunction with FixMethodOrder to present
	 * the tests in ascending order. (The order of tests in Java 7 is
	 * nondeterministic). The ZZZ means that this test runs after the
	 * correctness points have been determined.
	 */
	@Test
	public void zzzTestBinarySearchEfficiency() {
		if (correctnessCount < maxCorrectnessCount) {
			System.out.println("");
			efficiencyPoints = 0;
			fail("Search failed some correctness tests. It doesn't matter how fast it is if it doesn't work.");
			return;
		}

		int n = 100000000; // 10M
		System.out.println("Preparing large array...");
		int[] nums = getSortedArray(n, randomizer);
		System.out.println("Done. Searching...");
			
		double averageElapsedTimeInMicroseconds = timeSomeSearches(randomizer, n, nums);
		System.out.printf("Took %.2f microseconds on average to run search\n", averageElapsedTimeInMicroseconds);
		assignEfficiencyPoints(averageElapsedTimeInMicroseconds);
	}

	private static int[] getSortedArray(int n, Random randomizer) {
		// Quick thing to do is to generate them with random spacing between them
		int[] nums = new int[n];
		nums[0] = 10;
		for (int i = 1; i < n; i++) {
			nums[i] = nums[i-1] + randomizer.nextInt(100)+1;
		}
		return nums;
	}

	private double timeSomeSearches(Random randomizer, int n, int[] nums) {
		// Time some searches.
		int NUM_TO_SEARCH = 100;
		int maxToSearchFor = nums[n-1];
		long startTime;
		double averageElapsedTimeInNanoseconds;
		startTime = System.nanoTime();

		for (int i = 0; i < NUM_TO_SEARCH; i++) {
			// Do the search, ignoring the return value since we assume it is correct.
			int searchItem = randomizer.nextInt(maxToSearchFor); 
			EfficientSearch.search(nums, searchItem);
		}

		averageElapsedTimeInNanoseconds = (System.nanoTime() - startTime) / (double) NUM_TO_SEARCH;
		double averageElapsedTimeInMicroseconds = averageElapsedTimeInNanoseconds/1000;
		return averageElapsedTimeInMicroseconds;
	}

	private void assignEfficiencyPoints(double averageElapsedTimeInMicroseconds) {
		// 100 was tested in 201830. Actual speed was <= 4 millis for binary search, so this is generous.
		int cutoffForEfficiency = 100;
		if (averageElapsedTimeInMicroseconds < cutoffForEfficiency) {
			efficiencyPoints = maxEfficiencyPoints;
		} else {
			efficiencyPoints = 0;
			fail("Too slow. Either not using binary search or it is incorrect.");

		}
	}

	@AfterClass
	public static void displayPoints() {
		String message = "not efficient";
		if (correctnessCount >= maxCorrectnessCount) {
			if (efficiencyPoints > 0) {
				message = "efficient";
			}
		} else {
			message = "not correct";
		}
		System.out.printf("SEARCH POINTS = %d of %d (%s).\n", efficiencyPoints, maxEfficiencyPoints, message);
	}	
}