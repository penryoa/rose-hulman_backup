package misc;

public class Decorator_main {
	public static void main(String[] args) {
		// Try both an abstract class decorator and interface decorator
		// ABSTRACT CLASS VERSION
		Decorator_A_Base baseInstanceA = new Decorator_A_concreteBase();
		baseInstanceA = new Decorator_A_concreteDecorator(baseInstanceA);
		System.out.println("Abstract class: " + baseInstanceA.someMethodB());
		//INTERFACE VERSION:
		Decorator_I_Base baseInstanceI = new Decorator_I_concreteBase();
		baseInstanceI = new Decorator_I_concreteDecorator(baseInstanceI);
		System.out.println("Interface: " + baseInstanceI.someMethodB());
		
	}
}
