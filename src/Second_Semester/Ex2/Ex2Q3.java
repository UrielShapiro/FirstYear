package Second_Semester.Ex2;

import java.util.Stack;

public class Ex2Q3 {
	/*
	 * This function receives a string of size n. if n is an odd number, there is no way the string is balanced - therefore the function will return false.
	 * The function inserts the string, char by char, to a stack.
	 * If a closing bracket was put after the wrong opening bracket, the function will return false.
	 * If the closing bracket was in the right place (therefore the string is balanced until that index), the function will remove the opening bracket that match the closing bracket.
	 * The function will continue to check all of the characters in the string in the same way.
	 * If the stack has n/2 elements, and the next element is an opening bracket. there is no way the string is balanced (because even (n/2)-1 closing brackets won't be enough). Therefore the function will return false
	 * @return true if the stack is empty at the end of the run. false if the string is not balanced.
	 */
	
	public static boolean CatalnString(String s)
	{
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(stack.size() == s.length()/2 && (ch == '(' || ch == '[' || ch == '{' || ch == '<'))
			{
				return false;
			}
			if(ch == '(' || ch == '[' || ch == '{' || ch == '<')
			{
				stack.add(ch);
			}
			else if(ch == ')')
			{
				if(stack.isEmpty() || stack.pop() != '(')
				{
					return false;
				}
			}
			else if(ch == ']')
			{
				if(stack.isEmpty() || stack.pop() != '[')
				{
					return false;
				}
			}
			else if(ch == '}')
			{
				if(stack.isEmpty() || stack.pop() != '{')
				{
					return false;
				}
			}
			else if(ch == '>')
			{
				if(stack.isEmpty() || stack.pop() != '<')
				{
					return false;
				}
			}
		}
		return stack.isEmpty();
		
	}
}
