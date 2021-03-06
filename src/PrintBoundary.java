import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Print boundary of binary tree
 * @author shubham
 *
 */
public class PrintBoundary {

	public static void main(String[] args) {

		Node1 root = new Node1(1);
		Node1 two = new Node1(2);
		Node1 three = new Node1(3);
		Node1 four = new Node1(4);
		Node1 five = new Node1(5);
		Node1 six = new Node1(6);
		Node1 seven = new Node1(7);
		Node1 eight = new Node1(8);

		root.left = two;
		root.right = three;
		two.left = four;
		two.right = five;
		three.left = six;
		three.right = seven;
		five.left = eight;

		System.out.println("-----------");
		System.out.println(pathSum(root, 7));
	}

	/*
	 * Print all root to leaf paths in a binary tree
	 */
	public static void printRootToLeaf(Node1 root, List<Integer> list) {
		if(root == null) {
			return;
		}

		list.add(root.data);

		if(root.left == null && root.right == null) {
			System.out.println(list);
			return;
		}

		List<Integer> listLeft = new ArrayList<Integer>();
		listLeft.addAll(list);
		List<Integer> listRight = new ArrayList<Integer>();
		listRight.addAll(list);
		printRootToLeaf(root.left, listLeft);
		printRootToLeaf(root.right, listRight);

	}

	/*
	 * Print all paths that sum to 'sum'
	 */
	public static void printPathWithGivenSum(Node1 root, int sum, List<Integer> list) {

		if(root == null) {
			return;
		}

		list.add(root.data);

		if(root.left == null && root.right == null) {
			if(sum == root.data) {
				System.out.println(list);
			}
			return;
		}

		List<Integer> listLeft = new ArrayList<Integer>();
		listLeft.addAll(list);
		List<Integer> listRight = new ArrayList<Integer>();
		listRight.addAll(list);
		printRootToLeaf(root.left, listLeft);
		printRootToLeaf(root.right, listRight);
	}

	/*
	 * Check if a path in BST has sum 'sum'
	 */
	public static boolean pathSum(Node1 root, int sum) {

		if(root == null || sum == 0) {
			return (sum == 0);
		}

		else {
			sum = sum - root.data;
			return pathSum(root.left, sum) || pathSum(root.right, sum);

		}
	}


	/**
	 * Prints boundary nodes of a binary tree
	 * @param root - the root node
	 */
	public static void printOutsidesOfBinaryTree(Node1 root) {

		Stack<Node1> rightSide = new Stack<Node1>();
		Stack<Node1> stack = new Stack<Node1>();
		stack.push(root);

		boolean printingLeafs = false;
		Node1 node = stack.pop();

		while (node != null) {

			// add all the non-leaf right nodes left
			// to a separate stack
			if (stack.isEmpty() && printingLeafs && 
					(node.left != null || node.right != null)) {
				rightSide.push(node);
			}

			if (node.left == null && node.right == null) {
				// leaf node, print it out
				printingLeafs = true;
				System.out.println(node.data + " ");
				node = stack.isEmpty() ? null : stack.pop();
			} else {
				if (!printingLeafs) {
					System.out.println(node.data + " ");
				}

				if (node.left != null && node.right != null) {
					stack.push(node.right);
				}
				node = node.left != null ? node.left : node.right;
			}
		}

		// print out any non-leaf right nodes (if any left)
		while (!rightSide.isEmpty()) {
			System.out.println(rightSide.pop().data + " ");
		}
	}

}

class Node1 {

	@Override
	public String toString() {
		return "Node1 [data=" + data + ", left=" + left + ", right=" + right
		+ "]";
	}

	int data;
	Node1 left;
	Node1 right;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node1 getLeft() {
		return left;
	}

	public void setLeft(Node1 left) {
		this.left = left;
	}

	public Node1 getRight() {
		return right;
	}

	public void setRight(Node1 right) {
		this.right = right;
	}

	public Node1(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}