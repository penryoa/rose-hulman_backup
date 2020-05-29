package polymorphism;

public class Cat extends Pet {

	private String name;

	public Cat(String name) {
		super("Cat", name);
		this.name = name;
	}

	public void doSpecialAbility() {
		System.out.println(this.name + " says: Yawn. Zzz...");
	}
}
