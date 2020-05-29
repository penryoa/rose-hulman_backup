package editortrees;

import editortrees.EditTree.Container;

public class Node {

	enum Code {
		SAME, LEFT, RIGHT;
		// Used in the displayer and debug string
		public String toString() {
			switch (this) {
			case LEFT:
				return "/";
			case SAME:
				return "=";
			case RIGHT:
				return "\\";
			default:
				throw new IllegalStateException();
			}
		}
	}

	char element; // the Node's data
	Node left, right; // subtrees
	int rank; // in-order position of this node within its own subtree.
	Code balance; // "/", "=", or "\\"
	private int leftHeight; // height of the left subtree
	private int rightHeight; // height of the right subtree
	public Container c; // Container class is in EditTree and holds Nodes
						// (NULL_NODE and returnable Nodes), the number of
						// rotations, etc.

	// ================== CONSTRUCTORS ================== //

	/*
	 * Constructor that makes a NULL_NODE
	 */
	public Node() {
		this.element = ' ';
		this.left = null;
		this.right = null;
		this.leftHeight = 0;
		this.rightHeight = 0;
		this.rank = 0;
		this.balance = Code.SAME;
	}

	/*
	 * Constructor that makes a node with element ch
	 */
	public Node(char ch, Container c) {
		this.element = ch;
		this.c = c;
		this.left = c.NULL_NODE;
		this.right = c.NULL_NODE;
		this.leftHeight = 0;
		this.rightHeight = 0;
		this.rank = 0;
		this.balance = Code.SAME;
	}

	/*
	 * Constructs a node from a node
	 */
	public Node(Node node, Container c) {
		this.element = node.element;
		this.left = new Node(node.left, c);
		this.right = new Node(node.right, c);
		this.leftHeight = node.leftHeight;
		this.rightHeight = node.rightHeight;
		this.c = c;
		this.setBalanceCode();
	}

	
	
	// ================== BASIC FUNCTIONS ================== //

	/*
	 * Returns the size of the tree recursively and efficiently
	 */
	public int getSize() {
		if (this.element == ' ') {
			return 0;
		}
		return this.rank + this.right.getSize() + 1;
	}

	/*
	 * Finds the node at position pos and returns the value at that position
	 */
	public char get(int pos) {
		if (this.element == ' ') {
			throw new IndexOutOfBoundsException();
		} else if (pos < this.rank) {
			return this.left.get(pos);
		} else if (pos > this.rank) {
			return this.right.get(pos - (this.rank + 1));
		}
		return this.element;
	}

	/*
	 * Prints each node's element out in an in-order traversal
	 */
	public String toString() {
		if (this.element == ' ') {
			return "";
		}
		return this.left.toString() + this.element + this.right.toString();
	}

	/*
	 * Prints each node's element, rank, and balance code in a pre-order
	 * traversal
	 */
	public String toDebugString() {
		if (this.element == ' ') {
			return "";
		}
		return "" + this.element + this.rank + this.balance + ", " + this.left.toDebugString()
				+ this.right.toDebugString();
	}

	/*
	 * Gets the height of a node by comparing its left and right nodes' heights
	 */
	public int height() {
		if (this.element == ' ') {
			return 0;
		}
		return (Math.max(this.leftHeight, this.rightHeight) + 1);
	}

	/**
	 * This method searches for a certain group of Nodes and returns the index
	 * of the first appearance that they can be found at
	 */
	public int find(String string) {
		if (this.left != c.NULL_NODE) {
			this.left.find(string);
			if (c.foundChars == string.length()) {
				return c.position;
			}
		}
		if (this.element == string.charAt(c.foundChars)) {
			c.foundChars++;

		} else if (this.element != string.charAt(c.foundChars)) {
			c.foundChars++;
			c.position = 0;
		}
		if (this.right != c.NULL_NODE) {

			this.right.find(string);
		}
		return -1;
	}

	
	
