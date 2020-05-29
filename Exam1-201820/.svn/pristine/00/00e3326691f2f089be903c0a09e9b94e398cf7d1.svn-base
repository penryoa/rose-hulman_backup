import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SmallProblemsTest {

	@Test
	public void testGenerate() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.addAll(Arrays.asList(8,4,2,1));
		ArrayList<Integer> actual = SmallProblems.generate(8); 
		assertEquals(expected, actual);
		
		expected.clear();
		expected.addAll(Arrays.asList(5,6,3,4,2,1));
		actual = SmallProblems.generate(5); 
		assertEquals(expected, actual);
		
		expected.clear();
		expected.addAll(Arrays.asList(50, 25, 26, 13, 14, 7, 8, 4, 2, 1));
		actual = SmallProblems.generate(50); 
		assertEquals(expected, actual);

		expected.clear();
		expected.addAll(Arrays.asList(1));
		actual = SmallProblems.generate(1); 
		assertEquals(expected, actual);
		
	}

	@Test
	public void testRotateIntoString() {
		char[] input1 = new char[] {'a', 'b', 'c', 'd'};
		assertEquals("bcda", SmallProblems.rotateIntoString(input1, 1));

		assertEquals("dabc", SmallProblems.rotateIntoString(input1, 3));
		
		char[] input2 = new char[] {'h', 'e', 'l', 'l', 'o'};
		assertEquals("llohe", SmallProblems.rotateIntoString(input2, 2));

		
		
		
	}
}
