package PrepreationsForTest;

public class Q6 {

	public static void main(String[] args) {
		System.out.println("is Prime: "+isPrime(10));

		System.out.println("sphenic number: "+Q6(42));
	}
	
	public static boolean Q6(int n)
	{
		int a = 0;
		int b = 0;
		int c = 0;
		if(n<=2)
		{
			return false;
		}
		else
		{
			for (int j = 1; j < n; j++) 
			{
				if(isPrime(j) && n % j == 0)
				{
					if(b==0 && c==0 && a==0)
					{
						a=j;
					}
					else if(b==0 && c==0)
					{
						b=j;
					}
					else if(c==0)
					{
						c=j;
					}
				}
			}
		}
		return a*b*c==n;
	}
	
	public static boolean isPrime(int i)
	{
		if(i<=1)
		{
			return false;
		}
		else
		{
			for (int j = 2; j <= (int) Math.sqrt(i); j++) 
			{
				if(i % j==0)
				{
					return false;
				}
			}
			return true;
		}
	}
}
