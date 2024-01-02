package PrepreationsForTest;

import java.util.ArrayList;

public class Q18 {

	public static void main(String[] args) {

		String[] arr = allCodes();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(" "+arr[i]);	

		}
	}
	public static String[] allCodes()
	{
		ArrayList<String> arr = new ArrayList<>();
		String ans = "";
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				for (int k = 1; k < 6; k++) {
					for (int m = 1; m < 6; m++) {
						if(m != k && m!=j && m!=i && k!=j && k!=i && j!=i)
						{
							ans = String.valueOf(m)+String.valueOf(k)+String.valueOf(j)+String.valueOf(i)+"#";
							arr.add(ans);
							ans = "";
						}
					}
				}
			}
		}
		String[] finalarr = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			finalarr[i]=arr.get(i);
		}

		return finalarr;

	}
}
