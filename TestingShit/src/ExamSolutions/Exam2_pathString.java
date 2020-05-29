package ExamSolutions;

public class Exam2_pathString {
	BinaryNode root;
	final BinaryNode NULL_NODE = new BinaryNode('\0');

	public Exam2_pathString() {
		root = NULL_NODE;
	}

	public String pathString(char item) {
		StringBuilder path = new StringBuilder();
		boolean found = root.pathString(item, path);
		if (found) {
			return path.toString();
		} else {
			return "";
		}
		//return found ? path.toString() : "";
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

		public boolean pathString(char item, StringBuilder path) {
			if (this == NULL_NODE) {
				return false;
			}
			boolean found = false;
			if (item < this.data) {
				found = left.pathString(item,  path);
				path.append(data);
			} else if (item > this.data) {
				path.append(data);
				right.pathString(item, path);
			} else {
				path.append(data);
				found = true;
			}
			return found;
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
}