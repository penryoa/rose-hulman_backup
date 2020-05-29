package evaluator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the postfix expression evaluator.
 * 
 * @author Matt Boutell, Hayley Price, and csse230-201410 students. Created Sep
 *         19, 2013.
 */
public class PostfixEvaluatorTest {

	private Evaluator postfixEvaluator;
	private static int points = 0;

	/**
	 * Creates an evaluator.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.postfixEvaluator = new PostfixEvaluator();
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixSingleOperation() {
		assertEquals(8, this.postfixEvaluator.evaluate("3 5 +"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixSingleOperationOrderMatters() {
		assertEquals(2, this.postfixEvaluator.evaluate("5 3 -"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixTwoOperations() {
		assertEquals(24, this.postfixEvaluator.evaluate("1 5 + 4 *"));
		assertEquals(36, this.postfixEvaluator.evaluate("24 6 / 9 *"));
		assertEquals(-24, this.postfixEvaluator.evaluate("1 5 - 6 *"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixMultipleOperatorsAtEnd() {
		assertEquals(10, this.postfixEvaluator.evaluate("1 5 4 + +"));
		assertEquals(-5, this.postfixEvaluator.evaluate("5 4 2 3 * + -"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixTwoOperationsWithExponents() {
		assertEquals(64, this.postfixEvaluator.evaluate("4 2 1 + ^"));
		assertEquals(17, this.postfixEvaluator.evaluate("4 2 ^ 1 +"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixThreeOperations() {
		assertEquals(26, this.postfixEvaluator.evaluate("1 5 + 4 * 2 +"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixTooManyOperands() {
		try {
			this.postfixEvaluator.evaluate("3 5 6 +");
			fail("Extra operand should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixTooManyOperators() {
		try {
			this.postfixEvaluator.evaluate("3 5 6 + - *");
			fail("Extra operator should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	// Student Tests
	/**
	 * test when there are too few operands
	 * 
	 */
	@Test
	public void testToofewOperands() {
		try {
			this.postfixEvaluator.evaluate("5 3 5 +");
			fail("should throw arthimetic error");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Tests that it handles multiple operands correctly
	 * 
	 */
	@Test
	public void testMultipleOps() {
		assertEquals(50, this.postfixEvaluator.evaluate("5 3 7 + *"));
		assertEquals(16, this.postfixEvaluator.evaluate("5 3 + 2 *"));
		assertEquals(100,
				this.postfixEvaluator
						.evaluate("6 5 + 4 * 40 - 2 ^ 2 * 4 / 2 + 10 *"));
		assertEquals(15,
				this.postfixEvaluator.evaluate("4 3 2 * 4 2 / 3 + + +"));
		assertEquals(16,
				this.postfixEvaluator.evaluate("4 3 2 * 4 2 / 3 1 + + + +"));
		points += 2;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixParenthesesInEquation() {
		try {
			this.postfixEvaluator.evaluate("3 4 ( 5 6 * ) + -");
			fail("Parentheses should result in an exception");
		} catch (ArithmeticException e) {
			// Successful if caught
			points += 1;
		}
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixSingleNumber() {
		assertEquals(4, this.postfixEvaluator.evaluate("4"));
		points += 1;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}
	 */
	@Test
	public void testImproperCharacter() {
		try {
			this.postfixEvaluator.evaluate("3 5 b + - *");
			fail("Wrong character makes exception");
		} catch (ArithmeticException e) {
			// Ok
			points += 1;
		}
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}
	 */
	@Test
	public void testIncorrectPostfixOrder() {
		try {
			this.postfixEvaluator.evaluate("3 * 5 7 + -");
			fail("Incorrect Order/Format");
		} catch (ArithmeticException e) {
			// Ok
			points += 1;
		}
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixMultipleTwoDigitNumbers() {
		assertEquals(22, this.postfixEvaluator.evaluate("10 20 + 3 / 12 +"));
		points += 1;
	}

	@Test
	public void testOperandsWithoutOperator() {
		try {
			this.postfixEvaluator.evaluate("3 5 6 ");
			fail("Extra operator should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	@Test
	public void testOperatorsWithoutOperands() {
		try {
			System.out.println(this.postfixEvaluator.evaluate("3 5 + + 5"));
			fail("Extra operand should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	@Test
	public void testNestedExponents() {
		assertEquals(262144, this.postfixEvaluator.evaluate("4 3 2 ^ ^"));
		assertEquals(43046721, this.postfixEvaluator.evaluate("3 2 2 ^ 2 ^ ^"));
		points += 2;
	}

	/**
	 * Test method for {@link PostfixEvaluator#evaluate(java.lang.String)}.
	 */
	@Test
	public void testEvaluatePostfixWithZero() {
		assertEquals(1, this.postfixEvaluator.evaluate("10 0 ^"));
		try {
			this.postfixEvaluator.evaluate("5 0 /");
			fail("Invalid operator should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Displays points.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception {
		String message = String.format("You earned %d/21 points for postfix",
				points);
		System.out.println(message);
	}
}