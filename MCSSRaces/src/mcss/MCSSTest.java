package mcss;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests MCSS class.
 */
public class MCSSTest {
	private int[] testSequence = { -3, 4, 2, 1, -8, -6, 4, 5, -2 };

	/**
	 * Tests {@link MCSS#mcssCubic(int[])}.
	 */
	@Test
	public void testMCSSCubic() {
		MCSS.Result r = MCSS.mcssCubic(this.testSequence);
		assertEquals(9, r.sum);
		assertEquals(6, r.startIndex);
		assertEquals(7, r.endIndex);
	}

	/**
	 * Tests {@link MCSS#mcssQuadratic(int[])}.
	 */
	@Test
	public void testMCSSQuadratic() {
		MCSS.Result r = MCSS.mcssQuadratic(this.testSequence);
		assertEquals(9, r.sum);
		assertEquals(6, r.startIndex);
		assertEquals(7, r.endIndex);
	}

	/**
	 * Tests {@link MCSS#mcssLinear(int[])}.
	 */
	@Test
	public void testMCSSLinear() {
		MCSS.Result r = MCSS.mcssLinear(this.testSequence);
		assertEquals(9, r.sum);
		assertEquals(6, r.startIndex);
		assertEquals(7, r.endIndex);
	}

}