	// ================== ADD/DELETE/CONCATENATE ================== //

	/*
	 * Adds a char (ch) to a specific position (pos)
	 */
	public Node add(char ch, int pos) {
		// throw exception if pos is out of bounds
		if (pos > this.rank + 1 && this.right == c.NULL_NODE) {
			throw new IndexOutOfBoundsException();
		}
		// recurse left in the tree
		if (pos <= this.rank) {
			this.rank++;

			if (this.left == c.NULL_NODE) {
				this.left = new Node(ch, c);
				this.leftHeight = 1;
				this.setBalanceCode();
			} else {
				this.left = this.left.add(ch, pos);
				this.leftHeight = Math.max(this.left.leftHeight, this.left.rightHeight) + 1;
				Node temp = this.checkRightRotation();
				temp.setBalanceCode();
				return temp;
			}

			// recurse right in the tree
		} else if (pos > this.rank) {
			if (this.right == c.NULL_NODE) {
				this.right = new Node(ch, c);
				this.rightHeight = 1;
			} else {
				this.right = this.right.add(ch, pos - (this.rank + 1));
				this.rightHeight = Math.max(this.right.leftHeight, this.right.rightHeight) + 1;
				Node temp = this.checkLeftRotation();
				temp.setBalanceCode();
				return temp;
			}
		}
		this.setBalanceCode();
		return this;
	}

	/*
	 * Adds a char to the rightmost position on the tree
	 */
	public Node add(char ch) {
		if (this.right == c.NULL_NODE) {
			this.rightHeight = 1;
			this.right = new Node(ch, c);
			this.setBalanceCode();
		} else {
			this.right = this.right.add(ch);
			this.rightHeight = Math.max(this.right.leftHeight, this.right.rightHeight) + 1;
			if (Math.abs(this.leftHeight - this.rightHeight) > 1) {
				if ((this.leftHeight < this.rightHeight) && (this.right.leftHeight < this.right.rightHeight)) {
					return this.singleLeftRot();
				} else if ((this.leftHeight < this.rightHeight) && (this.right.leftHeight > this.right.rightHeight)) {
					return this.doubleLeftRot();
				}
			}
		}
		this.setBalanceCode();
		return this;
	}

	/*
	 * Deletes a node from the tree at position pos
	 */
	public void delete(int pos) {
		// If the index is out of range, we have to throw an exception
		if (pos > this.rank + 1 && this.right == c.NULL_NODE) {
			throw new IndexOutOfBoundsException();
		}
		// recurse left in the tree
		if (pos < this.rank) {
			this.rank--;
			if (this.left == c.NULL_NODE) {
				throw new IndexOutOfBoundsException();
			}
			this.left.delete(pos);
			this.left = c.returned;
			this.setHeights();
			this.setBalanceCode();
			c.returned = this.checkLeftRotation();

			// recurse right in the tree
		} else if (pos > this.rank) {
			if (this.right == c.NULL_NODE) {
				throw new IndexOutOfBoundsException();
			}
			this.right.delete(pos - (this.rank + 1));
			this.right = c.returned;
			this.setHeights();
			c.returned = this.checkRightRotation();
			this.setBalanceCode();

			// we found the node we want to delete!
		} else {
			c.deleted = this;
			if (this.right == c.NULL_NODE && this.left == c.NULL_NODE) {
				c.returned = c.NULL_NODE;
			} else if (this.right != c.NULL_NODE) {
				if (this.right.left == c.NULL_NODE) {
					this.right.left = this.left;
					this.right.rank = this.rank;
					this.right.setHeights();
					c.returned = this.right.checkRightRotation();
					c.returned.setBalanceCode();

				} else {
					Node temp = this.right.findLeftNode();
					if (this.right.left.left.element != ' ') {
						this.right.left.delete(0);
						this.right.left = c.returned;
					} else {
						this.right.left = c.NULL_NODE;
					}
					temp.left = this.left;
					temp.right = this.right;
					temp.rank = this.rank;
					temp.right.rank--;
					temp.right.setHeights();
					temp.right.setBalanceCode();
					temp.right = temp.right.checkLeftRotation();
					temp.setHeights();
					temp.setBalanceCode();
					c.returned = temp;
				}
			} else if (this.left != c.NULL_NODE) {
				c.returned = this.left;
			}
		}
		this.setBalanceCode();
	}

