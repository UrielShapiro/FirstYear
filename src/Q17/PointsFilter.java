package Q17;

import java.util.ArrayList;

import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;

public class PointsFilter implements ShapeFilter {
	private Point2D[] _n;
	public PointsFilter(Point2D[] s)
	{
		_n=s;
	}
	@Override
	public boolean filter(GeoShapeable s) {
		Point2D[] arr =	s.getPoints();
		if(arr.length < _n.length)
		{
			return false;
		}
		ArrayList<Double> arr1 = new ArrayList<>();
		boolean contains = false;
		for (int i = 0; i < arr.length; i++) {
			contains = arr1.contains(_n[i]);
			if(!contains)
			{
				return false;
			}
		}
		return true;
	}

//	 public static GeoShapeable[] ff(GeoShapeable[] arr, filter f)
//	 {
//		 ArrayList<GeoShapeable> array = new ArrayList<>();
//		 for (int i = 0; i < arr.length; i++) {
//			if(f(arr[i]))
//			{
//				array.add(arr[i]);
//			}
//		}
//		 return (GeoShapeable[]) array.toArray();
//	 }

	
	
}
