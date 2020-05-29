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

		// FIXME: remove for template
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

		/**
		 * Returns the smallest element in the rest of the list, or the given
		 * minimum element if it is smaller.
		 * 
		 * @param minSoFar
		 * @return the smallest element
		 */
		abstract E getMin(E minSoFar);

		/**
		 * Returns the largest element in the rest of the list, or the given
		 * maximum element if it is larger.
		 * 
		 * @param maxSoFar
		 * @return the largest element
		 */
		abstract E getMax(E maxSoFar);

		// FIXME: remove for template
		/**
		 * Adds the element to maintain sorted order.
		 *
		 * @param element
		 */
		abstract void addInOrder(E element);
	}

	private class InternalNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			sb.append(this.data);
			sb.append(',');
			this.next.buildString(sb);
		}

		@Override
		E getMin(E minSoFar) {
			if (this.data.compareTo(minSoFar) < 0) {
				return this.next.getMin(this.data);
			} else {
				return this.next.getMin(minSoFar);
			}
		}

		@Override
		E getMax(E maxSoFar) {
			if (this.data.compareTo(maxSoFar) > 0) {
				return this.next.getMax(this.data);
			} else {
				return this.next.getMax(maxSoFar);
			}
		}

		// FIXME: remove for template
		@Override
		void addInOrder(E element) {
			if (element.compareTo(this.data) < 0) {
				this.prev.addAfter(element);
			} else {
				this.next.addInOrder(element);
			}
		}
	}

	private class HeadNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			this.next.buildString(sb);
		}

		@Override
		E getMin(E minSoFar) {
			return this.next.getMin(minSoFar);
		}

		@Override
		E getMax(E maxSoFar) {
			return this.next.getMax(maxSoFar);
		}

		// FIXME: remove for template
		@Override
		void addInOrder(E element) {
			this.next.addInOrder(element);
		}
	}

	private class TailNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			// Nothing to do here. The string representation is completed.
		}

		@Override
		E getMin(E minSoFar) {
			return minSoFar;
		}

		@Override
		E getMax(E maxSoFar) {
			return maxSoFar;
		}

		// FIXME: remove for template
		@Override
		void addInOrder(E element) {
			this.prev.addAfter(element);
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
	 * Returns a NEW doubly linked list consisting of the elements of this list
	 * in reverse order. Leaves the original list unchanged.
	 * 
	 * @return a new, reversed list
	 */
	public DoublyLinkedList<E> reverse() {
		// TODO: implement reverse()
		// return this;
		DoublyLinkedList<E> result = new DoublyLinkedList<E>();
		Node current = this.head.next;
		while (current != this.tail) {
			result.addFirst(current.data);
			current = current.next;
		}
		return result;
	}

	/**
	 * Removes the first element from this list.
	 * 
	 * @return the first element of this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	public E removeFirst() throws NoSuchElementException {
		// TODO: implement removeFirst(), For full credit you must add and use a
		// "remove()" method in the Node inner class.
		// return null;
		confirmHasElements();
		return this.head.next.remove();
	}

	/**
	 * Adds the given element to the list in the first position such that the
	 * subsequent list element, if any, is larger than the new one and all
	 * precedign list elements are smaller than the new one. If the list is
	 * originally empty, then this is equivalent to calling addFirst(element).
	 * 
	 * If all additions to a list are made using addInOrder(), then the
	 * resulting list will be sorted in ascending order.
	 * 
	 * @param element
	 *            to be added to the list
	 */
	public void addInOrder(E element) {
		// TODO: implement addInOrder()
		this.head.addInOrder(element);
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

	/**
	 * Returns the smallest element in this list. Requires !this.isEmpty().
	 * 
	 * @return the smallest element in this list
	 */
	public E min() {
		confirmHasElements();
		E minSoFar = this.head.next.data;
		return this.head.getMin(minSoFar);
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

	/**
	 * Counts the number of items in this list that are matched by the given
	 * matcher.
	 *
	 * @param matcher
	 * @return the count
	 */
	public int countMatches(ListItemMatcher<E> matcher) {
		int count = 0;
		Node current = this.head.next;
		while (current != this.tail) {
			if (matcher.isMatch(current.data)) {
				count++;
			}
			current = current.next;
		}
		return count;
	}
}
