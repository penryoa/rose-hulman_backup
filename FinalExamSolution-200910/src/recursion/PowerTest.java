package recursion;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the power function.
 *
 * @author Curt Clifton
 */
public class PowerTest {
	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower1() {
		assertEquals(1, Power.power(0,0));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower2() {
		assertEquals(1, Power.power(1,0));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower3() {
		assertEquals(1, Power.power(2,0));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower4() {
		assertEquals(0, Power.power(0,1));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower5() {
		assertEquals(1, Power.power(1,1));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower6() {
		assertEquals(2, Power.power(2,1));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower7() {
		assertEquals(1024, Power.power(2,10));
	}

	/**
	 * Tests {@link Power#power(int, int)}.
	 */
	@Test
	public void testPower8() {
		assertEquals(100, Power.power(10,2));
	}


}
