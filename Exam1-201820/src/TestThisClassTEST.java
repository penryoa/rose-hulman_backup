import static org.junit.Assert.*;

import org.junit.Test;

public class TestThisClassTEST {

	@Test
	public void TestTrue() {
		boolean expectedResult = false;
		boolean actualResult = TestThisClass.isGoingCrazy(2, 2);
		
		assertEquals(actualResult, expectedResult);
	}
	
	
	@Test
	public void TestFalse() {
		boolean expectedResult = false;
		boolean actualResult = TestThisClass.isGoingCrazy(2, 1);
		
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	public void TestBreak() {
		boolean expectedResult = false;
		boolean actualResult = TestThisClass.isGoingCrazy(3, 1);
		
		assertEquals(actualResult, expectedResult);
	}
	
}
