/*
 * This is a very small assignment.  It's mostly to show that you can
 * check out and check in code successfully.
 */
public class HW1 {

	/**
	 * This method takes in 4 separate integers. The first two comprise the first fraction
	 * as the numerator and denominator, respectively.  The third and fourth integers 
	 * comprise the second fraction.
	 * 
	 * This method returns the decimal result of adding the two fractions
	 * 
	 * Example
	 * 
	 * addFraction(1,2,1,4) returns 0.75
	 */
	public static double addFraction(int num1, int den1, int num2, int den2) {
		float num_1 = num1; float num_2 = num2; float den_1 = den1; float den_2 = den2;
		float one = num_1/den_1;
		float two = num_2/den_2;
		
		return one + two;
	}
	
	
	
	
	
	/**
	 * Takes a string that is all T's or F's.
	 * 
	 * T stands for touchdown and is worth 7 points.
	 * F stands for fieldgoal and is worth 3 points.
	 * 
	 * Returns the total score.
	 * 
	 * For example:
	 * "TT" returns 14
	 * "FTF" returns 13
	 * "FFFF" returns 12
	 * "" returns 0;
	 *  
	 * 
	 * @param a string only T's and F's
	 * @return a score
	 */
	public static int footballScore(String input) {

		int countT = 0;
		int countF = 0;
		
		for(int k=0; k < input.length(); k++) {
			if(input.charAt(k) == 'T') {
				countT = countT + 1;
			}
			if(input.charAt(k) == 'F') {
				countF = countF + 1;
			}
		}
			return (countF*3) + (countT*7);
	}
	
	
}//end class HW1
