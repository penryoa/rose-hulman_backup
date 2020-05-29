package polymorphism;

public class Fish extends Pet {
	
	private String name;
	private int numWiggles;

	public Fish(String name, int numWiggles) {
		super("Fish", name);
		this.name = name;
		this.numWiggles = numWiggles;
	}

	public void doSpecialAbility() {
		System.out.println(this.name + " says, time to move!");
		for (int j = 1; j <= this.numWiggles; j++) {
			System.out.println("Wiggle " + j);
		}
	}
}
