
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
		//TODO: Write this function
		if(first == null) {
			addFirst(newData);
			return;
		}
		Node newNode = new Node(newData, first.next);
		first.next = newNode;
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
		//TODO: Write this function
		Node current = first;
		while(current != null) {
			current.data = current.data.toUpperCase();
			current = current.next;
		}
	}
	
}
