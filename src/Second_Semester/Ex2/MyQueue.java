package Second_Semester.Ex2;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue
{
	private int _length=0;
	private Queue<Integer> _queue;
	private int _max = 0;
	private int _min = 0;
	private int _sum = 0;

	/*
	 * Constructor for the class.
	 */
	public MyQueue()
	{
		Queue<Integer> _queue = new LinkedList<>();
	}
	
	/* Time Complexity: O(1)
	 * This function adds the integer to the last place of the queue (Which is O(1).
	 * The function checks if the added integer will be the new minimum or maximum of the queue.
	 * The function increment the length counter of the queue and adds the integer to the sum of the queue.
	 */
	public void add(Integer o)
	{
		_queue.add(o);				//O(1)
		_length++;					//O(1)
		if(_length == 1)			//O(1)
		{
			_min = o;
			_max = o;
		}
		else if(_min > (int) o)			//O(1)
		{
			_min = (int) o;				//O(1)
		}
		else if(_max < (int) o)			//O(1)
		{
			_max = (int) o;				//O(1)
		}
		_sum += (int) o;	 			//O(1)
	}
	
	/* Worst Case: O(n) - The removed object was either the minimum or the maximum integer of the queue.
	 * Best Case: O(1)	= The removed object was neither the minimum or the maximum integer of the queue.
	 * The function decrease the length counter of the queue by 1 and subtract the removed element from the sum.
	 * The function does nothing if the queue is empty.
	 */
	public void remove()
	{
		if(_length == 0)										//O(1)	
		{
			return;
		}
		Integer removed_element = _queue.remove();				//O(1)
		_length--;												//O(1)
		_sum -= (int) removed_element;							//O(1)
		if(_length == 0)										//O(1)
		{
			_max = 0;											//O(1)
			_min = 0;											//O(1)
		}	
		else if((int) removed_element < _min || (int) removed_element > _max)		//O(1)
		{
			for (int i = 1; i < _length; i++) 					//O(n)
			{
				 Integer check = _queue.remove();				//O(1)
				 if((int) check > _max)							//O(1)
				 {
					 _max = (int) check;						//O(1)
				 }
				 if((int) check < _min)							//O(1)
				 {
					 _min = (int) check;						//O(1)
				 }
				 _queue.add(check);								//O(1)
			}
		}
	}

	/* Time Complexity: O(1)
	 * This function checks if the queue is empty by checking if the length of the queue ( = the amount of elements in it) is 0.
	 */
	public boolean empty()
	{			
		return _length == 0;	//O(1)
	}
	
	/* Time Complexity: O(1)
	 * This function returns the _max variable which has the integer value of the max element in the queue.
	 * If there are no elements in the queue, the function will return 0 (the initial value of _max).
	 */
	public int getMax()
	{
		return _max;						//O(1)
	}
	
	/* Time Complexity: O(1)
	 * This function returns the _min variable which has the integer value of the minimum element in the queue.
	 * If there are no elements in the queue, the function will return 0 (the initial value of _min).
	 */
	public int getMin()
	{
		return _min;						//O(1)
	}
	
	/* Time Complexity: O(1)
	 * This function calculates the average of all of the elements in the queue - by dividing the sum of the elements with the length of the queue.
	 * If there are no elements in the queue, the function will return 0.
	 */
	public double getAvg()
	{
		if(_length == 0)					//O(1)
		{
			return 0;						//O(1)
		}
		return _sum/_length;				//O(1)
	}
	
	/* Time Complexity: O(1)
	 * This function return the integer value of the amount of elements in the queue which was calculated each time a node is added or removed.
	 */
	public int getLen()
	{
		return _length;						//O(1)
	}
}