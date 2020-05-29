
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Chain implements Iterable<String> {

	LinkedHashSet<String> wordSet;
	String useWord;
	
	public Chain() {
		this.wordSet = new LinkedHashSet<>();
		this.useWord = " ";
	}
	
	public Chain(LinkedHashSet<String> set, String word) {
		this.wordSet = new LinkedHashSet<>();
		for(String s : set){
			this.wordSet.add(s);
		}
		this.wordSet.add(word);
		this.useWord = word;

		
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub.

		return this.wordSet.iterator();
	}

	public int length() {
		// TODO Auto-generated method stub.
		return this.wordSet.size();
	}

	public Chain addLast(String string) {
		// TODO Auto-generated method stub.
		
		return new Chain(this.wordSet, string);
	}

	public String getLast() {
		// TODO Auto-generated method stub.
		if(wordSet.size() ==0){
			return null;
		}
		return this.useWord;
	}

	public boolean contains(String string) {
		// TODO Auto-generated method stub.
		return this.wordSet.contains(string);
	}
}
