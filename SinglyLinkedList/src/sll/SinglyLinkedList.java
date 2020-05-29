package sll;

/**
 * This class provides a basic implementation of a singly linked list. It's
 * motivated by the implementation in Big Java, 5e, ch. 16.1.
 * 
 * @author DONE <Olivia Penry> on <1/31/2018>.
 *         Worked with Anna Thompson
 */
public class SinglyLinkedList implements ILinkedList {

	/**
	 * Note that in addition to a head (first) pointer this list contains a last
	 * pointer. Be sure to BOTH first and last up to date in all your code.
	 */
	private ListNode first;
	private ListNode last;

	/**
	 * It seems silly to write a getter for a public field, but this is needed
	 * for the test code.
	 */
	@Override
	public IListNode getFirst() {
		return first;
	}

	@Override
	public IListNode getLast() {
		return last;
	}

	@Override
	public void setFirst(IListNode first) {
		this.first = (ListNode) first;
	}

	@Override
	public void setLast(IListNode last) {
		this.last = (ListNode) last;
	}

	/**
	 * Constructs a new, empty linked list.
	 */
	public SinglyLinkedList() {
		this.first = null;
		this.last = null;
	}

	@Override
	public String toString() {
		if (this.first == null) {
			return "[]";
		}
		String result = "[";
		ListNode current = this.first;
		while (current != this.last) {
			result += (current.element + ", ");
			current = current.next;
		}
		result += current.element + "] first=[" + this.first.getElement() + "] last=[" + this.last.getElement() + "]";
		return result;
	}

	/**
	 * WARNING: add(Integer element) must be completed before OTHER tests can
	 * pass!
	 * 
	 * Make sure to complete this method FIRST before trying the other ones.
	 * 
	 * This method should add the given element to the end of this list.
	 */
	@Override
	public void add(Integer element) {
		// DONE 01 Implement the add(Integer element) method.
		if (first == null) {
			first = new ListNode(element);
			last = first;
		} else if (first == last) {
			first.next = new ListNode(element);
			last = first.next;
		} else {
			ListNode newNode = new ListNode(element);
			last.next = newNode;
			last = last.next;
		}

	}

	/**
	 * WARNING: add(Integer element) must be completed before this test can
	 * pass!
	 * 
	 * This method should return the number of elements in the list.
	 */
	@Override
	public Integer size() {
		// DONE 02 Implement the size() method.
		return sizeHelper(this);

	}

	public int sizeHelper(SinglyLinkedList list) {
		if (list.first == null) {
			return 0;
		} else if (list.first == list.last) {
			return 1;
		} else {
			SinglyLinkedList allButFirst = new SinglyLinkedList();
			ListNode current = list.first;
			while (current != null) {
				if (current.next == null) {
					break;
				}
				allButFirst.add(current.element);
				current = current.next;
			}
			return 1 + sizeHelper(allButFirst);
		}
	}

