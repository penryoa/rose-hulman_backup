
/*
 * A linked list class for storing Strings
 */
public class StringLinkedList {

	private class Node {
		
		public Node(String data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		public String data;
		public Node next;
		
	}
	
	private Node first;
	
	public StringLinkedList()
	{
		first = null;
	}
	
	/**
	 * Adds a string as the new first element of this list
	 * 
	 * Note - you should not need to modify this function!
	 * 
	 */
	public void addFirst(String newData)
	{
		Node newNode = new Node(newData, first);
		this.first = newNode;
	}
	
	/**
	 * Removes the last element of this list
	 * Returns the data of the element that is removed
	 * 
	 * If the list is empty, returns null
	 * 
	 * Note - you should not need to modify this function!
	 * 
	 */
	public String removeLast()
	{
		//zero element list
		if(first == null) return null;
		
		//one element list
		if(first.next == null) {
			String data = first.data;
			first = null;
			return data;
		}
		
		//the rest
		Node current = first;
		while(current.next.next != null) {
			current = current.next;
		}
		String data = current.next.data;
		current.next = null;
		return data;
	}
	
	/**
	 * Adds an element as the second element of the list.
	 * So if the list is A->B->C and the element X is added
	 * the list would become
	 * A->X->B->C
	 * 
	 * If the list is empty, this function should add the
	 * element as the first element
	 * 
	 */
	public void addSecond(String newData) {
		//TODO: solve me
	}
	
	/**
	 * Makes all the strings in the list uppercase.
	 * For example a->b->c would become A->B->C
	 * You can solve this problem with a loop or recursively.
	 * Both work (though the recursive solution uses a
	 * helper function).
	 * 
  
	 * 
	 */
	public void uppercase()
	{
		//TODO: solve me
	}
	
	/**
	 * NOTE: this question was not on the actual exam. 
	 * But you've had more practice with LinkedLists, so
	 * some slightly harder questions are possible.  Here's
	 * an example.
	 * 
	 * Removed duplicates from a list.  You can assume the
	 * list is in sorted order, so all the duplicates will
	 * be together.
	 * 
	 * For example:
	 * 
	 * A->A->B->B->B->C->D->D
	 * 
	 * would become A->B->C->D
	 * 
	 */
	public void removeSortedDuplicates()
	{
		Node current = this.first;
		if(current == null)
			return;
		while(current.next != null ) {
			if(current.next.data.equals(current.data)) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
	}
	
	/**
	 * NOTE: this question was not on the actual exam. 
	 * But you've had more practice with LinkedLists, so
	 * some slightly harder questions are possible.  Here's
	 * an example.
	 * 
	 * Copies the parameter list to the end of the given list.
	 * NOTE that this is a copy...the parameter list should
	 * not be changed, and you should make NEW nodes for the
	 * copied list.  
	 * 
	 * So far example if the current state of the list
	 * was A->B
	 * 
	 * and the parameter list was Q->Q
	 * 
	 * The list state would be modified to be
	 * 
	 * A->B->Q->Q
	 * 
	 * Note that you can access the private fields of the
	 * given linked list (just by saying parameterList.first
	 * or whatever)
	 * 
	 * You can make helper functions to make this easier if
	 * you want
	 * 
	 */
	public void copyAtEnd(StringLinkedList parameterList)
	{
		if(parameterList.first == null)
			return;
		Node parameterCurrent = parameterList.first;
		if(this.first == null) {
			this.first = new Node(parameterCurrent.data, null);
			parameterCurrent = parameterCurrent.next;
		}
		Node current = this.first;
		while(current.next != null) {
			current = current.next;
		}
		while(parameterCurrent != null) {
			current.next = new Node(parameterCurrent.data, null);
			current = current.next;
			parameterCurrent = parameterCurrent.next;
		}
	}
	
	
}
