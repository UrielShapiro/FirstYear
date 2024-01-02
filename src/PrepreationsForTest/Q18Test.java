package PrepreationsForTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import org.junit.jupiter.api.Test;

class Q18Test {

	@Test
	void testAllCodes() {
		String[] arr = Q18.allCodes();
		assertEquals(arr.length, factorial(5));
		ArrayList<String> arr2 = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			arr2.add(arr[i]);
		}
		Random r = new Random();
		for (int l = 0; l < r.nextInt(5, Integer.MAX_VALUE); l++) {
			String ans = "";
			String i = String.valueOf(r.nextInt(1,6));
			String j = String.valueOf(r.nextInt(1,6));
			String k = String.valueOf(r.nextInt(1,6));
			String m = String.valueOf(r.nextInt(1,6));
			if(!Objects.equals(m, k) && !Objects.equals(m, j) && !Objects.equals(m, i) && !Objects.equals(k, j) && !Objects.equals(k, i) && !Objects.equals(j, i))
			{
				ans = i+j+k+m+"#";
			}
			if(!ans.isEmpty())
			{
				System.out.println(ans);
				assertTrue(arr2.contains(ans));
			}
		}

	}
	int factorial(int num){
		if(num == 0){
			return 1;
		}
		return num*factorial(num-1);
	}

}
