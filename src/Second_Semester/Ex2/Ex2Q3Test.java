package Second_Semester.Ex2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class Ex2Q3Test {
	@RepeatedTest(50)
	@Test
	void testCatalnString() {
		char[] arr = new char[6];
		arr[0] = '(';
		arr[3] = ')';
		arr[1] = '[';
		arr[4] = ']';
		arr[2] = '{';
		arr[5] = '}';
		Random r = new Random();
		String s = "";
		for (int i = 0; i < r.nextInt(2,200); i++) {
			s += arr[r.nextInt(0,2)];
		}
		assertFalse(Ex2Q3.CatalnString(s));
		s = "";
		for (int i = 0; i < r.nextInt(2,200); i++) {
			s += arr[r.nextInt(3,5)];
		}
		assertFalse(Ex2Q3.CatalnString(s));
		s = "";
		for (int i = 0; i < r.nextInt(2,200); i++) {
			int rand = r.nextInt(0,2);
			s += arr[rand];
			if(arr[rand] == '(')
			{
				s += ')';
			}
			else if(arr[rand] == '[')
			{
				s += ']';
			}
			else if(arr[rand] == '{')
			{
				s += '}';
			}
		}
		assertTrue(Ex2Q3.CatalnString(s));
		s = "";
		for (int i = 0; i < r.nextInt(2,200); i++) {
			int rand = r.nextInt(0,2);
			int rand2 = r.nextInt(2,50);
			for (int j = 0; j < rand2 ; j++) {
				s += arr[rand];
			}
			for (int j = 0; j < rand2 ; j++) {
				if(arr[rand] == '(')
				{
					s += ')';
				}
				else if(arr[rand] == '[')
				{
					s += ']';
				}
				else if(arr[rand] == '{')
				{
					s += '}';
				}
			}
		}
		assertTrue(Ex2Q3.CatalnString(s));

		//		boolean ans = true;
		//		for (int i = 0; i < s.length()-1; i++) {
		//			char c = s.charAt(i);
		//			for (int j = i+1; j < s.length()-1; j++) {
		//				if(c == '(')
		//				{
		//					while(s.charAt(j) != ')')
		//					{
		//						if(j < s.length()-1 && (s.charAt(j) == ']' || s.charAt(j) == '}'))
		//						{
		//							ans = false;
		//						}
		//						j++;
		//					}
		//				}
		//				else if(c == '[')
		//				{
		//					while(s.charAt(j) != ']')
		//					{
		//						if(j < s.length()-1 &&(s.charAt(j) == ')' || s.charAt(j) == '}'))
		//						{
		//							ans = false;
		//						}
		//						j++;
		//					}
		//				}
		//				else if(c == '{')
		//				{
		//					while(s.charAt(j) != '}')
		//					{
		//						if(j < s.length()-1 &&(s.charAt(j) == ']' || s.charAt(j) == ')'))
		//						{
		//							ans = false;
		//						}
		//						j++;
		//					}
		//				}
		//			}
		//		}
		//		assertEquals(Ex2Q3.CatalnString(s),ans);

		//		String st = "((([[]]){}))";
		//		assertTrue(Ex2Q3.CatalnString(st));
		//		String str = "({[]})";
		//		assertTrue(Ex2Q3.CatalnString(str));
	}

}
