package paperPart;

/**
 * Counts foos.
 * 
 * @author Curt Clifton. Created Nov 14, 2009.
 */
public class FooCounter {

	/**
	 * Starting point.
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) {
		System.out.println("_n_ __1__ __c1_ __2__ __c2_");
		for (int n = 1; n < 100; n++) {
			System.out.printf("%3d %5d %5d %5d %5d%n", n, countFoos1(n), n
					* (n - 1), countFoos2(n), (n*n - 3*n + 2) / 2);
		}
	}

	/**
	 * @param n
	 * @return n * (n-1)
	 */
	private static int countFoos1(int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				count++; // foo();
			}
		}
		return count;
	}

	/**
	 * @param n
	 * @return (n*n - 3*n + 2) / 2
	 */
	private static int countFoos2(int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i - 1; j++) {
				count++; // foo();
			}
		}
		return count;
	}

}
