package maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Practice in using maps.
 * 
 * @author Nate Chenette
 *
 */

public class NGramCounting {

	/**
	 * Given an input text and a length n, the method should produce a Map from
	 * n-grams of the text (i.e., length-n substrings) to counts, where n-gram S
	 * is mapped to count C if S shows up C times among substrings of the text.
	 * 
	 * This method would be useful in frequency-based cryptanalysis of the
	 * classic substitution cipher.
	 * 
	 * @param text
	 * @param n,
	 *            the length of the n-grams to count
	 * @return
	 */
	static Map<String, Integer> nGramCounter(String text, int n) {

		Map<String, Integer> m = new HashMap<String, Integer>();

		for (int i = 0; i < text.length() - n + 1; i++) {
			String s = text.substring(i, i+n);
			if (m.containsKey(s)) {
				int value = m.get(s);
				m.replace(s, value + 1);
			} else {
				m.putIfAbsent(s, 1);
			}

		}
		return m;
	}
}
