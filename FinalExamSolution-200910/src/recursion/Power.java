package recursion;

/**
 * Demonstrates a recursive method.
 */
public class Power {
	/**
	 * Calculates x raised to the nth power using recursion.
	 *
	 * @param x
	 * @param n must be non-negative
	 * @return x^n
	 */
	public static int power(int x, int n) {
		// TODO: implement power() recursively
		if (n <= 0)
			return 1;
		return x * power(x, n-1);
	}
}
