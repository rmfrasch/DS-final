package HarvestPackage;


/**
 * @author RYAN FRASCH This binary tree is to pull data from the 2D array I have informatin being dumped into. Builds a binary tree from it and sends it to the console.
 *
 */
public class BinaryTree {
	protected Node root;
	private Node current;
	private int count;
	private boolean foundIt;

	public void binaryTree() {
		this.root = null;
	}

	/**
	 * insertes info. into the tree.
	 * @param rank is weight of the plot so the plots can be ranged based on it.
	 * @param pass is the pass of the plot being ranked
	 * @param range is the range of the plot being ranked.
	 */
	public void insert(double rank, int pass, int range) {
		Node CreatedNode = new Node(rank, pass, range);
		if (root == null) {
			root = CreatedNode;
			count++;
		} else {
			current = root;
			Node parent;
			
			while (true) {
				parent = current;
				if (rank < current.wtData) {
					current = current.left;
					if (current == null) {
						parent.left = CreatedNode;
						count++;
						return;
					}
				} else {

					current = current.right;
					if (current == null) {
						parent.right = CreatedNode;
						count++;
						return;
					}
				}
			}
		}
	}

	//checks out top of the tree.
	public String peek() {
		return "Pass:  " + current.passData + "  Range:  " + current.rangeData +  "  ||  Weight:  "  + current.wtData;
	}

	/**
	 * Uses the count to when you insert nodes to keep track of the size of the
	 * tree.
	 */
	public void size() {
		System.out.println("Size of tree is:  " + count);
	}


	/**
	 * Says if the tree is empty
	 */
	public void isEmpty() {
		if (root == null) {
			System.out.println("Yes, this tree is Empty");
		} else {
			System.out.println("Not Empty");
		}
	}

	/**
	 * This tree is never full, just limited by availabe memory and processing
	 * power.
	 */
	public void isFull() {
		System.out.println("Is never full.");
	}

	/**
	 * Prints all the nodes using the inOrder method to the console.
	 */
	public void print() {
		inOrder(root);
	}

	/**
	 * @param localRoot
	 *            for the starting point of printing the information. Uses recursion
	 *            to iterate through the nodes.
	 */
	public void inOrder(Node localRoot) {
		
		if (localRoot != null) {
			inOrder(localRoot.left);
			System.out.println("Pass:  " + localRoot.passData + "  Range:  " + localRoot.rangeData +  "  ||  Weight:  "  + localRoot.wtData);
			inOrder(localRoot.right);
			
		}
	}

	/**
	 * @author RYAN FRASCH A class just for defining nodes.
	 *
	 */
	class Node {
		double wtData;
		int passData;
		int rangeData;
		Node left;
		Node right;

		
		/**
		 * @param wtData is the weight of the plot
		 * @param passData is the pass of the plot
		 * @param rangeData is the range of the plot.
		 */
		public Node(double wtData, int passData, int rangeData) {
			this.wtData = wtData;
			this.passData = passData;
			this.rangeData = rangeData;
			this.left = null;
			this.right = null;
		}

		/**
		 * default constructor
		 */
		public Node() {
			this.wtData = 0;
			this.passData = 0;
			this.rangeData = 0;
			this.left = null;
			this.right = null;
		}

	}
}
