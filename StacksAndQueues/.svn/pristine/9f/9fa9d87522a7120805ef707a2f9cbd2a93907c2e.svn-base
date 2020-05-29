package evaluator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the infix expression evaluator.
 * 
 * @author Matt Boutell, Hayley Price, and csse230-201410 students. Created Sep
 *         19, 2013.
 */
public class InfixEvaluatorTest {
	private static int points = 0;
	private InfixEvaluator infixEvaluator;

	/**
	 * Displays points.
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception {
		String message = String.format("You earned %d/29 points for infix",
				points);
		System.out.println(message);
	}

	/**
	 * Creates an evaluator.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.infixEvaluator = new InfixEvaluator();
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixSimpleOneOperator() {
		assertEquals("3 4 +", this.infixEvaluator.convertToPostfix("3 + 4"));
		assertEquals("6 2 /", this.infixEvaluator.convertToPostfix("6 / 2"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixCheckPrecedence() {
		assertEquals("3 4 * 2 -",
				this.infixEvaluator.convertToPostfix("3 * 4 - 2"));
		assertEquals("7 6 5 * +",
				this.infixEvaluator.convertToPostfix("7 + 6 * 5"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixMultipleOperators() {
		assertEquals("5 6 * 7 4 * +",
				this.infixEvaluator.convertToPostfix("5 * 6 + 7 * 4"));
		assertEquals("6 4 + 3 - 2 + 1 -",
				this.infixEvaluator.convertToPostfix("6 + 4 - 3 + 2 - 1"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixPrecedenceWithExponents() {
		assertEquals("4 5 6 ^ * 2 +",
				this.infixEvaluator.convertToPostfix("4 * 5 ^ 6 + 2"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixParentheses() {
		assertEquals("6 5 4 + *",
				this.infixEvaluator.convertToPostfix("6 * ( 5 + 4 )"));
		assertEquals("5 10 + 3 /",
				this.infixEvaluator.convertToPostfix("( 5 + 10 ) / 3"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testConvertToPostfixParenthesesWithExponents() {
		assertEquals("6 5 4 3 * + 2 ^ *",
				this.infixEvaluator.convertToPostfix("6 * ( 5 + 4 * 3 ) ^ 2"));
		assertEquals("4 2 3 4 * + ^",
				this.infixEvaluator.convertToPostfix("4 ^ ( 2 + 3 * 4 )"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluate() {
		assertEquals(7, this.infixEvaluator.evaluate("3 + 4"));
		assertEquals(3, this.infixEvaluator.evaluate("6 / 2"));
		assertEquals(10, this.infixEvaluator.evaluate("3 * 4 - 2"));
		assertEquals(37, this.infixEvaluator.evaluate("7 + 6 * 5"));
		assertEquals(58, this.infixEvaluator.evaluate("5 * 6 + 7 * 4"));
		assertEquals(8, this.infixEvaluator.evaluate("6 + 4 - 3 + 2 - 1"));
		assertEquals(54, this.infixEvaluator.evaluate("6 * ( 5 + 4 )"));
		assertEquals(1734,
				this.infixEvaluator.evaluate("6 * ( 5 + 4 * 3 ) ^ 2"));
		assertEquals(5, this.infixEvaluator.evaluate("( 5 + 10 ) / 3"));
		points += 2;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateTooManyRightParentheses() {
		try {
			this.infixEvaluator.convertToPostfix("3 + 4 )");
			fail("Extra parenthesis should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateTooManyNestedRightParentheses() {
		try {
			this.infixEvaluator.convertToPostfix("3 + ( 4 + 5 ) + 7 )");
			fail("Extra parenthesis should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateTooManyLeftParentheses() {
		try {
			this.infixEvaluator.convertToPostfix("( 3 + 4");
			fail("Extra parenthesis should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateTooManyNestedLeftParentheses() {
		try {
			this.infixEvaluator.convertToPostfix("3 + ( ( 4 + 6 )");
			fail("Extra parenthesis should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	// STUDENT TESTS

	/**
	 * Test a case for no numbers
	 * 
	 */
	@Test
	public void testDoubleParentheses() {
		assertEquals("8 6 + 7 2 - *",
				this.infixEvaluator.convertToPostfix("( 8 + 6 ) * ( 7 - 2 ) "));
		points += 1;
	}

