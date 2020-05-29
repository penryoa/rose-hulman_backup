package ExamSolutions;

public class Exam2_isLeafBalanced {
	BinaryNode root;
	// The -17 is arbitrary, shouldn't refer to it in code.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public Exam2_isLeafBalanced() {
		root = NULL_NODE;
	}

	public boolean isLeafBalanced() {
		return root.leafHelper().isBalanced;
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

		public Container leafHelper() {
			if (this == NULL_NODE) {
				return new Container(0, true);
			}
			Container cL = left.leafHelper();
			Container cR = right.leafHelper();
			int numLeaves = cL.leafCount + cR.leafCount;
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				// is a leaf
				numLeaves++;
			}
			boolean isBalanced = (
					cL.isBalanced 
					&& cR.isBalanced 
					&& Math.abs(cL.leafCount - cR.leafCount) <= 1);
			return new Container(numLeaves, isBalanced);
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
		public boolean isBalanced;

		public Container(int count, boolean bool) {
			this.leafCount = count;
			this.isBalanced = bool;
		}
	}
}