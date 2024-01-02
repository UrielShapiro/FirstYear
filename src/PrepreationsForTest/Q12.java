package PrepreationsForTest;

public class Q12 {

	public static void main(String[] args) {
		System.out.println(Q12("acbbt"));
	}
	public static String Q12(String s)
	{
		int[] abc = new int[26];
		for (int i = 0; i < s.length(); i++) 
		{
			abc[s.charAt(i)-'a']++;
		}
		String ans = "";
		for (int i = 0; i < s.length(); i++) {
			if(abc[s.charAt(i)-'a']==1)
			{
				ans += s.charAt(i);
			}
		}
		return ans;
	}
}
	
//	public static String Q12(String s)
//	{
//		ArrayList<Character> ans = new ArrayList<>();
//		ans.add(s.charAt(0));
//		boolean equal = false;;
//		for (int i = 1; i < s.length(); i++) 
//		{
//			for (int j = 0; j < ans.size(); j++)
//			{
//				if(ans.isEmpty())
//				{
//					ans.add(s.charAt(i));
//				}
//				if(ans.get(j)==s.charAt(i))
//				{
//					ans.remove(j);
//					equal = false;
//				}
//				else
//				{
//					equal = true;
//				}
//			}
//			if(equal)
//			{
//				ans.add(s.charAt(i));
//			}
//		}
//		String realAns = "";
//		for (int i = 0; i < ans.size(); i++) {
//			realAns+=ans.get(i);
//		}
//		return realAns;

	
	//	public static String Q12(String s)
	//	{
	//		String ans = "";
	//		ans+=s.charAt(0);
	//		boolean NotDuplicate = false;
	//		for (int i = 1; i < s.length(); i++) 
	//		{
	//			for (int j = 0; j < ans.length(); j++)
	//			{
	//				if(ans.charAt(j)!=s.charAt(i))
	//				{
	//					NotDuplicate = true;
	//				}
	//				else
	//				{
	//					NotDuplicate = false;
	//				}
	//			}
	//			if(NotDuplicate)
	//			{
	//				ans+=s.charAt(i);
	//			}
	//		}
	//		return ans;
	//		
	//	}



