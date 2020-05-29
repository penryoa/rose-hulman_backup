package list;

/**
 * Demonstrates function objects.
 */
public class Matchers {
	/**
	 * @return an instance of ListItemMatcher that matches strings with more
	 *         than 3 characters
	 */
	public static ListItemMatcher<String> longStringMatcher() {
		// TODO: implement
		// return null;
		return new ListItemMatcher<String>() {
			public boolean isMatch(String element) {
				return element.length() > 3;
			}
		};
	}
}
