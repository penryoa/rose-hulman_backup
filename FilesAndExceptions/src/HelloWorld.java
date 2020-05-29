
public class HelloWorld {
	public static void main(String[] args) {
		try {
			System.out.println(1 / 0);
		} catch (ArithmeticException e) {
			System.out.println("Tried to divide by 0");
		}
	}
}
