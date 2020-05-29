package tree;

/**
 * An implementation of a binary search tree, containing Integer data.
 * 
 * @author <<TODO: your name here >>>.
 *
 */

public class BinarySearchTree {

	BinaryNode root;

	public final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	public int countExactlyBalancedNodes() {
//		int count = 0;
		return root.countBalancedNodes();
	}
	
	public int height() {
		if (this.root == NULL_NODE) {
			return -1;
		}
		int max = 0;
		return this.root.getHeight(max, 0);
	}
	
	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	// /////////////// BinaryNode
	class BinaryNode {
		
		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer data) {
			this.data = data;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}


		public int countBalancedNodes() {
			if (this == NULL_NODE){
				return 0;
			}
			return this.balance() + this.left.countBalancedNodes() + this.right.countBalancedNodes();
		}
		
		public int balance(){
			if (this == NULL_NODE){
				return 0;
			}
			if (this.isBalanced()){
				return 1;
			}
			return 0;
		}

		private boolean isBalanced() {
			if (this == NULL_NODE){
				return false;
			}
			// if leaf, this still works
			int leftHeight = this.left.getHeight(-1,0);
			int rightHeight = this.right.getHeight(-1, 0);
			return leftHeight == rightHeight;
		}


		public boolean isLeaf() {
			return left == NULL_NODE && right == NULL_NODE;
		}
		
		public int getHeight(int max, int tempMax) {
			if (this == NULL_NODE) {
				return max;
			}
			if (tempMax > max) {
				max = tempMax;
			}
			return Math.max(this.right.getHeight(max, tempMax + 1), this.left.getHeight(max, tempMax + 1));
		}


		// The next 2 methods are used by the unit tests
		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(this.data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(this.data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data.toString() + right.toString();
		}
	}

}