import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of the SimpleSet interface that uses an ArrayList. The
 * items are stored in no particular order.
 * 
 * @author <<<Your name here>>>. Created Sep 28, 2013.
 * @param <E>
 *            Generic type.
 */
public class ArrayListSet<E extends Comparable<E>> implements SimpleSet<E> {
	// You many add no other instance variables.
	private ArrayList<E> items;

	// TODO: Reference SimpleSet documentation for javadoc for these methods.
	
	/**
	 * Creates an empty array list.
	 */
	public ArrayListSet() {
		// TODO: Implement constructor
	}

	@Override
	public boolean add(E element) {
		// TODO: implement
		// You may store the elements in whatever order you choose.
		// Efficiency doesn't matter except for addAll().
		return false;
	}

	@Override
	public void addAll(SimpleSet<E> otherSet) {
		// TODO: implement
	}

	@Override
	public void clear() {
		// TODO: implement
	}

	@Override
	public boolean contains(E element) {
		// TODO: implement
		return false;
	}

	@Override
	public boolean containsAll(SimpleSet<E> other) {
		// TODO: implement
		return false;
	}

	@Override
	public boolean equals(Object o) {
		// Ensure that you are comparing with another 
		if (!(o instanceof ArrayListSet)) {
			return false;
		}
		
		ArrayListSet other = (ArrayListSet)o;
		// TODO: complete this. Hint: if you rely on another method you wrote, this will only take a single line.
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO: implement
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO: implement
		return null;
	}

	@Override
	public boolean remove(E element) {
		// TODO: implement
		return false;
	}

	@Override
	public int size() {
		// TODO: implement
		return -1;
	}

	@Override
	public E[] toArray() {
		// I'm giving you part of this,
		// so you won't have to look back at the written assignment
		int size = this.items.size(); 

		// Uses reflection, as shown in WA2, #3 (generic max/min). Assumes this
		// array isn't empty.
		@SuppressWarnings("unchecked")
		E[] result = (E[]) Array
				.newInstance(this.items.get(0).getClass(), size);
		// TODO: fill the array.
		
		return result;
	}

	@Override
	public String toString() {
		// TODO: implement
		return null;
	}
}
