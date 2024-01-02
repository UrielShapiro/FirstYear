package Q17;

import Exe.Ex4.geo.GeoShapeable;

public class AreaFilter implements ShapeFilter {
	private GeoShapeable _n;
	public AreaFilter(GeoShapeable s)
	{
		_n=s;
	}
	
	@Override
	public boolean filter(GeoShapeable s) {
		return _n.area()<s.area();
	}

}
