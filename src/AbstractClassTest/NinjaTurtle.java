package AbstractClassTest;

public class NinjaTurtle extends Turtle {
	private int weapon;

	public NinjaTurtle(String n, int w, int c, int weap) {
		super(n, w, c);
		weapon = weap;
	}

	@Override
	public void roar()
	{
		System.out.println("kababanga");
	}
}
