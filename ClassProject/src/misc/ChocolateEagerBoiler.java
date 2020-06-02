package misc;
 
public class ChocolateEagerBoiler {
	private boolean empty;
	private boolean boiled;
	private static ChocolateEagerBoiler uniqueInstance = new ChocolateEagerBoiler();
	
	private ChocolateEagerBoiler() {
		empty = true;
		boiled = false;
	}
  
	public static ChocolateEagerBoiler getInstance() {
		System.out.println("Returning instance of Chocolate Boiler");
		return uniqueInstance;
	}

	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			// fill the boiler with a milk/chocolate mixture
		}
	}
 
	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// drain the boiled milk and chocolate
			empty = true;
		}
	}
 
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			// bring the contents to a boil
			boiled = true;
		}
	}
  
	public boolean isEmpty() {
		return empty;
	}
 
	public boolean isBoiled() {
		return boiled;
	}
}
