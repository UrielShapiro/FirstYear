package PrepreationsForTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShuffleArrayTest {

	@Test
	void testShuffle() {
		for (int i = 0; i < 10000; i++) 
		{
			int[] normal = new int[100];
			int[] shuffled = new int[100];
			int counter = 0;
			for (int j = 0; j < 100; j++) {
				normal[j] = j;
				shuffled[j] = j;
			}
			ShuffleArray.shuffle(shuffled);
			for (int k = 0; k < shuffled.length; k++) 
			{
				if(shuffled[k] != normal[k])
				{
					counter++;
				}
			}
			assertTrue(counter>50);
		}
	}
}
