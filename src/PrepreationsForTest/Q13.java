package PrepreationsForTest;

import java.util.Comparator;
import java.util.Random;

public class Q13 {

	public static void main(String[] args) {
		Comparator<Integer> comp = (o1, o2) -> Integer.compare(o1, o2);
		Random r = new Random();
		int[] p = new int[r.nextInt(5, 20)];
		for (int i = 0; i < p.length; i++) {
			p[i] = r.nextInt(-1000,1000);
			System.out.print(p[i] + " ");
		}
		System.out.println();
		mySort(p,comp);
		for (int i = 0; i < p.length; i++) {
			System.out.print(p[i] + " ");
		}
	}
	public static void mySort(int[] p, Comparator<Integer> comp)
	{
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p.length; j++) {
				int k = comp.compare(p[i],p[j]);
				if(k<0)
				{
					int temp = p[j];
					p[j] = p[i];
					p[i]= temp;
				}
			}
		}
	}

}
