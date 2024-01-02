package Q891011;

import java.util.Random;

public class Q11 {

	public static void main(String[] args) {
		for (int i = 0; i <20; i++) {
			BinaryTree1<Object> bt = new BinaryTree1<>();
			Random r = new Random();
			for (int j = 0; j < r.nextInt(5,5000); j++) {
				bt.add(j);
			}
			System.out.println(Q11(bt));
		}

	}
	public static <T> double Q11(BinaryTree<T> bt){
		int size = BinaryTreeAlgo.size(bt);
		int height = BinaryTreeAlgo.height(bt);
		return (double) height/log2(size); 
	}
	
	public static int log2(int N)
	{
		return (int)(Math.log(N) / Math.log(2));
	}


}
