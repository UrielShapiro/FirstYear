package PrepreationsForTest;

import java.util.ArrayList;

public class TestExamQ2 {

	public static void main(String[] args) {
		ArrayList<String> w = new ArrayList<>();
		String a = "Hello";
		w.add("He");
		w.add("llo");
		System.out.println(isPer(w,a));
	}
	public static boolean isPer(ArrayList<String> words, String line)
	{
		if(line.length()==0 && words.isEmpty())
		{
			return true;
		}
		int k = 0;
		for (int i = 0; i < words.size(); i++) {
			if(!line.contains(words.get(i)))
			{
				return false;
			}
			k+=words.get(i).length();
		}
		if(k!=line.length())
		{
			return false;
		}
		for (int i = 0; i < words.size(); i++) {
			if(line.startsWith(words.get(i)))
			{
				ArrayList<String> words1 = new ArrayList<String>(words);
				words1.remove(words.get(i));
				String line1 = line.substring(words.get(i).length());
				return isPer(words1,line1);
			}
		}
		return true;
	}

}
