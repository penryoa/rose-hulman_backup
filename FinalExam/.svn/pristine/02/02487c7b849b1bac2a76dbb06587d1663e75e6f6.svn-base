package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class AdjacencyListGraph<T> extends Graph<T> {
	HashMap<T,Vertex> keyToVertex;
	
	public class Vertex {
		T data;
		List<Vertex> successors;
		List<Vertex> predecessors;
		
		/*
		 * Creates a new vertex given just the data
		 */
		Vertex(T key) {
			this.data = key;
			this.successors = new ArrayList<Vertex>();
			this.predecessors = new ArrayList<Vertex>();
		}
	}
	
	public AdjacencyListGraph() {
		keyToVertex = new HashMap<T,Vertex>();
	}
	
	public AdjacencyListGraph(Set<T> keys) {
		keyToVertex = new HashMap<T,Vertex>();
		for (T key : keys) {
			Vertex vert = new Vertex(key);
			keyToVertex.put(key, vert);
		}
	}

	public boolean addVertex(T key) {
		if (keyToVertex.containsKey(key)) {
			return false;
		}
		Vertex vert = new Vertex(key);
		keyToVertex.put(key, vert);
		return true;
	}
	

	@Override
	public boolean addEdge(T from, T to) {
		if (!this.hasVertex(from)) {
			throw new NoSuchElementException("Could not find vertex containing " + from.toString());
		}
		if (!this.hasVertex(to)) {
			throw new NoSuchElementException("Could not find vertex containing " + to.toString());
		}
		Vertex fromV = keyToVertex.get(from);
		Vertex toV = keyToVertex.get(to);
		List<Vertex> fromSuccs = fromV.successors;
		List<Vertex> toPreds = toV.predecessors;
		if (fromSuccs.contains(toV)) {
			return false;
		}
		fromSuccs.add(toV);
		toPreds.add(fromV);
		return true;
	}


	@Override
	public boolean hasVertex(T key) {
		return this.keyToVertex.containsKey(key);
	}


	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (!this.hasVertex(from)) {
			throw new NoSuchElementException("Could not find vertex containing " + from.toString());
		}
		if (!this.hasVertex(to)) {
			throw new NoSuchElementException("Could not find vertex containing " + to.toString());
		}
		return keyToVertex.get(from).successors.contains(keyToVertex.get(to));
	}
	
	
	@Override
	public Set<T> keySet() {
		return this.keyToVertex.keySet();
	}


	@Override
	public Iterator<T> successorIterator(T key) {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		return new SuccessorIterator(this.keyToVertex.get(key).successors);
	}
	
	private class SuccessorIterator implements Iterator<T> {
		private Iterator<Vertex> itV;
		
		private SuccessorIterator(List<Vertex> succs) {
			itV = succs.iterator();
		}

		@Override
		public boolean hasNext() {
			return itV.hasNext();
		}

		@Override
		public T next() {
			return itV.next().data;
		}
		
	}
	

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		return new PredecessorIterator(this.keyToVertex.get(key).predecessors);
	}
	
	private class PredecessorIterator implements Iterator<T> {
		private Iterator<Vertex> itV;
		
		private PredecessorIterator(List<Vertex> preds) {
			itV = preds.iterator();
		}

		@Override
		public boolean hasNext() {
			return itV.hasNext();
		}

		@Override
		public T next() {
			return itV.next().data;
		}
		
	}
}
