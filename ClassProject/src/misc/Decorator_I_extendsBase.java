package misc;

public abstract class Decorator_I_extendsBase implements Decorator_I_Base {
	protected Decorator_I_Base instanceVar;
	
	public Decorator_I_extendsBase(Decorator_I_Base instVar) {
		this.instanceVar = instVar;
	}
	
	@Override
	public abstract String someMethodB();
}
