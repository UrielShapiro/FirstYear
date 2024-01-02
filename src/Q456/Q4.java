package Q456;

public class Q4 {

	public static <T> void main(String[] args) {
		MyListInterface<T> l = new MyList<T>();
		l.add((T) "A");
		l.add((T) "B");
		l.add((T) "C");
		System.out.println(isSet(l));

	}
	public static <T> boolean isSet(MyListInterface<T>  l) 
	{
		for (int i = 0; i < l.size(); i++) {
			for (int j = i+1; j < l.size(); j++) {
				if(l.get(i).equals(l.get(j)))
				{
					return false;
				}
			}
		}
		return true;
	}
	//DOESNT WORK DUE TO "T" Object
//	public static <T> MyListInterface<T> toSet(MyListInterface<T>  l) 
//	{
//		MyList<T> ans = new MyList<>();
//		ans.add(l.get(0));													
//		for (int i = 0; i < ans.size(); i++) 
//		{
//			for (int j = 1; j < l.size(); j++) 
//			{
//				if(!(ans.get(i).equals(l.get(j))))
//				{
//					ans.add(l.get(j));
//				}
//			}
//		}
//		return ans;
//	}
}


