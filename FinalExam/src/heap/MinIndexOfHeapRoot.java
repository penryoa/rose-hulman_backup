package heap;

/**
 * 
 * A class containing the static IsHeap method.
 *
 * @author Nate Chenette and <<TODO: your name here >>>.
 */

public class MinIndexOfHeapRoot {

	/**
	 * Using the standard 1-indexed "heap structure" interpretation of the given
	 * input array of ints, returns the lowest index x such that the tree rooted
	 * at x forms a valid (min) binary heap.
	 * 
	 * @param arr,
	 *            the input array. Note that the 0th element is irrelevant to
	 *            the method, and the array will always be "filled" with data,
	 *            so that the heap-structure interpretation of the array will
	 *            have arr.length() - 1 elements in the corresponding tree.
	 * @return the smallest index
	 */
	public static int minIndexOfHeapRoot(int[] arr) {
		int index = 1;
		int validIndex = arr.length; // obviously not valid, but it'll change
										// later.
		helper(index, arr, validIndex);
		return validIndex;
	}

	/*
	 * Here's my issue with this. The valid minimum index is always returned
	 * even though the helper function is taking the minimum of the current
	 * index and the valid index whenever the tree is valid.
	 * 
	 * I recently started with the boolean stuff, but I'm not sure that's the
	 * best approach.
	 * 
	 * The lower heaps need to communicate up if it is a valid heap or not. That
	 * was my problem mostly. I couldn't figure out an effective way in time.
	 */

	public static boolean helper(int index, int[] arr, int validIndex) {
		int indexLC = 2 * index; // index of left child
		int indexRC = 2 * index + 1; // index of right child
		if (indexLC >= arr.length || indexRC >= arr.length) {
			// we've reached the first leaf
			validIndex = Math.min(validIndex, index);
			return true; // we can stop here for sure.
		}
		if (arr[index] < arr[indexLC] && arr[index] < arr[indexRC]) {
			// valid tree so far!
			validIndex = Math.min(index, validIndex);
			// let's recurse into the children to make sure it's still okay.
			boolean leftSide = helper(indexLC, arr, validIndex);
			boolean rightSide = helper(indexRC, arr, validIndex);
			return leftSide && rightSide; // hopefully true.
		} else {
			// it isn't a valid tree; keep trying.
			if (arr[index] < arr[indexLC]) {
				// the left side is valid; we can continue left
				helper(indexLC, arr, validIndex);
			} else if (arr[index] < arr[indexRC]) {
				// the right side is valid; we can continue right
				helper(indexRC, arr, validIndex);
			}
			return false;
		}
	}

}
