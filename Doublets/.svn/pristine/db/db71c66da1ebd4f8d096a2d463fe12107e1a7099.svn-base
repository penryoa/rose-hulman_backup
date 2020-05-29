import java.util.Stack;

public class StackChainManager extends ChainManager {
	
	private int size = 0;
	private Stack<Chain> words = new Stack<Chain>();
	
	
	/**
	 * Constructors
	 */
	
//	public StackChainManager() {
//		this.words = new Stack<>();
//	}
//	
//	public StackChainManager(String end) {
//		this.words = new Stack<>();
//		this.end = end;
//	}

	
	/**
	 * ChainManager methods
	 */
	
	@Override
	public void add(Chain chain) {
		this.words.push(chain);
		this.size++;
		this.updateMax(this.size);
	}

	@Override
	public Chain next() {
		this.size--;
		this.incrementNumNexts();
		return this.words.pop();
		
	}

	@Override
	public boolean isEmpty() {
		return (this.words.isEmpty());
	}

}
