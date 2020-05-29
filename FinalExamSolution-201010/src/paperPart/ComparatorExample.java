package paperPart;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Demonstrates Comparators.
 * 
 * @author Curt Clifton. Created Nov 14, 2009.
 */
public class ComparatorExample {
	private class CompareStringsByLength implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return o1.length() - o2.length();
		}
	}
	/**
	 * Sorts the given array in order of increasing length.
	 *
	 * @param input
	 */
	public void sortByLength(String[] input) {
		Comparator<? super String> comp = new CompareStringsByLength();
		Arrays.sort(input, comp);
	}


}
