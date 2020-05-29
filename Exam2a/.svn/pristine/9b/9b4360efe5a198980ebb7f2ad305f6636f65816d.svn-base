package bst;

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
public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public int internalNodeSum() {
		return this.root.interalNodeSum();
	}

	public boolean attachPathMinAndMaxToNode(int value) {
		return this.root.attachPath(value, null, null, new BooleanContainer()).wasFound;
	}

	/* Couldn't debug in time! Sorry. */
	public ArrayList<Integer> listWithinToleranceOfTarget(int target, int tolerance) {
		if (this.root == NULL_NODE) {
			return new ArrayList<Integer>();
		}

		String s = this.root.listWithinT(target, tolerance);
		// System.out.println(s);

		if (s == "") {
			return new ArrayList<Integer>();
		}

		String[] line = s.split(" ");
		// System.out.println("Line length : " + line.length);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < line.length; i++) {
			if (line[i] != " ") {
				int value = Integer.valueOf(line[i]);
				// System.out.println(value);
				list.add(value);
			}
		}
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
			} else if (this.right == NULL_NODE && this.left == NULL_NODE) {
				return 0;
			} else if (this.right == NULL_NODE && this.left != NULL_NODE) {
				return this.data + left.interalNodeSum();
			} else if (this.right != NULL_NODE && this.left == NULL_NODE) {
				return this.data + right.interalNodeSum();
			} else if (this.right != NULL_NODE && this.left != NULL_NODE) {
				return this.data + this.left.interalNodeSum() + this.right.interalNodeSum();
			}
			return 0;
		}

		public BooleanContainer attachPath(int value, Object min, Object max, BooleanContainer bc) {
			if (this == NULL_NODE) {
				return bc;
			} else if (this.data == value) {
				if (min == null) {
					min = this.data;
				}
				if (max == null) {
					max = this.data;
				}
				if (this.data < (int) min) {
					min = this.data;
				} else if (this.data > (int) max) {
					max = this.data;
				}
				this.left = new BinaryNode((int) min);
				this.right = new BinaryNode((int) max);
				bc.setBoolean(true);
			} else if (this.data < value) {
				if (!bc.wasFound) {
					if (min == null) {
						min = this.data;
					}
					if (max == null) {
						max = this.data;
					}
					if (this.data < (int) min) {
						min = this.data;
					} else if (this.data > (int) max) {
						max = this.data;
					}
					return this.right.attachPath(value, min, max, bc);
				}
			} else if (this.data > value) {
				if (!bc.wasFound) {
					if (min == null) {
						min = this.data;
					}
					if (max == null) {
						max = this.data;
					}
					if (this.data < (int) min) {
						min = this.data;
					} else if (this.data > (int) max) {
						max = this.data;
					}
					return this.left.attachPath(value, min, max, bc);
				}
			}
			return bc;
		}

		public String listWithinT(int target, int tolerance) {
			if (this == NULL_NODE) {
				return "";
			}
			if (Math.abs(target - this.data) <= tolerance) {
				return this.data + " " + left.listWithinT(target, tolerance) + " "
						+ right.listWithinT(target, tolerance) + " ";
			} else {
				return left.listWithinT(target, tolerance) + " " + right.listWithinT(target, tolerance) + " ";
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

	public class BooleanContainer {
		boolean wasFound;

		public BooleanContainer() {
			this.wasFound = false;
		}

		public BooleanContainer(boolean b) {
			this.wasFound = b;
		}

		public void setBoolean(boolean b) {
			this.wasFound = b;
		}
	}

}