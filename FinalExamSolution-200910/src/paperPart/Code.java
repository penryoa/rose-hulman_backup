package paperPart;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * A catch-all for the code on the exam.
 * 
 * @author cclifton. Created Nov 16, 2008.
 */
public class Code {

	/**
	 * Runs tests.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(sumElements(a));

		System.out.print("stackExample: ");
		stackExample();
		System.out.println();

		System.out.print("mapExample1: ");
		mapExample1();
		System.out.println();

		System.out.print("mapExample2: ");
		mapExample2();
		System.out.println();
		
		fooer(10);
	}

	private static void fooer(int N) {
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				foo();
			}
		}
	}

	private static void foo() {
		System.out.print('.');
	}

	private static void mapExample2() {
		HashMap<Character,Set<Integer>> map = 
			new HashMap<Character, Set<Integer>>();

		Set<Integer> one = new TreeSet<Integer>();
		one.add(1);

		Set<Integer> two = new TreeSet<Integer>();
		two.add(2);

		map.put('x', one);
		map.put('y', two);
		map.put('z', one);
		map.get('z').add(3);

		System.out.print(map.get('x'));
		System.out.print(map.get('y'));
		System.out.print(map.get('z'));
	}

	private static void mapExample1() {
		HashMap<Character, String> map = new HashMap<Character, String>();
		map.put('a', "Turkey");
		map.put('b', "Pumpkin Pie");
		map.put('a', "Tofurkey");
		System.out.print(map.get('b'));
		System.out.print(map.get('a'));
	}

	/**
	 * @param arr
	 * @return the sum of the elements in the given array
	 */
	public static int sumElements(int[] arr) {
		return sumElements(arr, 0);
	}

	// Helper method
	private static int sumElements(int[] arr, int i) {
		if (i >= arr.length) {
			return 0;
		}
		return arr[i] + sumElements(arr, i + 1);
	}

	private static void stackExample() {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		System.out.print(s.pop());
		s.push(3);
		System.out.print(s.pop());
		s.push(4);
		s.push(5);
		System.out.print(s.pop());
		System.out.print(s.pop());
		System.out.print(s.pop());

	}

}
