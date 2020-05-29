package recursion;

public class RecursionProblems {

	/**
	 * In this method you are given an String which contains words separate by spaces.
	 * You should return that String of words with the same words reversed in order.
	 * 
	 * 
	 * You are NOT allowed to use any Java loops to implement this feature. 
	 * 
	 * You CAN use a recursive helper function for this problem, but it is not required.
	 * 
	 * Example:
	 * reverseWordOrder("this is a test"), returns "test a is this"
	 * reverseWordOrder("a"), returns "a"
	 * reverseWordOrder(""), returns ""
	 * reverseWordOrder("arm the bear"), returns "bear the arm"
	 * 
	 * Note: you do NOT need to remove trailing spaces from your String
	 * 
	 * You CANNOT use split and should not convert the String to an array.
	 * 
	 * Hint: String.indexOf(" ") will return the index of the first " " substring in the string
	 *       (Or -1 if " " is not found)
	 */
	public static String reverseWordOrder(String sentence) {
		int indexLastSpace = sentence.lastIndexOf(" ", sentence.length());

		if (indexLastSpace == -1) {
			return sentence;
		} else {
			return reverseHelper(sentence, sentence.length()-1, indexLastSpace);
		}
	}
	
	public static String reverseHelper(String s, int index, int indexLastSpace) {
		if (index == -1){
			return "";
		}
		
		if (index == indexLastSpace) {
			indexLastSpace = s.lastIndexOf(" ", index);
			return reverseHelper(s, index - 1, indexLastSpace) + s.charAt(index);
		} else {
			return reverseHelper(s, index - 1, indexLastSpace) + s.charAt(index);
		}
	}
	
	public static String wholeWord(String s, int startIndex, int lastIndex) {
		if (startIndex == lastIndex) {
			return "";
		}
		return s.charAt(startIndex) + wholeWord(s, startIndex + 1, lastIndex);
	}


}
