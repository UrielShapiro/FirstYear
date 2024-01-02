package PrepreationsForTest;

import java.util.ArrayList;
import java.util.Random;

public class Q7 {

	public static void main(String[] args) {
		Random r = new Random();
		int random = r.nextInt(5,200);
		double[] arr = new double[random];
		for (int i = 0; i < random; i++) {
			arr[i] = i*r.nextDouble();
		}
		for (int i = 0; i < Q7(arr).length; i++) {
			System.out.println(Q7(arr)[i]);

		}
	}
	public static double[] Q7(double[] arr)
	{
		if(arr.length==1)
		{
			return arr;
		}
		ArrayList<Double> max = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			ArrayList<Double> a = new ArrayList<>();
			while(i < arr.length)
			{
				if(i+1<arr.length && arr[i]<arr[i+1])
				{
					a.add(arr[i]);
					i++;
				}
				else
				{
					break;	
				}
			}
			if(a.size()>max.size())
			{
				max.addAll(a);
			}
		}
		double[] ans = new double[max.size()];
		for (int j = 0; j < max.size(); j++) {
			ans[j] = max.get(j);
		}

		return ans;
	}
}
