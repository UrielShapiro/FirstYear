package Second_Semester;

public class HW2 {

	public static void main(String[] args) {
		//System.out.println(power2(4));
		System.out.println(reverseint(123456));
	}
	public static int power2(int i)
	{
		if(i==0)
		{
			return 1;
		}
		return 2*power2(i-1);
	}
	public static int reverseint(int i)
	{
		int max = 0;
		for (int j = 1; j < i; j=(int) Math.pow(j,10)) {
			if(i/j != 0)
			{
				max = j;
			}
		}
		int reveresed = 0;
		for (int j = max; j > 0; j=j/10) {
			reveresed += i%j;
		}
		return reveresed;
	}

}
