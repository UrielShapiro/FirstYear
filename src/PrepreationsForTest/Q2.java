package PrepreationsForTest;

public class Q2 {

	public static void main(String[] args) 
	{
		System.out.println(q2("vvvvabbvvvvvv"));
	}
	public static String q2(String str)
	{
		if("".equals(str))
		{
			return "";
		}
		String ans = "";
		ans += str.charAt(0);
		int i = 1;
		while(i<str.length() && str.charAt(0)==str.charAt(i))
		{
			i++;
		}
		return ans+=q2(str.substring(i));
	}
}
