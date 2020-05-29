package list;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This is a collection that stores comparable objects and can answer some basic
 * queries.
 * 
 * @author Curt Clifton.
 * @param <E>
 *            the type of elements in the list
 */
public class DoublyLinkedList<E extends Comparable<E>> {
	private abstract class Node {
		E data;
		Node next;
		Node prev;

		/**
		 * Adds the given element after this node.
		 * 
		 * @param element
		 */
		final void addAfter(E element) {
			InternalNode newNode = new InternalNode();
			newNode.data = element;
			newNode.next = this.next;
			newNode.prev = this;
			this.next.prev = newNode;
			this.next = newNode;
		}

		/**
		 * Removes and returns the element in this node.
		 * 
		 * @return the element
		 */
		public E remove() {
			E result = this.data;
			this.next.prev = this.prev;
			this.prev.next = this.next;
			return result;
		}

		/**
		 * Builds a string representation of the list from this node to the end
		 * of the list by mutating the given builder.
		 * 
		 * @param sb
		 *            mutated
		 */
		abstract void buildString(StringBuilder sb);

		// FIXME: remove rest of these methods for template
		/**
		 * Adds the rest of this list, in reverse order, to the front of the
		 * given list
		 * 
		 * @param result
		 */
		abstract void buildReverse(DoublyLinkedList<E> result);

		/**
		 * Checks whether the rest of the list after this node is in-order
		 * according to the given comparator.
		 * 
		 * @param comp
		 * @param previousData
		 * @return
		 */
		abstract boolean isInOrder(Comparator<E> comp, E previousData);

		/**
		 * Returns the index of an object equal to object in the rest of this
		 * list.
		 * 
		 * @param object
		 * @param index
		 *            the index of the next internal node
		 * @return
		 */
		abstract int indexOf(E object, int index);
	}

	private class InternalNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			sb.append(this.data);
			sb.append(',');
			this.next.buildString(sb);
		}

		@Override
		void buildReverse(DoublyLinkedList<E> result) {
			result.addFirst(this.data);
			this.next.buildReverse(result);
		}

		@Override
		boolean isInOrder(Comparator<E> comp, E previousData) {
			if (comp.compare(previousData, this.data) > 0) {
				return false;
			}
			return this.next.isInOrder(comp, this.data);
		}

		@Override
		int indexOf(E object, int index) {
			if (this.data.equals(object)) {
				return index;
			} else {
				return this.next.indexOf(object, index + 1);
			}
		}
	}

	private class HeadNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			this.next.buildString(sb);
		}

		@Override
		void buildReverse(DoublyLinkedList<E> result) {
			this.next.buildReverse(result);
		}

		@Override
		boolean isInOrder(Comparator<E> comp, E previousData) {
			return this.next.isInOrder(comp, previousData);
		}

		@Override
		int indexOf(E object, int index) {
			return this.next.indexOf(object, index);
		}
	}

	private class TailNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			int lastIndex = sb.length() - 1;
			if (sb.charAt(lastIndex) == ',') {
				sb.deleteCharAt(lastIndex);
			}
		}

		@Override
		void buildReverse(DoublyLinkedList<E> result) {
			// nothing to do but terminate the recursion
		}

		@Override
		boolean isInOrder(Comparator<E> comp, E previousData) {
			return true;
		}

		@Override
		int indexOf(E object, int index) {
			return -1;
		}
	}

	private Node head;
	private Node tail;

	/**
	 * Constructs a new, empty list.
	 */
	public DoublyLinkedList() {
		this.head = new HeadNode();
		this.tail = new TailNode();

		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Constructs a new list containing the elements from the given array.
	 * 
	 * @param <T>
	 *            the type of the elements
	 * @param array
	 * @return a new list
	 */
	public static <T extends Comparable<T>> DoublyLinkedList<T> fromArray(
			T[] array) {
		DoublyLinkedList<T> result = new DoublyLinkedList<T>();
		for (T t : array) {
			result.addLast(t);
		}
		return result;
	}

	/**
	 * Returns a NEW doubly linked list consisting of the elements of this list
	 * in reverse order. Leaves the original list unchanged.
	 * 
	 * @return a new, reversed list
	 */
	public DoublyLinkedList<E> reverse() {
		// TODO: implement reverse()
		DoublyLinkedList<E> result = new DoublyLinkedList<E>();
		this.head.buildReverse(result);
		return result;
	}

	/**
	 * Checks whether the items in this list are in order according to the given
	 * Comparator object.
	 * 
	 * @param comp
	 *            a function object to compare the elements of this list
	 * 
	 * @return true if and only if this list is sorted according to comp
	 */
	public boolean isInOrder(Comparator<E> comp) {
		// TODO: implement isInOrder()
		if (this.isEmpty()) {
			return true;
		}
		return this.head.isInOrder(comp, this.head.next.data);
	}

	/**
	 * Searches this list for an object equal to the given object. Returns the
	 * index of that object's position in the list. That is, returns 0 if the
	 * given object is equal to the first item in this list, 1 if it is the
	 * second, and so on. Returns -1 if the object doesn't appear in the list.
	 * 
	 * @param object
	 * 
	 * @return the index of the object in the list, or -1 if it doesn't appear
	 */
	public int indexOf(E object) {
		// TODO: implement indexOf(). For full credit you must add and use an
		// appropriate helper method in the Node inner class.
		return this.head.indexOf(object, 0);
	}

	/**
	 * Adds the given element to the head of this list.
	 * 
	 * @param element
	 */
	public void addFirst(E element) {
		this.head.addAfter(element);
	}

	/**
	 * Adds the given element to the tail of this list.
	 * 
	 * @param element
	 */
	public void addLast(E element) {
		this.tail.prev.addAfter(element);
	}

	/**
	 * Removes and returns the head element of the list.
	 * 
	 * @return the head element of the list
	 */
	public E removeFirst() {
		confirmHasElements();
		return this.head.next.remove();
	}

	/**
	 * Removes and returns the tail element of the list.
	 * 
	 * @return the tail element of the list
	 */
	public E removeLast() {
		confirmHasElements();
		return this.tail.prev.remove();
	}

	/**
	 * @throws NoSuchElementException
	 *             if this list is empty/
	 */
	private void confirmHasElements() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("empty list");
	}

	/**
	 * @return true if this list is empty
	 */
	public boolean isEmpty() {
		return this.head.next == this.tail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		this.head.buildString(sb);
		sb.append(']');
		return sb.toString();
	}
}
