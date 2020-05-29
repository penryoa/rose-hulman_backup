/**
 * Specifies the methods that any concrete Chain Manager must support.
 * 
 * @author Matt Boutell. Created Mar 18, 2011.
 */
public abstract class ChainManager {
	private int nNexts = 0;
	private int maxSize = 0;

	/**
	 * Stores the given chain so it can be processed later.
	 *
	 * @param chain
	 */
	public abstract void add(Chain chain);

	/**
	 * Removes and returns the next Chain that the ChainManager has stored.
	 *
	 * @return The next Chain, or null if the manager has no Chains.
	 */
	public abstract Chain next();

	/**
	 * 
	 * @return true if the ChainManager has no chains
	 */
	public abstract boolean isEmpty();

	/**
	 * @return The number of times next() has been called. For unit tests.
	 */
	public int getNumberOfNexts() {
		return this.nNexts;
	}

	/**
	 * Adds 1 to the number of times the next() method has been called.
	 */
	protected void incrementNumNexts() {
		this.nNexts++;
	}

	/**
	 * Ppdate the maxsize, if the current number of chains exceeds
	 * {@link ChainManager#maxSize}
	 * 
	 * @param size
	 *            the number of chains currently stored
	 */
	protected void updateMax(int size) {
		if (size > maxSize)
			maxSize = size;
	}

	/**
	 * @return The largest number of chains stored at any one time. For unit tests.
	 */
	public int maxSize() {
		return this.maxSize;
	}
}
