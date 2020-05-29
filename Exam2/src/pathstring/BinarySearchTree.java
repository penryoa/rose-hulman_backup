package pathstring;

public class BinarySearchTree {
	BinaryNode root;
	final BinaryNode NULL_NODE = new BinaryNode('\0');

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public String pathString(char item) {
		Container c = new Container();
		if (this.root.find(item, c).found) {
			return this.root.find(item, c).pathString;
		} else {
			return "";
		}

		// RAN OUT OF TIME //
		// I would have made it so that the helper class successfully returns an
		// instance of the Container class and have the Container hold the
		// pathString. As chars are added to the pathString, there is a method
		// in the Container class that should sort it as it grows.
	}

	// /////////////// BinaryNode
	public class BinaryNode {
		public char data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(char element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(char element, BinaryNode left, BinaryNode right) {
			this(element);
			this.left = left;
			this.right = right;
		}

		public BinaryNode insert(char e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public Container find(char item, Container c) {
			if (this == NULL_NODE) {
				return c;
			}
			if (this.data < item) {
				c.addToString(this.data);
				this.right.find(item, c);
				return c;
			} else if (this.data > item) {
				c.addToString(this.data);
				this.left.find(item, c);
				return c;
			} else {
				c.found = true;
				return c;
			}
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}
	}

	// The next methods are used by the unit tests
	public void insert(char e) {
		root = root.insert(e);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public class Container {
		public boolean found;
		// public BinaryNode node;
		public String pathString;

		public Container() {
			this.found = false;
			// this.node = NULL_NODE;
			this.pathString = "";
		}

		public void addToString(char e) {
			this.pathString += e;
		}
	}
}