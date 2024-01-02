package Exe.EX3;

import java.util.ArrayList;

/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. 
 * 
 * @author
 * ID1: 314779745
 * ID2: 325723203
 * */
public class MyMap2D implements Map2D{
	private int[][] _map;

	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}

	public MyMap2D(int[][] data) { 
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++)
		{
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++)
			{
				this.setPixel(x, y, arr[x][y]);					
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) 
	{ 
		if(x>=getWidth() || x<0 || y>=getHeight() || y<0)		//If the value of x or y that we would like to receive is outside of the matrix bounds, the function will return white. will be used for GOL (all cells out of bounds are dead)
		{
			return WHITE;
		}
		else 													//If the cell that we want to receive its color is inside the matrix bounds, the function will return it's color.
		{
			return _map[x][y];
		}
	}
	@Override
	public int getPixel(Point2D p) { 
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) 
	{
		if(x>=0 && x<getWidth() && y>=0 && y<getHeight())
		{
			_map[x][y] = v;
		}
	}
	public void setPixel(Point2D p, int v) 
	{ 
		setPixel(p.ix(), p.iy(), v);
	}
	public boolean insideMatrix(int x, int y)
	{ 
		//This function will return a boolean value if the asked coordinate is inside the matrix or outside.
		if(x>getWidth() || x<0 || y>getHeight() || y<0)			//The function will check if either x or y are outside of the map bounds
		{
			return false;										//If x or y are outside of the map bounds, the function will return false
		}
		else													//If x or y are not outside of the map bounderies, they are inside. the function will return true.
		{
			return true;
		}
	}
	public double fx(double x1, double y1, double x, double m)
	{
		//This function will compute the y value for given slope,x and a coordinate (x,y).
		//This function will return the y value.
		//This function will be used for the segment function in order to attach a y value of a given x on the line.
		double y = m*(x-x1)+y1;
		return y;
	}
	public double fy(double x1 , double y1 , double y , double m )
	{
		//This function will compute the x value for given slope,y and a coordinate (x,y).
		//This function will return the x value.
		//This function will be used for the segment function in order to attach a x value of a given y on the line.
		double x =(-y-m*x1+y1)/(-m);
		return x;
	}
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int v) 
	{
		//This function paints a segment between 2 given points on the map.
		//The function's algorithm calculates the slope between the 2 points and separate to cases.
		//The function is painting each dot between the 2 points by calculating the formula of the line that goes between the 2 points. Adding (or subtracting, depends on the case) 1 to the x or y (depends on the case) and finding the x or y that match that x or y on the line formula (using fy or fx functions). 
		double dx = p1.x()-p2.x();						//Calculates the delta between the y values of the 2 points
		double dy = p1.y()-p2.y();						//Calculates the delta between the x values of the 2 points
		double m=0;
		if(dx!=0)
		{
			m = dy/dx;							//Calculates the slope of the line that goes through the points. using the slope formula (delta y / delta x)
		} 
		double x_distance =Math.abs(p1.x()-p2.x());		//Calculate the distance between the x value of the points. will be used later to check which distance is greater, the x distance of the y distance (in order to separate to cases)
		double y_distance =Math.abs(p1.y()-p2.y());		//Calculate the distance between the y value of the points. will be used later to check which distance is greater, the x distance of the y distance (in order to separate to cases)
		if(Math.round(dy)==0)							//If the value of delta y is 0, the line is horizontal (parallel to x axis). the function will paint it.
		{
			for(int i = Math.min(p1.ix(), p2.ix());i<=Math.max(p1.ix(), p2.ix());i++)		//For loop that starts at the minimum x value and end at the maximum x value. each point between the 2 (in jumps of 1) will be painted
			{
				Point2D x_Line = new Point2D(i,p1.iy());									//Create a new point with the coordinates of i (which grows as the for loop continue) and the y of one of the points (the line is horizontal, therefore i doesn't matter which y we take between the points).
				setPixel(x_Line,v);															//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		if(Math.round(dx)==0)																////If the value of delta x is 0, the line is vertical (parallel to y axis). the function will paint it.														
		{
			for(int i = Math.min(p1.iy(), p2.iy());i<=Math.max(p1.iy(), p2.iy());i++)		//For loop that starts at the minimum y value and end at the maximum y value. each point between the 2 (in jumps of 1) will be painted
			{
				Point2D y_Line = new Point2D(p1.x(),i);										//Create a new point with the coordinates of i (which grows as the for loop continue) and the x of one of the points (the line is vertical, therefore i doesn't matter which x we take between the points).
				setPixel(y_Line,v);															//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		if(y_distance>x_distance)															//If the distance between the y of the points is greater then the distance between the x of the points. we will work with the y values in the for loop.
		{
			for(int i = Math.min(p1.iy(), p2.iy());i<=Math.max(p1.iy(), p2.iy());i++)		//For loop that runs from the minimum y value (the lower dot) and runs until it reaches the maximum y (that is the higher dot)
			{
				Point2D yLine = new Point2D(fy(p1.x(),p1.y(),i,m),i);						//Creating a new Point2D which has the x value that match the y value on the line formula (the i value here refers to y)
				setPixel(yLine,v);															//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		if(x_distance>y_distance)															//If the distance between the x of the points is greater then the distance between the y of the points. we will work with the x values in the for loop.
		{
			for(int i = Math.min(p1.ix(), p2.ix());i<=Math.max(p1.ix(), p2.ix());i++)		//For loop that runs from the minimum x value (the left dot) and runs until it reaches the maximum x (that is the right dot)	
			{
				Point2D xLine = new Point2D(i,fx(p1.x(),p1.y(),i,m));						//Creating a new Point2D which has the y value that match the x value on the line formula (the i value here refers to x)
				setPixel(xLine,v);															//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		if(m<0 && (y_distance>x_distance))													//If the slope is negative and the y distance is greater than the x distance, we would start painting from the highest y to the lowest.
		{
			for(int i = Math.max(p1.iy(), p2.iy());i<=Math.min(p1.iy(), p2.iy());i--)		//For loop that runs from the maximum y value to the minimum y value by subtracting 1 each time from the y value
			{
				Point2D yLine = new Point2D(fy(p1.x(),p1.y(),i,m),i);						//Creating a new Point2D which has the x value that match the y value on the line formula (the i value here refers to y)
				setPixel(yLine.ix(),yLine.iy(),v);											//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		if(m<0 && (x_distance>y_distance))													//If the slope is negative and the x distance is greater than the y distance, we would start painting from the highest x to the lowest.
		{
			for(int i = Math.max(p1.ix(), p2.ix());i<=Math.min(p1.ix(), p2.ix());i--)		//For loop that runs from the maximum x value to the minimum x value by subtracting 1 each time from the x value
			{
				Point2D yLine = new Point2D(i,fx(p1.x(),p1.y(),i,m));						//Creating a new Point2D which has the y value that match the x value on the line formula (the i value here refers to x)
				setPixel(yLine.ix(),yLine.iy(),v);											//Paint the dot in the coordinate we generated, with the given colour
			}
		}
		//Sometimes the algorithm doesn't paint the points that were clicked.
		//This is a quality assurance step that will paint these points if they were not already painted.
		if(getPixel(p1)!=v)
		{
			setPixel(p1,v);
		}
		if(getPixel(p2)!=v)
		{
			setPixel(p2,v);
		}
	}
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) 
	{		
		//This function draws a rectangle between 2 given points in a given color.
		//The function does so by painting every cell between the minimum cell to the maximum cell (every cell that is between the points)
		for(int i = Math.min(p1.ix(),p2.ix());i<=Math.max(p1.ix(),p2.ix());i++) 		//i equals to the minimum value of x and run until the maximum value of x. Each time add 1   
		{
			for(int j= Math.min(p1.iy(), p2.iy());j<=Math.max(p1.iy(), p2.iy());j++)	//j equals to the minimum value of y and run until the maximum value of y. Each time add 1   
			{
				setPixel(i,j,col);		// Paint the cell in place (i,j) with the given color.
			}
		}

	}

	@Override
	public void drawCircle(Point2D p, double rad, int col)
	{
		//This function draws a circle from a given point and a radius.
		//The function draws the circle by painting each cell that  <= distance to the center of the circle as the radius.
		if(rad>0)		//if the radius is negative or 0, there will be no circle to draw.
		{
			for(int x = 0;x<getWidth();x++) {			
				for(int y=0;y<getHeight();y++) {	
					Point2D p2 = new Point2D(x,y);		//Create a new point with the coordinates (x,y) that change every loop
					if(p.distance(p2)<=rad)				//If the distance between p and p2 is smaller then the radius, that cell will be painted.
					{
						_map[x][y]=col;						//Paint the cell in the given color.
					}
				}
			}
		}
	}
	@Override
	public int fill(Point2D p, int color) 
	{
		//This function fills all of the cells that are not blocked with a given color.
		//This function uses the breath search algorithm form the shortest path function
		MyMap2D copyMap = new MyMap2D(_map);			//Create a new map to "draw" the numbers on.
		for(int x = 0;x<getWidth();x++) 
		{
			for(int y = 0;y<getHeight();y++)
			{
				if(_map[x][y] == getPixel(p))
				{
					copyMap.setPixel(x, y,-1);			//Paint all of the "not blocked" cells in -1, this will mark that they are empty
				}
				else
				{
					copyMap.setPixel(x, y,-2);			//Paint all of the blocked cells in -2. this will mark that they are blocked.
				}
			}
		}
		copyMap.setPixel(p,0);							//Set the clicked cells as 0, will be used for the breath search.
		int entered = 1;								//entered variable will be >=1 if a given cell was painted.
		int counter = 0;								//This variable will be the radius of of the painted cells. will be used for the Dijkstra's algorithm
		while(entered!=0)								//This loop will run as long as there are cells to get in to.
		{
			entered=0;									//Initialising entered to 0
			for(int x = 0;x<getWidth(); x++) 
			{
				for(int y = 0;y<getHeight(); y++)
				{
					if(copyMap.getPixel(x,y)==counter)								//If the function has reached the counter, it will check its neighbour cells to see if they are empty (-1), if they are, the function will paint them.
					{
						if(insideMatrix(x+1,y) && copyMap.getPixel(x+1, y) == -1)	//The function will check the surrounding cells to see if they are empty and are inside the map.
						{
							copyMap.setPixel(x+1, y, counter+1);					//Paint that cell with counter +1	
							entered++;												//A cell was entered, therefore we want to raise our entered variable by 1.
						}
						if(insideMatrix(x-1,y) && copyMap.getPixel(x-1, y) == -1)
						{
							copyMap.setPixel(x-1, y, counter+1);
							entered++;
						}
						if(insideMatrix(x,y+1) && copyMap.getPixel(x, y+1) == -1)
						{
							copyMap.setPixel(x, y+1, counter+1);
							entered++;
						}
						if(insideMatrix(x,y-1) && copyMap.getPixel(x, y-1) == -1)
						{
							copyMap.setPixel(x, y-1, counter+1);
							entered++;
						}
					}
				}
			}
			if(entered>0)															//If entered > 0, on that loop, cells were painted. therefore we want to increase our counter to paint the next cells
			{
				counter++;
			}
		}
		int painted = 0;							//Will count the amount of cells that were painted in order to return that value.
		for(int x = 0;x<getWidth(); x++) 
		{
			for(int y = 0;y<getHeight(); y++)
			{
				Point2D newPoint = new Point2D(x,y);
				if(copyMap.getPixel(newPoint)>=0)				//The function will paint all of the non-blocked cells, therefore they were all painted with positive numbers.
				{
					_map[x][y]=color;
					painted++;									//Counter for how many cells were painted
				}
			}
		}
		return painted;											//return the amount of painted cells
	}

	@Override
	public int fill(int x, int y, int color) 
	{
		//This function paints a given area using an x coordinate and y coordinate.
		//This function converts the coordinates to a Point2D object and uses the fill function using that Point2D.
		Point2D p = new Point2D(x,y);
		return fill(p,color);
	}
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2)
	{
		//This function computes the shortest path from 2 given points.
		//The path goes only through same coloured cells
		//The function returns an array of points that represent the path from the first point to the second point
		//This function uses Dijkstra's algorithm in order to calculate the shortest path.
		MyMap2D copyMap = new MyMap2D(_map);				//Creating a duplicate map in order to "paint" the path on it and not on the original.
		int color = copyMap.getPixel(p1);					//A variable that will contain the color of the first point that was clicked. will be used later.
		if(copyMap.getPixel(p2) != copyMap.getPixel(p1))	//If the color of both  point is not equal, we want to return a null array, because there will be no path.
		{
			return null;
		}
		if(p2.equals(p1))									//If the first point and the second point are the same point, the path will be only one point.
		{
			Point2D[] arr = new Point2D[1];					//Creating an array with one cell, to store the only point that is in the "path"
			arr[0]=p1;										//storing the first point in the first cell in the array
			return arr;										//return the array which includes only the first point.
		}
		//If none of the conditions above match, the function will "paint" all of the cells that are in the same color as the first point with -1.
		//All of the cells that are not in the first point color, will be treated as "blocks" and will be "painted" with -2. that way we can notice between them.
		for(int x = 0;x<copyMap.getWidth();x++) 			//For loop that runs on all of the cells in the array
		{
			for(int y = 0;y<copyMap.getHeight();y++)
			{
				if(_map[x][y] == color)						//If the current cell in the map is in the same color as the first point, the function will "paint" it with -1, this will symbol that we an go through this cell (it is not blocked)
				{
					copyMap.setPixel(x, y,-1);				//"Painting" the cell with -1.
				}
				else										//If the cell is in every other color that is not the same as the first point color, the fucntion will "paint" it with -2, this will symbol that this cell is blocked.
				{	
					copyMap.setPixel(x, y,-2);				//"Painting" the cell with -2.
				}
			}
		}
		copyMap.setPixel(p1,0);								//Setting the first point as 0.
		int entered = 1;									//Variable that will count if the function has entered a given cell
		int counter = 0;									//Variable that will count to how much cells we reached using the "Breath search"
		while(entered!=0 && copyMap.getPixel(p2)== -1)		//This loop will run as long as there are cells that are being painted and p2 which represent the end is not painted
		{
			entered=0;										//Initialise entered value to 0
			for(int x = 0;x<getWidth(); x++) 
			{
				for(int y = 0;y<getHeight(); y++)
				{
					Point2D p = new Point2D(x,y);
					if(copyMap.getPixel(p)==counter)								//If the function has reached the counter, it will check its neighbour cells to see if they are empty (-1), if they are, the function will paint them.
					{
						if(insideMatrix(x+1,y) && copyMap.getPixel(x+1, y) == -1)	//The function will check the surrounding cells to see if they are empty and are inside the map.
						{
							copyMap.setPixel(x+1, y, counter+1);					//Paint that cell with the value of counter +1 (because the distance to that point is higher by 1)
							entered++;												//A cell was entered, therefore we want to raise "entered" value by 1.
						}
						if(insideMatrix(x-1,y) && copyMap.getPixel(x-1, y) == -1)
						{
							copyMap.setPixel(x-1, y, counter+1);
							entered++;
						}
						if(insideMatrix(x,y+1) && copyMap.getPixel(x, y+1) == -1)
						{
							copyMap.setPixel(x, y+1, counter+1);
							entered++;
						}
						if(insideMatrix(x,y-1) && copyMap.getPixel(x, y-1) == -1)
						{
							copyMap.setPixel(x, y-1, counter+1);
							entered++;
						}
					}
				}
			}
			if(entered>0)						//If entered > 0, on that loop, cells were painted. therefore we want to increase our counter to paint the next cells
			{
				counter++;
			}
		}
		Point2D[] path_arr = new Point2D[counter+1];					//Set a new Point2D array that will include all of the points that represent the path between p1 to p2.
		path_arr[counter]=p2;											//Set the last cell in the array to be p2, which is the end of the path.
		for(int cell_index = counter-1;cell_index>=0;cell_index--)
		{
			//In this for loop, the function will insert points to the path array.
			//The function will check in each of the surrounding cells to p2, which has the value of p2 -1 and insert that Point2D to the array.
			//Afterwards, the function will set p2 as the new inserted point, in order to check if the surrounding points of that point should be inserted.
			if(insideMatrix(p2.ix(),p2.iy()-1) && copyMap.getPixel(p2.ix(),p2.iy()-1) == cell_index)
			{
				path_arr[cell_index]= new Point2D(p2.ix(),p2.iy()-1);
				p2=path_arr[cell_index];
			}
			else if(insideMatrix(p2.ix(),p2.iy()+1) && copyMap.getPixel(p2.ix(), p2.iy()+1) == cell_index)
			{
				path_arr[cell_index]= new Point2D(p2.ix(),p2.iy()+1);
				p2=path_arr[cell_index];
			}
			else if(insideMatrix(p2.ix()+1,p2.iy()) && copyMap.getPixel(p2.ix()+1, p2.iy()) == cell_index)
			{
				path_arr[cell_index]= new Point2D(p2.ix()+1,p2.iy());
				p2=path_arr[cell_index];
			}
			else if(insideMatrix(p2.ix()-1,p2.iy()) && copyMap.getPixel(p2.ix()-1, p2.iy()) == cell_index)
			{
				path_arr[cell_index]= new Point2D(p2.ix()-1,p2.iy());
				p2=path_arr[cell_index];
			}
		}
		ArrayList<Point2D> path = new ArrayList<Point2D>();			//Set a new ArrayList in order to remove all of the null cells from the path array
		for(int j = 0; j<path_arr.length-1;j++)
		{
			if(path_arr[j]!=null)									//If the cell value isn't null, insert it to the new path array.
			{
				path.add(path_arr[j]);
			}
		}
		if(!path.contains(p2))										//Sometimes p2 is not inserted, therefore it should fix it.
		{
			path.add(p2);
		}
		Point2D[] newPath = new Point2D[path.size()];				//Create a new array to return at the end.
		for(int j = 0; j<path.size();j++)
		{
			path.toArray(newPath);									//Convert the ArrayList array to the normal array.
		}
		return newPath;												//Return the path array
	}
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		if(shortestPath(p1,p2) != null)
		{
			return shortestPath(p1,p2).length; 	//The length of the array in shortestPath function is the number of points between the 2 points.
		}
		else									//If there is no path between the two points, the function will return 0
		{
			return 0;
		}
	}

	@Override
	public void nextGenGol()
	{
		for(int x = 0;x<getWidth();x++) 	 
		{
			for(int y = 0;y<getHeight();y++)	
			{
				if(getPixel(x,y) != WHITE)				//If a cell in the map is not white, we want to paint it in black, to match game of life
				{
					setPixel(x,y,BLACK);
				}
			}
		}
		MyMap2D copyMap = new MyMap2D(_map);					//Setting a new map that we would check the game parameters on. That way, we can change the original map without "hurting" the game (if we change one of the cells and continue to check, it can change if we would "kill" or "alive" a neighbour cell)
		int neighbor_counter = 0; 								//integer that will count the number of neighbours to each tested cell
		for(int x = 0;x<getWidth();x++) 
		{
			for(int y = 0;y<getHeight();y++)	
			{
				//The function will check the neighbours of each cells, if they are black - they are alive and will be counted.
				//Every cell beyond the map borders is counted as white. was changed in the getPixel function.
				if(copyMap.getPixel(x+1,y) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x-1,y) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x,y+1) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x,y-1) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x+1,y+1) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x+1,y-1) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x-1,y+1) == BLACK)	
				{
					neighbor_counter++;	
				}
				if(copyMap.getPixel(x-1,y-1) == BLACK)
				{
					neighbor_counter++;	
				}

				if(copyMap.getPixel(x, y) == BLACK)						//If the cell is black (alive), the function will check its neighbour count
				{
					if(neighbor_counter < 2 || neighbor_counter > 3)	//If the number of the neighbor_counter is smaller then 2 or the neighbor_counter is bigger then 3, the cell should die in the next round.
					{
						setPixel(x,y,WHITE);							//Kill that cell.
					}
					neighbor_counter=0;									//Reset the neighbor_counter to 0 in order to check the next cell
				}
				if(copyMap.getPixel(x,y) == WHITE)						//If the cell is white (dead), the function will check its neighbour count to see if it should be alive next round
				{
					if(neighbor_counter==3)								//If the number of the neighbor_counter is 3, that cell should be alive in the next round
					{
						setPixel(x,y,BLACK);							//Make that cell "Alive" 
					}
					neighbor_counter=0;									//Reset the neighbor_counter to 0 in order to check the next cell
				}
			}
		}
	}

	@Override
	public void fill(int c)
	{
		//This function fills the entire map with a given color.
		//c = color.
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);				//Running over all of the map and changing each cell to the color c.
			}
		}

	}

	////////////////////////////////////////////////////////USED FOR TESTS//////////////////////////////////////////////

	public boolean equals(MyMap2D map1)		
	{
		//This function returns if a given map is equal in its size and painted cells to the map that the function was activated on.
		boolean ans = true;				//We assume the maps are equal
		boolean dimensions = false;
		if(map1.getHeight() == this.getHeight() && map1.getWidth()==this.getWidth())
		{
			dimensions = true;				//The function will check if the maps have the same dimensions.
		}
		for(int x = 0;x<map1.getWidth();x++) 
		{
			for(int y = 0;y<map1.getHeight();y++) 
			{
				if(dimensions && map1.getPixel(x, y) != this.getPixel(x, y))
				{
					ans=false;				//The function will check if the maps have the same colour in every cell, if they do, they are equal
				}
			}
		}
		if(dimensions == false)				//If the maps are not in the same dimensions, they are not the same
		{
			ans =false;
		}
		return ans;
	}
	public void copy(MyMap2D map1)		
	{
		//Copy constructor that copies map1 values to a map that this function was activated on.
		boolean dimensions = false;				//We can copy one map to an other only if their dimensions are equal, we assume they are not.
		if(map1.getHeight() == this.getHeight() && map1.getWidth()==this.getWidth())
		{
			dimensions = true;					//If their dimensions are equal we would continue to copy.
		}
		if(dimensions)
		{
			for(int x = 0;x<map1.getWidth();x++) 
			{
				for(int y = 0;y<map1.getHeight();y++) 
				{
					this.setPixel(x, y, map1.getPixel(x, y));			//Copy each cell in the given map to the other map.
				}
			}
		}
	}
}