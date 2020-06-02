package misc;

public abstract class Decorator_A_extendsBase extends Decorator_A_Base{
	protected Decorator_A_Base instanceVar;
	
	public Decorator_A_extendsBase(Decorator_A_Base instVar) {
		this.instanceVar = instVar;
	}
	
	public abstract String someMethodA();
}
