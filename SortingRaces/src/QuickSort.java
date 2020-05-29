
public class QuickSort {

	public static void sort(int[] array, int low, int high) {

		if (low >= high) {
			return;
		}
		int pivot = array[low];

		int pivot_final_pos = partition(low, high, pivot, array);

		if (low < pivot_final_pos - 1) {
			sort(array, low, pivot_final_pos - 1);
		}
		if (high > pivot_final_pos) {
			sort(array, pivot_final_pos, high);
		}

	}

	private static int partition(int low, int high, int pivot, int[] array) {

		int i = low + 1;
		int j = high;

		while (true) {
			while (i < array.length && array[i] < pivot) {
				i++;
			}
			while (j > 0 && array[j] > pivot) {
				j--;
			}
			if (i >= j) {
				break;
			}

			swap(array, i, j);
		}

		swap(array, low, j);
		return i;
	}

	private static void swap(int[] array, int low, int j) {

		int temp = array[low];
		array[low] = array[j];
		array[j] = temp;
	}

}
