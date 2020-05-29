package comparingShapes;

/**
 * An implement of triangles with area and perimeter.
 * 
 * @author boutell. Created Dec 1, 2013.
 */
public class Triangle {
	private double a, b, c; // 3 sides

	/**
	 * Creates a triangle with the given sides.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * 
	 */
	public Triangle(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}


	/**
	 * @return The area of this triangle.
	 */
	public double area() {
		// TODO: Implement this. Hint: lookup and use Heron's formula. 
		return -17.0;
	}

	/**
	 * @return The perimeter of this triangle.
	 */
	public double perimeter() {
		return this.a + this.b + this.c;
	}

	@Override
	public String toString() {
		return String.format("Triangle with a=%.2f,b=%.2f,c=%.2f", this.a,
				this.b, this.c);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Triangle)) {
			return false;
		}
		Triangle other = (Triangle)obj;
		// CONSIDER: not very robust, but works for testing purposes. 
		return this.a == other.a && this.b == other.b && this.c == other.c;
	}

	

}
