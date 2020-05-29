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
		public int compare(T o1, T o2) {
			return o1.compareTo(o2);
		}
	};
	private Comparator<T> REVERSE_COMPARATOR = new Comparator<T>() {
		@Override
		public int compare(T o1, T o2) {
			return o2.compareTo(o1);
		}
	};

	public BinaryHeap(Class<T> type) {
		this.type = type; // will be used in the resize operation
		this.data = (T[]) Array.newInstance(type, DEFAULT_CAPACITY);
		this.comparator = this.DEFAULT_COMPARATOR;
		this.size = 0;
	}

	public BinaryHeap() {
		this.data = (T[]) Array.newInstance(Comparable.class, DEFAULT_CAPACITY);
		this.comparator = this.DEFAULT_COMPARATOR;
		this.size = 0;
	}

	public void sort(T[] array) {
		// DONE: gotta finish this
		// we don't want to lose the current data
		
		T[] temp = array;
		this.data = (T[]) Array.newInstance(Comparable.class, DEFAULT_CAPACITY); // in-place

		for(int i = 0;i<array.length;i++){
			this.insert(temp[i]);
		}
		
		for(int i = 0;i<array.length;i++){
			temp[i] = this.deleteMin();
		}
		
		array = this.data;
		
	}
	

	

	private void percolateDown(int pos) {
		// DONE Auto-generated method stub.
		int temp;
		T temp2 = this.data[pos];
		for (; pos * 2 <= size; pos = temp) {
			temp = pos * 2;
			if (temp != size && comparator.compare(this.data[temp + 1], this.data[temp]) < 0) {
				temp++;
			}
			if (comparator.compare(this.data[temp], temp2) < 0) {
				this.data[pos] = this.data[temp];
			} else {
				break;
			}
		}
		this.data[pos] = temp2;

	}

	public boolean insert(T value) {
		// DONE: gotta finish this
		// will use comparator.compare(pos,pos/2)--this is the child and parent
		if (size + 1 == this.data.length) {
			this.doubleArray();
		}

		int use = ++size;

		this.data[0] = value;
		// comparing child and parent
		for (; comparator.compare(value, this.data[use / 2]) < 0; use /= 2) {
			this.data[use] = this.data[use / 2];
		}
		this.data[use] = value;
		data[0] = null;

		return true;

	}

	private void doubleArray() {
		T[] tempArray = this.data;
		this.data = (T[]) Array.newInstance(type, tempArray.length * 2);
		for (int i = 0; i < tempArray.length; i++) {
			this.data[i] = tempArray[i];
		}
	}

	public T deleteMin() {
		// DONE: gotta finish this
		T min = findMin();
		if (min == null) {
			return null;
		}
		// array index out of bounds. why? why does it hate us?
		this.data[1] = this.data[size--];

		percolateDown(1);

		return min;
	}

	public T findMin() {
		return data[1];
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(data, 0, size + 1));
	}

	
//	/**
//	 * 
//	 * @param array
//	 */
//	public void sort(int[] array) {
//		int[] temp = array;
//		this.data = (T[]) Array.newInstance(Comparable.class, DEFAULT_CAPACITY); // in-place
//
//		for(int i = 0;i<array.length;i++){
//			this.insert(temp[i], array);
//		}
//		
//		for(int i = 0;i<array.length;i++){
//			temp[i] = this.deleteMin();
//		}
//		
//		array = this.data;
//	}
//
//	private void insert(int i, int[] array) {
//
//		int use = ++array.length;
//		int value = i;
//		array[0] = value;
//		// comparing child and parent
//		for (; comparator.compare(value, this.data[use / 2]) < 0; use /= 2) {
//			array[use] = array[use / 2];
//		}
//		array[use] = value;
//		data[0] = null;
//
//		return true;
//	}
}
