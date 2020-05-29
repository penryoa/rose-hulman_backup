package evaluator;
import java.util.Scanner;


/**
 * Runs an interactive shell.
 *
 * @author Matt Boutell.
 */
public class Main {

	/**
	 * Runs an interactive shell.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: Let Eclipse help you fix these errors by creating the required classes and methods. 
		// Then implement those classes per the specification.
		String exp, type;
		Scanner scanner = new Scanner(System.in);
		Evaluator infixEvaluator = new InfixEvaluator();
		Evaluator postfixEvaluator = new PostfixEvaluator();
		
		while (true) {
			System.out.print("Enter (p)ostfix, (i)nfix, or (q)uit: ");
			type = scanner.nextLine();

			if (type.startsWith("q")) {
				break;
			}
			
			try {
				if (type.startsWith("p")) {
					System.out.println("Enter a space-delimited postfix expression containing integers and operators (+-*/^): ");
					exp = scanner.nextLine();
					System.out.println(postfixEvaluator.evaluate(exp));
				} else if (type.startsWith("i")) {
					System.out.println("Enter a space-delimited infix expression containing integers, parentheses, and operators (+-*/^): ");
					exp = scanner.nextLine();
					System.out.print(((InfixEvaluator)infixEvaluator).convertToPostfix(exp) + " = ");
					System.out.println(infixEvaluator.evaluate(exp));
				}
			} catch (ArithmeticException e) {
				System.out.println("Malformed expression");
			}
		}
		scanner.close();
	}
	
}
