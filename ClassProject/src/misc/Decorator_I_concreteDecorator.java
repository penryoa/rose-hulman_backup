package misc;

public class Decorator_I_concreteDecorator extends Decorator_I_extendsBase {
	
	public Decorator_I_concreteDecorator(Decorator_I_Base instVar) {
		super(instVar);
	}
	
	@Override
	public String someMethodA() {
		return instanceVar.someMethodA();
	}
	
	@Override
	public String someMethodB() {
		return instanceVar.someMethodB() + " AND deck the halls";
	}



}
