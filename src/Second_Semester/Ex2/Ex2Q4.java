package Second_Semester.Ex2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ex2Q4 
{
	private static LinkedList[] array = new LinkedList[10];

	/*
	 * Time Complexity: O(n+m) <= O(2n) = O(n)
	 */
	public static void Q4Sort(double[] double_array)
	{
		for (int j = 0; j < array.length; j++) 						//O(1)
		{
			array[j] = new LinkedList<Double>();			
		}
		for (int i = 0; i < double_array.length; i++) {				//O(n) - n = double_array.length
			array[(int) double_array[i]].add(double_array[i]);		
		}
		int index = 0;												
		for (int j = 0; j <= 9; j++) {								//O(1)
			for (int i = 0; i < array[j].size(); i++) {				//O(m) -> for m <= n
				double_array[index++] = (double) array[j].get(i);	
			}
		}
	}

	/*
	 * Time Complexity: O(1)
	 */
	public static void remove(int i)
	{
		array[i] = new LinkedList<Double>();		//O(1)
	}

	/*
	 * Time Complexity: O(1)
	 */
	public void add(double i)
	{
		array[(int) i].add(i); 		//O(1)
	}

	/*
	 * Time Complexity: O(n)
	 */
	public double[] returnRange(int min_range, int max_range)
	{
		if(min_range > max_range || min_range < 0 || max_range > 9)	//O(1)
		{
			return new double[0];
		}
		ArrayList<Double> nums_in_range = new ArrayList<>();
		for (int i = min_range; i <= max_range; i++) 				//O(1)
		{
			for (int j = 0; j < array[i].size(); j++) 				//O(n)
			{
				nums_in_range.add((Double) array[i].get(i));		//Will run maximum n times - if min_range = 0, and max_range = 9
			}
		}
		double[] ans = new double[nums_in_range.size()];
		for (int i = 0; i < nums_in_range.size(); i++) 				//O(n)
		{			
			ans[i] = nums_in_range.get(i);
		}
		return ans;
	}

	/*
	 * Time Complexity: O(1)
	 */
	public int Range()
	{
		int counter = 0;
		for (int i = 0; i <= 9; i++)				//O(1)
		{
			if(!array[i].isEmpty())					//O(1)
			{
				counter++;
			}
		}
		return counter;

	}
}
