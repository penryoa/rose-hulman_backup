
public class BadFrac {
	public int numerator;
	public int denominator;
	
	public BadFrac() {
		this.numerator = 0;
		this.denominator = 1;
	}
	
	public BadFrac(int val1, int val2) {
		this.numerator = val1;
		this.denominator = val2;
	}

	public static int gcm(int a, int b) {
	    return b == 0 ? a : gcm(b, a % b);
	}
	
	
	//TODO 1: Write some unit tests in BadFracTest.java to determine the problem
	/**
	 * The method should return true when the fraction has been reduced to its lowest terms
	 * 
	 * Examples of fractions where this is true:
	 * 	1/2, 5/1, 2/5, 0/1
	 * 
	 * Examples of fractions where this is false:
	 * 2/4, 6/2, 0/2
	 * 
	 * @return whether the fraction is reduced
	 */
	public boolean isReduced() {
		if(this.numerator == 0){
			if (this.denominator == 1)
				return true;
		}
		
		int val = gcm(this.numerator, this.denominator);
		System.out.println(val);
		if(gcm(this.numerator, this.denominator) == 1)
			return true;
		return false;
	}
	
	public BadFrac add(int num, int den) {
		BadFrac var1 = new BadFrac(num, den);
		return add(var1);
	}
	
	//TODO2: Write some unit tests in BadFracTest.java to discover what is wrong
	/**
	 * Taking in another fraction, this method should add the two fractions
	 * and return a NEW BadFrac type with the resulting REDUCED fraction.
	 * If the fraction is NOT reduced, the result is considered incorrect.
	 * 
	 * @param incoming (a BadFrac type)
	 * @return new fraction in lowest terms as a BadFrac type
	 */
	public BadFrac add(BadFrac incoming) {
		int commonDen = this.denominator * incoming.denominator;
		int newNum = (this.numerator*incoming.denominator) + (incoming.numerator*this.denominator);
		
		int gcd = gcm(newNum, commonDen);
		
		newNum = newNum/gcd;
		commonDen = commonDen/gcd;
		
		return new BadFrac(newNum, commonDen);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + denominator;
		result = prime * result + numerator;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadFrac other = (BadFrac) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BadFrac [numerator=" + numerator + ", denominator=" + denominator + "]";
	}
}
