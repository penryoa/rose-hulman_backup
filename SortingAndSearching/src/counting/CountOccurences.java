package counting;

public class CountOccurences {
	public static int countOccurences(int value, int[] array) {
		int count = 0;
		int i = 0;
		while (true) {
			if (value == array[i]) {
				++count;
			}
			if (i == array.length / 2) {
				System.out.println(i);
				return count;
			}
			i++;
		}
	}

	public static void reverse(int[] array) {
		int k = 0;
		for (int i = 0; i < array.length; ++i) {
			for (int j = 0; j < i; j++) {
				if (array[i] != array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
				k++;
			}
		}
		System.out.println(k);
	}

	public static void main(String[] args) {
		reverse(new int[] { 1, 2, 3, 4, 5 });
		reverse(new int[] { 1, 2, 3, 4, 5, 6 });
	}
}
