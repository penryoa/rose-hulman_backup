package anagram;

import java.util.ArrayList;

/**
 * This utility class can test whether two strings are anagrams.
 *
 * @author Claude Anderson.
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * For example, "Data Structure" and "Saturated Curt" are anagrams, as are
	 * "Elvis" and "Lives".
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {

		String s1Copy = s1.toLowerCase();
		String s2Copy = s2.toLowerCase();
		ArrayList<Character> possibleChars = new ArrayList<Character>();
		
		for (int i = 0; i < s1.length(); i++) {
			possibleChars.add(s1Copy.charAt(i));
		}

		return anagramHelper(possibleChars, s2Copy, 0);
	}

	public static boolean anagramHelper(ArrayList<Character> possibleChars, String s2, int index) {
		
		if (index == s2.length() && possibleChars.size() == 0) {
			return true;
		}
		else if (index > s2.length()) {
			return false;
		}

		char currentChar = s2.charAt(index);

		if (possibleChars.contains(currentChar)) {
			possibleChars.remove((Character) currentChar);
			return anagramHelper(possibleChars, s2, index + 1);
		}
		return false;
	}
}
