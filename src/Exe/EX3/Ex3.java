package Exe.EX3;

import java.awt.*;

/**
 * This class is a simple "inter-layer" connecting (aka simplifing) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * You should change this class!
 * 
 * @author
 * ID1: 314779745
 * ID2: 325723203
 */
public class Ex3 {
	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static final int YELLOW = Color.YELLOW.getRGB();
	public static final int RED = Color.RED.getRGB();
	public static final int GREEN = Color.GREEN.getRGB();
	public static final int BLUE = Color.BLUE.getRGB();


	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);

	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}

	public static void drawGrid(Map2D map) {
		int w = map.getWidth();
		int h = map.getHeight();
		for(int i=0;i<w;i++) {
			StdDraw_Ex3.line(i, 0, i, h);
		}
		for(int i=0;i<h;i++) {
			StdDraw_Ex3.line(0, i, w, i);
		}
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; }
		if(p.equals("White")) {_color = Color.WHITE; }
		if(p.equals("Black")) {_color = Color.BLACK; }
		if(p.equals("Red")) {_color = Color.RED; }
		if(p.equals("Yellow")) {_color = Color.YELLOW; }
		if(p.equals("Green")) {_color = Color.GREEN; }

		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}
		if(p.equals("Clear"))
		{
			_map.fill(WHITE);
		}
		drawArray(_map);
	}
	static Point2D p1;								//New point that will store the first click
	static Point2D p2;								//New point that will store the second click
	//Boolean variables that would set to true if the function they are related to was done. in order to reset p1 and p2.
	static boolean rect_drawn=false;
	static boolean circle_drawn=false;
	static boolean segment_drawn=false;
	static boolean shortestPath_drawn = false;
	public static void mouseClicked(Point2D p)
	{	
		//In almost all of the functions we used a method to save the first click and only then save the second click and activate the function.
		//We have done so in a way that if p1 is null, p will be save to p1. if p1 != null, the click will be saved to p2 and run the function before inserting p to p1.
		//After every function was done, the mode will be reseted.
		System.out.println(p);
		int col = _color.getRGB();					//Setting the color as what the user clicked previously.
		if(_mode.equals("ShortestPath"))
		{
			if(p1!=null)
			{
				p2=p;
				Point2D[] path = _map.shortestPath(p1, p2);			//Creating a new Point2D array that will have the Point2D array from the shortest path function.
				if(path!=null)										//If there is no path between p1 to p2, path array would be null and "No Path Exist" will be printed
				{
					for(int i=0;i<path.length;i++)
					{
						_map.setPixel(path[i], col);				//Paint all of the points that are in the path array. these point represent the shortest path between p1 to p2
					}
					if(_map.getPixel(p2)!=col && _map.shortestPathDist(p1, p2) !=0)			//A correction for an error that happens sometimes when the last point is not painted.
					{
						_map.setPixel(p2, col);
					}
					_mode="";										//Reset the mode									
					shortestPath_drawn=true;						//set shortestPath_drawn to true, in order to reset p1 and p2.
				}
				else
				{
					System.out.println("No Path Exist");			//If there is no path, the following messege will be printed.
				}
			}
			p1=p;													//Set p1 as p (will happen before inserting p to p2).
		}

		if(_mode.equals("Point")) {
			_map.setPixel(p,col );
		}
		if(_mode.equals("Fill")) {
			_map.fill(p, col);
			_mode = "none";
		}
		if(_mode.equals("Rect")) 
		{
			if(p1!=null)	
			{
				p2 = p;	
				_map.drawRect(p1,p2, col);		//Draw a rectangle using the 2 points in the given color.
				_mode="";						//Reset the mode.
				rect_drawn=true;				//Set rect_drawn to true, in order to reset p1 and p2.
			}
			p1=p;	
		}
		if(_mode.equals("Circle"))
		{
			if(p1!=null)	
			{
				p2 = p;	
				double rad = p1.distance(p2); 			//Rad is the distance between p1 which is the center of the circle to p2 which is at the end of the radius of the circle
				_map.drawCircle(p1,rad, col);			//Draw a circle using p1, a given radius and the set color.
				_mode="";								//Reset the mode.		
				circle_drawn=true;						//Set circle_drawn to true in order to reset p1 and p2.

			}
			p1=p;
		}
		if(_mode.equals("Segment")) 
		{
			if(p1!=null)
			{
				p2 = p;
				_map.drawSegment(p1, p2, col);			//Draw segment using 2 points and a selected color.
				_mode="";								//Reset the mode.
				segment_drawn = true;					//Set segment_drawn to true in order to reset p1 and p2.
			}
			p1=p;
		}
		if(_mode.equals("Gol")) 
		{
			_map.nextGenGol();							//Initialize a round of game of life.
		}
		drawArray(_map);
		if(rect_drawn || circle_drawn || segment_drawn || shortestPath_drawn)		//If one of the following functions has happned, we want to reset p1 and p2.
		{
			p1=null;
			p2=null;
			//Set the boolean variable to false in order to use them again later.
			rect_drawn=false;
			circle_drawn=false;
			segment_drawn =false;
			shortestPath_drawn=false;
		}
	}
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}