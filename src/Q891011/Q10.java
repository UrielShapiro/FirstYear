package Q891011;

import java.util.ArrayList;



public class Q10 {
	public static <T> void main(String[] args) 
	{
		BinaryTree<T> bs = new BinaryTree1<T>();
		String s = "AAAAA";
		for (int i = 0; i < 30; i++) {
			bs.add((T) s);
		}
		System.out.println(q10(bs));
	}

	public static <T> ArrayList <T> q10(BinaryTree <T> bs){
		ArrayList <T> t = new ArrayList<>();
		q10helper(bs,t);
		return t;
	}

	private static <T> ArrayList <T> q10helper(BinaryTree<T> bs, ArrayList<T> t) {
		if( bs == null) 
		{
			return null;
		}
		else{
			q10helper(bs.getLeft(),t);
			if (bs.isLeaf())
			{
				t.add(bs.getRoot());
			}
			q10helper(bs.getRight(),t);
		}
		return t;	
	}
}



