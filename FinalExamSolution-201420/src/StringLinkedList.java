
/**
 * A class for linked list problems.
 * 
 * A few methods have already been written for you.
 * 
 * Note that you should feel free to add new methods
 * (e.g. utility methods like size() or recursive helper
 * methods) if they will help you solve the problems.
 * 
 * @author TODO <YOUR_NAME_GOES_HERE>
 *
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
	
	/**
	 * Default constructor.
	 *
	 */
	public StringLinkedList()
	{
		this.first = null;
	}
	
	/**
	 * Adds a string as the new first element of this list
	 * 
	 * Note - you should not need to modify this method!
	 * 
	 * @param newData 
	 * 
	 */
	public void addFirst(String newData)
	{
		Node newNode = new Node(newData, this.first);
		this.first = newNode;
	}
	
	/**
	 * Removes the last element of this list
	 * Returns the data of the element that is removed
	 * 
	 * If the list is empty, returns null
	 * 
	 * Note - you should not need to modify this method!
	 * 
	 * @return the data of the element that is removed
	 * 
	 */
	public String removeLast()
	{
		//zero element list
		if(this.first == null) return null;
		
		//one element list
		if(this.first.next == null) {
			String data = this.first.data;
			this.first = null;
			return data;
		}
		
		//the rest
		Node current = this.first;
		while(current.next.next != null) {
			current = current.next;
		}
		String data = current.next.data;
		current.next = null;
		return data;
	}
	
	/**
	 * Removes all elements after a particular max size.
	 * 
	 * So for example, if the list max size was 2:
	 * 
	 * "1"->"2"->"3"->"4" becomes "1"->"2"
	 * "a"->"b"->"c" becomes "a"->"b"
	 * "a" becomes "a"
	 * 
	 * Note that you'll need a special case for when maxSize = 0
	 * 
	 * 
	 * @param maxSize
	 */
	public void limitToSize(int maxSize) {
		if(maxSize == 0) {
			this.first = null;
			return;
		}
		
		Node current = this.first;
		
		for(int i = 0; i < maxSize - 1; i++) {
			
			current = current.next;
			
			if(current == null)
				return;
		}
		
		current.next = null;
	}
	
	/**
	 * Returns true if the list is sorted, false otherwise.
	 * 
	 * For example:
	 * 
	 * The list "apple"->"bacon"->"ninja" would return true
	 * This list "bigger"->"badder" would return false
	 * 
	 * Use string's compareTo method to compare strings
	 * 
	 * An empty list or a list with one element is always considered sorted
	 * 
	 * @return true if list is sorted
	 */
	public boolean isSorted() {
		if(this.first == null) {
			return true;
		}
		
		Node current = this.first;
		
		while(current.next != null) {
			if(current.data.compareTo(current.next.data) > 0) {
				return false;
			}
			current = current.next;
		}
		return true;
	}
	
	
	/**
	 * 
	 * Takes a list of strings and converts it to a list of strings
	 * such that each string is one (or zero) characters long.
	 * 
	 * So for example:
	 * "foo"->"bar" yields "f"->"o"->"o"->"b"->"a"->"r"
	 * "a"->""->"bc" yields "a"->""->"b"->"c"
	 * 
	 * Note that the empty string stays the empty string.
	 * 
	 * Also recall that you can convert a character to a string by adding ""
	 * String s = 'a' + "";
	 */
	public void explodeStrings() {
		Node current = this.first;
		while(current != null) {
			if(current.data.length() > 1) {
				String rest = current.data.substring(1);
				current.data = current.data.charAt(0) + "";
				current.next = new Node(rest, current.next);
			}
			current = current.next;
		}
	}
	
}
