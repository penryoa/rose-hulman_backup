import java.util.Set;

/**
 * The interface for the links within the dictionary It defines what words are
 * within the dictionary and what are candidates adjacent to a specific word
 * 
 * @author zhang
 *
 */
public interface LinksInterface {
	/**
	 * Returns all the words that are the same length as the 
	 * given word and differ from it by exactly 1 character.
	 * 
	 * @param word
	 * @return A set of words meeting the described conditions.
	 */
	public Set<String> getCandidates(String word);

	/**
	 * @param word
	 * @return true if the given word is in this dictionary
	 */
	public boolean exists(String word);
}
