package containsPairDifference;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Test;


public class Testing {

	private static int pointsLinearTimeAlgorithm = 0;
	private static int pointsArrayOnlyAlgorithm = 0;



	@Test
	public void testLinearTimeAlgorithm() {
		int[] a = {5,9};
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(a,4));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(a,5));
		pointsLinearTimeAlgorithm += 2;

		int[] bSorted = {3,8,10,55,80};
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(bSorted,45));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(bSorted,77));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(bSorted,72));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(bSorted,2));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(bSorted,4));
		pointsLinearTimeAlgorithm += 2;

		int[] b = {8,55,3,80,10};
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(b,45));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(b,77));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(b,72));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(b,2));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(b,4));
		pointsLinearTimeAlgorithm += 3;
		

		int[] c = {1,2,5,8,9,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(c)); // shuffles the array
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,1));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(c,2));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,3));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,4));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(c,5));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,6));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,7));
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(c,8));
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(c,9));
		pointsLinearTimeAlgorithm += 3;
		

		int[] d = {1,4,9,16,25,36,49,64,81,100,121,144}; // the numbers i^2 for i=1,...,12
		for (int i = 0; i < 11; i++) { // should contain differences 3, 5, 7, ..., 23
			Collections.shuffle(Arrays.asList(d)); // shuffles the array
			assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(d,3+2*i));
		}
		for (int i = 0; i < 10; i++) { // should contain differences 8, 12, 16, ..., 44
			Collections.shuffle(Arrays.asList(d));
			assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(d,8+4*i));
		}
		for (int i = 0; i < 9; i++) { // should contain differences 15, 21, 27, ..., 63
			Collections.shuffle(Arrays.asList(d));
			assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(d,15+6*i));
		}
		for (int i = 0; i < 10; i++) { // should NOT contain differences 6, 10, 14, ..., 42
			Collections.shuffle(Arrays.asList(d));
			assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(d,6+4*i));
		}
		Collections.shuffle(Arrays.asList(d)); 
		// should NOT contain difference 25
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(d,25));
		pointsLinearTimeAlgorithm += 4;
	}
	


	@Test
	public void testLinearTimeAlgorithmZeroDifference() {
		int[] c = {1,2,5,8,9,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(c)); // shuffles the array
		assertFalse(ContainsPairDifference.containsPairDifferenceLinearTime(c,0)); // should find no 0-diff
		
		int[] cR = {1,2,5,8,9,12,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(cR)); // shuffles the array
		assertTrue(ContainsPairDifference.containsPairDifferenceLinearTime(cR,0)); // should find 0-diff
		pointsLinearTimeAlgorithm += 4;
	
	}
	
	
	@Test
	public void testArrayOnlyAlgorithm() {
		int[] a = {5,9};
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(a,4));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(a,5));
		pointsArrayOnlyAlgorithm += 2;
		
		int[] bSorted = {3,8,10,55,80};
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(bSorted,45));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(bSorted,77));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(bSorted,72));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(bSorted,2));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(bSorted,4));
		pointsArrayOnlyAlgorithm += 2;

		int[] b = {8,55,3,80,10};
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(b,45));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(b,77));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(b,72));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(b,2));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(b,4));
		pointsArrayOnlyAlgorithm += 3;
		

		int[] c = {1,2,5,8,9,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(c)); // shuffles the array
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,1));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(c,2));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,3));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,4));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(c,5));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,6));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,7));
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(c,8));
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(c,9));
		pointsArrayOnlyAlgorithm += 3;
		

		int[] d = {1,4,9,16,25,36,49,64,81,100,121,144}; // the numbers i^2 for i=1,...,12
		for (int i = 0; i < 11; i++) { // should contain differences 3, 5, 7, ..., 23
			Collections.shuffle(Arrays.asList(d)); // shuffles the array
			assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(d,3+2*i));
		}
		for (int i = 0; i < 10; i++) { // should contain differences 8, 12, 16, ..., 44
			Collections.shuffle(Arrays.asList(d));
			assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(d,8+4*i));
		}
		for (int i = 0; i < 9; i++) { // should contain differences 15, 21, 27, ..., 63
			Collections.shuffle(Arrays.asList(d));
			assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(d,15+6*i));
		}
		for (int i = 0; i < 10; i++) { // should NOT contain differences 6, 10, 14, ..., 42
			Collections.shuffle(Arrays.asList(d));
			assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(d,6+4*i));
		}
		Collections.shuffle(Arrays.asList(d)); 
		// should NOT contain difference 25
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(d,25));
		pointsArrayOnlyAlgorithm += 4;
	}
	


	@Test
	public void testArrayOnlyAlgorithmZeroDifference() {
		int[] c = {1,2,5,8,9,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(c)); // shuffles the array
		assertFalse(ContainsPairDifference.containsPairDifferenceArrayOnly(c,0)); // should find no 0-diff
		
		int[] cR = {1,2,5,8,9,12,12,15,16,19,22,23};
		Collections.shuffle(Arrays.asList(cR)); // shuffles the array
		assertTrue(ContainsPairDifference.containsPairDifferenceArrayOnly(cR,0)); // should find 0-diff
		pointsArrayOnlyAlgorithm += 4;
	
	}
	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("***   containsPairDifferenceLinearTime unit tests:                    %2d/18\n", pointsLinearTimeAlgorithm);
		System.out.printf("         (subject to deductions if not linear-time)\n");
		System.out.printf("***   containsPairDifferenceArrayOnly unit tests:                     %2d/18\n", pointsArrayOnlyAlgorithm);
		System.out.printf("         (subject to deductions if using an extra data structure)\n");	
	}
}
