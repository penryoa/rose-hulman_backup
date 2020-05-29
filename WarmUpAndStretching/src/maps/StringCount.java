package maps;


/**
 * For use in the frequency analysis demo of NGramCountingTests.
 * 
 * No need to change anything here.
 * 
 * @author Nate Chenette
 *
 */

public class StringCount implements Comparable<StringCount> {
	String str;
	Integer count;

	public StringCount(String s, Integer ct) {
		str = s;
		count = ct;
	}
	
	public String getStr() {
		return str;
	}
	
	public Integer getCount() {
		return count;
	}

	@Override
	public int compareTo(StringCount other) {
		return this.count.compareTo(other.count);
	}
	
	
}