	/**
	 * This is used when the tree passed in is larger than this tree
	 */
	public Node concatenateLeft(Node node) {
		if (this.height() > node.height() + 1) {
			this.left = this.left.concatenateLeft(node);
			this.left.setHeights();
			this.left.setBalanceCode();
			this.rank = this.left.getSize();
			return this;
		} else {
			this.delete(0);
			Node returnedNode = c.returned;
			Node deletedNode = c.deleted;
			deletedNode.right = returnedNode;
			deletedNode.left = node;
			deletedNode.rank = deletedNode.left.getSize();
			deletedNode.setHeights();
			deletedNode.setBalanceCode();
			deletedNode = deletedNode.checkLeftRotation();
			deletedNode = deletedNode.checkRightRotation();
			return deletedNode;
		}
	}

	/**
	 * This is used when the tree passed in is smaller than this tree
	 */
	public Node concatenateRight(Node node) {
		if (this.height() > node.height()) {
			this.right = this.right.concatenateRight(node);
			this.right.setHeights();
			this.right.setBalanceCode();
			return this;
		} else {
			Node temp = this.findRightNode();
			if (temp.left != c.NULL_NODE) {
				temp.delete(temp.rank + 1);
			} else if (temp.right != c.NULL_NODE) {
				temp.delete(1);
			} else {
				temp.delete(0);
			}

			Node temp2 = c.deleted;
			temp2.left = this;
			temp2.right = node;

			temp2.setHeights();
			temp2.setBalanceCode();
			temp2.rank = temp2.left.getSize();
			return temp2;
		}
	}

	
	
	// ================== HELPER METHODS ================== //

	/**
	 * Resets the right and left heights. Makes no changes if it is already
	 * good.
	 */
	public void setHeights() {
		if (this.right.element != ' ') {
			this.rightHeight = Math.max(this.right.leftHeight, this.right.rightHeight) + 1;
		} else {
			this.rightHeight = 0;
		}
		if (this.left.element != ' ') {
			this.leftHeight = Math.max(this.left.leftHeight, this.left.rightHeight) + 1;
		} else {
			this.leftHeight = 0;
		}
	}

	/**
	 * Returns the far left node
	 * 
	 */
	public Node findLeftNode() {
		if (this.rank == 0) {
			return this;
		}
		return this.left.findLeftNode();

	}

	/**
	 * Returns the second to the farthest right node
	 */
	public Node findRightNode() {
		if (this.rightHeight <= 1) {
			return this;
		}
		return this.right.findRightNode();
	}

	/*
	 * Resets the balance code of a node by comparing the left and right nodes'
	 * heights
	 */
	public void setBalanceCode() {
		if (this.leftHeight == this.rightHeight) {
			this.balance = Code.SAME;
		} else if (this.leftHeight > this.rightHeight) {
			this.balance = Code.LEFT;
		} else if (this.rightHeight > this.leftHeight) {
			this.balance = Code.RIGHT;
		}
	}

	
	
	// ================== MAKING NEW TREES ================== //

	/*
	 * Copies EditorTree e, passing in e's NULL_NODE and its root n.
	 */
	public void copyTree(Node n, Node nNull) {
		if (n != nNull) {
			if (n.left != nNull) {
				this.left = new Node(n.left.element, c);
				this.left.copyTree(n.left, nNull);
			}

			if (n.right != nNull) {
				this.right = new Node(n.right.element, c);
				this.right.copyTree(n.right, nNull);
			}
			this.rank = n.rank;
			this.setHeights();
			this.setBalanceCode();
		}
	}

