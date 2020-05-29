package queues;

import java.util.NoSuchElementException;


public interface SimpleQueue<T> {
	/**
	 * Removes all the items from this queue and everything back to the initial
	 * state, including setting its capacity back to the initial capacity.
	 */
	public void clear();

	/**
	 * Adds the given item to the tail of the queue, resizing the internal array
	 * if needed.
	 * 
	 * @param item
	 */
	public void enqueue(T item);

	/**
	 * Removes and returns the item at the head of the queue.
	 * 
	 * @return The item at the head of the queue.
	 * @throws NoSuchElementException
	 *             If the queue is empty.
	 */
	public T dequeue() throws NoSuchElementException;

	/**
	 * Returns the item at the head of the queue without mutating the queue.
	 * 
	 * @return The item at the head of the queue.
	 * @throws NoSuchElementException
	 *             If the queue is empty.
	 */
	public T peek() throws NoSuchElementException;

	/**
	 * @return True if and only if the queue contains no items.
	 */
	public boolean isEmpty();

	/**
	 * @return The number of items in this queue.
	 */
	public int size();

	/**
	 * Searches for the given item in this queue.
	 * 
	 * @param item
	 * @return True if the item is contained within the queue.
	 */
	public boolean contains(T item);

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, regardless of the implementation. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString();

	/**
	 * Displays the contents of the queue in a raw format that will depend on the implementation. 
	 * This is for your debugging purposes.
	 */
	public String debugString();
	
	
	
	
}
