package Second_Semester.Ex2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class Ex2Q4Test {
	public static Random r = new Random();
	
	public static double[] RandomArray()
	{
		double[] arr = new double[r.nextInt(2,500)];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextDouble(0,9);
		}
		return arr;
		
	}
	@RepeatedTest(50)
	@Test
	void testQ4Sort() {
		double[] arr = RandomArray();
		double[] compare1 = new double[arr.length];
		double[] compare2 = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			compare1[i] = arr[i];
			compare2[i] = arr[i];
		}
		Arrays.sort(compare1);
		Ex2Q4.Q4Sort(compare2);
		for (int i = 0; i < arr.length; i++) {
			if((int) compare1[i] != (int) compare2[i])
			{
				System.out.println("compare1: " + compare1[i]);
				System.out.println("compare2: " + compare2[i]);

			}
			assertEquals((int) compare1[i],(int) compare2[i]);
		}
	}

	//@Test
//	void testRemove() {
//		double[] arr =  RandomArray();
//		Ex2Q4.Q4Sort(arr);
//		int remove = r.nextInt(0,9);
//		int counter = 0;
//		for (int i = 0; i < arr.length; i++) {
//			if((int) arr[i] == remove)
//			{
//				counter++;
//			}
//		}
//		assertTrue(Ex2Q4.remove(remove)[remove]==null);
		
	}
//
//	@Test
//	void testAdd() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testReturnRange() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRange() {
//		fail("Not yet implemented");
//	}
