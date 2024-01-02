package Second_Semester.Ex3;

import java.util.Arrays;
import java.util.Comparator;

public class Q4 {
	public class Node
	{
		private int _key;
		private int _priority;
		private Node right, left;

		public Node(int key, int priority)
		{
			_key = key;
			_priority = priority;
		}
		public void setLeft(Node l)
		{
			this.left = l;
		}
		public void setRight(Node r)
		{
			this.right = r;
		}
		public Node(Node o) {
			this._key = o._key;
			this._priority = o._priority;
			this.left = o.left;
			this.right = o.right;
		}
	}
	public class Treap
	{
		public Node[] _arr;
		private Node _root;
		/*
		 * Algorithm: Having all of the Treap's nodes in an array.
		 *			  Sorting the array by priority (using Arrays.sort and a custom comparator).
		 *			  Initialising the root of the Treap as the first node in the array.
		 *			  Inserting each node from the array to the Treap using an helper function.
		 * Runtime Complexity: O(BuildTreap()) = O(Arrays.sort()) + O(for block).
		 * 						insert() runtime complexity varies depending on the amount of nodes that were already inserted.
		 * 						We will analyse the best and worst cases of the following code block:
		 *						  for (int i = 1; i < _arr.length; i++) 			
		 *							{
		 *							insert(_root, _arr[i]);	
		 *							}
		 * 						Best Case: The Treap will be balanced - therefore its height is bounded by log n.
		 * 								   The runtime of the insert function depend on the index of i, therefore the complexity of that block is O(sigma(log n)) = O(log(n!) = O(n*log n). 
		 * 								   Side note: Sigma(log n) = log 1 + log 2 + log 3 + ... + log n
		 * 												using logarithmic rules: log 1 + log 2 + log 3 + ... + log n = log(1 * 2 * 3 * ... * n) = log(n!)
		 * 											    O(log(n!)) is equivalent to O(n*log n).
		 * 						Worst Case: The array is sorted by the nodes's keys (the height of the Treap would be n).
		 * 									The runtime of the insert function depend on the index of i, therefore the complexity of that block is O(sigma(n)) = O(n^2).
		 * 									Side note: Sigma(n) = 1 + 2 + 3 + ... + n = (n(n+1))/2
		 * 												O((n(n+1))/2) = O(n^2).
		 * 
		 * 						Arrays.sort() uses a dual pivot QuickSort with a custom comparator, which it's runtime complexity is: O(c*n*log n) when c is the runtime complexity of the comparator. in this case: O(1*n*log n) = O(n*log n).
		 * 						
		 * 						To sum it up: O(Arrays.sort()) + O(for block):
		 * 								   		Best Case: O(n*log n) + O(n*log n) = O(n*log n).
		 * 										Worst Case: O(n*log n) + O(n^2) = O(n^2).
		 */
		public void BuildTreap() 
		{
			Arrays.sort(_arr, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) 
				{
					return Integer.compare(o1._priority, o2._priority);
				}
			});
			_root = _arr[0];
			for (int i = 1; i < _arr.length; i++) 			
			{
				insert(_root, _arr[i]);	
			}
		}
		/*
		 * This function uses a root to insert a given node as one of it's sub-trees.
		 * If o.key > root.key - o should be a right son of the root. the function would check if root has a right child, if it doesn't, o would be set as the right child of root.
		 * 							 If the root already has a right child, the function will recursively check where to insert o with the right child of the root.
		 * If o.key <= root.key - exactly the same as (o.key > root.key) case, but with the left child of root.
		 * Runtime Complexity is mentioned in the function above.
		 * @param: root - The node which o will be inserted to it's sub-tree.
		 * @param: o - The node that will be inserted.
		 */
		private void insert(Node root, Node o)
		{
			if(o._key > root._key)
			{
				if(root.right == null)
				{
					root.setRight(o);
				}
				else 
				{
					insert(root.right, o);
				}
			}
			else if(o._key <= root._key)
			{
				if(root.left == null)
				{
					root.setLeft(o);
				}
				else 
				{
					insert(root.left, o);
				}
			}
		}
	}
}



