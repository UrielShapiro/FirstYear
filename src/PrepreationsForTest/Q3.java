package PrepreationsForTest;

public class Q3 {

	public static void main(String[] args) 
	{
		System.out.println(Q3("aaaaaaaabab","ab"));
	}
	public static int Q3(String s1, String s2)
	{
		int ans = 0;
		if(!s1.contains(s2))
		{
			return 0;
		}
		else
		{
			char[] arr = s1.toCharArray();
			char[] comp = s2.toCharArray();
			int j=0;
			boolean equals = false;
			for (int i = 0; i < arr.length; i++) {
				if((i<=(arr.length-comp.length) && arr[i]==comp[j]))
				{
					equals = true;
					for (int l = 0; l < comp.length; l++) {
						if(arr[i+l]!=comp[l])
						{
							equals = false;
						}
					}
					if(equals)
					{
						ans++;
					}
				}
			}
		}
		return ans;
	}
}
