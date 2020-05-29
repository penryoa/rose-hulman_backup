package evaluator;

import java.util.Stack;

public class InfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		// TODO Auto-generated method stub.
		String newString = (String) convertToPostfix(expression);
		PostfixEvaluator post = new PostfixEvaluator();
		return post.evaluate(newString);
	}

	public Object convertToPostfix(String string) {
		String[] splitLine = string.split(" ");
		String s = new String();
		Stack<String> operators = new Stack<String>();

		// Loop through entire splitLine
		for (int i = 0; i < splitLine.length; i++) {
			try {
				// This handles operands.
				int k = Integer.parseInt(splitLine[i]);
				s += Integer.toString(k) + " ";

			} catch (NumberFormatException e) {
				// This handles operators.
				String current = splitLine[i];

				// If operator stack is empty, push the current operator on the
				// stack.
				if (operators.isEmpty()) {
					operators.push(current);
				}

				// If the operator stack isnt empty...
				else {

					String topOfStack = operators.peek();

					
					if (current.equals(")")) {
						String topOfStackTemp = operators.pop();
						while (!topOfStackTemp.equals("(")) {
							s += topOfStackTemp + " ";
							topOfStackTemp = operators.pop();
						}
												
					}
					
					// If the current operator's precedence is higher than that
					// of the top of the stack, or if the current operator is an
					// open parenthese, just push the current operator to the
					// stack.
					if ((checkPrec(current) >= checkPrec(topOfStack) && !current.equals(")") || current.equals("("))) {
						operators.push(current);

					} else {
						// Pops out all the operators that are more important
						// than the current operator.
						while (checkPrec(current) < checkPrec(topOfStack)) {
							s += operators.pop() + " ";
							if (!operators.isEmpty()) {
								topOfStack = operators.peek();
							} else {
								break;
							}
							
						}
						// !!!! HERE IS AN ISSUE. PLZ FIX 
						operators.push(current);
					}

					
				}
			}
		}

		while (!operators.isEmpty()) {
			s += operators.pop() + " ";
		}

		if (s.endsWith(" ")) {
			s = (String) s.subSequence(0, s.length() - 1);
		}

		return s;
	}

	public int checkPrec(String s) {
		if (s.equals("+") || s.equals("-")) {
			return 1;
		} else if (s.equals("*") || s.equals("/")) {
			return 2;
		} else if (s.equals("^")) {
			return 3;
		} 
		
		return -1;
	}

	/**
	 * public Object convertToPostfix(String exp) { // initializing empty String
	 * for result String result = new String("");
	 * 
	 * // initializing empty stack Stack<Character> stack = new Stack<>();
	 * 
	 * for (int i = 0; i < exp.length(); ++i) { char c = exp.charAt(i);
	 * 
	 * // If the scanned character is an operand, add it to output. if
	 * (Character.isLetterOrDigit(c)) result += c;
	 * 
	 * // If the scanned character is an '(', push it to the stack. else if (c
	 * == '(') stack.push(c);
	 * 
	 * // If the scanned character is an ')', pop and output from the stack //
	 * until an '(' is encountered. else if (c == ')') { while (!stack.isEmpty()
	 * && stack.peek() != '(') result += stack.pop();
	 * 
	 * if (!stack.isEmpty() && stack.peek() != '(') return "Invalid Expression";
	 * // invalid expression else stack.pop(); } else // an operator is
	 * encountered { while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek()))
	 * result += stack.pop(); stack.push(c); }
	 * 
	 * }
	 * 
	 * // pop all the operators from the stack while (!stack.isEmpty()) result
	 * += stack.pop();
	 * 
	 * return result; }
	 * 
	 * static int Prec(char ch) { switch (ch) { case '+': case '-': return 1;
	 * 
	 * case '*': case '/': return 2;
	 * 
	 * case '^': return 3; } return -1; }
	 */
}