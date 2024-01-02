package Q456;

import java.util.ArrayList;

public class Q5 {

	
	
	public static <T> void main(String[] args) {
		MyList<Object> a = new MyList<>();
		Double d = Double.MAX_VALUE;
		String s = String.valueOf(d);
		Integer i = Integer.MAX_VALUE;
		Long l = Long.MAX_VALUE;
		Character c = Character.MAX_VALUE;
		
		a.add(s);
		a.add(s);
		a.add(s);
		a.add(s);
		a.add(s);
		a.add(s);

		a.add(d);
		a.add(i);
		a.add(l);
		a.add(c);

		System.out.println(Q5(a));
	}
	public static <T> int Q5(MyListInterface <T> l) {
		ArrayList<String> ans = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			ans.add(l.get(i).getClass().getSimpleName());
			System.out.println(l.get(i).getClass().getSimpleName());
		}
		for (int i = 0; i < ans.size(); i++) {
			for (int j = i+1; j < ans.size(); j++) {
				if(ans.get(i).toString().equals(ans.get(j).toString()))
				{
					System.out.println("Removed " +ans.remove(j));
				}
			}
		}
		for (int i = 0; i < ans.size(); i++) {
			System.out.println("FINAL " +ans.get(i).toString());
		}
		return ans.size();
	}
}



