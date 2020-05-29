import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search
 * Tree
 *
 * @author Matt Boutell and Olivia Penry.
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
	private BinaryNode root;
	private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * Sets the root node
	 */
	void setRoot(BinaryNode n) {
		this.root = n;
	}
	
	/**
	 * Returns the number of nodes in the tree
	 */
	public int size() {
		return root.sizeHelper();
	}
	
	/**
	 * 
	 */
	public boolean containsNonBST(T val) {
		return this.root.containsNonBSTHelper(val);
	}
	
	/**
	 * This method inserts the given element i into the tree.
	 */
	public boolean insert(T i) {
		if (this.root == NULL_NODE) {
			BinaryNode n = new BinaryNode(i);
			setRoot(n);
			return true;
		} else if (i == null) {
			throw new IllegalArgumentException();
		} else {
			BooleanContainer bc = new BooleanContainer();
			bc = this.root.insert(i);
			return bc.getBoolean();
		}
	}
	
	/**
	 * This removes the given element i from the tree.
	 * 
	 * I wrote more comments where I actually implemented 
	 * this method in BinaryNode, but I couldn't get it to fully work.
	 * (lines 268-271 describe my attempted approach)
	 */
//	public boolean remove(T i) {
//		if (this.root == NULL_NODE) {
//			return false;
//		} else if (this.root.data == i && this.root.left == NULL_NODE && this.root.right == NULL_NODE) {
//			this.root = NULL_NODE;
//			return true;
//		} else if (i == null) {
//			throw new IllegalArgumentException();
//		} else {
//			BooleanContainer bc = new BooleanContainer();
//			bc = this.root.remove(i, bc);
//			this.root = bc.node;
//			return bc.getBoolean();
//		}
//	}
	
	/**
	 * This checks through the tree to see if the tree contains the given element i.
	 */
	public boolean contains(T i) {
		return root.contains(i);
	}

	/**
	 * Returns the height of the tree.
	 */
	public int height() {
		if (this.root == NULL_NODE) {
			return -1;
		}
		int max = 0;
		return this.root.heightHelper(max, 0);
	}

	/**
	 * Returns whether or not the tree is empty
	 */
	public boolean isEmpty() {
		return this.root == NULL_NODE;
	}

	/**
	 * In-order and inefficient iterator using ArrayList
	 */
	public Iterator<T> inefficientIterator() {
		return new ArrayListIterator();
	}

	/**
	 * In-order iterator using Stack
	 */
	public Iterator<T> preOrderIterator() {
		return new preOrderIterator();
	}

	/**
	 * Pre-order iterator using Stack
	 */
	public Iterator<T> iterator() {
		return new iterator();
	}
	
	/**
	 * Turns the tree to an ArrayList
	 */
	public Object toArrayList() {
		ArrayListIterator arrIterator = new ArrayListIterator();
		return arrIterator.toArrayList();
	}

	/**
	 * Turns the tree into an Array
	 */
	public Object[] toArray() {
		ArrayListIterator arrIterator = new ArrayListIterator();
		return arrIterator.toArrayList().toArray();
	}

	/**
	 * Turns the tree into a String
	 */
	public String toString() {
		ArrayListIterator list = new ArrayListIterator();
		return list.toArrayList().toString();
	}

	// ============ BINARY NODE CLASS ============ //

	class BinaryNode {
		private T data;
		private BinaryNode left;
		private BinaryNode right;

		public BinaryNode() {
			this.data = null;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(T element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public T getData() {
			return this.data;
		}

		public BinaryNode getLeft() {
			return this.left;
		}

		public BinaryNode getRight() {
			return this.right;
		}

		public void setLeft(BinaryNode left) {
			this.left = left;
		}

		public void setRight(BinaryNode right) {
			this.right = right;
		}

		/* My methods */

		public BooleanContainer insert(T i) {
			if (this == NULL_NODE) {
				BinaryNode node = new BinaryNode(i);
				BooleanContainer c = new BooleanContainer(node);
				c.setBoolean(true);
				return c;
			} else if (i.compareTo(data) < 0) {
				BooleanContainer c = left.insert(i);
				left = c.node;
				BooleanContainer d = new BooleanContainer(this);
				d.setBoolean(c.getBoolean());
				return d;
			} else if (i.compareTo(data) > 0) {
				BooleanContainer c = right.insert(i);
				right = c.node;
				BooleanContainer d = new BooleanContainer(this);
				d.setBoolean(c.getBoolean());
				return d;
			} else if (i.compareTo(data) == 0) {
				return new BooleanContainer();
			}
			return null;
		}

		public BooleanContainer remove(T i, BooleanContainer bc) {
			if (this == NULL_NODE) {
				BooleanContainer c = new BooleanContainer();
				c.setBoolean(bc.getBoolean());
				return c;
			} else if (i.compareTo(data) < 0) {				
				BooleanContainer c = left.remove(i, bc);
				left = c.node;
				BooleanContainer d = new BooleanContainer(this);
				d.setBoolean(c.getBoolean());
				return d;
			} else if (i.compareTo(data) > 0) {				
//				My attempt at implementing the idea described in lines 268-271.
				if (bc.getBoolean()) {
					if (right.right == NULL_NODE) {
						bc.setNode(right);
						right = NULL_NODE;
					} else {
						BooleanContainer c = right.remove(i, bc);
						right = c.node;
						BooleanContainer d = new BooleanContainer(this);
						d.setBoolean(c.getBoolean());
						return d;
					}
				}
//				
				
				BooleanContainer c = right.remove(i, bc);
				right = c.node;
				BooleanContainer d = new BooleanContainer(this);
				d.setBoolean(c.getBoolean());
				return d;
				
			} else if (i.compareTo(data) == 0) {
				
				if (left == NULL_NODE && right == NULL_NODE) {
					return new BooleanContainer();
					
				} else if (left == NULL_NODE && right != NULL_NODE) {
					BooleanContainer c = new BooleanContainer(right);
					c.setBoolean(true);
					return c;
					
				} else if (left != NULL_NODE && right == NULL_NODE) {
					BooleanContainer c = new BooleanContainer(left);
					c.setBoolean(true);
					return c;
					
				} else {
					/** Goal:
						Get the farthest right from the left node and make this.data = left.right.data
						I tried lots of things and couldn't get it to work.
					*/
					bc.setBoolean(true);
					BooleanContainer c = left.remove(i, bc);
					left = c.node;
					this.data = bc.node.data;
					c.setNode(this);
					return c;
				}
			}
			return bc;
		}
		
		public boolean contains(T i) {
			if (this == NULL_NODE) {
				return false;
			} else if (i.compareTo(data) < 0) {
				return left.contains(i);
			} else if (i.compareTo(data) > 0) {
				return right.contains(i);
			} else if (i.compareTo(data) == 0) {
				return true;
			}
			return false;
		}

		public int heightHelper(int max, int tempMax) {
			if (this == NULL_NODE) {
				return max;
			}

			if (tempMax > max) {
				max = tempMax;
			}

			return Math.max(this.right.heightHelper(max, tempMax + 1), this.left.heightHelper(max, tempMax + 1));
		}

		public int sizeHelper() {
			if (this == NULL_NODE) {
				return 0;
			} else {
				return 1 + left.sizeHelper() + right.sizeHelper();
			}
		}

		public boolean containsNonBSTHelper(T val) {
			if (this == NULL_NODE) {
				return false;
			}

			if (this.data.equals(val)) {
				return true;
			}

			return this.right.containsNonBSTHelper(val) || this.left.containsNonBSTHelper(val);
		}

		public void toArrayList(ArrayList<T> list) {
			if (this == NULL_NODE) {
				return;
			}
			left.toArrayList(list);
			list.add(data);
			right.toArrayList(list);
		}

		public void toArray(Object[] objects, int index) {
			if (this == NULL_NODE) {
				return;
			}
			left.toArray(objects, index);
			objects[index] = data;
			index = index + 1;
			right.toArray(objects, index);
		}

		public void toStack(Stack<BinaryNode> nodes) {
			if (this == NULL_NODE) {
				return;
			}
			this.right.toStack(nodes);
			this.left.toStack(nodes);
			nodes.push(this);
		}

		public void toStackInOrder(Stack<BinaryNode> nodes) {
			if (this == NULL_NODE) {
				return;
			}
			this.right.toStackInOrder(nodes);
			nodes.push(this);
			this.left.toStackInOrder(nodes);
		}

	}

	// ============ ITERATOR CLASSES ============ //

	/**
	 * ArrayList Iterator Class
	 */
	public class ArrayListIterator implements Iterator<T> {
		private ArrayList<T> list = new ArrayList<>();
		private int index;

		public ArrayListIterator() {
			index = 0;
			list = toArrayList();

		}

		@Override
		public boolean hasNext() {
			return index < list.size();
		}

		// next always returns the current value and moves to the next one
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more next");
			}
			T temp = list.get(index);
			index++;
			return temp;
		}

		public ArrayList<T> toArrayList() {
			ArrayList<T> list = new ArrayList<T>();
			root.toArrayList(list);
			return list;
		}
	}

	/**
	 * preOrder lazy iterator class
	 */
	public class preOrderIterator implements Iterator<T> {
		Stack<BinaryNode> nodes;

		public preOrderIterator() {
			nodes = toStack();
		}

		@Override
		public boolean hasNext() {
			return !this.nodes.isEmpty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more next");
			}
			return this.nodes.pop().data;
		}

		public Stack<BinaryNode> toStack() {
			Stack<BinaryNode> nodes = new Stack<BinaryNode>();
			root.toStack(nodes);
			return nodes;
		}

	}

	/**
	 * inOrder lazy iterator class
	 */
	public class iterator implements Iterator<T> {
		Stack<BinaryNode> nodes;

		public iterator() {
			nodes = toStackInOrder();
		}

		@Override
		public boolean hasNext() {
			return !this.nodes.isEmpty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more next");
			}
			return this.nodes.pop().data;
		}

		public Stack<BinaryNode> toStackInOrder() {
			Stack<BinaryNode> nodes = new Stack<BinaryNode>();
			root.toStackInOrder(nodes);
			return nodes;
		}
	}

	// ============ BOOLEAN CONTAINER CLASS ============ //
	protected class BooleanContainer {
		boolean wasModified;
		BinaryNode node;
		boolean somethingWasInserted = false;

		public BooleanContainer() {
			this.node = NULL_NODE;
			this.wasModified = false;
		}

		public BooleanContainer(BinaryNode node) {
			this.node = node;
			this.wasModified = false;
		}

		public void setBoolean(boolean b) {
			this.wasModified = b;
		}

		public boolean getBoolean() {
			return this.wasModified;
		}

		public void setNode(BinaryNode n) {
			this.node = n;
		}

		public BinaryNode getNode() {
			return this.node;
		}
		
		public void setSomethingWasInserted(Boolean b) {
			this.somethingWasInserted = b;
		}

	}
}
