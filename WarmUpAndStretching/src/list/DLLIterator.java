package list;

import java.util.NoSuchElementException;

/**
 * This is a basic iterator type for our doubly linked list class.
 * 
 * @author Curt Clifton
 * @param <E>
 *            the type of elements in the list
 */
public interface DLLIterator<E> {
	/**
	 * @return true iff the list has more elements
	 */
	boolean hasNext();

	/**
	 * Returns the next element in the list, if one exists.
	 * 
	 * @return the next element in the list
	 * @throws NoSuchElementException
	 *             if !this.hasNext()
	 */
	E next() throws NoSuchElementException;
}
