package search;

public class EfficientSearch {
	public static int search(int[] sortedArray, int searchTerm) {
		// TODO You recognize this starting code as a SEQUENTIAL (one at a time, in-order) search. 
		// It runs in O(n) worst-case time.
		// So if there are 1,000,000 items in the array, it will have to make that many comparisons in the worst case.
		// 
		// Since the array is sorted, 
		// replace it with the much-more efficient BINARY search, which runs in O(log n) worst case time. 
		// If there are 1,000,000 items in the array, it will only have to make ~20 comparisons.
		//
		// You can look up binary search algorithm from the CSSE220 materials
		// or here: https://en.wikipedia.org/wiki/Binary_search_algorithm#Procedure
		
//		for (int i = 0; i < sortedArray.length; i++) {
//			if (searchTerm == sortedArray[i]) {
//				return i;
//			}
//		}
		
		// Not found
		return searchHelper(sortedArray, searchTerm, 0, sortedArray.length-1);
	}
	
	public static int searchHelper(int[] arr, int term, int L, int R) {
		
		int M = (L+R)/2;
		
		if (L>R) {
			return -1;
		}
		
		if (arr[M] == term) {
			return M;
		}
		if(arr[M] < term ) {
			return searchHelper(arr, term, M+1, R);
		} 
		else {
			return searchHelper(arr, term, L, M-1);
		}
	}
}
