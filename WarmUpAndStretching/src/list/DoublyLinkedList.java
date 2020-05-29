package list;

import java.util.NoSuchElementException;

/**
 * This is a collection that stores measurable objects and can answer some basic
 * statistical queries.
 * 
 * @author Curt Clifton.
 * @param <E>
 *            the type of elements in the list
 */
public class DoublyLinkedList<E extends Comparable<E>> {
	/**
	 * This class represents a node in the list.
	 */
	protected abstract class Node {
		/**
		 * The element stored in this list node.
		 */
		E data;
		/**
		 * The next node in this list.
		 */
		Node next;
		/**
		 * The previous node in this list.
		 */
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
	}

	private class InternalNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			sb.append(this.data);
			sb.append(',');
			this.next.buildString(sb);
		}
	}

	private class HeadNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			this.next.buildString(sb);
		}
	}

	private class TailNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			// Nothing to do here. The string representation is completed.
		}
	}

	/**
	 * The (dummy) head node of this list. Does not contain data. The first node
	 * with data can be found at this.head.next, unless the list is empty. If
	 * the list is empty then this.head.next == this.tail.
	 */
	protected Node head;

	/**
	 * The (dummy) tail node of this list. Does not contain data. The last node
	 * with data can be found at this.tail.prev, unless the list is empty. If
	 * the list is empty then this.tail.prev == this.head.
	 */
	protected Node tail;

	/**
	 * Constructs a new, empty list. The empty list consists of head and tail
	 * nodes pointing at each other.
	 */
	public DoublyLinkedList() {
		this.head = new HeadNode();
		this.tail = new TailNode();

		this.head.next = this.tail;
		this.tail.prev = this.head;
	}

	/**
	 * Adds the given element to this list.
	 * 
	 * @param element
	 */
	public void add(E element) {
		this.addFirst(element);
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
	 * Removes the first element from this list.
	 * 
	 * @return the first element of this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	public E removeFirst() throws NoSuchElementException {
		confirmHasElements();
		return this.head.next.remove();
	}

	/**
	 * Removes the last element from this list.
	 * 
	 * @return the last element of this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	public E removeLast() throws NoSuchElementException {
		confirmHasElements();
		return this.tail.prev.remove();
	}

	/**
	 * @throws NoSuchElementException
	 *             if this list is empty
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
	
	/**
	 * @return a new iterator over this list.
	 */
	public DLLIterator<E> iterator() {
		final Node initialHead = this.head;
		return new DLLIterator<E>() {
			private Node current = initialHead;

			public boolean hasNext() {
				return this.current.next != DoublyLinkedList.this.tail;
			}

			public E next() throws NoSuchElementException {
				if (!hasNext()) 
					throw new NoSuchElementException("That's all, folks.");
				this.current = this.current.next;
				return this.current.data;
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		this.head.buildString(sb);
		if (this.head.next != this.tail)
			sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		return sb.toString();
	}
}
