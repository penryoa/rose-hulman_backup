package list;

/**
 * This is the type of a function object for matching items in a list.
 * 
 * @param <E> the type of elements in the list
 */
public interface ListItemMatcher<E extends Comparable<E>> {
	/**
	 * @param element the element to be tested
	 * @return true if the given element is a match
	 */
	boolean isMatch(E element);
}
