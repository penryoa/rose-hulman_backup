package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and <<<your name here>>>
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue<T> {
	// TODO: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// TODO: The javadoc for overridden methods is in the SimpleQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private Class<T> type;

	private int front;
	private int end;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		front = 0;
		end = -1;
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		// DONE: implement this method

		if (this.array.length == 0) {
			return "[]";
		}

		String s = new String();
		s += "[";

		for (int i = front; i < this.array.length; i++) {
			if (i == end) {
				if (this.array[i] != null) {
					s += this.array[i];
				}
			} else {
				if (this.array[i] != null) {
					s += this.array[i] + ", ";
				}
			}
		}

		for (int i = 0; i < front; i++) {
			if (i == end) {
				if (this.array[i] != null) {
					s += this.array[i];
					break;
				}
			} else {
				if (this.array[i] != null) {
					s += this.array[i] + ", ";
				}
			}
		}
		s += "]";
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		T[] newArray = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.array = newArray;
		front = 0;
	}

	@Override
	public void enqueue(T item) {
		if ((end == this.array.length - 1 && front == 0) || (end + 1 == front && front != 0)) {

			@SuppressWarnings("unchecked")
			T[] tempArray = (T[]) Array.newInstance(this.type, this.array.length * 2);

			int count = 0;

			for (int i = front; i < this.array.length; i++) {
				tempArray[count] = this.array[i];
				count += 1;
			}

			for (int i = 0; i < front; i++) {
				tempArray[count] = this.array[i];
				count += 1;
			}

			front = 0;
			end = this.array.length - 1;
			this.array = tempArray;

		}
		if (end == this.array.length - 1) {
			this.array[0] = item;
			end = 0;
		} else {
			this.array[end + 1] = item;
			end++;
		}
		
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		T item = this.array[front];

		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		this.array[front] = null;

		if (front == this.array.length - 1) {
			front = 0;
		} else {
			front++;
		}

		return item;
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		return this.array[front];
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] != null) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean contains(T item) {
		for (T t : this.array) {
			if (t == item) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String debugString() {
		return null;
	}

}
