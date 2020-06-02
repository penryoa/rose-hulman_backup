package misc;

public class Adapter implements ITarget{
	
	Adaptee a;
	
	public Adapter(Adaptee a) {
		this.a = a;
	}
	
	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

}
