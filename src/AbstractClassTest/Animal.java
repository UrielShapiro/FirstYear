package AbstractClassTest;

public abstract class Animal {
	protected String name;
	protected int weight;
	private int num_of_animals;
	
	public Animal(String n, int w)
	{
		name =n;
		weight = w;
		num_of_animals++;
	}
	
	public abstract void roar();
}
