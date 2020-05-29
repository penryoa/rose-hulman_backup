package buildtree;

/**
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 */
	public BinaryTree(String chars, String children) {
		// TODO: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		root = NULL_NODE;
		root = root.constructorHelper(chars, children, 0).node;
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
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

		public Character data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data + right.toStructuredString() + ")";
		}

		/**
		 * This is the function responsible for recursively adding nodes to a
		 * tree, returning a container that holds a node and the index in which
		 * the node was added. The last call to the function returns the root
		 * node.
		 * 
		 * 
		 * @param chars
		 *            // String containing the element of each node in a
		 *            pre-order traversal
		 * @param children
		 *            // String representing each node's child in a pre-order
		 *            traversal
		 * @param index
		 *            // keeps track of where we should look in the chars and
		 *            children Strings when making the next node in the tree
		 * @return
		 */
		public container constructorHelper(String chars, String children, int index) {
			if (index >= chars.length()) {
				return new container(NULL_NODE, index);
			} else if (children.charAt(index) == '2') {
				BinaryNode newNode = new BinaryNode(chars.charAt(index));
				container leftContainer = constructorHelper(chars, children, index + 1);
				newNode.left = leftContainer.node;
				container rightContainer = constructorHelper(chars, children, leftContainer.index);
				newNode.right = rightContainer.node;
				return new container(newNode, rightContainer.index);
			} else if (children.charAt(index) == 'L') {
				BinaryNode newNode = new BinaryNode(chars.charAt(index));
				container leftContainer = constructorHelper(chars, children, index + 1);
				newNode.left = leftContainer.node;
				newNode.right = NULL_NODE;
				return new container(newNode, leftContainer.index);
			} else if (children.charAt(index) == 'R') {
				BinaryNode newNode = new BinaryNode(chars.charAt(index));
				newNode.left = NULL_NODE;
				container rightContainer = constructorHelper(chars, children, index + 1);
				newNode.right = rightContainer.node;
				return new container(newNode, rightContainer.index);
			} else if (children.charAt(index) == '0') {
				BinaryNode newNode = new BinaryNode(chars.charAt(index));
				newNode.left = NULL_NODE;
				newNode.right = NULL_NODE;
				return new container(newNode, index + 1);
			} else {
				return new container(NULL_NODE, index + 1);
			}

		}
	}

	/**
	 * I found it very helpful to be able to return both a node and an index
	 * value that indicates where to look in the chars and children lists.
	 * 
	 * This class is simply labeled container because all it does is hold
	 * information.
	 * 
	 * @author penryoa
	 *
	 */
	public class container {
		BinaryNode node;
		int index;

		public container(BinaryNode node, int index) {
			this.node = node;
			this.index = index;
		}
	}
}