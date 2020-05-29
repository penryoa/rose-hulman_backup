import java.util.ArrayList;
import java.util.HashMap;

public class MapAnd2DArrayProblems {

	/**
	 * Zero Diagonals
	 * 
	 * Given a n x n 2D array change all of the diagonal values to be zero.
	 * Diagonals include from upper left to lower right AND from lower left to
	 * upper right.
	 * 
	 * (Hint what are the coordinates of the lower left corner?) (Hint what are
	 * the coordinates of the upper right corner?)
	 * 
	 * 
	 * Example1: {{1, 1, 3}, {2, 3, 4}, {3, 7, 6}}
	 * 
	 * returns
	 * 
	 * {{0, 1, 0}, {2, 0, 4}, {0, 7, 0}}
	 *
	 * Example2: {{1, 1, 3, 4}, {2, 3, 4, 8}, {3, 7, 6, 2}, {5, 8, 1, 9}}
	 * 
	 * returns
	 * 
	 * {{0, 1, 3, 0}, {2, 0, 0, 8}, {3, 0, 0, 2}, {0, 8, 1, 0}}
	 * 
	 * 
	 * Example3: {{1, 1, 5, 1, 8}, {2, 9, 4, 3, 6}, {3, 8, 6, 5, 3}, {4, 7, 6,
	 * 2, 4}, {5, 6, 6, 8, 1}}
	 * 
	 * returns
	 * 
	 * {{0, 1, 5, 1, 0}, {2, 0, 4, 0, 6}, {3, 8, 0, 5, 3}, {4, 0, 6, 0, 4}, {0,
	 * 6, 6, 8, 0}}
	 * 
	 * 
	 * 
	 * @param array
	 *            - The input array
	 * 
	 */
	public static void zeroDiagonals(int[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (i == j) {
					array[i][j] = 0;
				}
				
				
				if (j==array.length-1-i) {
					array[i][j] = 0;
				}
				
			}
		}

	}
	
	
	

	/**
	 * Given an ArrayList<String> of words, return an int which is the total
	 * number of words which started with the most common *starting* letter
	 *
	 * You can assume that all letters will be lower case and that there will be
	 * only one letter which is the most common.
	 *
	 * REQUIREMENT: YOU MUST USE A MAP TO SOLVE THIS PROBLEM
	 *
	 * 
	 * { } returns 0 ( no words start with any letters) { "apple", "banana",
	 * "bread", "channel" } returns 2 (because "b" is the most common starting
	 * letter and occurs 2 times) { "add", "adam", "alien","all", "zero" }
	 * returns 4 (because "a" is the most common starting letter and occurs 4
	 * times) { "zero" } returns 1 (because "z" is the only starting letter and
	 * occurs once)
	 */
	public static int maxFirstLetterCount(ArrayList<String> words) {
		HashMap<Character, Integer> visited = new HashMap<Character, Integer>();
		
		for(int k=0; k < words.size(); k++) {
			visited.putIfAbsent(words.get(k).charAt(0), 0);
		}
		
//		System.out.println(visited.keySet());
		
		for(Character keys:visited.keySet()) {
			for(int k=0; k < words.size(); k++) {
				if (keys.equals(words.get(k).charAt(0))) {
//					System.out.println(words.get(k).charAt(0) +" and " + keys);
					visited.replace(words.get(k).charAt(0), visited.get(keys)+1);
//					System.out.println(visited.get(keys));
				}
			}
		}
		
		int maxCount=0;
		
		for(Character keys:visited.keySet()) {
			if (visited.get(keys) > maxCount) {
				maxCount = visited.get(keys);
			}
		}
		
		
		
		return maxCount;
	}








}
