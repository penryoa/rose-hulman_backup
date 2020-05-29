package recursion;

/**
 * 
 * These problems should ALL be solved recursively - not with the use of rarely
 * used library functions, for loops, or while loops.
 * 
 * You can use a recursive helper function on any problem if that makes it
 * easier for you.
 * 
 * Solve any 3 of these 4 problems for full credit. No bonus points for doing
 * the 4th. :)
 * 
 */

public class Recursion {
	/**
	 * Count Consecutive Pairs. Given a String you must return the number of
	 * times that two characters occur consecutively within it.
	 * 
	 * 
	 * For example, "hello" returns 1. "mississippi" returns 3. "csse220"
	 * returns 2. "smthng" returns 0. "EEEEeek!" returns 4. "" returns 0.
	 * 
	 * Reminder: no loops on any of these problems. You may use a recursive
	 * helper method if you like, but it isn't required.
	 * 
	 * @param input
	 *            - a String
	 * @return int - the number of consecutive letter found in input
	 */
	public static int countConsecutivePairs(String input) {

		if (input.length() == 1 || input.length() == 0) {
			return 0;
		}
		char first = input.charAt(0);
		char second = input.charAt(1);
		String allButFirst = input.substring(1, input.length());

		if (first == second) {
			return 1 + countConsecutivePairs(allButFirst);
		}
		return countConsecutivePairs(allButFirst);
	}

	/**
	 * You are given an array of integers. You are to determine if there are
	 * more even numbers, odd numbers, or a tie. If there are more even number
	 * then you should return "evens". If there are more odd numbers then you
	 * should return "odds". If there are an equal number of evens and odds, you
	 * should return "tie".
	 * 
	 * Examples: 
	 * evensOddsOrTie( {1,2,3,4,5,6 } ) -> "tie" 
	 * evensOddsOrTie({1,2,3,4,5 } ) -> "odds" 
	 * evensOddsOrTie( {2,3,4,5,6 } ) -> "evens"
	 * evensOddsOrTie( {3 } ) -> "odds" 
	 * evensOddsOrTie( {1,1,4,4,8,6 } ) -> "evens" 
	 * evensOddsOrTie( { } ) -> "tie"
	 * 
	 * Reminder: no loops on any of these problems. You may use a recursive
	 * helper method if you like, but it isn't required.
	 * 
	 * @param nums
	 *            - array of ints
	 * @return String - "tie" "odds" or "evens" depending on how many evens/odds
	 *         appear
	 */
	public static String evensOddsOrTie(int[] nums) {
		if (nums.length == 0) {
			return "tie";
		}
		
		return evenOddCounter(0,0,0,nums);
	}
	
	public static String evenOddCounter(int odds, int evens, int index, int[] nums) {
		if (index == nums.length) {
			if (odds > evens) {
				return "odds";
			} else if (evens > odds) {
				return "evens";
			} else {
				return "tie";
			}
		}
		
		if (nums[index] % 2 == 0) {
			return evenOddCounter(odds, evens + 1, index + 1, nums);
		} else {
			return evenOddCounter(odds + 1, evens, index + 1, nums);
		}
	}
	
	

	/**
	 * Returns the average length of the Strings in an array of Strings as a
	 * double.
	 * 
	 * You can assume a NON-EMPTY array (although the array may contain
	 * empty-strings i.e. "" )
	 * 
	 * ["apple","banana","orange","left"] returns 5.25. ["a","aa","aaa","aaaa"]
	 * returns 2.5; [""] returns 0.0;
	 * 
	 * Hint: you will want a helper method for this problem
	 * 
	 * @param words
	 *            : Array of Strings
	 * @return average length of strings in array
	 */
	public static double averageWordLength(String[] words) {
		if (words.length == 0) {
			return 0.0;
		}
		
		return wordLengthHelper(words, 0)/words.length;
	}

	public static double wordLengthHelper(String[] input, int index) {
		if (index == input.length - 1) {
			return input[index].length();
		}
		
		return input[index].length() + wordLengthHelper(input, index + 1);
	}

	/**
	 * This method returns true if the given array contains matching pairs of
	 * positive and negative numbers (with the same absolute value) in opposite
	 * relative positions. (This is similar to a palindrome.)
	 * 
	 * You can assume all numbers in the first half of the array are positive
	 * and all numbers in the second half of the array are negative. (Zero will
	 * not appear in the array)
	 * 
	 * Examples: [ 1, 2, -2, -1 ] returns true [ 7, 3, -3, -7 ] returns true [
	 * 1, 2, 3, -3, -2, -1 ] returns true [] returns true
	 * 
	 * [1 2 3 -2 -1] returns false [-3] returns false [ 1, 2, -1, -2 ] returns
	 * false [ 9, 3, -9, -3 ] returns false [ 1, 2, 3, -4, -1, -1 ] returns
	 * false
	 * 
	 * You'll want to use a recursive helper method for this one.
	 * 
	 * *YOU MAY NOT MAKE A COPY OF THE ARRAY*
	 * 
	 * Hint: you will want a helper method for this problem
	 * 
	 * @param ar
	 *            - array of integers
	 * @return boolean indicating if the positive and negative numbers match
	 */
	public static boolean hasMatchingPositiveAndNegatives(int[] ar) {
		if (ar.length == 0) {
			return true;
		}
		if (ar.length == 1) {
			return false;
		}
		int first = ar[0];
		int last = ar[ar.length - 1];
		int[] input = insideInts(ar);

		if (first == -last) {
			return hasMatchingPositiveAndNegatives(input);
		} else {
			return false;
		}
	}

	public static int[] insideInts(int[] ar) {
		int[] input = new int[ar.length - 2];
		for (int k = 0; k < ar.length - 2; k++) {
			input[k] = ar[k + 1];
		}
		return input;
	}

}
