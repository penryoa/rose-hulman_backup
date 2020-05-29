package isleafbalanced;

public class BinarySearchTree {
	BinaryNode root;
	// The -17 is arbitrary, shouldn't refer to it in code.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public boolean isLeafBalanced() {
		if (this.root == NULL_NODE) {
			return true;
		}
		Container cL = new Container();
		Container cR = new Container();
		if (this.root.left != NULL_NODE) {
			this.root.left = this.root.left.leafHelper(cL);
		}
		if (this.root.right != NULL_NODE) {
			this.root.right = this.root.right.leafHelper(cR);
		}

		// I realized too late that the instructions asked for EACH node to be
		// leaf balanced. In retrospect, I would make the Container hold a
		// leafCountLeft and leafCountRight to show both the left and right
		// tree's leaf counts. The Container would also hold a boolean saying
		// whether or not it is balanced. If unbalanced, the boolean would
		// change to false and stay that way no matter what.

		return (Math.abs(cL.leafCount - cR.leafCount) < 2);
	}

	///////////////// BinaryNode
	public class BinaryNode {
		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(Integer element, BinaryNode left, BinaryNode right) {
			this(element);
			this.left = left;
			this.right = right;
		}

		public BinaryNode insert(Integer e) {
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

		public BinaryNode leafHelper(Container c) {
			if (this == NULL_NODE) {
				return this;
			}
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				c.leafCount++;
				return this;
			}
			this.left = this.left.leafHelper(c);
			this.right = this.right.leafHelper(c);

			return this;
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
	public void insert(Integer e) {
		root = root.insert(e);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public class Container {
		public int leafCount;

		public Container() {
			this.leafCount = 0;
		}
	}
}