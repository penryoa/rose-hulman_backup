package sll.algorithms;

import sll.ILinkedList;
import sll.IListNode;
import sll.ListNode;
import sll.SinglyLinkedList;

// DONE: complete sll.SinglyLinkedList first, then use it to complete these problems.
// Worked with Anna Thompson

public class Algorithms {
	/**
	 * 
	 * Write a function that takes an array of integers and returns a linked
	 * list. The linked list should contain only the integers in the original
	 * list that begin with the number 1.
	 * 
	 * So arraylistOf1s({123,456,1, 21}) yields [123, 1]
	 */
	public static SinglyLinkedList arraylistOf1s(int[] integers) {
		// DONE: Solve me
		if (integers.length == 0) {
			return new SinglyLinkedList();
		} else {
			SinglyLinkedList newList = new SinglyLinkedList();
			return arrayListOf1sHelper(integers, newList, 0);
		}
	}

	public static SinglyLinkedList arrayListOf1sHelper(int[] integers, SinglyLinkedList currentList, Integer index) {
		if (integers.length <= index) {
			return currentList;
		}

		if (startsWithOne(integers[index])) {
			currentList.add(integers[index]);
		}

		return arrayListOf1sHelper(integers, currentList, index + 1);

	}

	public static boolean startsWithOne(int value) {
		if (value % 10 < 10) {
			if (value == 1 || value % 10 == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return startsWithOne(value / 10);
		}
	}

	/**
	 * Takes a list of numbers in sorted (ascending) order and a number to add.
	 * 
	 * Adds the number in the correct place in the sorted list.
	 * 
	 * so insertIntoSorted([1,5,9],6) yields [1,5,6,9]
	 * 
	 */
	public static void insertIntoSorted(ILinkedList list, int number) {
		// DONE: Solve me
		if (list.size() == 0) {
			list.add(number);
		} else if (list.size() == 1) {
			ListNode newNode = new ListNode(number);
			if (list.getFirst().getElement() >= number) {
				newNode.setNext(list.getFirst());
				list.setLast(list.getFirst());
				list.setFirst(newNode);
			} else {
				newNode.setNext(null);
				list.setLast(newNode);
				list.getFirst().setNext(newNode);
			}
		} else {
			insertIntoSortedHelper(list, number, list.getFirst(), list.getFirst().getNext());
		}
	}

	public static void insertIntoSortedHelper(ILinkedList list, int number, IListNode current, IListNode next) {
		if (number >= list.getLast().getElement()) {
			list.add(number);
			return;
		}

		if (current == null) {
			return;
		}

		if (next.getElement() > number) {
			ListNode newNode = new ListNode(number);
			newNode.setNext(next);
			current.setNext(newNode);
			return;
		} else {
			insertIntoSortedHelper(list, number, current.getNext(), next.getNext());
		}
	}

	/**
	 * Takes a linked list and removes all numbers longer than 3 digits from the
	 * list.
	 * 
	 * So removeLongNumbers([1,1000,3,99999,999]) yields [1, 3, 999]
	 */
	public static void removeLongNumbers(ILinkedList list) {
		// DONE: Solve me

		if (list.size() == 0) {
			return;
		}

		if (list.getFirst().getElement() >= 1000) {
			list.setFirst(list.getFirst().getNext());
		}

		removeLongNumbersHelper(0, list, list.getFirst(), list.getFirst().getNext());
	}

	public static void removeLongNumbersHelper(int index, ILinkedList list, IListNode previousNode,
			IListNode currentNode) {

		if (index >= list.size()) {
			return;
		}

		if (currentNode.getElement() >= 1000) {
			previousNode.setNext(currentNode.getNext());
			removeLongNumbersHelper(index + 1, list, previousNode, currentNode.getNext());

		} else {
			removeLongNumbersHelper(index + 1, list, currentNode, currentNode.getNext());
		}
	}

	/**
	 * Returns whether the given linked list is sorted in increasing order.
	 * 
	 * So checkSorted([1,2,3]) yields true. checkSorted ([1,3,2]) yields false.
	 * checkSorted ([]) yields true.
	 */
	public static boolean checkSorted(ILinkedList list) {
		// DONE: Solve me
		if (list.size() <= 1) {
			return true;
		}
		IListNode current = list.getFirst();
		boolean b = false;
		while (current.getNext() != null && current.getElement() < current.getNext().getElement()) {
			b = true;
			current = current.getNext();
		}
		return b;
	}

	/**
	 * Duplicates all elements of the list.
	 * 
	 * So doubleList([1, 2, 3]) yields [1, 1, 2, 2, 3, 3].
	 */
	public static void doubleList(ILinkedList list) {
		// DONE: Solve me
		if (list.size() == 0) {
			return;
		}
		if (list.size() == 1) {
			IListNode current = list.getFirst();
			Integer element = current.getElement();
			int i = list.indexOf(element);
			list.insertAtIndex(i, element);
			return;
		}
		IListNode current = list.getFirst();
		Integer element = current.getElement();
		while (current != null) {
			int i = list.indexOf(element);
			if (i <= 0) {
				list.insertAtIndex(i, element);
				current = current.getNext().getNext();
			} else {
				list.insertAtIndex(i, element);
				current = current.getNext();
			}
		}
		int i = list.indexOf(list.getLast().getElement());
		list.insertAtIndex(i, list.getLast().getElement());
	}

	/**
	 * Returns whether the list represents a Fibonacci sequence.
	 * 
	 * Recall: fib(i) = fib(i-1) + fib(i-2)
	 * 
	 * [0,1] is too short to be a fibonacci sequence. [1,2,3] is a fibonacci
	 * sequence because 3 = 2 + 1. [0,1,1] is a fibonacci sequence because 1 = 1
	 * + 0. [1,4,5,9,14] is a fibonacci sequence.
	 */
	public static boolean isFibonacciSequence(ILinkedList list) {
		// done: Solve me
		if (list.size() <= 2) {
			return false;
		}
		boolean ret = false;
		int index = 0;
		IListNode current = list.getFirst();
		while (index < list.size() - 2) {
			if (current.getElement() + current.getNext().getElement() == current.getNext().getNext().getElement()) {
				ret = true;
				current = current.getNext();
				index += 1;
			} else {
				ret = false;
				break;
			}
		}
		return ret;

	}

	/**
	 * Gets the value of the ith node.
	 * 
	 * If i==0, return the value of the given node.
	 * 
	 * If you are passed an invalid index you should throw an
	 * IndexOutOfBoundsException.
	 * 
	 */
	public static Integer recursiveGet(int index, IListNode iListNode) {
		// DONE: Use RECURSION to solve this problem.

		if (index < 0 || iListNode == null) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return iListNode.getElement();
		}

		return recursiveGetHelper(index, 0, iListNode);
	}

	public static Integer recursiveGetHelper(int index, int currentIndex, IListNode iListNode) {
		if (currentIndex > index) {
			throw new IndexOutOfBoundsException();
		}

		if (currentIndex == index) {
			return iListNode.getElement();
		}

		return recursiveGetHelper(index, currentIndex + 1, iListNode.getNext());
	}

}
