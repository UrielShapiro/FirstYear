package Q891011;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTreeAlgo {
	public static <T> int height(BinaryTree<T> bt) {
		int ans = -1;
		if(bt!=null && !bt.isEmpty()) {
			return 1+ Math.max(height(bt.getLeft()), height(bt.getRight()));
		}
		return ans;
	}
	/**
	 * Counts the number of nodes in the binary tree
	 * @param bt
	 * @return
	 */
	public static <T> int size(BinaryTree<T> bt) {
		int ans = 0;
		if(bt !=null && !bt.isEmpty()) {
			ans = 1 + size(bt.getLeft())+ size(bt.getRight());
		}
		return ans;
	}
	public static <T> int inOrderPrint(BinaryTree<T> bt) {
		int ans = 0;
		if(bt!=null && !bt.isEmpty()) {
			int l = inOrderPrint(bt.getLeft());
			System.out.println(bt.getRoot());
			int r = inOrderPrint(bt.getRight());
			ans = 1+l+r;
		}
		return ans;
	}
	public static <T> int inOrderFullPrint(BinaryTree<T> bt) {
		return inOrderFullPrint(bt, "");
	}
	public static <T> int inOrderFullPrint(BinaryTree<T> bt, String path) {
		int ans = 0;
		if(bt!=null && !bt.isEmpty()) {
			int l = inOrderFullPrint(bt.getLeft(), path+"L");
			System.out.println(path+":"+bt.getRoot());
			int r = inOrderFullPrint(bt.getRight(), path+"R");
			ans = 1+l+r;
		}
		return ans;
	}
	public static <T> ArrayList<T> toArrayList(BinaryTree<T> bt) {
		ArrayList<T> ans = new ArrayList<T>();
		toArrayList(bt, ans);
		return ans;
	}
	private static <T> void toArrayList(BinaryTree<T> bt, ArrayList<T> arr) {
		if(bt!=null && !bt.isEmpty()) {
			ArrayList<T> l = toArrayList(bt.getLeft());
			ArrayList<T> r = toArrayList(bt.getRight());
			arr.addAll(l);
			arr.add(bt.getRoot());
			arr.addAll(r);
		}
	}
	public static <T> T rightMost(BinaryTree<T> binaryTree) {
		T ans = null;
		BinaryTree<T> tmp = binaryTree;
		while(tmp!=null && !tmp.isEmpty()) {
			ans = tmp.getRoot();
			tmp = tmp.getRight();
		}
		return ans;
	}
	public static <T> T leftMost(BinaryTree<T> bt) {
		T ans = null;
		BinaryTree<T> tmp = bt;
		while(tmp!=null && !tmp.isEmpty()) {
			ans = tmp.getRoot();
			tmp = tmp.getLeft();
		}
		return ans;
	}
	public static <T>boolean isInOrder(BinaryTree<T> bt, Comparator<T> comp) {
		boolean ans = true;
		if(bt!=null && !bt.isEmpty() && !bt.isLeaf()) {
			ans &= isInOrder(bt.getLeft(), comp);
			ans &= isInOrder(bt.getRight(), comp);
			if( bt.getLeft()!=null) {
				int cc = comp.compare( rightMost(bt.getLeft()), bt.getRoot());
				if(cc>0) {ans = false;}
			}
			if( bt.getRight()!=null) {
				int cc = comp.compare( bt.getRoot(), leftMost(bt.getRight()));
				if(cc>=0) {ans = false;}
			}
		}
		return ans;
	}
	/**
	 * A nice trick for save and load objects
	 * @param <T> the type of the binary tree.
	 * @param bt
	 * @param name
	 */
	public static void save(Object ob, String name) {
		try{
			FileOutputStream f = new FileOutputStream(name);
			ObjectOutputStream s = new ObjectOutputStream(f);
				    s.writeObject(ob);
				} 
		catch (IOException error) {
			error.printStackTrace();
		}
		System.out.println("Successfully saved to file "+name+" the Object "+ob);
	}
	public static Object load(String name) {
		Object ans = null;
		try{
			FileInputStream f = new FileInputStream(name);
			ObjectInputStream s = new ObjectInputStream(f);
				    ans = s.readObject();
				} 
		catch (Exception error) {
			error.printStackTrace();
		}
		System.out.println("Successfully loaded the file "+name+" the Object "+ans);
		return ans;
	}
}
