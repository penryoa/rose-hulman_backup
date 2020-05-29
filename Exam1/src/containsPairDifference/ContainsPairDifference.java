package containsPairDifference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class ContainsPairDifference {

	public static boolean containsPairDifferenceLinearTime(int[] array, int diff) {

		// ------------------------------------------------------------------------
		// THOUGHT #1: I know that for it to be O(N), it must go through a loop
		// of length n.
		// ------------------------------------------------------------------------
		// for (int i = 0; i < array.length - 1; i++) {
		// int current = array[i];
		// int next = array[i + 1];
		// if (current + next < diff) {
		// // ???
		// }
		// }
		// ------------------------------------------------------------------------

		// ------------------------------------------------------------------------
		// THOUGHT #2: I was thinking a set would be good since it orders the
		// integers, but then there's the issue of adding all the numbers from
		// the array. That makes it O(N) just to sort, let alone search through.
		// ------------------------------------------------------------------------
		// TreeSet<Integer> set = new TreeSet<>();
		// set.addAll(array);
		// ------------------------------------------------------------------------

		// ------------------------------------------------------------------------
		// FINAL THOUGHT:
		// This isn't O(N), but O(N^2).
		// I have some ideas, I'm just not sure how to code them.

		// a) I understand that it is best to sort the list, and in looking at
		// the Collections oracle page, I think TreeSet would work well, but I
		// don't quite understand how to implement that while not getting worse
		// than linear time.

		// b) I would think that if the list is sorted, you could make a loop
		// and see when array[i] > diff. At that point, you can look at the
		// smaller numbers and see if subtracting them gets you the difference
		// you're looking for.

		// c) I'm not finding a pattern like we did with MCSS. If I could figure
		// out a pattern, I'd have a better direction on what to code. (We
		// noticed that any negative prefix couldn't contribute to the MCSS -
		// that's kind of what I'm talking about)
		// ------------------------------------------------------------------------

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (Math.abs(array[i] - array[j]) == diff) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean containsPairDifferenceArrayOnly(int[] array, int diff) {

		// Here, I wanted to sort the array. I don't have enough time to make
		// this NOT an ArrayList, but if I had time, here's what I'd do:
		// 1) When the sorted array needs to add something, you find the index
		// the element needs to be added to and shift each of the elements in
		// the sorted array to the right starting at that index, adding the
		// desired element at the newly freed space.
		// 2) Call containsPairDifferenceLinearTime with the newly sorted array.

		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(0, array[0]);
		
		for (int i = 1; i < array.length; i++) {
			if (arr.get(i - 1) <= array[i]) {
				arr.add(i, array[i]);
			} else {
				int tempIndex = i - 2;
				while (true) {
					if (tempIndex == -1) {
						arr.add(0, array[i]);
						break;
					} else if (arr.get(tempIndex) <= array[i]) {
						arr.add(tempIndex, array[i]);
					}
					tempIndex--;
				}
			}
		}

		return containsPairDifferenceLinearTime(array, diff);
	}

}
