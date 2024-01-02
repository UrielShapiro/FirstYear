package Second_Semester.Ex3;

public class Ex3 {
	public class BinaryTree {
		private Node root; // root of the BST

		// Empty constructor - wasn't requested to implement.
		public BinaryTree() {;}
		
		
		// Question 1.a.
		
		/*
		 * This function receives a binary tree and checks if it is a binary search tree, by sending the binary tree's root with a given maximum and minimum parameters of which the root's key needs to be within (is set to Integer MAX/MIN value as initially, because the root has no limitations on it's key) to an helper function
		 * Time Complexity: O(isValidBST(o, max, min)) = O(n)
		 * @param: tree - a BinaryTree
		 * @return: True if the given BinaryTree is a binary search tree - There is no exception from the minimum and maximum values for every node's key in the helper function.
		 * 			False if at least one node is not within the range [min,max) for a set minimum and maximum values.
		 */
		public static boolean isValidBST(BinaryTree tree)
		{
			return isValidBST(tree.root, Integer.MAX_VALUE, Integer.MIN_VALUE);
		}
		/*
		 * This function checks if a given node in a given binary tree "follows" the rules of the BST it is a part of.
		 * Algorithm: The function checks if the key of a given node is in a range between a minimum and maximum values (Here we set it to o.key in [min,max)).
		 * These minimum and maximum values change when recursively entering the function.
		 * Each "right turn" reduces the minimum value to the value of the key of the current node.
		 * Each "left turn" reduces the maximum value to the value of the key of the current node.
		 * That way, the in the next recursive run of the function, the checked node will be checked if it's key is within the minimum and maximum values which vary depending on the route to that node.
		 * Time Complexity: The function occurs once for each node in the BinaryTree (assuming the BinaryTree is indeed a BST, the function will recursively end only after reaching the last node). therefore: O(n).
		 * @param: o - BinaryTree Node
		 * @param: max - The upper bound of a given node's key.
		 * @param: min - The lower bound of a given node's key.
		 * @return: If the function recursively reached a leaf's son (o == null), there was no exception in the BST (all nodes in the current path, are ordered in a BST order). the function will return true.
		 * 			If a given node's key has a deviation from either the upper or lower bound - there is an exception in the BST order and the function will return false.
		 */
		public static boolean isValidBST(Node o, int max, int min)
		{
			if(o == null)
			{
				return true;
			}
			if(o.getKey() > max || o.getKey() <= min)
			{
				return false;
			}
			else
			{
				return isValidBST(o.right, max, o.getKey()) && isValidBST(o.left, o.getKey(), min);
			}
		}
		
		
		//Question 1.b.
		
		/*
		 * This function receives a binary tree and checks if it is a red-black tree, by sending the binary tree's root to an helper function.
		 * Time Complexity: O(BlackNodes(Node o)) = O(n*log n).
		 * @param: tree - a BinaryTree
		 * @return: True if the given BinaryTree is a red-black tree - every path from the root to each leaf contains the same amount of black nodes 
		 * 			False if at least one route from the root to a leaf contains a different amount of black nodes.
		 */
		public static boolean hasValidHeight(BinaryTree tree)
		{
			return BlackNodes(tree.root);
		}
		/*
		 * This function checks if all the paths from a given node in a given binary tree to it's leafs contain the same amount of black nodes.
		 * Algorithm: If the given node is not null - it has sons (they might be null though) The function uses an helper function to check the amount of black nodes in a path from that node's right child and left child to one of their leafs.
		 * If they both have the same amount of black nodes - the function will recursively check the same condition on them.
		 * Time Complexity: The function occurs once for each node in the BinaryTree (assuming the BinaryTree is indeed a red-black, the function will recursively end only after reaching the last node). therefore: n*O(BlackNodesCounter) = O(n*log n).
		 * @param: o - BinaryTree Node
		 * @return: If the function recursively reached a leaf's son (o == null), there was no exception (all nodes in the current path, contain the same amount of black nodes from them to their leafs). the function will return true.
		 * 			If a given node's children contain a different amount of black nodes in the path from them to one of their leafs - there is an exception in the condition of a red-black tree and the function will return false.
		 */
		public static boolean BlackNodes(Node o)
		{
			if(o != null)
			{
				if(BlackNodesCounter(o.left) == BlackNodesCounter(o.right))
				{
					return BlackNodes(o.left) && BlackNodes(o.right);
				}
				return false;
			} 
			return true;
		}
		/*
		 * This function checks the amount of black nodes from a given node to it's rightmost leaf.
		 * Algorithm: If the given node is null - it is black (from the definition), the function will return 1.
		 * If the given node isn't null - the function will check if it is black - if it is, the function will recursively check it's right child and add 1 to the black nodes counter.
		 * If the given node isn't null nor black, the function will recursively check it's right child and add the amount of black nodes in a certain route to a leaf, to the black nodes counter.
		 * Explanation: It is correct to "go only right" in each route, because the "black height" of a node defined by the amount of black nodes from it to one of it's leafs. in the current algorithm we chose it's rightmost leaf.
		 * 				In case of a difference between the amount of black nodes in a right route to a left route, the function above will find it.
		 * Time Complexity: The function occurs once for each right son of a given node. assuming the input is valid - red-black tree has a max height of 2log(n+1), therefore, for each node, the worst runtime complexity is: O(log n).
		 * @param: o - BinaryTree Node
		 * @return: The amount of black nodes in the path from Node o to it's rightmost leaf.
		 */
		public static int BlackNodesCounter(Node o)
		{
			if(o == null)
			{
				return 1;
			}
			else if(o.getColor() == o.BLACK)
			{
				return BlackNodesCounter(o.right) + 1; 
			}
			return BlackNodesCounter(o.right);
		}

	}
	class Node {
		private final Integer key; // associated data
		private final boolean color;
		private final boolean BLACK = true;
		private final boolean RED = false;
		Node left, right;
		
		// constructor
		public Node(Integer data, boolean color)
		{
			this.key = data;
			this.color = color;
			this.left = null;
			this.right = null;
		}
		public Integer getKey()
		{
			return this.key;
		}
		public boolean getColor()
		{
			return this.color;
		}
		public void setRight(Node o)
		{
			this.right = o;
		}
		//Not really required.
		public String toString(){
			return "Node Parameters: Key: "+this.key+", Color: "+this.color+"\nRight child: "+this.right.toString()+"\nLeft Child:"+this.left.toString();

		}
	}
}
