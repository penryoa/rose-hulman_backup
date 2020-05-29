import java.util.Iterator;

/**
 * A simplified version of the java.util.Set interface.
 * 
 * @author Matt Boutell. Created Sep 28, 2013.
 * 
 * @param <E>
 *            Generic type.
 */
public interface SimpleSet<E> extends Iterable<E> {

	/**
	 * Adds this element to the set.
	 * 
	 * @param element
	 * @return true if and only if the element was successfully added to this
	 *         Set.
	 */
	public boolean add(E element);

	/**
	 * Adds all the elements from the other Set to this Set.
	 * You may assume each Set is non-empty.
	 * 
	 * @param otherSet
	 */
	public void addAll(SimpleSet<E> otherSet);

	/**
	 * Empties the given set.
	 */
	public void clear();

	/**
	 * @param element
	 * @return True if and only if the given element is in this Set.
	 */
	public boolean contains(E element);

	/**
	 * Returns true if this set contains all of the elements of the other set. 
	 * @param other
	 * @return true if this set contains all of the elements of the other set.
	 */
	public boolean containsAll(SimpleSet<E> other);

	/**
	 * Compares this Set with the other Set.
	 *
	 * @param other
	 * @return true if the given sets have the same elements (although the order can be different).
	 */
	@Override
	public boolean equals(Object o);

	/**
	 * @return True if and only if this Set contains no elements.
	 */
	public boolean isEmpty();

	/**
	 * Returns an iterator over the elements in this set, in no particular
	 * order.
	 * 
	 * @return an iterator over the elements in this set.
	 */
	@Override
	public Iterator<E> iterator();

	/**
	 * Removes the element from this Set if it is there.
	 * 
	 * @param element
	 * 
	 * @return True if and only if the given element was successfully removed.
	 */
	public boolean remove(E element);

	/**
	 * @return The number of elements in this Set.
	 */
	public int size();

	/**
	 * A string representation of this Set, separated by commas and spaces and
	 * contained in curly braces {}, as shown in the Tests.
	 * 
	 * @return A string representation of this Set, as described.
	 */
	@Override
	public String toString();

	/**
	 * Returns an array containing all of the elements in this set, in any
	 * order. Assumes this array isn't empty.
	 * 
	 * @return an array containing all of the elements in this set.
	 */
	public E[] toArray();
}
