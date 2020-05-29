package evaluator;

import java.util.Stack;

public class PostfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {

		String[] splitLine = expression.split(" ");
		Stack<Integer> numbers = new Stack<Integer>();

		for (int i = 0; i < splitLine.length; i++) {
			try {
				int k = Integer.parseInt(splitLine[i]);
				numbers.push(k);
				
			} catch (NumberFormatException e) {
				
				if (numbers.isEmpty()) {
					throw new ArithmeticException();
				}
				
				int two = numbers.pop();
				
				if (numbers.isEmpty()) {
					throw new ArithmeticException();
				}
				
				int one = numbers.pop();

				
				if (splitLine[i].equals("+")) {
					numbers.push(one + two);
				}
				
				else if (splitLine[i].equals("-")) {
					numbers.push(one-two);
				}
				
				else if (splitLine[i].equals("*")) {
					numbers.push(one*two);
				}
				
				else if (splitLine[i].equals("/")) {
					numbers.push(one/two);
				}
				
				else if (splitLine[i].equals("^")) {
					numbers.push((int) Math.pow(one, two));
				}
			}
		}

		int top = numbers.pop();
		
		if (numbers.isEmpty()) {
			return top;
		} else {
			throw new ArithmeticException();
		}
	}

}
