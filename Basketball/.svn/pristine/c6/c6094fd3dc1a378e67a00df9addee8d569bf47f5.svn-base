package stage1;
import java.util.Random;
/*
 * The BasketballUtility class provides static methods to get random numbers in a deterministic way.
 * This makes it possible to reproduce the same behaviors which is useful for writing tests
 * and for debugging.
 * 
 * Note: If you change the RANDOM_SEED constant then the random numbers will be very different
 */
public class BasketballUtility {
	
	//This ensures that the results of random events are deterministic and repeatable
	public static final long RANDOM_SEED = 1000;
	
	//This creates an Object of type Random which has methods to return random values
	private static Random rand = new Random(RANDOM_SEED);
	
	// If you use the line below instead the line above, 
	// then you will get different random numbers each time
	// However, the results will not be repeatable.
	//private static Random rand = new Random();
	
	/*
	 * Method that should be used in order to get a random double for use when
	 * modeling a basketball being shot 
	 * 
	 * @param no input parameters
	 * @return a double with a value between 0 (inclusive) and 1.0 (exclusive)
	 */
	public static double getRandomDouble() {
		return rand.nextDouble();
	}
	
	//Just to demonstrate how the number generator works
	public static void main(String[] args) {
		
		for (int i=0; i< 10; i++) {
			System.out.println( BasketballUtility.getRandomDouble()   );
		}
		
	}

}
