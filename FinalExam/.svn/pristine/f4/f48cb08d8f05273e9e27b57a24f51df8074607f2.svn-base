package graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Abstract class to represent the Graph ADT. It is assumed that every vertex contains some 
 * data of type T, which serves as the identity of that node and provides access to it.
 * 
 * @author Nate Chenette and <<TODO: your name here >>>.
 *
 * @param <T>
 */
public abstract class Graph<T> {
	
	
	/**
	 * Returns the number of directed triangles (3-cycles) in the graph.
	 * @return
	 */
	public abstract int numDirectedTriangles(); // Can also remove "abstract" modifier and implement here
	
	
	
	/* Selected GraphSurfing Milestone 1 Methods */
	
	/**
	 * Adds a directed edge from the vertex containing "from" to the vertex containing "to". 
	 * @param from
	 * @param to
	 * @return true if the add is successful, false if the edge is already in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean addEdge(T from, T to) throws NoSuchElementException;

	
	/**
	 * Determines whether a vertex is in the graph.
	 * @param key
	 * @return true if the graph has a vertex containing key.
	 */
	public abstract boolean hasVertex(T key);
	
	
	/**
	 * Determines whether an edge is in the graph.
	 * @param from
	 * @param to
	 * @return true if the directed edge (from, to) is in the graph, otherwise false.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean hasEdge(T from, T to) throws NoSuchElementException;
	
	
	/**
	 * Returns the Set of vertex keys in the graph. 
	 * @return
	 */
	public abstract Set<T> keySet();
	
	/**
	 * Returns a Set of keys that are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> successorSet(T key) throws NoSuchElementException {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		Iterator<T> succIt = this.successorIterator(key);
		Set<T> toReturn = new HashSet<T>();
		while (succIt.hasNext()) {
			toReturn.add(succIt.next());
		}
		return toReturn;
	}
	
	/**
	 * Returns a Set of keys that are predecessors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> predecessorSet(T key) throws NoSuchElementException {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		Iterator<T> predIt = this.predecessorIterator(key);
		Set<T> toReturn = new HashSet<T>();
		while (predIt.hasNext()) {
			toReturn.add(predIt.next());
		}
		return toReturn;
	}
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> successorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> predecessorIterator(T key) throws NoSuchElementException;
	


	
	
}

