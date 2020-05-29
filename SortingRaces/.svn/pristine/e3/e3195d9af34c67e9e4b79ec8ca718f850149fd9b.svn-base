import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This program runs various sorts and gathers timing information on them.
 *
 * @author Olivia Penry and Kaelyn Bock Created May 7, 2013.
 */
public class SortRunner {
	private static Random rand = new Random(17); // uses a fixed seed for
													// debugging. Remove the
													// parameter later.

	/**
	 * Starts here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// array size must be an int. You will need to use something much larger
		int size = 15000;

		// Each integer will have the range from [0, maxValue). If this is
		// significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE;

		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		System.out.println("======= RANDOM INT ARRAYS =======");
		runAllSortsForOneArray(randomIntArray);

		// DONE: Tests 2-4
		// Generate the three other types of arrays (shuffled, almost sorted,
		// almost reverse sorted)
		// and run the sorts on those as well.

		int[] shuffledIntArray = getUniqueElementArray(size);
		int[] almostSortedArray = getAlmostSortedArray(size);
		int[] almostReverseArray = getAlmostReverseSortedArray(size);

		System.out.println("======= SHUFFLED INT ARRAYS =======");
		runAllSortsForOneArray(shuffledIntArray);
		System.out.println("======= ALMOST SORTED ARRAYS =======");
		runAllSortsForOneArray(almostSortedArray);
		System.out.println("======= ALMOST REVERSE ARRAYS =======");
		runAllSortsForOneArray(almostReverseArray);
	}

	/**
	 * 
	 * Runs all the specified sorts on the given array and outputs timing
	 * results on each.
	 *
	 * @param array
	 */
	private static void runAllSortsForOneArray(int[] array) {
		long startTime, elapsedTime;
		boolean isSorted = false;

		// DONE: Read this.
		// We prepare the arrays. This can take as long as needed to shuffle
		// items, convert
		// back and forth from ints to Integers and vice-versa, since you aren't
		// timing this
		// part. You are just timing the sort itself.

		int[] sortedIntsUsingDefaultSort = array.clone();
		Integer[] sortedIntegersUsingDefaultSort = copyToIntegerArray(array);
		Integer[] sortedIntegersUsingHeapSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingTreeSort = sortedIntegersUsingDefaultSort.clone();
		// No skiplist this term. Integer[] sortedIntegersUsingSkipListSort =
		// sortedIntegersUsingDefaultSort.clone();
		int[] sortedIntsUsingQuickSort = array.clone();

		int size = array.length;

		// What is the default sort for ints? Read the javadoc.
		startTime = System.currentTimeMillis();
		Arrays.sort(sortedIntsUsingDefaultSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingDefaultSort);
		displayResults("int", "Java quick sort", elapsedTime, size, isSorted);

		// What is the default sort for Integers (which are objects that wrap
		// ints)?
		startTime = System.currentTimeMillis();
		Arrays.sort(sortedIntegersUsingDefaultSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "Java merge sort", elapsedTime, size, isSorted);

		// Sort using the following methods, and time and verify each like done
		// above.
		// DONE: a simple sort that uses a TreeSet but handles a few duplicates
		// gracefully.
		BinarySearchTree t = new BinarySearchTree();
		startTime = System.currentTimeMillis();
		treeSort(sortedIntegersUsingTreeSort, t);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingTreeSort);
		displayResults("Integer", "tree sort", elapsedTime, size, isSorted);

		// DONE: your implementation of quick sort. I suggest putting this in a
		// static method in a Quicksort class.
		startTime = System.currentTimeMillis();
		QuickSort.sort(sortedIntsUsingQuickSort, 0, sortedIntsUsingQuickSort.length - 1);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingQuickSort);
		displayResults("Integer", "quick sort", elapsedTime, size, isSorted);

		// DONE: your BinaryHeap sort. You can put this sort in a static method
		// in another class.
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Integer.class);
		startTime = System.currentTimeMillis();
		heap.sort(sortedIntegersUsingHeapSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingHeapSort);
		displayResults("Integer", "heap sort", elapsedTime, size, isSorted);
	}

	private static void treeSort(Integer[] sortedIntegersUsingTreeSort, BinarySearchTree t) {
		for (int i = 0; i < sortedIntegersUsingTreeSort.length; i++) {
			t.insert(sortedIntegersUsingTreeSort[i]);
		}
		Object[] temp = t.toArray();
		for (int i = 0; i < sortedIntegersUsingTreeSort.length; i++) {
			sortedIntegersUsingTreeSort[i] = (Integer) temp[i];
		}
	}

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size, boolean isSorted) {
		if (isSorted) {
			System.out.printf("Sorted %.1e %ss using %s in %d milliseconds\n", (double) size, typeName, sortName,
					elapsedTime);
		} else {
			System.out.println("ARRAY NOT SORTED");
		}
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a
	 *            An array to check to see if it is sorted.
	 */
	private static boolean verifySort(int[] a) {
		// DONE: implement this.
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a
	 *            An array to check to see if it is sorted.
	 */
	private static boolean verifySort(Integer[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
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
		int[] real = new int[size];
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		for (int i = 0; i < list.size(); i++) {
			real[i] = list.get(i);
		}

		return real;
	}

	private static int[] getAlmostSortedArray(int size) {
		int[] real = new int[size];

		for (int i = 0; i < size; i++) {
			real[i] = i;
		}

		int temp = real[size / 2];
		real[(size / 2)] = real[(size / 2) + 1];
		real[(size / 2) + 1] = temp;

		return real;
	}

	private static int[] getAlmostReverseSortedArray(int size) {
		int[] array = new int[size];
		for (int i = size; i > 0; i--) {
			array[size - i] = i;
		}
		int temp = array[size / 2];
		array[(size / 2)] = array[(size / 2) + 1];
		array[(size / 2) + 1] = temp;
		return array;
	}
}
