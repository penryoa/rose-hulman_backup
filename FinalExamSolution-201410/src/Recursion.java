import java.util.ArrayList;


public class Recursion {

	/**
	 * 
	 * Returns a string where each character is surrounded by ( and )
	 * For example
	 *    surroundCharactersWithParen("abc") returns "(a)(b)(c)"
	 *    
	 * You must solve this problem with recursion
	 */
	public String surroundCharactersWithParen(String input) {
		if(input.isEmpty()) return "";
		String theRest = surroundCharactersWithParen(input.substring(1));
		return "(" + input.charAt(0) + ")" + theRest;
	}
	
	/**
	 * 
	 * Takes in a string that represents a series of wins, losses, and ties.
	 * Returns a score for the overall series where a win is worth 1 point
	 * a tie is worth 0 points and a loss is worth -1 points.  The string
	 * will consist exclusively of Ws, Ls, and Ts (representing wins losses
	 * and ties respectively).
	 * 
	 * For example:
	 *    overallScore("WWL") returns 1
	 *    overallScore("TTLLL") returns -3
	 *    overallScore("") returns 0
	 *    
	 * You must solve this problem with recursion
	 */
	public int overallScore(String series) {
		if(series.isEmpty()) return 0;
		int otherScores = overallScore(series.substring(1));
		switch(series.charAt(0)) {
		case 'W': 
			return otherScores + 1;
		case 'L': 
			return otherScores - 1;
		case 'T': 
			return otherScores;
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * 
	 * Takes in an ArrayList<Integer> and returns the largest element.
	 * You are allowed to modify the ArrayList and remove elements if 
	 * you wish, but I'll be more impressed with your code if you use
	 * a helper function so that the arraylist does not need to be modified.
	 * (Me being impressed won't score you any more points though)
	 * 
	 * Note that attempting to get the largest element of an empty list
	 * is undefined.  If the function is called with an empty list,
	 * your code should throw an IllegalArguementException
	 * 
	 * For example:
	 * largestElement of [1,2,2,-1] returns 2
	 * largestElement of [-25] returns -25
	 * largestElement of [] throws IllegalArguementException
	 * 
	 * You must solve this problem with recursion
	 */
	public int largestElement(ArrayList<Integer> list) {
		if(list.isEmpty())
			throw new IllegalArgumentException();
		int current = list.remove(0);
		if(list.isEmpty()) return current;
		int otherMax = largestElement(list);
		if(otherMax > current) return otherMax;
		return current;
	}
}
