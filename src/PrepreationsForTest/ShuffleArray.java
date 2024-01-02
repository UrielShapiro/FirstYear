package PrepreationsForTest;

import java.util.Random;

public class ShuffleArray {

	public static void main(String[] args) {
		int[] arr = new int[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		shuffle(arr);
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	public static void shuffle(int[] arr)
	{
		Random r = new Random();
		for (int i = 0; i < arr.length; i++) {
			int index = r.nextInt(0,arr.length);
			int temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		}
	}
}
