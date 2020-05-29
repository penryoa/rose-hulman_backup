import java.util.ArrayList;


public class TwelveProblems {

	
	/** 	DONE!
	 * Given a particular point in the coordinate plane, compute the 
	 * point's distance from the origin (0,0)
	 * 
	 * For example
	 * 
	 * distanceFromOrigin(-1,0) returns 1
	 * distanceFromOrigin(77,77) returns 108.894
	 * distanceFromOrigin(-3,-4) returns 5
	 * 
	 * Google for Java square root to figure out how to do it 
	 */
	public static double distanceFromOrigin(double x, double y) {
		double sqsum = x*x + y*y;
		double dis = Math.sqrt(sqsum);
		return dis;
	} 
	
	
	
	
	
	/**		DONE!
	 * Determine if a given positive integer has a 5 in the second rightmost digit
	 * 
	 * For example:
	 * 
	 * 5 returns false
	 * 52 returns true
	 * 151 returns true
	 * 30050 returns true
	 * 5000 returns false
	 * 
	 * Hint: you'll want to use the modulus operator % and the division operation /
	 * See section 4.2.3 for details.  Don't convert the number to a string!
	 * 
	 * Bonus Hint: you usually should not use an if statement to return a boolean
	 * 
	 * if (x % 2 == 0)  { // checks if x is even
	 *    return true; 
	 * } else {
	 *    return false;
	 * }
	 * 
	 * Instead, just return the result of the boolean test directly
	 * 
	 * return x % 2 == 0; //returns true or false
	 * 
	 * @param input
	 * @return
	 */
	public static boolean secondDigit5(int input) {
		int div = input/10;
		int div2 = div/5;
		return div2%2 != 0;
	}
	
	
	
	
	
	
	
