import junit.framework.Assert;

import org.junit.Test;


@SuppressWarnings("javadoc")
public class RecursionTest {

	Recursion r = new Recursion();
	
	@Test
	public void testReverseString() {
		Assert.assertEquals("cba", this.r.reverseString("abc"));
		Assert.assertEquals("olleh", this.r.reverseString("hello"));
		Assert.assertEquals("a", this.r.reverseString("a"));
		Assert.assertEquals("", this.r.reverseString(""));
	}

	@Test
	public void testComputeMissingCharacters() {
		Assert.assertEquals("ABC", this.r.computeMissingCharacters("hAelBloC", "hello"));
		Assert.assertEquals("Qabc", this.r.computeMissingCharacters("Q123abc", "123"));
		Assert.assertEquals("Q123", this.r.computeMissingCharacters("Q123abc", "abc"));
		Assert.assertEquals("", this.r.computeMissingCharacters("", ""));
		Assert.assertEquals("", this.r.computeMissingCharacters("a", "a"));

	}

	@Test
	public void testHasEvenNumberOfXs() {
		Assert.assertTrue(this.r.hasEvenNumberOfXs("xxxx"));
		Assert.assertFalse(this.r.hasEvenNumberOfXs("xxx"));
		Assert.assertTrue(this.r.hasEvenNumberOfXs("xx"));
		Assert.assertFalse(this.r.hasEvenNumberOfXs("x"));
		Assert.assertTrue(this.r.hasEvenNumberOfXs(""));
		Assert.assertTrue(this.r.hasEvenNumberOfXs("dasxsaxfxgx"));
		Assert.assertFalse(this.r.hasEvenNumberOfXs("axaxfxa"));
		Assert.assertTrue(this.r.hasEvenNumberOfXs("qqqqxx"));
		Assert.assertFalse(this.r.hasEvenNumberOfXs("xqqqq"));
		Assert.assertTrue(this.r.hasEvenNumberOfXs("hello world"));

	}

}
