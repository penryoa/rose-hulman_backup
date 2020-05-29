import java.util.LinkedList;
import java.util.Queue;

public class QueueChainManager extends ChainManager{

	private int size = 0;
	private Queue<Chain> words = new LinkedList<Chain>();
	
	
	@Override
	public void add(Chain chain) {
		this.words.offer(chain);
		this.size++;
		this.updateMax(this.size);
	}

	@Override
	public Chain next() {
		this.size--;
		this.incrementNumNexts();
		return this.words.poll();
		
	}

	@Override
	public boolean isEmpty() {
		return (this.words.isEmpty());
	}

}
