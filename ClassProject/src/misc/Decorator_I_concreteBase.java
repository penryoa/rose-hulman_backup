package misc;

public class Decorator_I_concreteBase implements Decorator_I_Base{
	@Override
	public String someMethodA() {
		return "from method A";
	}
	@Override
	public String someMethodB() {
		return "ho ho ho";
	}
}
