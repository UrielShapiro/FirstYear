package PrepreationsForTest;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class TestingMain {

	public static void main(String[] args) {
		String a1 = "aaaaa";
		String a2 = "a";
		System.out.println(a1.contains(a2));
		a1.trim();
		
		
		
		
		
		System.out.println("Write Something: ");
		Scanner sc = new Scanner(System.in);
		String one = sc.next();
		System.out.println("Write Another thing: ");
		String two = sc.next();
		sc.close();
		if(one.compareTo(two)==0)
		{
			System.out.println("The strings are equal");
		}
		else
		{
			System.out.println("The strings are not equal");

		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String name = "Heyyouanimals";
		//System.out.println(name.trim());
		//System.out.println(name.concat(" you"));
		char[] ch = name.toCharArray();
		List<Character> l = new LinkedList<Character>();
		for (int i = 0; i < ch.length; i++) {
			l.add(ch[i]);
		}
		for (int i = 0; i < ch.length; i++) {
			System.out.print(l.get(i));

		}
		System.out.println();


		Comparator<Character> comperator = new Comparator<Character>()
		{
			@Override
			public int compare(Character o1, Character o2) {
				return Character.compare(o1, o2);
			}
		};
		l.sort(comperator);
		for (int i = 0; i < ch.length; i++) {
			System.out.print(l.get(i));

		}


	}
}
