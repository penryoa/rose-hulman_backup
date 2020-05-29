package paperPart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Samples of data structure use.
 *
 * @author Curt Clifton.
 *         Created Nov 14, 2009.
 */
public class DataStructureUse {

	/**
	 * Starts.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		stackSample();
		System.out.println();
		System.out.println("-----------------------");
		mapSample();
		System.out.println();
		System.out.println("-----------------------");
		mapAndSetSample();
		System.out.println();
	}

	private static void stackSample() {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.print(s.pop());
		System.out.print(s.pop());
		s.push(4);
		System.out.print(s.pop());
		s.push(5);
		System.out.print(s.pop());
		System.out.print(s.pop());
	}

	private static void mapSample() {
		HashMap<Character,String> map = 
			new HashMap<Character, String>();
		map.put('a', "Turkey");
		map.put('b', "Pumpkin Pie");
		map.put('a', "Tofurkey");
		System.out.print(map.get('b'));
		System.out.print(map.get('a'));
	}

	private static void mapAndSetSample() {
		HashMap<String, HashSet<Integer>> map = 
			new HashMap<String, HashSet<Integer>>();
		map.put("Turkey", new HashSet<Integer>());
		map.put("Dressing", new HashSet<Integer>());
		map.get("Turkey").add(10);
		map.get("Turkey").add(20);
		map.get("Turkey").add(30);
		map.get("Dressing").add(40);
		map.get("Dressing").add(40);
		map.get("Dressing").add(50);
		map.put("Turkey", new HashSet<Integer>());
		System.out.print(map.get("Turkey"));
		System.out.print(map.get("Dressing"));
	}

}
