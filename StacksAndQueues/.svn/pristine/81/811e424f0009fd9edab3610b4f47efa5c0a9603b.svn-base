package evaluator;
/**
 * Evaluates expression strings passed to it.
 * 
 * @author Matt Boutell. Created Sep 19, 2013.
 */
public abstract class Evaluator {
	/**
	 * Calculates the value of the given expression.
	 * 
	 * @param expression
	 *            Each item in the expression string must be delimited by
	 *            whitespace.
	 * @return The value of the given expression.
	 * @throws ArithmeticException
	 *             If the expression is malformed.
	 */
	public abstract int evaluate(String expression) throws ArithmeticException;

	/**
	 * Checks if the given token (word) is an operator. The token should be just
	 * a single character.
	 * 
	 * @param token
	 * @return True if the given string is an operator.
	 */
	protected static boolean isOperator(String token) {
		return "*/+^-()".contains(token) && token.length() == 1;
	}
}