	/**
	 * A tree's string is turned into an EditorTree by looking at certain
	 * indexes and making child Nodes from those certain positions and calling
	 * recursively until the string we're looking at has nothing in it
	 */
	public void treeFromString(String s) {
		if (s.length() != 0) {
			int len = s.length();
			int current = (len) / 2;
			int leftVal = (current) / 2;
			int rightVal = (len + current) / 2;
			if (this.element != s.charAt(leftVal)) {
				this.left = new Node(s.charAt(leftVal), c);
			}
			if (this.element != s.charAt(rightVal)) {
				this.right = new Node(s.charAt(rightVal), c);
			}
			if (leftVal != 0) {
				this.left.treeFromString(s.substring(0, current));
			}
			if (rightVal != len - 1) {
				this.right.treeFromString(s.substring(current + 1, len));
			}
			this.setHeights();
			this.rank = this.left.getSize();
			this.setBalanceCode();
		}

	}

	
	
	// ================== ROTATIONS ================== //

	/*
	 * If a right rotation may be needed, this function will check and call for
	 * a rotation if necessary.
	 */
	public Node checkRightRotation() {
		if (Math.abs(this.leftHeight - this.rightHeight) > 1) {
			if ((this.leftHeight > this.rightHeight) && (this.left.leftHeight > this.left.rightHeight)) {
				return this.singleRightRot();
			} else if ((this.leftHeight > this.rightHeight) && (this.left.leftHeight < this.left.rightHeight)) {
				return this.doubleRightRot();
			}
		}
		return this;
	}

	/*
	 * If a left rotation may be needed, this function will check and call for a
	 * rotation if necessary.
	 */
	public Node checkLeftRotation() {
		if (Math.abs(this.leftHeight - this.rightHeight) > 1) {
			if ((this.leftHeight < this.rightHeight) && (this.right.leftHeight < this.right.rightHeight)) {
				return this.singleLeftRot();
			} else if ((this.leftHeight < this.rightHeight) && (this.right.leftHeight > this.right.rightHeight)) {
				return this.doubleLeftRot();
			}
		}
		return this;
	}

	/*
	 * Performs a double-right rotation by performing two single rotations
	 */
	private Node doubleRightRot() {
		this.left = this.left.singleLeftRot();
		return this.singleRightRot();
	}

	/*
	 * Performs a double-left rotation by performing two single rotations
	 */
	private Node doubleLeftRot() {
		this.right = this.right.singleRightRot();
		return this.singleLeftRot();
	}

	/*
	 * Performs a single-left rotation and increases the number of rotations
	 */
	private Node singleLeftRot() {
		Node orphaned = this.right.left;
		Node newtop = this.right;
		this.right.left = this;
		this.right.rank += this.rank + 1;
		this.rightHeight = this.rightHeight - (this.right.rightHeight + 1) + this.right.leftHeight;
		this.right.leftHeight = ((Math.max(this.right.leftHeight, this.leftHeight)) + 1);
		this.right.setBalanceCode();
		this.right = orphaned;
		this.setBalanceCode();
		c.num_rot++;
		newtop.setBalanceCode();
		return newtop;
	}

	/*
	 * Performs a single-right rotation and increases the number of rotations
	 */
	private Node singleRightRot() {
		Node orphaned = this.left.right;
		Node newtop = this.left;
		this.left.right = this;
		this.rank -= (this.left.rank + 1);
		this.leftHeight = this.leftHeight - (this.left.leftHeight + 1) + this.left.rightHeight;
		this.left.rightHeight = (Math.max(this.left.rightHeight, this.rightHeight)) + 1;
		this.left.setBalanceCode();
		this.left = orphaned;
		this.setBalanceCode();
		c.num_rot++;
		return newtop;
	}

}