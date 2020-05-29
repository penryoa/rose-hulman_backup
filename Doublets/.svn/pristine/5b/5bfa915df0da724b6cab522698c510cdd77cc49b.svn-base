import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Links implements LinksInterface {

	public String name;
	public HashSet<String> wordList;
	public HashMap<String, HashSet<String>> wordMap;

	public Links(String name) throws FileNotFoundException {
		this.name = name;
		this.wordMap = new HashMap<>();
		this.wordList = new HashSet<>();
		LinksHelper(name);

	}

	public void LinksHelper(String name) throws FileNotFoundException {
		FileReader file = new FileReader(this.name);
		Scanner s = new Scanner(file);

		while (s.hasNext()) {
			String word = s.nextLine();
			this.wordList.add(word);

		}
		
		for (String word : this.wordList) {
			HashSet<String> answerSet = new HashSet<String>();
			for (String wordCheck : this.wordList) {
				if (wordCheck.length() == word.length()) {
					int lettersMatching = 0;
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == wordCheck.charAt(i)) {
							lettersMatching++;
						}
					}
					if (lettersMatching == word.length() - 1) {
						answerSet.add(wordCheck);
					}
				}
			}
			if (answerSet.isEmpty()) {
				answerSet = null;
			}
			this.wordMap.put(word, answerSet);
		}
		
		s.close();
	}

	@Override
	public Set<String> getCandidates(String word) {
		return this.wordMap.get(word);
	}

	@Override
	public boolean exists(String word) {
		return this.wordMap.containsKey(word);
	}

}
