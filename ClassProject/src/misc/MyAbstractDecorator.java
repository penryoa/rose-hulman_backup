package misc;

public abstract class MyAbstractDecorator extends ConcreteClass {
	
	private ConcreteClass decorated;
	
	MyAbstractDecorator(ConcreteClass clazz) {
		this.decorated = clazz;
	}
	
	@Override
	public void method1() {
		
	}
	
	@Override
	public void method2() {
		
	}
	
}
