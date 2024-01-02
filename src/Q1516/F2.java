package Q1516;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class F2 implements Parabula{
	private double _a;
	private double _b;
	private double _c;

	public F2(double a, double b, double c)
	{
		_a = a;
		_b = b;
		_c = c;
	}
	public F2(double[] a)
	{
		_a = a[0];
		_b = a[1];
		_c = 0;
	}
	@Override
	public double f(double x) {
		return _a*Math.pow(x, 2)+_b*x+_c;
	}

	@Override
	public Parabula add(F2 p) {
		this._a += p._a;
		this._b += p._b;
		this._c += p._c;
		return null;
	}




	@Override
	public double[] get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double extream(Parabula p) {
		try
		{
			return (-_b+Math.sqrt(Math.pow(_b,2)-4*_a*_c))/2*_a;

		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
		return _a;
	}
	public static int numberOfRealRoots(F2 p) 
	{
		try {
			if(Math.sqrt(Math.pow(p._b,2)-4*p._a*p._c)==0)
			{
				return 1;
			}
			return 2;
		} catch (Exception e) {
			return 0;
		}
	}
	public double[] deriv(double[] a)
	{
		double[] arr = new double[2];
		arr[0] = 2*a[0];
		arr[1] = a[1];
		return arr;
	}
	public static void sort(ArrayList<F2> a)
	{
		Comparator<F2> excomp = new Comparator<F2>()
		{
			@Override
			public int compare(F2 o1, F2 o2) 
			{
				double x1 = o1.extream(o1);
				double x2 = o2.extream(o2);
				return Double.compare(o1.f(x1), o2.f(x2));
			}
		};
		Collections.sort(a,excomp);
	}
}
