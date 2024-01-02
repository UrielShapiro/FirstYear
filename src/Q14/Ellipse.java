package Q14;

import Exe.Ex4.geo.Point2D;

public class Ellipse {
	private Point2D _p;
	private Point2D _q;
	private double _n;
	
	public Ellipse(int px, int py, int qx, int qy, double n)
	{
		Point2D p = new Point2D(px,py);
		Point2D q = new Point2D(qx,qy);
		_p = p;
		_q = q;
		_n = n;
	}
	public Ellipse(Ellipse e)
	{
		_p = new Point2D(e._p);
		_q = new Point2D(e._q);
		_n = e._n;
	}
	public int where(Point2D p)
	{
		if(p.distance(this._p)+p.distance(this._q)==this._n)
		{
			return 0;
		}
		if(p.distance(this._p)+p.distance(this._q)>this._n)
		{
			return 1;
		}
		return -1;
	}
	  public boolean equals(Ellipse e)
	  {
		  if(e._n != _n)
		  {
			  return false;
		  }
		  if((e._p.equals(_p) && e._q.equals(_q)) || (e._p.equals(_q) && e._q.equals(_p)))
		  {
			  return true;
		  }
		  else
		  {
			  return false;
		  }
	  }



}
