package Second_Semester;

public class Ex1 {
	/**
	 * This function calculates the amount of appearances of the integer k within a given integer array.
	 * The function first checks if k is in the array (using the Binary search function).
	 * If it is, the function will subtract the rightmost index which has the integer k with the leftmost index (and add 1 to compensate for the array index numbering).
	 * Time Complexity: O(log(n))
	 * @param a - Integer array
	 * @param k	- Integer to find the amount of appearances within the array
	 * @return	amount of times k appears within the array a.
	 */
	public static int ones (int[] a, int k)
	{	
		int index = BinarySearch(a,k,0,a.length-1);		//Binary Search to see if the requested numbers is in the array. will be used later to narrow the search range.
		if(index == -1)									//If the Binary search returns -1, the requested number is not in the array.
		{
			return 0;									//K is not in the array, 0 will be returned.
		}
		int right = RightBound(a,k,index,a.length-1);	//Searching for the rightmost index in which k is in, using narrowed range for time saving.
		int left = LeftBound(a,k,0,index);				//Searching for the leftmost index in which k is in, using narrowed range for time saving.
		return right - left + 1;						//returning the amount of k's that are between the right bound to the left bound (+1 to compensate the array index numbering)
	}
	/*
	 * This function returns the leftmost index in which k is in by using binary search.
	 * Time Complexity: O(log(n))
	 */
	private static int LeftBound(int[] a, int k, int low, int high)
	{
		if(low>high)									//The stop condition for the function, if low>high -> the number was not found and the function will return -1. This step is unnecessary because the index is definatly in the array if this function runs (because we have already checked it in the original Binary search)
		{
			return -1;
		}
		int middle = (low + high) / 2; 					//Finding the middle index between the given bounds
		if(k == a[middle] && (middle == 0 || a[middle-1] != k))		//Finding the leftmost index in which k is in, by returning an index only if a[middle-1] != k or middle is already the leftmost index 
		{
			return middle;
		}
		else if(k > a[middle])
		{
			return LeftBound(a,k,middle+1,high);		//Searching for the leftmost index by narrowing the range by 1 to the right
		}
		return LeftBound(a,k,low,middle-1);				//Searching for the leftmost index by narrowing the range by 1 to the left
	}
	/*
	 * This function returns the rightmost index in which k is in by using binary search.
	 * Time Complexity: O(log(n)).
	 */
	private static int RightBound(int[] a, int k, int low, int high)
	{
		if(low>high)									//The stop condition for the function, if low>high -> the number was not found and the function will return -1. This step is unnecessary because the index is definatly in the array if this function runs (because we have already checked it in the original Binary search)
		{
			return -1;
		}
		int middle = (low + high) / 2; 					//Finding the middle index between the given bounds
		if(k == a[middle] && (middle == a.length-1 || a[middle+1] != k))
		{
			return middle;
		}
		else if(k < a[middle])
		{
			return RightBound(a,k,low,middle-1);		//Searching for the rightmost index by narrowing the range by 1 to the left
		}
		return RightBound(a,k,middle+1,high);			//Searching for the rightmost index by narrowing the range by 1 to the right
	}
	
	/*
	 * Ordinary Binary search.
	 * If k is not in the array, the function will return -1.
	 * Time Complexity: O(log(n)).
	 */
	private static int BinarySearch(int[] a, int k, int low, int high)
	{
		if(low>high)									//The stop condition for the function, if low>high -> the number was not found and the function will return -1.
		{
			return -1;
		}
		int middle = (low + high) / 2; 					//middle is the average of low + high, that way we would "cut" the array by half each time we recursively enter the function
		if(k == a[middle])								//If k was found, the function will return the index in which k is in.
		{
			return middle;
		}
		else if(k < a[middle])							//if a[middle] > k, the function needs to search in the left half of the array - because the array is sorted
		{
			return BinarySearch(a,k,low,middle-1);
		}
		return BinarySearch(a,k,middle+1,high);			//If a[middle] < k, the function needs to search in the right half of the array - because the array is sorted
	}
}
