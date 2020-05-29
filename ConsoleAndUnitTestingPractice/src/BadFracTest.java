import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BadFracTest {

	@Test
	public void testIsReduced() {
		
		ArrayList<BadFrac> fractions = new ArrayList<BadFrac>();
		fractions.add(new BadFrac(1, 2));
		fractions.add(new BadFrac(5, 1));
		fractions.add(new BadFrac(2, 5));
		fractions.add(new BadFrac(0,1));
		
		for (BadFrac frac:fractions) {
			boolean actualResult = frac.isReduced();
			
			assertEquals(true, actualResult);
		}
		
	}
	
	@Test
	public void testIsNotReduced() {
		
		ArrayList<BadFrac> fractions = new ArrayList<BadFrac>();
		fractions.add(new BadFrac(2, 4));
		fractions.add(new BadFrac(6, 2));
		fractions.add(new BadFrac(0, 2));
		fractions.add(new BadFrac(4,6));
		
		for (BadFrac frac:fractions) {
			boolean actualResult = frac.isReduced();
			
			assertEquals("Numerator" + frac.numerator
					+ "Denominator" + frac.denominator, false, actualResult);
		}
		
	}
	
	@Test
	public void testAdd() {
		BadFrac frac12 = new BadFrac(1, 2);
		BadFrac frac51 = new BadFrac(5, 1);
		
		BadFrac actualResult = frac12.add(frac51);
		
//		assertEquals(11, actualResult.numerator);
//		assertEquals(2, actualResult.denominator);
		
		assertEquals(new BadFrac(11, 2), actualResult);
		
	}
	
	@Test
	public void testAddOneSixth() {
		BadFrac frac16 = new BadFrac(1,6);
		BadFrac frac162 = new BadFrac(1,6);
		
		BadFrac actualResult = frac16.add(frac162);
		
		assertEquals(new BadFrac(1,3), actualResult);
	}
	
}