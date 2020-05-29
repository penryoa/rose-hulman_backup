package comparingShapes;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Tests the circle and triangle classes
 * 
 * @author Matt Boutell. Created Dec 1, 2013.
 */

public class ShapeTest {

	private static int points = 0;

	/**
	 * Tests the given method.
	 */
	@Test
	public void testSortCircles() {
		Circle c1 = new Circle(10);
		Circle c2 = new Circle(18.4);
		Circle c3 = new Circle(15.3);
		Circle c4 = new Circle(8.25);
		Circle c5 = new Circle(0.9);
		Circle[] circles = new Circle[] { c1, c2, c3, c4, c5 };
		System.out.println("Before sorting circles: ");
		printArray(circles);

		// TODO: Nothing to change here. But note,
		// there is only one way to sort circles, since sorting by
		// radius, perimeter, or area will always give the same results.
		// To make this code run without crashing, make the Circle class
		// Comparable (that is, change the Circle class)
		System.out.println("After sorting circles: ");
		Arrays.sort(circles);
		printArray(circles);
		assertEquals(circles[0], c5);
		assertEquals(circles[1], c4);
		assertEquals(circles[2], c1);
		assertEquals(circles[3], c3);
		assertEquals(circles[4], c2);
		points += 2;
	}

	/**
	 * Tests the given method.
	 */
	@Test
	public void testSortTrianglesByPerimeter() {
		Triangle t1 = new Triangle(3, 4, 5);
		Triangle t2 = new Triangle(6, 6, 1);
		Triangle t3 = new Triangle(4.5, 4.5, 4.5);
		Triangle t4 = new Triangle(1, 1, 1.5);
		Triangle t5 = new Triangle(12, 5, 13);
		Triangle[] triangles = new Triangle[] { t1, t2, t3, t4, t5 };
		System.out.println("Before sorting triangles: ");
		printArray(triangles);

		System.out.println("After sorting triangles by perimeter: ");
		// TODO: Nothing to write here. Just study this example. Note that we
		// can sort triangles by perimeter or area, so it isn't enough to make
		// triangles implement Comparable. For maximum flexibility, we write and
		// pass Comparator objects to the sort() method to compare triangles by
		// perimeter and by area.
		Comparator<Triangle> byPerimeter = new Comparator<Triangle>() {
			@Override
			public int compare(Triangle first, Triangle second) {
				return (int) Math
						.signum(first.perimeter() - second.perimeter());
			}
		};
		Arrays.sort(triangles, byPerimeter);
		printArray(triangles);
		assertEquals(triangles[0], t4);
		assertEquals(triangles[1], t1);
		assertEquals(triangles[2], t2);
		assertEquals(triangles[3], t3);
		assertEquals(triangles[4], t5);
	}

	/**
	 * Tests the given method.
	 */
	@Test
	public void testSortTrianglesByArea() {
		Triangle t1 = new Triangle(3, 4, 5);
		Triangle t2 = new Triangle(6, 6, 1);
		Triangle t3 = new Triangle(4.5, 4.5, 4.5);
		Triangle t4 = new Triangle(1, 1, 1.5);
		Triangle t5 = new Triangle(12, 5, 13);
		Triangle[] triangles = new Triangle[] { t1, t2, t3, t4, t5 };
		System.out.println("Before sorting triangles: ");
		printArray(triangles);

		System.out.println("After sorting triangles by area: ");
		// TODO: Create and add a comparator to compare triangles by area,
		// so we can sort by area. Then pass it as a second argument to the
		// sort method.
		Arrays.sort(triangles);
		printArray(triangles);
		assertEquals(triangles[0], t4);
		assertEquals(triangles[1], t2);
		assertEquals(triangles[2], t1);
		assertEquals(triangles[3], t3);
		assertEquals(triangles[4], t5);
		points += 4;
	}

	/**
	 * Tests the given method.
	 */
	@Test
	public void testSortTrianglesByAreaUsingTreeSet() {
		Triangle t1 = new Triangle(3, 4, 5);
		Triangle t2 = new Triangle(6, 6, 1);
		Triangle t3 = new Triangle(4.5, 4.5, 4.5);
		Triangle t4 = new Triangle(1, 1, 1.5);
		Triangle t5 = new Triangle(12, 5, 13);
		Triangle[] triangles = new Triangle[] { t1, t2, t3, t4, t5 };
		System.out.println("Before sorting triangles: ");
		printArray(triangles);
		System.out.println("Sort by area using a TreeSet: ");

		// You can sort the triangles without calling sort directly.
		// The TreeSet data structure keeps its data sorted as you insert it.
		// (We'll write a simple version of TreeSet in a later assignment.)
		// The purpose of this part is to get experience with TreeSets, their
		// methods, and iterating with foreach.
		// Do this as follows:
		// TODO: 1 Modify the next line to create a new TreeSet - use the version
		// of the constructor that takes a
		// comparator, and pass the same comparator you wrote for the last test.
		TreeSet<Triangle> triangleSet = null;

		// TODO: 2 Iterate through the unsorted triangles array, adding the
		// triangles
		// to the TreeSet.

		// TODO: 3 Iterate through the TreeSet using a foreach loop (Java's
		// "enhanced" for loop) and output them. For an example of the foreach
		// loop, see the assert tests later in this test.
		// Format this like the printArray function. But since you don't have an
		// array index to check for the last element, I'll let you keep the
		// extra comma+space.
		// Challenge: You could get rid of the last character though: remember a
		// nice string method to do this?

		
		
		// Note: the next line also prints the triangle set nicely - go,
		// built-in methods! Compare with your results.
		// System.out.println(triangleSet);

		// These tests use a foreach loop to traverse the tree set
		int i = 0; // Counter needed for the array, not the set.
		Triangle[] expectedOrder = new Triangle[] { t4, t2, t1, t3, t5 };

		// Read this loop as: "for each triangle t in the triangle set..."
		for (Triangle t : triangleSet) {
			assertEquals(expectedOrder[i], t);
			i++;
		}
		// Thanks for continuing to read. The foreach loop is just shorthand for using an iterator:
		i = 0;
		for (Iterator<Triangle> iter = triangleSet.iterator(); iter.hasNext(); ) {
			Triangle t = iter.next();
			assertEquals(expectedOrder[i], t);
			i++;
		}
		points += 4;
	}

	@AfterClass
	public static void showPoints() {
		System.out.printf("COMPARING SHAPES POINTS = %d of 10\n", points);
	}

	private static void printArray(Object[] array) {
		// StringBuilders work like growable arrays, doubling capacity as they
		// get full. They are more efficient that Strings, which need to be
		// reallocated freshly each time you append to them.
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]); // calls toString
			if (i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]\n");
		System.out.println(sb.toString());
	}
}
