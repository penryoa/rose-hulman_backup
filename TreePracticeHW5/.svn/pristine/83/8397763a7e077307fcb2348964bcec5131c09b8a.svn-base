/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		this.root = NULL_NODE;
		this.root = this.root.maxDepthHelper(0, maxDepth);
	}

	public int getSumOfHeights() {
		// TODO. 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		return this.root.getHeight();
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data + right.toStructuredString() + "]";
		}

		public BinaryNode maxDepthHelper(int depth_index, int max_depth) {
			if (depth_index > max_depth) {
				return NULL_NODE;
			}
			BinaryNode node = new BinaryNode(depth_index);
			node.left = node.left.maxDepthHelper(depth_index + 1, max_depth);
			node.right = node.right.maxDepthHelper(depth_index + 1, max_depth);
			return node;
		}

		public int getHeight() {
			if (this == NULL_NODE) {
				return 0;
			}
			return left.getHeight() + right.getHeight();
		}
	}

	// /////////////// BooleanContainer
	public class BooleanContainer {
		public Boolean bool;
		public BinaryNode node;

		public BooleanContainer(Boolean b, BinaryNode n) {
			this.bool = b;
			this.node = n;
		}

		public BooleanContainer(Boolean b) {
			this.bool = b;
			this.node = NULL_NODE;
		}

		public BooleanContainer(BinaryNode n) {
			this.bool = false;
			this.node = n;
		}

		public BooleanContainer() {
			this.bool = false;
			this.node = NULL_NODE;
		}

		public void setBool(Boolean b) {
			this.bool = b;
		}

		public void setNode(BinaryNode n) {
			this.node = n;
		}
	}
}