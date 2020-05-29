package polymorphism;

public abstract class Pet {
	private String animal;
	private String name;
	private boolean isEating;
	
	public Pet(String animal, String name) {
		this.animal = animal;
		this.name = name;
		this.isEating = false;
	}
	
	public String getName() {
		return this.name;
	}
	public boolean isEating() {
		return this.isEating;
	}
	public void eatFood() {
		System.out.println(this.animal + " " + this.name + " is eating food.");
		this.isEating = true;
	}
	public void doSpecialAbility() {
	}
}