	/**
	 * This method should insert the given element at the given index in the
	 * list. This DOES NOT replace the element at an index, but inserts an
	 * element.
	 * 
	 * If you are passed an invalid index you should throw an
	 * IndexOutOfBoundsException.
	 * 
	 * Note that this inserts a value at the specific index NOT a value. Please
	 * also note that it is LEGAL to insert at the END of the list.
	 * 
	 * Examples: [2, 6] -> insertAtIndex(1, 7) -> [2, 7, 6] [2, 7, 6] ->
	 * insertAtIndex(0, 8) -> [8, 2, 7, 6] [8, 2, 7, 6] -> insertAtIndex(4, 9)->
	 * [8, 2, 7, 6, 9] ^ ^ ^ ^ ^ | | | | | 0 1 2 3 4 <- indices
	 * 
	 */
	@Override
	public void insertAtIndex(int index, Integer element) throws IndexOutOfBoundsException {
		// DONE 03 Implement the insertAtIndex(int index, int value) method.

		ListNode newNode = new ListNode(element);

		if (index > this.size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if (this.first == null || index == this.size()) {
			this.add(element);
			return;

		} else if (this.first == this.last) {
			if (index == 0) {
				this.last = this.first;
				this.first = newNode;
				newNode.next = this.last;
			} else {
				this.first.next = newNode;
				this.last = this.first.next;
			}

		} else {
			if (index == 0) {
				newNode.next = this.first;
				this.first = newNode;
			} else {
				insertAtIndexHelper(index, 0, element, this.first, null);
				resetLast(this.first);
			}
		}

	}

	public void insertAtIndexHelper(int index, int currentIndex, Integer element, ListNode current, ListNode previous) {

		try {

			if (currentIndex == index) {
				ListNode temp = new ListNode(element);
				previous.next = temp;
				temp.next = current;
				return;
			}

			insertAtIndexHelper(index, currentIndex + 1, element, current.next, current);

		} catch (IndexOutOfBoundsException e) {
			throw e;
		}

	}

	public int getIndexOfLast(ListNode current, int count) {
		if (current.next == null) {
			return count;
		} else {
			return getIndexOfLast(current.next, count + 1);
		}
	}

	public void resetLast(ListNode current) {
		if (current.next == null) {
			this.last = current;
		} else {
			resetLast(current.next);
		}
	}

	/**
	 * This method should return true if the list contains the given element and
	 * false if it does not.
	 * 
	 */
	@Override
	public boolean contains(Integer element) {
		// DONE 04 Implement the contains(int x) method.

		if (first == null) {
			return false;
		} else if (first == last) {
			if (first.element == element) {
				return true;
			} else {
				return false;
			}
		} else {
			return containsHelper(element, this.first);
		}
	}

	public boolean containsHelper(Integer element, ListNode node) {

		if (node.element == element) {
			return true;
		} else {
			if (node.next != null) {
				node = node.next;
				return containsHelper(element, node);
			}
		}
		return false;
	}

	/**
	 * This method should attempt to remove the FIRST occurrence of the given
	 * element in the list.
	 * 
	 * If the element is found and removed, return true otherwise if the element
	 * is not found, then return false.
	 */
	@Override
	public boolean remove(Integer element) {
		// DONE 05 Implement the remove(Integer element) method

		if (this.first == null) {
			return false;

		} else if (this.first == this.last) {
			if (this.first.element == element) {
				this.first = null;
				this.last = null;
				return true;
			} else {
				return false;
			}

		} else {
			if (this.first.element == element) {
				this.first = this.first.next;
				return true;
			} else {
				boolean b = removeHelper(element, this.first, this.first.next);
				this.resetLast(this.first);
				return b;
			}
		}
	}

	public boolean removeHelper(Integer element, ListNode previousNode, ListNode currentNode) {

		if (currentNode == null) {
			return false;
		}
		if (currentNode.element == element) {
			previousNode.next = currentNode.next;
			currentNode.next = null;
			return true;
		} else {
			return removeHelper(element, currentNode, currentNode.next);
		}
	}

	/**
	 * This method should return the index of the FIRST occurrence of the given
	 * element, OR -1 if the element is not found in the list.
	 * 
	 */
	@Override
	public int indexOf(Integer element) {
		// DONE 06 Implement the indexOf(int x) method.
		if (this.first == null) {
			return -1;
		} else if (this.first == this.last) {
			if (this.first.element == element) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return indexOfHelper(element, this.first, 0);
		}
	}

	public int indexOfHelper(Integer element, ListNode currentNode, int index) {
		if (currentNode == null) {
			return -1;
		}
		if (currentNode.element == element) {
			return index;
		} else {
			return indexOfHelper(element, currentNode.next, index + 1);
		}
	}

	/**
	 * Replaces the element at the given index with the new element. RETURNS the
	 * OLD element.
	 * 
	 * The method should throw an IndexOutOfBoundsException if an invalid index
	 * is provided.
	 */
	@Override
	public Integer set(int index, Integer element) throws IndexOutOfBoundsException {
		// DONE 07 Implement the set(int index, int element) method.
		if (this.size() == 0 || index > this.size() || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return setHelper(index, 0, this.first, element);
	}

	public Integer setHelper(int index, int currentIndex, ListNode currentNode, Integer element) {

		if (currentIndex == index) {
			int e = currentNode.element;
			currentNode.element = element;
			return e;
		} else {
			return setHelper(index, currentIndex + 1, currentNode.next, element);
		}
	}
}
