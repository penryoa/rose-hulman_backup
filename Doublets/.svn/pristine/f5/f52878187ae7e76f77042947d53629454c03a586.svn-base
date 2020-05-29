import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueChainManager extends ChainManager {

	private String endWord;
	private PriorityQueue<Chain> queue;

	public PriorityQueueChainManager(String s) {
		super();
		this.endWord = s;
		Entry entry = new Entry(this.endWord);
		this.queue = new PriorityQueue<Chain>(entry);
	}

	@Override
	public void add(Chain chain) {
		this.queue.offer(chain);
		updateMax(this.queue.size());

	}

	@Override
	public Chain next() {
		if (!this.isEmpty()) {
			this.incrementNumNexts();
			return this.queue.poll();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.queue.isEmpty());
	}

	public class Entry implements Comparator<Chain> {

		private String endWordEntry;

		public Entry(String endWord) {
			this.endWordEntry = endWord;
		}

		public int distanceEstimator(String word) {
			int count = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == this.endWordEntry.charAt(i)) {
					count++;
				}
			}
			return count;
		}

		@Override
		public int compare(Chain arg0, Chain arg1) {
			int arg0Value = distanceEstimator(arg0.getLast());
			int arg1Value = distanceEstimator(arg1.getLast());
			return (arg1.length() + arg1Value) - (arg0.length() + arg0Value);

		}
	}
}
