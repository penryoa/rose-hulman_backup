package misc;

public class Decorator_A_concreteBase extends Decorator_A_Base{

	@Override
	public String someMethodA() {
		return "from concrete base";
	}
	
	public String someMethodB() {
		return super.someMethodB() + " ho ho";
	}

}
