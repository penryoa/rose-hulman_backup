
public class Frac4162018 {

	public static void main(String[] args) {
		double x0 = (Math.sqrt(5)-1)/2;
		double x = x0;
		double c = -2;
		int num_orb = 50;

		for (int k = 0; k < num_orb; k++) {
			System.out.println("x" + k + ": " + x);
			x = Math.pow(x, 2) + c;
		}

		// EXAMPLES IN CLASS (c = -2)
		// x0 = -2: eventual fixed point
		// x0 = -1: fixed
		// x0 = 0: eventual fixed point (?)
		// x0 = 1: eventual fixed point (?)
		// x0 = 2: fixed point
		// x0 = (sqrt(5)-1)/2: period 2 orbit
		// x0 = 1.99: everywhere in [-2, 2]
		// x0 = 2.01: infinity
		// x0 = 1.5: everywhere in [-2, 2]
		// x0 = -0.9: everywhere in [-2, 2]

	}

}
