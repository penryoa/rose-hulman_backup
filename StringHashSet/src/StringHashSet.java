import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * A hash set implementation for Strings. Cannot insert null into the set. Other
 * requirements are given with each method.
 *
 * @author Matt Boutell and <<TODO: Olivia Penry >>>. Created Oct 6, 2014.
 */
public class StringHashSet {

	// The initial size of the internal array.
	private static final int DEFAULT_CAPACITY = 5;
	final Node NULL_NODE = new Node();
	int capacity;
	int size;
	Node[] arr;

	// You'll want fields for the size (number of elements) and the internal
	// array of Nodes. I also added one for the capacity (the length
	// of the internal array).

	private class Node {
		// TODO: Implement this class . These are just linked-list style
		// nodes, so you will need at least fields for the data and a reference
		// to the next node, plus a constructor. You can choose to use a
		// NULL_NODE at the end, or not. I chose not to do so this time.
		String data;
		Node next;

		public Node() {
			this.data = null;
			this.next = null;
		}

		public Node(String data) {
			this.data = data;
			this.next = NULL_NODE;
		}

		public Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}

		public boolean hasNext() {
			return this.next != NULL_NODE;
		}

		public boolean isNullNode() {
			return this.data == null;
		}
	}

	/**
	 * Creates a Hash Set with the default capacity.
	 */
	public StringHashSet() {
		// Recall that using this as a method calls another constructor
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates a Hash Set with the given capacity.
	 */
	public StringHashSet(int initialCapacity) {
		initialize(initialCapacity);
	}

	private void initialize(int initialCapacity) {
		// TODO: Set the capacity to the given capacity, and initialize the
		// other fields.
		// Why did we pull this out into a separate method? Perhaps another
		// method needs to initialize the hash set as well? (Hint)
		this.arr = new Node[initialCapacity];
		this.capacity = initialCapacity;
		for (int i = 0; i < initialCapacity; i++) {
			arr[i] = NULL_NODE;
		}
		this.size = 0;
	}

	/**
	 * Calculates the hash code for Strings, using the x=31*x + y pattern.
	 * Follow the specification in the String.hashCode() method in the Java API.
	 * Note that the hashcode can overflow the max integer, so it can be
	 * negative. Later, in another method, you'll need to account for a negative
	 * hashcode by adding Integer.MAX_VALUE before you mod by the capacity
	 * (table size) to get the index.
	 *
	 * This method is NOT the place to calculate the index though.
	 *
	 * @param item
	 * @return The hash code for this String
	 */
	public static int stringHashCode(String item) {
		int L = item.length();
		int code = 0;
		for (int i = 0; i < L; i++) {
			code = code * 31 + item.charAt(i);
		}
		return code;
	}

	/**
	 * Adds a new node if it is not there already. If there is a collision, then
	 * add a new node to the -front- of the linked list.
	 * 
	 * Must operate in amortized O(1) time, assuming a good hashcode function.
	 *
	 * If the number of nodes in the hash table would be over double the
	 * capacity (that is, lambda > 2) as a result of adding this item, then
	 * first double the capacity and then rehash all the current items into the
	 * double-size table.
	 *
	 * @param item
	 * @return true if the item was successfully added (that is, if that hash
	 *         table was modified as a result of this call), false otherwise.
	 */
	public boolean add(String item) {
		if ((this.size + 1) / this.capacity >= 2) {
			resize();
		}
		int index = getIndex(item);
		System.out.printf("Item: %s Index: %d\n", item, index);
		Node newNode = new Node(item);
		Node n = arr[index];
		// System.out.printf("Node Index: %d\n", index);
		if (n.isNullNode()) {
			arr[index] = newNode;
		} else {
			if (n.data == item)
				return false;
			while (n.hasNext()) {
				if (n.data == item) {
					return false;
				}
				n = n.next;
			}
			n.next = newNode;
		}
		this.size++;
		return true;
	}

	/**
	 * Prints an array value on each line. Each line will be an array index
	 * followed by a colon and a list of Node data values, ending in null. For
	 * example, inserting the strings in the testAddSmallCollisions example
	 * gives "0: shalom hola null 1 bonjour null 2 caio hello null 3 null 4 hi
	 * null". Use a StringBuilder, so you can build the string in O(n) time.
	 * (Repeatedly concatenating n strings onto a growing string gives O(n^2)
	 * time)
	 * 
	 * @return A slightly-formatted string, mostly used for debugging
	 */
	public String toRawString() {
		String newline = System.getProperty("line.separator");
		String s = "";
		for (int i = 0; i < capacity; i++) {
			if (arr[i] == NULL_NODE) {
				if (i == capacity - 1) {
					s += i + ": null";
				} else {
					s += i + ": null" + newline;
				}
			} else {
				s += i + ":";
				Node n = arr[i];
				while (!n.isNullNode()) {
					s += " " + arr[i].data;
					n = n.next;
				}
				if (i == capacity - 1) {
					s += " null";
				} else {
					s += " null" + newline;
				}
			}
		}
		return s;
	}

	// public String toRawHelper(int i) {
	//
	// if (i >= capacity) {
	// return "";
	// } else if (arr[i] == NULL_NODE) {
	// return "" + i + ": null" + newline + toRawHelper(i + 1);
	// } else {
	// String s = "" + i + ": ";
	// Node n = arr[i];
	// while (!n.isNullNode()) {
	// s += "" + arr[i].data;
	// n = n.next;
	// }
	// if (i == capacity - 1) {
	// return s + " null";
	// } else {
	// return s + " null" + newline + toRawHelper(i + 1);
	// }
	// }
	// }

	/**
	 * 
	 * Checks if the given item is in the hash table.
	 * 
	 * Must operate in O(1) time, assuming a good hashcode function.
	 *
	 * @param item
	 * @return True if and only if the item is in the hash table.
	 */
	public boolean contains(String item) {
		int index = getIndex(item);
		for (Node n : arr) {
			if (n.data == item) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of items added to the hash table. Must operate in O(1)
	 * time.
	 *
	 * @return The number of items in the hash table.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * @return True iff the hash table contains no items.
	 */
	public boolean isEmpty() {
		return (this.size == 0);
	}

	/**
	 * Removes all the items from the hash table. Resets the capacity to the
	 * DEFAULT_CAPACITY
	 */
	public void clear() {
		initialize(DEFAULT_CAPACITY);
	}

	/**
	 * Removes the given item from the hash table if it is there. You do NOT
	 * need to resize down if the load factor decreases below the threshold.
	 * 
	 * @param item
	 * @return True If the item was in the hash table (or equivalently, if the
	 *         table changed as a result).
	 */
	public boolean remove(String item) {
		int index = getIndex(item);
		// Case 1: There are no nodes at that index
		if (arr[index] == NULL_NODE) {
			return false;
		} else {
			Node n = arr[index];
			// Case 2: only one element and it is/isn't item
			if (!n.hasNext() && n.data == item) {
				arr[index] = NULL_NODE;
				return true;
			} 
			
			// Case 3: more than one element and it is/isn't item
			while (n.hasNext()) {
				if (n.next.data == item) {
					n.next = n.next.next;
					return true;
				}
				n = n.next;
			}
			
		}
		return false;
	}

	/**
	 * Adds all the items from the given collection to the hash table.
	 *
	 * @param collection
	 * @return True if the hash table is modified in any way.
	 */
	public boolean addAll(Collection<String> collection) {
		
		return true;
	}

	/**
	 * 
	 * Challenge Feature: Returns an iterator over the set. Return the items in
	 * any order that you can do efficiently. Should throw a
	 * NoSuchElementException if there are no more items and next() is called.
	 * Should throw a ConcurrentModificationException if next() is called and
	 * the set has been mutated since the iterator was created.
	 *
	 * @return an iterator.
	 */
	public Iterator<String> iterator() {
		return null;
	}

	// Challenge Feature: If you have an iterator, this is easy. Use a
	// StringBuilder, so you can build the string in O(n) time. (Repeatedly
	// concatenating n strings onto a string gives O(n^2) time)
	// Format it like any other Collection's toString (like [a, b, c])
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0; i < capacity; i++) {
			if (arr[i] != NULL_NODE) {
				Node n = arr[i];
				while (!n.isNullNode()) {
					s += "" + n.data + ", ";
					n = n.next;
				}
			}
		}
		// return s + "]";
		return s.substring(0, s.length() - 2) + "]";

	}

	// ========== My methods ==================
	public int getIndex(String s) {
		// System.out.println("Integer.MAX and code: " + stringHashCode(s) %
		// this.capacity);
		return Math.abs(stringHashCode(s) % this.capacity);
		// return (stringHashCode(s) + Integer.MAX_VALUE) % this.capacity;
	}

	public void resize() {
		Node[] temp = this.arr;
		this.capacity *= 2;
		initialize(capacity);
		for (Node n : temp) {
			int index = getIndex(n.data);
			this.arr[index] = n;
		}
	}
}
