package Exe.EX3;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MyMap2DTest 
{
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();
	public static final int BLUE = Color.BLUE.getRGB();

	@Test
	public void testInsideMatrix() 
	{
		//Test for the function that checks if a given point is inside the matrix borders.
		Random random = new Random();
		for(int i=0;i<1000;i++)
		{
			int x = random.nextInt(200)+1;
			int y = random.nextInt(200)+1;
			MyMap2D copyMap = new MyMap2D(x,y);
			int m = random.nextInt(400)-50;
			int n= random.nextInt(400)-50;
			boolean inside = true;
			if(m<0 || m>copyMap.getWidth() || n<0 || n>copyMap.getHeight())
			{
				inside = false;
			}
			boolean ans = copyMap.insideMatrix(m, n);
			assertEquals(ans,inside);
		}
	}

	@Test
	public void testFx() 
	{
		MyMap2D copyMap = new MyMap2D(200,200);
		assertEquals(copyMap.fx(3, 9, 55, 50),2609);
		assertEquals(copyMap.fx(95, 52, 5,11),-938);
		assertEquals(copyMap.fx(9, 13, 5,-1),17);
	}

	@Test
	public void testFy()
	{
		MyMap2D copyMap = new MyMap2D(200,200);
		assertEquals(copyMap.fy(97, 55, 13, 52), (double) 2501/26);
		assertEquals(copyMap.fy(8, 700, 7, 12), (double) -49.75);
		assertEquals(copyMap.fy(8, 8, 99, 3), (double) 115/3);
	}

	@Test
	public void testDrawSegment()
	{
		//This function test if the segment is full with no holes.

		//First Test
		MyMap2D copyMap = new MyMap2D(100,100);
		Point2D firstP = new Point2D(5.88671875,3.0546875);
		Point2D secondP = new Point2D(50.88671875,3.0546875);
		copyMap.drawSegment(firstP, secondP, 1);
		boolean ans1 = true;
		for(int i = firstP.ix();i<=secondP.ix();i++)
		{
			Point2D p = new Point2D(i,firstP.iy());
			if(copyMap.getPixel(p)!=1)
			{
				ans1=false;
			}
		}
		assertTrue(ans1);
		copyMap.fill(0);

		//Second Test
		Point2D p3 = new Point2D(4.96875,17.234375);
		Point2D p4 = new Point2D(13.015625,25.046875);
		copyMap.drawSegment(p3, p4, 1);
		boolean ans2 = true;
		for(int i = p3.ix();i<=p4.ix();i++)
		{
			for(int y = p3.iy();y<=p4.iy();y++)
			{
				Point2D p = new Point2D(i,y);
				if(copyMap.getPixel(p)!=1)
				{
					ans1=false;
				}
			}
		}
		assertTrue(ans2);
		copyMap.fill(0);

		//Third Test
		Point2D p5 = new Point2D(6.0625,36.0625);
		Point2D p6 = new Point2D(14.109375,27.859375);
		copyMap.drawSegment(p5, p6, 1);
		boolean ans3 = true;
		for(int i = p5.ix();i<=p6.ix();i++)
		{
			for(int y = p5.iy();y<=p6.iy();y--)
			{
				Point2D p = new Point2D(i,y);
				if(copyMap.getPixel(p)!=1)
				{
					ans3=false;
				}
			}
		}
		assertTrue(ans3);
	}

	@Test
	public void testDrawRect() 
	{
		MyMap2D copyMap = new MyMap2D(100,100);
		Random random = new Random();
		for(int i=0;i<1000;i++)
		{
			int x = random.nextInt(50)+1;
			int y = random.nextInt(50)+1;
			Point2D p1 = new Point2D(x,y);

			int m = random.nextInt(50)+1;
			int n= random.nextInt(50)+1;
			Point2D p2 = new Point2D(n,m);

			copyMap.fill(WHITE);
			copyMap.drawRect(p1, p2, BLACK);

			int q = random.nextInt(90)+1;
			int w = random.nextInt(90)+1;
			Point2D random_point = new Point2D(q,w);

			double Min_x = Math.min(p1.x(), p2.x());
			double Max_x = Math.max(p1.x(), p2.x());
			double Min_y = Math.min(p1.y(), p2.y());
			double Max_y = Math.max(p1.y(), p2.y());
			boolean inside_x = false;
			boolean inside_y = false;
			boolean painted = false;
			if(random_point.x()>=Min_x && random_point.x()<=Max_x)		//Checks if the random point is inside the rectangle borders
			{
				inside_x = true;
			}
			if(random_point.y()<=Max_y && random_point.y()>=Min_y)		//Checks if the random point is inside the rectangle borders
			{
				inside_y = true;
			}
			if(inside_x && inside_y)
			{
				if(copyMap.getPixel(random_point)==BLACK)
				{
					painted = true;
				}
				assertTrue(painted);
			}
			if(!inside_x || !inside_y)
			{
				if(copyMap.getPixel(random_point)==BLACK)
				{
					assertFalse(painted);
				}
			}
		}
	}

	@Test
	public void testDrawCircle()
	{
		//This test checks if a random point on the map is outside of the circle and painted from the drawCircle function.
		MyMap2D copyMap = new MyMap2D(200,200);
		Random random = new Random();
		for(int t = 0; t<1000; t++)
		{
			int x = random.nextInt(100);
			int y = random.nextInt(100);
			Point2D p1 = new Point2D(x,y);

			int m = random.nextInt(100);
			int n= random.nextInt(100);
			Point2D p2 = new Point2D(n,m);

			double rad = p1.distance(p2);

			int q = random.nextInt(150);
			int w = random.nextInt(150);
			Point2D random_point = new Point2D(q,w);
			copyMap.fill(WHITE);
			copyMap.drawCircle(p1, rad, BLACK);
			boolean inside = true;
			boolean not_painted = true;
			if(random_point.distance(p1)>rad)
			{
				inside = false;
				if(!inside && copyMap.getPixel(random_point)==BLACK)
				{
					not_painted=false;
				}
				else if(inside && !(copyMap.getPixel(random_point)==BLACK))
				{
					not_painted = false;
				}
			}
			assertTrue(not_painted);
		}
	}

	@Test
	public void testFillPoint2DInt() 
	{
		MyMap2D copyMap = new MyMap2D(100,100);
		copyMap.fill(WHITE);
		Point2D paint = new Point2D(30,84);
		int test1 = copyMap.fill(paint, BLACK);
		int counter = 0;
		for(int x = 0;x<copyMap.getWidth();x++)
		{	
			for(int y=0;y<copyMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if(copyMap.getPixel(p)==BLACK)
				{
					counter++;
				}
			}
		}
		assertEquals(counter,test1);

		copyMap.fill(WHITE);
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(100,100);
		copyMap.drawSegment(p1,p2,BLUE);
		Point2D paint1 = new Point2D(32.8984375,84.265625);
		int test2 = copyMap.fill(paint1, BLACK);
		int counter1 = 0;
		for(int x = 0;x<copyMap.getWidth();x++)
		{	
			for(int y=0;y<copyMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if(copyMap.getPixel(p)==BLACK)
				{
					counter1++;
				}
			}
		}
		assertEquals(counter1,test2);

		for(int i = 0; i<100; i++)
		{
			copyMap.fill(WHITE);
			Point2D p3 = new Point2D(Math.random()*100,0);
			Point2D p4 = new Point2D(100,Math.random()*100);
			copyMap.drawSegment(p3,p4,BLUE);
			Point2D paint2 = new Point2D(Math.random()*100,Math.random()*100);
			int test3 = copyMap.fill(paint2, BLACK);
			int counter2 = 0;
			for(int x = 0;x<copyMap.getWidth();x++)
			{	
				for(int y=0;y<copyMap.getHeight();y++) 
				{
					Point2D p = new Point2D(x,y);
					if(copyMap.getPixel(p)==BLACK)
					{
						counter2++;
					}
				}
			}
			assertEquals(counter2,test3);
		}
	}



	@Test
	public void testFillIntIntInt() {
		MyMap2D copyMap = new MyMap2D(100,100);
		for(int i = 0; i<100; i++)
		{
			copyMap.fill(WHITE);
			Point2D p1 = new Point2D(Math.random()*100,0);
			Point2D p2 = new Point2D(100,Math.random()*100);
			copyMap.drawSegment(p1,p2,BLUE);
			int test = copyMap.fill((int) Math.random()*100,(int) Math.random()*100, BLACK);
			int counter = 0;
			for(int x = 0;x<copyMap.getWidth();x++)
			{	
				for(int y=0;y<copyMap.getHeight();y++) 
				{
					Point2D p = new Point2D(x,y);
					if(copyMap.getPixel(p)==BLACK)
					{
						counter++;
					}
				}
			}
			assertEquals(counter,test);
		}
	}

	@Test
	public void testShortestPath() {
		MyMap2D copyMap = new MyMap2D(100,100);
		for(int i = 0; i<100; i++)
		{
			copyMap.fill(WHITE);
			Point2D p1 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D p2 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D[] test = copyMap.shortestPath(p1,p2);
			for(int m=0;m<test.length;m++)
			{
				copyMap.setPixel(test[m], BLACK);
			}
			int counter = 0;
			for(int x = 0;x<copyMap.getWidth();x++)
			{	
				for(int y=0;y<copyMap.getHeight();y++) 
				{
					Point2D p = new Point2D(x,y);
					if(copyMap.getPixel(p)==BLACK)
					{
						counter++;
					}
				}
			}
			assertEquals(test.length,counter);
		}

		MyMap2D Map1 = new MyMap2D(5,5);
		Map1.fill(WHITE);
		Point2D p3 = new Point2D(0,0);
		Point2D p4 = new Point2D(5,5);
		Point2D[] test1 = copyMap.shortestPath(p3,p4);
		assertEquals(test1.length,10);

		MyMap2D Map2 = new MyMap2D(7,7);
		Map2.fill(WHITE);
		Point2D p5 = new Point2D(2, 5);
		Point2D p6 = new Point2D(3, 1);
		Point2D segmentP1 = new Point2D(1, 3);
		Point2D segmentP2 = new Point2D(4, 3);
		Map2.drawSegment(segmentP1, segmentP2, BLUE);
		Point2D[] test2 = Map2.shortestPath(p5,p6);
		assertEquals(test2.length,9);
	}

	@Test
	public void testShortestPathDist() 
	{
		MyMap2D copyMap = new MyMap2D(100,100);
		for(int i = 0; i<100; i++)
		{
			copyMap.fill(WHITE);
			Point2D p1 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D p2 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D[] test = copyMap.shortestPath(p1,p2);
			int length = copyMap.shortestPathDist(p1,p2);
			for(int m=0;m<test.length;m++)
			{
				copyMap.setPixel(test[m], BLACK);
			}
			int counter = 0;
			for(int x = 0;x<copyMap.getWidth();x++)
			{	
				for(int y=0;y<copyMap.getHeight();y++) 
				{
					Point2D p = new Point2D(x,y);
					if(copyMap.getPixel(p)==BLACK)
					{
						counter++;
					}
				}
			}
			assertEquals(length,counter);
		}
		for(int i=0;i<100;i++)
		{
			MyMap2D Map1 = new MyMap2D(100,100);
			Map1.fill(WHITE);
			Point2D p3 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D p4 = new Point2D(Math.random()*100,Math.random()*100);
			Point2D[] test1 = copyMap.shortestPath(p3,p4);
			int length1 = copyMap.shortestPathDist(p3,p4);
			if(test1 != null)
			{
				assertEquals(test1.length,length1);
			}
		}
	}
	@Test
	public void testEqauls()
	{
		MyMap2D Map1 = new MyMap2D(5,5);
		MyMap2D Map2 = new MyMap2D(5,5);
		Map1.setPixel(1, 1, BLACK);
		boolean ans1 = Map1.equals(Map2);
		assertFalse(ans1);

		MyMap2D Map3 = new MyMap2D(5,5);
		MyMap2D Map4 = new MyMap2D(5,5);
		Map3.setPixel(1, 1, BLACK);
		Map4.setPixel(1, 1, BLACK);
		boolean ans2 = Map3.equals(Map4);
		assertTrue(ans2);

		MyMap2D Map5 = new MyMap2D(5,5);
		MyMap2D Map6 = new MyMap2D(5,5);
		for(int i=0;i<Map5.getHeight();i++)
		{
			for(int j=0;j<Map5.getWidth();j++)
			{
				Map3.setPixel(i,j, BLACK);
				Map4.setPixel(i,j, BLACK);
			}
		}
		boolean ans3 = Map5.equals(Map6);
		assertTrue(ans3);
	}
	@Test
	public void testCopy()
	{
		MyMap2D Map1 = new MyMap2D(5,5);
		MyMap2D Map2 = new MyMap2D(5,5);
		Map1.setPixel(1, 1, BLACK);
		Map2.copy(Map1);
		boolean ans1 = Map1.equals(Map2);
		assertTrue(ans1);

		MyMap2D Map3 = new MyMap2D(5,5);
		MyMap2D Map4 = new MyMap2D(5,5);
		Map4.setPixel(1, 1, BLACK);
		Map3.copy(Map4);
		Map4.setPixel(1, 2, BLACK);
		boolean ans2 = Map3.equals(Map4);
		assertFalse(ans2);
	}

	@Test
	public void testNextGenGol() 
	{
		MyMap2D OriginalMap = new MyMap2D(6,6);
		OriginalMap.fill(WHITE);
		//First Test
		OriginalMap.setPixel(1,1, BLACK);
		OriginalMap.setPixel(1,2, BLACK);
		OriginalMap.setPixel(2,2, BLACK);
		OriginalMap.setPixel(1,2, BLACK);
		for(int i=0;i<100;i++)
		{
			OriginalMap.nextGenGol(); 
		}
		boolean ans1 = true;
		for(int x = 0;x<OriginalMap.getWidth();x++)
		{	
			for(int y=0;y<OriginalMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if((x==1 && y==1) || (x==1 && y==2) || (x==2 && y==1) || (x==2 && y==2))
				{
					if(!(OriginalMap.getPixel(p)==BLACK))
					{
						ans1 = false;
					}
				}
				else {
					if(OriginalMap.getPixel(p)!=WHITE)
					{
						ans1=false;
					}
				}
			}
		}
		assertTrue(ans1);

		//Second Test
		OriginalMap.fill(WHITE);
		OriginalMap.setPixel(1,2, BLACK);
		OriginalMap.setPixel(2,3, BLACK);
		OriginalMap.setPixel(3,3, BLACK);
		OriginalMap.setPixel(4,2, BLACK);
		OriginalMap.setPixel(2,1, BLACK);
		OriginalMap.setPixel(3,1, BLACK);
		for(int i=0;i<100;i++)
		{
			OriginalMap.nextGenGol(); 
		}
		boolean ans2 = true;
		for(int x = 0;x<OriginalMap.getWidth();x++)
		{	
			for(int y=0;y<OriginalMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if((x==1 && y==2) || (x==2 && y==3) || (x==3 && y==3) || (x==4 && y==2) || (x==2 && y==1) || (x==3 && y==1))
				{
					if(!(OriginalMap.getPixel(p)==BLACK))
					{
						ans2 = false;
					}
				}
				else {
					if(OriginalMap.getPixel(p)!=WHITE)
					{
						ans2=false;
					}
				}
			}
		}
		assertTrue(ans2);

		//Third Test
		OriginalMap.fill(WHITE);
		OriginalMap.setPixel(1, 3, BLACK);
		OriginalMap.setPixel(2, 4, BLACK);
		OriginalMap.setPixel(3, 4, BLACK);
		OriginalMap.setPixel(4, 3, BLACK);
		OriginalMap.setPixel(4, 2, BLACK);
		OriginalMap.setPixel(3, 1, BLACK);
		OriginalMap.setPixel(2, 2, BLACK);
		for(int i=0;i<100;i++)
		{
			OriginalMap.nextGenGol(); 
		}
		boolean ans3 = true;
		for(int x = 0;x<OriginalMap.getWidth();x++)
		{	
			for(int y=0;y<OriginalMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if((x==1 && y==3) || (x==2 && y==4) || (x==3 && y==4) || (x==4 && y==3) || (x==4 && y==2) || (x==3 && y==1) || (x==2 && y==2))
				{
					if(!(OriginalMap.getPixel(p)==BLACK))
					{
						ans3 = false;
					}
				}
				else {
					if(OriginalMap.getPixel(p)!=WHITE)
					{
						ans3=false;
					}
				}
			}
		}
		assertTrue(ans3);

		//Forth Test
		OriginalMap.fill(WHITE);
		OriginalMap.setPixel(2, 4, BLACK);
		OriginalMap.setPixel(1, 4, BLACK);
		OriginalMap.setPixel(1, 3, BLACK);
		OriginalMap.setPixel(2, 2, BLACK);
		OriginalMap.setPixel(3, 3, BLACK);
		for(int i=0;i<100;i++)
		{
			OriginalMap.nextGenGol(); 
		}
		boolean ans4 = true;
		for(int x = 0;x<OriginalMap.getWidth();x++)
		{	
			for(int y=0;y<OriginalMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if((x==2 && y==4) || (x==1 && y==4) || (x==1 && y==3) || (x==2 && y==2) || (x==3 && y==3))
				{
					if(!(OriginalMap.getPixel(p)==BLACK))
					{
						ans4 = false;
					}
				}
				else {
					if(OriginalMap.getPixel(p)!=WHITE)
					{
						ans4=false;
					}
				}
			}
		}
		assertTrue(ans4);

		//Fifth Test
		OriginalMap.fill(WHITE);
		OriginalMap.setPixel(1,2, BLACK);
		OriginalMap.nextGenGol(); 
		boolean ans5 = true;
		for(int x = 0;x<OriginalMap.getWidth();x++)
		{	
			for(int y=0;y<OriginalMap.getHeight();y++) 
			{
				Point2D p = new Point2D(x,y);
				if(OriginalMap.getPixel(p)==BLACK)
				{
					ans5=false;
				}
			}
		}
		assertTrue(ans5);

	}
}
