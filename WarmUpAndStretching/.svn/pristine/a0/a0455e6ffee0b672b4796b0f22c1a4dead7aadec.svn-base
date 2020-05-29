package euclid;

public class Euclid {
	/**
	 * Implementation requirement: must do recursively, as given in the spec.
	 * 
	 * @param a
	 *            First integer
	 * @param b
	 *            Second integer
	 * @return The greatest common divisor of a and b using Euclid's recursive
	 *         algorithm.
	 */
	public static long gcd(long a, long b) {
//		System.out.println("Fib of 6:" + fibonacci(6));
//		System.out.println("if a is "+a+" and b is "+b+ ": " + fibonacci(a));
		return gcdHelper(a, b, 1, 1);
	}

	public static long gcdHelper(long a, long b, long currentNum, long maxNum) {
		if (currentNum > (a / 2) || currentNum > (b / 2) ) {
			return maxNum;
		}

		if (a % currentNum == 0 && b % currentNum == 0) {
			maxNum = currentNum;
		}
		
//		if (fibonacci(a) == b){
//			return 1;
//		}
		
		if (a % 2 == 0 && b % 2 == 0) {
			if (currentNum != 1) {
				return gcdHelper(a, b, currentNum + 2, maxNum);
			}
		}

		return gcdHelper(a, b, currentNum + 1, maxNum);
	}
	
//	public static long fibonacci(long n)  {
//	    if(n == 0) return 0;
//	    if(n <= 2) return 1;
//	    return fibonacci(n - 1) + fibonacci(n - 2);
//	}

}
