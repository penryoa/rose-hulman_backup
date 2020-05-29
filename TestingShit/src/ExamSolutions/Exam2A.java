package ExamSolutions;

import java.util.ArrayList;

/**
 *
 * Exam 2a. Tree methods.
 * 
 * @author
 */

/*
 * TODO: Directions: Implement the method below. See the paper for details.
 */
public class Exam2A {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public Exam2A() {
		root = NULL_NODE;
	}

	public int internalNodeSum() {
		return this.root.interalNodeSum();
	}

	public boolean attachPathMinAndMaxToNode(int value) {
		return this.root.attachPath(value, value, value);
	}

	/* Couldn't debug in time! Sorry. */
	public ArrayList<Integer> listWithinToleranceOfTarget(int target, int tolerance) {
		ArrayList<Integer> list = new ArrayList<>();
		root.listWithinT(list, target, tolerance);
		return list;
	}

	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}

	@Override
	public String toString() {
		return root.toString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public int data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(int element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public int interalNodeSum() {
			if (this == NULL_NODE) {
				return 0;
			} 
			if (this.isLeaf()){
				return 0;
			}
			return this.data + left.interalNodeSum() + right.interalNodeSum();
		}
		
		public boolean isLeaf(){
			return left == NULL_NODE && right == NULL_NODE;
		}

		public boolean attachPath(int value, int min, int max) {
			if (this == NULL_NODE) {
				return false;
			} 
			if (data < min) {
				min = data;
			}
			if (data > max) {
				max = data;
			}
			if (value == data) {
				left = new BinaryNode(min);
				right = new BinaryNode(max);
				return true;
			} else if (value < data) {
				return left.attachPath(value, min, max);
			} else {
				return right.attachPath(value, min, max);
			}
		}

		public void listWithinT(ArrayList<Integer> list, int target, int tolerance) {
			if (this == NULL_NODE) {
				return;
			}
			if (this.data > target - tolerance) {
				// data > low, we need to look left
				left.listWithinT(list, target, tolerance);
			}
			if (this.data >= target - tolerance && this.data <= target + tolerance){
				// in range, so add to list
				list.add(this.data);
			}
			if (this.data < target + tolerance) {
				// data < high, we need to look right
				right.listWithinT(list, target, tolerance);
			}
		}

		public BinaryNode insert(int e) {
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

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}
			String myInfo = indent + String.format("%c\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
	}
}