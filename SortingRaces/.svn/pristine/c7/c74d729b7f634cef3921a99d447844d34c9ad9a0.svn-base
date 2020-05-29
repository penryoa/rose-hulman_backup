import java.util.Arrays;
import java.util.Random;


/**
 * This program runs various sorts and gathers timing information on them.
 *
 * @author <<YOUR NAMES HERE>>
 *         Created May 7, 2013.
 */
public class SortRunner {
	private static Random rand = new Random(17); // uses a fixed seed for debugging. Remove the parameter later.
	
	/**
	 * Starts here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// array size must be an int. You will need to use something much larger
		int size = 100; 
		
		// Each integer will have the range from [0, maxValue). If this is significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE; 
		
		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		runAllSortsForOneArray(randomIntArray);

		// TODO: Tests 2-4
		// Generate the three other types of arrays (shuffled, almost sorted, almost reverse sorted)
		// and run the sorts on those as well.

	}

	/**
	 * 
	 * Runs all the specified sorts on the given array and outputs timing results on each.
	 *
	 * @param array
	 */
	private static void runAllSortsForOneArray(int[] array) {
		long startTime, elapsedTime; 
		boolean isSorted = false;

		// TODO: Read this.
		// We prepare the arrays. This can take as long as needed to shuffle items, convert
		// back and forth from ints to Integers and vice-versa, since you aren't timing this 
		// part. You are just timing the sort itself.
		
		int[] sortedIntsUsingDefaultSort = array.clone();
		Integer[] sortedIntegersUsingDefaultSort = copyToIntegerArray(array);
		Integer[] sortedIntegersUsingHeapSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingTreeSort = sortedIntegersUsingDefaultSort.clone();
		// No skiplist this term. Integer[] sortedIntegersUsingSkipListSort = sortedIntegersUsingDefaultSort.clone();
		int[] sortedIntsUsingQuickSort = array.clone();

		int size = array.length;
		
		// What is the default sort for ints? Read the javadoc.
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntsUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingDefaultSort);
		displayResults("int", "the default sort", elapsedTime, size, isSorted);
		
		// What is the default sort for Integers (which are objects that wrap ints)?
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntegersUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "the default sort", elapsedTime, size, isSorted);

		// Sort using the following methods, and time and verify each like done above. 
		// TODO: a simple sort that uses a TreeSet but handles a few duplicates gracefully. 
		
		// TODO: your implementation of quick sort. I suggest putting this in a static method in a Quicksort class.
		
		// TODO: your BinaryHeap sort. You can put this sort in a static method in another class. 
		
	}
	

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size,  boolean isSorted) {
		if (isSorted) {
			System.out.printf("Sorted %.1e %ss using %s in %d milliseconds\n", (double)size, typeName, sortName, elapsedTime);
		} else {
			System.out.println("ARRAY NOT SORTED");
		}
	}
	
	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(int[] a) {
		// TODO: implement this.
		return false;
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(Integer[] a) {
		// TODO: implement this.
		return false;
	}

	/**
	 * Copies from an int array to an Integer array.
	 *
	 * @param randomIntArray
	 * @return A clone of the primitive int array, but with Integer objects.
	 */
	private static Integer[] copyToIntegerArray(int[] ints) {
		Integer[] integers = new Integer[ints.length];
		for (int i = 0; i < ints.length; i++) {
			integers[i] = ints[i];
		}
		return integers;
	}

	/**
	 * Creates and returns an array of random ints of the given size.
	 *
	 * @return An array of random ints.
	 */
	private static int[] getRandomIntArray(int size, int maxValue) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = rand.nextInt(maxValue);
		}
		return a;
	}

	/**
	 * Creates a shuffled random array.
	 *
	 * @param size
	 * @return An array of the ints from 0 to size-1, all shuffled
	 */
	private static int[] getUniqueElementArray(int size) {
		// TODO: implement and call this method.
		return null;
	}
	
}