	/**
	 * Determine if a given string ends with an uppercase letter.
	 * 
	 * For example:
	 * 
	 * endsWithUppercase("dog") returns false
	 * endsWithUppercase("doG") returns true
	 * endsWithUppercase("") returns false
	 * 
	 * Note that you can check if a particular char is uppercase like this:
	 * 
	 * char myChar = 'A';
	 * if(Character.isUpperCase(myChar)) {
	 * 	System.out.println("uppercase!");
	 * }
	 * 
	 * Note that the empty string is considered not to end with an uppercase
	 * letter.
	 * 
	 * Requires if statements, strings
	 */
	public static boolean endsWithUpperCaseLetter(String input) {
		if (input.length() > 1) {
			char mychar = input.charAt(input.length() -1);
			if (Character.isUpperCase(mychar)) {
				return true;
		} 	else {
				return false;
		}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Returns the first number taken to the power of the second number
	 * 
	 * For example pow(2,3) returns 2^3 or 8
	 * 
	 * DO NOT USE FUNCTIONS in Math (yes, Math.pow is how you would really do it)
	 * Instead, write this code yourself as practice.
	 * 
	 * Don't forget about negative powers!  But both parameters are integers,
	 * so you don't need to worry about fractional powers.
	 * 
	 * Hint: if you want to get the reciprocal of an integer, do it like this
	 * 1.0/coolInt   not like this 1/coolInt
	 * 
	 * Why?  See section 4.2.3 in your book
	 * 
	 * Requires: for loops
	 */
	public static double pow(int num, int power) {
		double temp = 1;
		double number = num;
		double austin = power;
		if (power >= 0) {
			for (double k=1; k < (austin + 1); k++) {
				temp = temp * number;
			}
		}
		if (austin < 0) {
			for (double k=1; k < (-austin + 1); k++) {
				temp = temp * (1/number);
			}
		}
		return temp;
	}
	
	
	
	
	
	
	
	
	/**
	 * Given two strings of the same length, returns the index at which the
	 * strings first differ.  If the strings are equal the function should
	 * return -1.
	 * 
	 * For example:
	 *   firstDifference("abc", "axy") returns 1
	 *   firstDifference("aby", "abz") returns 2
	 *   firstDifference("foo", "bar") returns 0
	 *   firstDifference("ninja", "ninja") returns -1
	 *   firstDifference("","") returns -1
	 * 
	 * You don't need to worry about inputs of different lengths.
	 * 
	 * Hint: if you want to compare the two strings to see if they
	 * are equal.  For example, something like this:
	 * 
	 * if(one.equals(two)) return -1;
	 * 
	 * Individual characters however, should be compared with ==
	 * char a = one.charAt(0);
	 * char b = two.charAt(0);
	 * if(a == b) {
	 *   System.out.println("First characters are equal");
	 * }
	 * 
	 * Requires: for loops or while loops, strings
	 */
	public static int firstDifference(String one, String two) {
		int index = -1;
		for (int k=0; k < one.length(); k++) {
			char a = one.charAt(k);
			char b = two.charAt(k);
			if (a!=b) {
				return k;
			}
		}
		return index;
	}
	
	
	
	
	
	
	/**
	 * Given one string, return the most common character.
	 * 
	 * Example:
	 *   mostCommonCharacter("aaab") returns 'a'
	 *   mostCommonCharacter("abcbcdc") returns 'c'
	 * 
	 * You can assume that your string will not be empty and that
	 * one character will be more common than all the others (i.e. there
	 * won't be a tie for the most common character)
	 * 
	 * Your solution should use a pair of nested for loops.  You might be
	 * tempted to use something like python's dictionary here, but
	 * we'll save that till later when we cover hashmaps.
	 * 
	 * Requires: for loops, strings
	 */
	public static char mostCommonCharacter(String input) {
		
		char maxchar = input.charAt(0);
		int maxcount = 0;
		
		for (int k=0; k<input.length(); k++) {
			if (input.charAt(k) == maxchar) {
				maxcount += 1;
			}
		}
		
		for (int j=1; j<input.length(); j++) {
			
			if (input.charAt(j) != maxchar) {
				int tempcount = 0;
				for (int m=j; m<input.length(); m++) {
					if (input.charAt(m) == input.charAt(j)) {
						tempcount += 1;
					}
				}
				
				if (tempcount > maxcount) {
					maxchar = input.charAt(j);
				}
			}
		}
		
			
		
		
		return maxchar;
	}
	
	
	
	
	
	
	
	
	/**
	 * Finds the first number in an array divisible by 77 and
	 * returns it. 
	 * 
	 * If no number is found, the function returns -1
	 * 
	 * Example:
	 *  firstDivisibleBy77({88,24,154,77}) returns 154
	 *  firstDivisibleBy77({5929, 280}) returns 5929
	 *  firstDivisibleBy77({1,2,3,4}) returns -1
	 * 
	 * Don't forget about the modulus operator (%)
	 */
	public static int firstDivisibleBy77(int[] numbers) {
		int index = -1;
		for (int k=0; k < numbers.length; k++) {
			double temp = numbers[k];
			if (temp%77 == 0) {
				return numbers[k];
			}
			
		}
		return index;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Creates an array of all the powers of two, up to (and including)
	 * the given exponent starting at 2^0.
	 * 
	 * If the given exponent is less than zero, return an empty array.
	 * You can do this by saying "return new int[0];"
	 * 
	 * For example:
	 *   powersOfTwo(3) returns {1,2,4,8}
	 *   powersOfTwo(0) returns {1}
	 *   powersOfTwo(-66) returns {}
	 * 
	 * Requires: arrays, for loops
	 */
	public static int[] powersOfTwo(int maxExponent) {
		
		if (maxExponent == 0) {
			int[] temp = new int[1];
			temp[0] = 1;
			return temp;
					
		}
		
		if (maxExponent > 0) {
			
			int[] twos = new int[maxExponent+1];
			
			for (int k=0; k < maxExponent + 1; k++) {
				int temp = 1;
				
				if (k == 0) {
					twos[0] = 1;
				}
					
				if (k>=1) {
					for (int j=0; j < k; j++) {
						temp = temp * 2;
					}
				twos[k] = temp;
				}
			}
			return twos;	
		}
		return new int[0];
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Given two arrays of integers that are the same length, returns
	 * a new array that contains the pairwise max of the corresponding
	 * elements of the original arrays (i.e. the larger of the two numbers
	 * in that slot in the original arrays)
	 * 
	 * For example:
	 *   maxArray({2,10},{1,200}) returns {2,200}
	 *   maxArray({-5, 60, 7},{-10,400,8}) returns {-5,400,8} 
	 *   
	 *   Requires: arrays, for loops
	 */
	public static int[] maxArray(int[] one, int[] two) {
		
		int[] new_list = new int[one.length];
		
		for (int k=0; k < one.length; k++) {
			if (one[k] >= two[k]) {
				new_list[k] = one[k];
			} else {
				new_list[k] = two[k];
			}
		}
		return new_list;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * Given two arrays, count the number of times the first array occurs
	 * in the second array.  You can assume that the first array is shorter
	 * than the second.
	 * 
	 * For example:
	 *   timesOccur({1,2}, {7,1,2,7,7,7,1,2,7}) returns 2
	 *   timesOccur({1,1}, {1,1,1,3} returns 2 (in the 0th and 1st position)
	 *   timesOccur({1,2}, {1,3,2,1} returns 0
	 * 
	 *  Requires: arrays, nested for loops
	 */
	public static int timesOccur(int[] shorter, int[] longer) {
		
		int count = 0;
		
		for (int k=0; k < longer.length-shorter.length+1; k++) {
			
			int[] temp = new int[shorter.length];
			
			for (int j=0; j < shorter.length; j++) {
				temp[j] = longer[k+j];
			
			}
			
			if (shorter.equals(temp)) {
				count += 1;	
			}
			
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Given an arraylist of strings, return a new list where
	 * any time the word "double" appears in the original list
	 * it is doubled in the new list.
	 * 
	 * For example:
	 *   doubleDouble(["foo","double"]) returns ["foo","double","double"]
	 *   doubleDouble(["a","double","b","double","c"]) returns ["a","double","double","b","double","double","c"]
	 *   
	 * Be careful not to modify the original list.  Start by creating a new
	 * output list that holds the results!  E.g.
	 * 
	 * ArrayList<String> output = new ArrayList<String>();
	 * 
	 * When you are comparing strings, be sure to use .equals and not ==
	 * Eg.
	 * if(currentString .equals("double")) { stuff }
	 * 
	 */
	public static ArrayList<String> doubleDouble(ArrayList<String> input) {
		
		ArrayList<String> mylist = new ArrayList<String>();
		
		for (int k=0; k < input.size(); k++) {
			mylist.add(input.get(k));
			if (input.get(k)=="double") {
				mylist.add("double");
			}
		}
		
		return mylist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Given a string, return all 3 character substrings of that string in an arraylist.
	 * That is, first it will return the 1st 3 characters of the string
	 * Then it will return the 2nd 3rd and 4th characters
	 * Then it will return the 3rd 4th and 5th characters
	 * etc.
	 * 
	 * For example:
	 *   threeCharacterStrings("hello") returns ["hel","ell","llo"]
	 *   threeCharacterStrings("foo") returns ["foo"]
	 *   threeCharacterStrings(["ab"]) returns []
	 */
	public static ArrayList<String> threeCharacterStrings(String input) {
		
		ArrayList<String> mylist = new ArrayList<String>();
		
		if (input.length() >= 3) {
			for (int k=0; k<input.length()-2; k++) {
				mylist.add(input.substring(k, k+3));
				}
			}
		
		return mylist;
	}
	
	
}
