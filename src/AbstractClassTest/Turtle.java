package AbstractClassTest;

public class Turtle extends Animal {
	private int carapace_size;

	public Turtle(String n, int w, int c) {
		super(n, w);
		carapace_size = c;
	}
	@Override
	public void roar() {
		System.out.println("Squik");		
	}

}