	@Test
	public void testParenthesesToAPower() {
		assertEquals("3 3 + 3 3 + ^",
				this.infixEvaluator.convertToPostfix("( 3 + 3 ) ^ ( 3 + 3 )"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 * 
	 */
	@Test
	public void testEvaluateMultipleParentheses() {
		// DONE. Now also tests that integer division truncates.
		String infix = "( 2 + 3 * 7 ) / ( 4 + 1 )";
		assertEquals("2 3 7 * + 4 1 + /",
				this.infixEvaluator.convertToPostfix(infix));
		assertEquals(4, this.infixEvaluator.evaluate(infix));
		points += 1;
	}

	@Test
	public void testImproperCharacter() {
		try {
			this.infixEvaluator.evaluate("a * 2 + 5");
			fail("Wrong character makes exception");
		} catch (ArithmeticException e) {
			// Ok
			points += 1;
		}
	}

	@Test
	public void testEvaluateLong1() {
		assertEquals(
				75,
				this.infixEvaluator
						.evaluate("7 * 4 + 5 + 2 / 2 - 4 ^ ( 7 - 2 ^ 3 + 2 ) + 6 * 7 - ( 6 + 3 - 2 ) / 7 + 9 - 8 / 2 - 1"));
		points += 1;
	}

	@Test
	public void testEvaluateLong2() {
		assertEquals(36,
				this.infixEvaluator.evaluate("7 * 4 + 5 + 2 / 2 - 4 + 6"));
		points += 1;
	}

	@Test
	public void testEvaluateLong3() {
		assertEquals(852,
				this.infixEvaluator.evaluate("4 * 6 * 7 * 5 + 8 / 2 * 3"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateMultipleNestedLoopsAndTwoDigitNumbers1() {
		assertEquals(0,
				this.infixEvaluator.evaluate("( 3 + ( 3 + 3 ) * 6 ) - 39"));
		assertEquals(100,
				this.infixEvaluator.evaluate("( 10 + ( 20 - 30 ) * 2 ) ^ 2"));
		points += 2;
	}

	/**
	 * Our tests for InfixEvaluator
	 * 
	 */
	@Test
	public void testConvertToPostfixTest1() {
		assertEquals("45 5 + 10 /",
				this.infixEvaluator.convertToPostfix("( 45 + 5 ) / 10"));
		assertEquals(50, this.infixEvaluator.evaluate("( 45 + 5 )"));
		assertEquals("4 5 * 3 + 2 +",
				this.infixEvaluator.convertToPostfix("4 * 5 + 3 + 2"));
		points += 1;
	}

	@Test
	public void testConvertToPostfixTest2() {
		assertEquals(50, this.infixEvaluator.evaluate("( 45 + 5 )"));
		assertEquals("4 5 * 3 + 2 +",
				this.infixEvaluator.convertToPostfix("4 * 5 + 3 + 2"));
		points += 1;
	}

	@Test
	public void testNoNumber() {
		try {
			this.infixEvaluator.convertToPostfix("( ( ) ^ - ");
			fail("Extra operand should result in an exception.");
		} catch (ArithmeticException e) {
			points += 1;
		}
	}

	@Test
	public void testNoOperation() {
		// Was testing that an exception was thrown when converting to postfix,
		// but the exception should be thrown when evaluating.
		try {
			this.infixEvaluator.evaluate("234 235 1235 1");
			fail("Extra operand should result in an exception.");
		} catch (ArithmeticException e) {
			points += 1;
		}
	}

	@Test
	public void testOperandsWithoutOperator() {
		try {
			System.out.println(this.infixEvaluator.evaluate("3 + 3 4 + 5"));
			fail("Extra operand should result in an exception.");
		} catch (ArithmeticException e) {
			// OK
			points += 1;
		}
	}

	@Test
	public void testNestedExponents() {
		assertEquals(262144, this.infixEvaluator.evaluate("4 ^ ( 3 ^ 2 )"));
		assertEquals(43046721,
				this.infixEvaluator.evaluate("3 ^ ( ( 2 ^ 2 ) ^ 2 )"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testEvaluateParenGroups() {
		assertEquals(18,
				this.infixEvaluator.evaluate("( 3 * 2 + 7 ) + ( 10 / 2 )"));
		points += 1;
	}

	/**
	 * Test method for {@link InfixEvaluator#convertToPostfix(java.lang.String)}
	 * .
	 */
	@Test
	public void testDivideDownToZero() {
		assertEquals(0, this.infixEvaluator.evaluate("100 / 10 / 10 / 10 / 10"));
		points += 1;
	}
}