package quack;

import static org.junit.Assert.*;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

public class QuackTest {

	private static final String SPACES = "                                        ";
	
	// The number of points earned for the current test case, or negative
	// to indicate that the test case is not graded.
	private static double pointsForCurrentCase;
	private static double totalPoints = 0.0;
	
	@Test
	public void testInsertVeryBasic() {
		announceCase("testInsertVeryBasic");
		Quack q = new Quack();
		q.insert("one");
		assertEquals("[one]", q.toString());
		q.insert("two");
		q.insert("three");
		
		pointsForCurrentCase = 3;
	}
	
	@Test
	public void testStackModeOnly() {
		announceCase("testStackModeOnly");
		Quack q = new Quack();
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		assertEquals("four", q.retrieve());
		assertEquals("three", q.retrieve());
		assertEquals("two", q.retrieve());
		assertEquals("one", q.retrieve());
		pointsForCurrentCase = 6;
	}

	@Test
	public void testQueueModeOnly() {
		announceCase("testQueueModeOnly");
		Quack q = new Quack();
		q.inStackMode = false;
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		assertEquals("one", q.retrieve());
		assertEquals("two", q.retrieve());
		assertEquals("three", q.retrieve());
		assertEquals("four", q.retrieve());
		pointsForCurrentCase = 6;
	}
	
	
	@Test
	public void testStackToQueue() {
		announceCase("testStackToQueue");
		Quack q = new Quack();
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		q.insert("five");
		q.insert("six");
		// In stack mode
		assertEquals("six", q.retrieve());
		assertEquals("five", q.retrieve());
		q.toQueueMode();                 // to queue mode
		assertEquals("one", q.retrieve());
		assertEquals("two", q.retrieve());
		q.insert("seven");
		q.insert("eight");
		assertEquals("three", q.retrieve());
		assertEquals("four", q.retrieve());
		assertEquals("seven", q.retrieve());
		pointsForCurrentCase = 4;
	}
	

	
	@Test
	public void testQueueToStack() {
		announceCase("testQueueToStack");
		Quack q = new Quack();
		q.inStackMode = false;
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		q.insert("five");
		q.insert("six");
		// In queue mode
		assertEquals("one", q.retrieve());
		assertEquals("two", q.retrieve());
		q.toStackMode();                // to stack mode
		assertEquals("six", q.retrieve());
		assertEquals("five", q.retrieve());
		q.insert("seven");
		assertEquals("seven", q.retrieve());
		assertEquals("four", q.retrieve());
		pointsForCurrentCase = 4;
	}


	
	@Test
	public void testQuackWithMultipleModes() {
		announceCase("testBothWithMultipleModes");
		Quack q = new Quack();
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		q.insert("five");
		q.insert("six");
		q.insert("seven");
		q.insert("eight");
		q.insert("nine");
		// In queue mode
		assertEquals("nine", q.retrieve());
		q.toQueueMode();
		assertEquals("one", q.retrieve());
		q.toQueueMode();
		assertEquals("two", q.retrieve());
		q.toStackMode();
		assertEquals("eight", q.retrieve());
		q.toStackMode();
		assertEquals("seven", q.retrieve());
		q.insert("ten");
		q.insert("eleven");
		q.insert("twelve");
		q.toQueueMode();
		assertEquals("three", q.retrieve());
		q.toStackMode();
		assertEquals("twelve", q.retrieve());
		pointsForCurrentCase = 6;
	}
	
	@Test
	public void testRetrieveOnEmpty() {
		announceCase("testRetrieveOnEmpty");
		Quack q = new Quack();
		try {
			q.retrieve();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e) {
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");
			}
		}
		pointsForCurrentCase = 1;
	}
	

	@After
	public void tearDown() throws Exception {
		if (pointsForCurrentCase >= 0) {
			System.out.println(MessageFormat.format("{0, number, 0.0}",
					pointsForCurrentCase));
			totalPoints += pointsForCurrentCase;
		}
	}

	// Prints a well formatted message to the console for accumulating points
	// and notes that the current test is graded.
	private static void announceCase(String caseName) {
		pointsForCurrentCase = 0.0;
		StringBuilder annoucement = new StringBuilder();
		annoucement.append(caseName);
		annoucement.append(SPACES);
		System.out.print(annoucement.substring(0, 40));
	}

	@AfterClass
	public static void showPoints() {
		System.out.printf("UNIT TEST POINTS (out of 30)            %4.1f\n",
				totalPoints);
		System.out.printf("10 efficiency points will be checked manually");
	}
}
