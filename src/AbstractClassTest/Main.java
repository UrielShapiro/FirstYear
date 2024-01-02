package AbstractClassTest;

public class Main {
	public static void main(String[] args) {
		//O(1)
		//System.out.println(mult());
		//System.out.println(sum((double) 1/3));
		System.out.println(newsum());
//		for (int i = 0; i < 10; i++) {
//			System.out.println("array["+i+".add("+i+",a"+i+");");
//		}

	}
	public static double sum(double d)
	{
		double ans = 0;
		for (int i = 0; i < 10000; i++) 
		{
			ans += Math.pow(d,i);
		}
		return ans;
		
	}
	public static double newsum()
	{
		double ans = 0;
		for (int i = 2; i < 10000; i++) 
		{
			ans += (double) (Math.pow(-1,i))/(i);
		}
		return ans;
		
	}
	public static int mult()
	{
		int s1 = 1;
		int s2 = -1;
		int s3 = 1;
		int ans = 0;
		for (int i = 0; i < 9; i++)
		{
			s3 = s3*2;
		}
		for (int i = 0; i <= 9; i++) 
		{
			ans += s1*s2*s3;
			s1 = s1*4;
			s2=s2*-1;
			s3=s3/2;
		}
		return ans;
	}

}
