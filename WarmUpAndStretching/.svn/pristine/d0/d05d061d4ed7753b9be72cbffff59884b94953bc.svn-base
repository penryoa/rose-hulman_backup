package euclid;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Simple introduction or reminder about different kinds of maps.
 * 
 * @author Matt Boutell
 *
 */
public class EuclidTest {
	private static float points = 0;
	private static final float MAX_POINTS = 6;

	@Test
	public void testBasic() {
		assertEquals(6, Euclid.gcd(42, 18));
		points += 1;
	}

	@Test
	public void testReversed() {
		// Shows that the first number doesn't need to be the larger one.
		assertEquals(6, Euclid.gcd(18, 42));
		points += 1;
	}

	@Test
	public void testLargeGcd() {
		assertEquals(60, Euclid.gcd(180, 120));
		points += 1;
	}

	@Test
	public void testLargePrimeGcd() {
		assertEquals(89, Euclid.gcd(178, 445));
		points += 1;
	}

	@Test
	public void testWorstCaseRuntime() {
		// Each of these uses the most iterations possible for the size of a and
		// b. What is true about a and b in each case? That is, do you recognize
		// where these numbers come from?
		assertEquals(1, Euclid.gcd(3, 5));
		assertEquals(1, Euclid.gcd(5, 8));
		assertEquals(1, Euclid.gcd(8, 13));
		assertEquals(1, Euclid.gcd(13, 21));
		assertEquals(1, Euclid.gcd(21, 34));
		assertEquals(1, Euclid.gcd(34, 55));
		assertEquals(1, Euclid.gcd(121393, 196418));
		points += 2;
	}

	@AfterClass
	public static void showResults() {
		System.out.printf("EUCLID POINTS = %.1f of %.1f\n", points, MAX_POINTS);
		System.out.println("You'll get these unit test points only if it you did it recursively.");
	}
}
