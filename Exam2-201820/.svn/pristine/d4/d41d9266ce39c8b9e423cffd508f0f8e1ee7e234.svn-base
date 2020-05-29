package polymorphism;

public class Dog extends Pet{
	
	private String name;

	public Dog(String name) {
		super("Dog", name);
		this.name = name;
	}
	
	public void doSpecialAbility() {
		System.out.print(this.name + " says: ");
		if (startsWithVowel(this.getName())) {
			System.out.println("Arf!");
		} else {
			System.out.println("Bow wow!");
		}
	}
	
	private boolean startsWithVowel(String s) {
		char ch = Character.toLowerCase(s.charAt(0));
		// When y is the first letter of a word, it isn't a vowel.
		return ch == 'a' || ch == 'e' || ch == 'i' 
				|| ch == 'o' || ch == 'u';
	}	
}
