import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T extends Comparable<T>> {

	private int size;
	private T[] data;
	private static final int DEFAULT_CAPACITY = 5;
	private Class<T> type;
	private Comparator<T> comparator;
	private Comparator<T> DEFAULT_COMPARATOR = new Comparator<T>() {
		@Override
		public int compare(T a, T b) {
			return a.compareTo(b);
		}

	};
	private Comparator<T> REVERSE_COMPARATOR = new Comparator<T>() {
		@Override
		public int compare(T a, T b) {
			return b.compareTo(a);
		}

	};

	public BinaryHeap(Class<T> type) {
		this.type = type; // will be used in resize()
		this.data = (T[]) Array.newInstance(type, DEFAULT_CAPACITY);
		this.comparator = DEFAULT_COMPARATOR;
		this.size = 0;
	}

	public void sort(T[] array) {
		// Don't lose the current data!
		T[] temp = this.data;
		int tempSize = this.size;
		this.data = array; // in-place
		this.size = array.length;

		// TODO Swap the smallest item in the array to position 0,
		// so it is out of the way of the heap operations.

		// Change the array into a heap. (n calls to insert?)
		// Call buildHeap()
		buildHeap();

		// Say we have a heap at this point.

		// Change comparator to reverse (deleteMin becomes deleteMax)
		this.comparator = REVERSE_COMPARATOR;

		for (int i = 0; i < data.length; i++) {
			T max = deleteMin();
			data[size] = max; // check if off-by-one
		}

		// Change comparator back to min heap
		this.comparator = DEFAULT_COMPARATOR;

		this.data = temp;
		this.size = tempSize;
	}

	private void buildHeap() {
		for (int i = this.size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}

	public T deleteMin() {
		if (this.data[1] == null) {
			return null;
		}
		// T min = this.data[1];
		T min = findMin();
		// this.size--;
		this.data[1] = this.data[this.size--];
		percolateDown(1);
		return min;

	}

	private void percolateDown(int pos) {
		int child = pos * 2;
		T temp = this.data[pos];

		while (true) {
			if (child > size) {
				break;
			}
			if (child != this.size && comparator.compare(this.data[child + 1], this.data[child]) < 0) {
				child++;
			}
			if (comparator.compare(this.data[child], temp) < 0) {
				this.data[pos] = this.data[child];
			} else {
				break;
			}
			pos = child;
			child = pos * 2;
		}

		// for (; pos * 2 <= this.size; pos = child){
		// child = pos*2;
		// if (child != this.size && comparator.compare(this.data[child + 1],
		// this.data[child])<0){
		// child++;
		// }
		// if (comparator.compare(this.data[child], temp) < 0){
		// this.data[pos] = this.data[child];
		// } else {
		// break;
		// }
		// }
		this.data[pos] = temp;
	}

	private void percolateUp(int pos) {
		// pos is the position of the recently inserted element
		while (true) {
			if (pos == 1) {
				break;
			}
			if (comparator.compare(this.data[pos], this.data[pos / 2]) < 0) {
				// child data < parent; swap data
				T temp = this.data[pos];
				this.data[pos] = this.data[pos / 2];
				this.data[pos / 2] = temp;

				pos /= 2;

			} else {
				break;
			}
		}
	}

	public void insert(T value) {
		// Boutell returned boolean??
		// will use comparator.compare(pos, pos/2)
		if (this.size + 1 == this.data.length) {
			doubleData();
		}

		this.size++;
		int pos = this.size;
		this.data[pos] = value;
		percolateUp(pos);

	}

	public void doubleData() {
		T[] temp = (T[]) Array.newInstance(this.type, this.data.length * 2);
		for (int n = 1; n < this.data.length; n++) {
			temp[n] = this.data[n];
		}
		this.data = temp;
	}

	public T findMin() {
		T min = this.data[1];
		for (int i = 2; i < this.size; i++) {
			if (data[i] != null) {
				if (comparator.compare(data[i], min) < 0) {
					min = data[i];
				}
			}
		}
		return min;
	}

	@Override
	public String toString() {
		// #thanksForBeingLazyDrB
		return Arrays.toString(Arrays.copyOfRange(data, 0, size + 1));
	}

}
