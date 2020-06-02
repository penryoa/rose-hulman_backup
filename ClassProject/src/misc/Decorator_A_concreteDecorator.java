package misc;

public class Decorator_A_concreteDecorator extends Decorator_A_extendsBase{
	
	public Decorator_A_concreteDecorator(Decorator_A_Base var) {
		super(var);
	}

	@Override
	public String someMethodA() {
		return instanceVar.someMethodA();
	}

//	public String someMethodB() {
//		return instanceVar.someMethodB() + " AND deck the halls";
//	}

}
