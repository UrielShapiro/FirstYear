package Second_Semester;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class Ex1Test {

	@RepeatedTest(value = 5000)
	void testOnes() {
		Random rand = new Random();
		int size = rand.nextInt(1,1000);
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {
			int j = rand.nextInt(0,9);
			arr[i] = j;
		}
		Arrays.sort(arr);
		int counter = 0;
		int random = rand.nextInt(0,9);
		for (int i = 0; i < arr.length; i++)
		{
			if(arr[i] == random)
			{
				counter++;
			}
		}
		if(counter != Ex1.ones(arr,random))
		{
			System.out.print(Arrays.toString(arr));
			System.out.println();
			System.out.println("counter:"+counter);
			System.out.println("ones: "+Ex1.ones(arr,random));
			System.out.println("random number: "+random);
		}
		assertEquals(counter,Ex1.ones(arr,random));
		assertEquals(0,Ex1.ones(arr, rand.nextInt(10,Integer.MAX_VALUE)));
	}
}
