package numberSequence;

public class Fibonacci implements Sequence {

	int n_2 = 1;
	int n_1 = 1;
	
	@Override
	public int next() {
		int n = n_1 + n_2;
		n_2 = n_1;
		n_1 = n;
		return n;
	}
	
	@Override
	public void reset() {
		n_2 = 1;
		n_1 = 1;
	}
}
