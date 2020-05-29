package recursion;

/**
 * This class, based on one by Cay Horstmann in Big Java, represents a sentence.
 * 
 * @author Curt Clifton. Created Nov 14, 2009.
 */
public class Sentence {
	private final String contents;

	/**
	 * Constructs a sentence object with the given contents.
	 * 
	 * @param contents
	 */
	public Sentence(String contents) {
		this.contents = contents;
	}

	/**
	 * Recursively calculates whether the given sentence is an anagram of this
	 * one.
	 * 
	 * Two sentences are anagrams if the characters in one are an exact
	 * rearrangement (permutation) of the characters in the other. This method
	 * ignores case, so that "Spot" and "Tops" are considered to be anagrams.
	 * 
	 * @param other
	 * @return whether the given sentence is an anagram of this one
	 */
	public boolean isAnagram(Sentence other) {
		// TODO: implement isAnagram() recursively
		if (this.isOnlyWhitespace() && other.isOnlyWhitespace()) {
			return true;
		}
		char first = Character.toLowerCase(this.contents.charAt(0));
		if (Character.isWhitespace(first)) {
			return this.dropFirstChar().isAnagram(other);
		}
		int indexOf = other.contents.toLowerCase().indexOf(first);
		if (indexOf < 0) {
			return false;
		}
		Sentence s1 = new Sentence(this.contents.substring(1));

		String otherFirstPart = other.contents.substring(0, indexOf);
		String otherSecondPart = other.contents.substring(indexOf + 1);
		Sentence s2 = new Sentence(otherFirstPart + otherSecondPart);

		return s1.isAnagram(s2);
	}

	/**
	 * @return true if all the characters in this sentence are whitespace
	 */
	private boolean isOnlyWhitespace() {
		char[] chs = this.contents.toCharArray();
		for (char c : chs) {
			if (!Character.isWhitespace(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return a new sentence like this one, but with the first character
	 *         removed
	 */
	public Sentence dropFirstChar() {
		return new Sentence(this.contents.substring(1));
	}

	/**
	 * @return a new sentence like this one, but with the last character removed
	 */
	public Sentence dropLastChar() {
		return new Sentence(this.contents.substring(0,
				this.contents.length() - 1));
	}
	
	/**
	 * @param n the index of the character to drop
	 * @return a new sentence like this one, but with the given character removed
	 */
	public Sentence dropChar(int n) {
		String firstPart = this.contents.substring(0, n);
		String secondPart = this.contents.substring(n + 1);
		return new Sentence(firstPart + secondPart);
	}
	
	/**
	 * @return the length of this sentence
	 */
	public int length() {
		return this.contents.length();
	}

	@Override
	public String toString() {
		return this.contents;
	}
	
	
}
