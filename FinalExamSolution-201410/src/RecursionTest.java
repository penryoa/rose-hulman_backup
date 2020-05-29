import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class RecursionTest {

	Recursion object;
	
	@Before
	public void setUp() throws Exception {
		object = new Recursion();
	}

	@Test
	public void testSurroundCharactersWithParen() {
		Assert.assertEquals("(a)(b)(c)", object.surroundCharactersWithParen("abc"));
		Assert.assertEquals("(X)(y)", object.surroundCharactersWithParen("Xy"));
		Assert.assertEquals("", object.surroundCharactersWithParen(""));
	}

	@Test
	public void testOverallScore() {
		Assert.assertEquals(1, object.overallScore("WWL"));
		Assert.assertEquals(1, object.overallScore("WLW"));
		Assert.assertEquals(1, object.overallScore("LWW"));
		Assert.assertEquals(1, object.overallScore("TTTTTTTWWL"));
		Assert.assertEquals(1, object.overallScore("WT"));
		Assert.assertEquals(-2, object.overallScore("LL"));
		Assert.assertEquals(0, object.overallScore(""));
	}

	@Test
	public void testLargestElement() {
		ArrayList<Integer> foo = new ArrayList<Integer>();
		foo.add(2);
		foo.add(1);
		foo.add(-1);
		Assert.assertEquals(2, object.largestElement(foo));
		if(foo.size() == 3) {
			//looks like you used a helper function!
			System.out.println("You are cool");
		}
		foo = new ArrayList<Integer>();
		foo.add(-25);
		Assert.assertEquals(-25, object.largestElement(foo));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLargestElementException() {
		ArrayList<Integer> foo = new ArrayList<Integer>();
		object.largestElement(foo);
	}

}
