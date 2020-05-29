package priorityQueue;

import java.util.ArrayList;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<TODO: Your name here>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;
	private int numItems;

	public ArrayListMinPQ() {
		// DONE: implement
		this.items = new ArrayList<T>();
		this.numItems = items.size();
	}

	public T findMin() {
		// This is also known as peekMin
		// DONE: implement
		if (this.isEmpty())
			return null;

		T min = this.items.get(0);
		for (T item : this.items) {
			if (item.compareTo(min) < 0) {
				min = item;
			}
		}
		return min;
	}

	public T deleteMin() {
		// DONE: implement

		T t = this.findMin();
		if (t != null) {
			this.items.remove(this.findMin());
			this.numItems--;
		}
		return t;
	}

	public void insert(T item) {
		// DONE: implement
		this.items.add(item);
		this.numItems++;
	}

	public int size() {
		// DONE: implement
		return this.numItems;
	}

	public boolean isEmpty() {
		// DONE: implement
		return this.numItems == 0;
	}

	public void clear() {
		// DONE: implement
		this.numItems = 0; 
	}
}
