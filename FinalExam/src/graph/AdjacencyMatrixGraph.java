package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import graph.AdjacencyListGraph.Vertex;

public class AdjacencyMatrixGraph<T> extends Graph<T> {

	HashMap<T, Integer> keyToIndex;
	ArrayList<T> indexToKey;
	int[][] matrix;

	public AdjacencyMatrixGraph(Set<T> keys) {
		this.keyToIndex = new HashMap<T, Integer>();
		this.indexToKey = new ArrayList<T>();
		int size = keys.size();
		this.matrix = new int[size][size]; // automatically initialized to all-0
											// matrix
		int index = 0;
		for (T key : keys) {
			this.indexToKey.add(key);
			this.keyToIndex.put(key, index++);
		}
	}

	@Override
	public boolean addEdge(T from, T to) {
		if (!this.hasVertex(from)) {
			throw new NoSuchElementException("Could not find vertex containing " + from.toString());
		}
		if (!this.hasVertex(to)) {
			throw new NoSuchElementException("Could not find vertex containing " + to.toString());
		}
		int fromInd = this.keyToIndex.get(from);
		int toInd = this.keyToIndex.get(to);
		if (this.matrix[fromInd][toInd] > 0) {
			return false;
		}
		this.matrix[fromInd][toInd] = 1;
		return true;
	}

	@Override
	public boolean hasVertex(T key) {
		return this.keyToIndex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (!keyToIndex.containsKey(from)) {
			throw new NoSuchElementException("Could not find vertex containing " + from.toString());
		}
		if (!keyToIndex.containsKey(to)) {
			throw new NoSuchElementException("Could not find vertex containing " + to.toString());
		}
		int fromInd = this.keyToIndex.get(from);
		int toInd = this.keyToIndex.get(to);
		return (this.matrix[fromInd][toInd] > 0);
	}

	@Override
	public Set<T> keySet() {
		return this.keyToIndex.keySet();
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		return new SuccessorIterator(this.keyToIndex.get(key));
	}

	public class SuccessorIterator implements Iterator<T> {
		int fromInd;
		int toInd;
		int numVerts;

		public SuccessorIterator(int index) {
			fromInd = index;
			toInd = 0;
			numVerts = indexToKey.size();
			advanceInd();
		}

		@Override
		public boolean hasNext() {
			return (toInd < numVerts);
		}

		@Override
		public T next() {
			T toReturn = indexToKey.get(toInd++);
			advanceInd();
			return toReturn;
		}

		private void advanceInd() {
			while (toInd < numVerts && matrix[fromInd][toInd] == 0) {
				toInd++;
			}
		}

	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		return new PredecessorIterator(this.keyToIndex.get(key));
	}

	public class PredecessorIterator implements Iterator<T> {
		int fromInd;
		int toInd;
		int numVerts;

		public PredecessorIterator(int index) {
			toInd = index;
			fromInd = 0;
			numVerts = indexToKey.size();
			advanceInd();
		}

		@Override
		public boolean hasNext() {
			return (fromInd < numVerts);
		}

		@Override
		public T next() {
			T toReturn = indexToKey.get(fromInd++);
			advanceInd();
			return toReturn;
		}

		private void advanceInd() {
			while (fromInd < numVerts && matrix[fromInd][toInd] == 0) {
				fromInd++;
			}
		}

	}

	@Override
	public int numDirectedTriangles() {
		int count = 0;

		for (int first = 0; first < keyToIndex.size(); first++) {
			for (int second = 0; second < keyToIndex.size(); second++) {
				// loop through matrix and find first's successors
				if (matrix[first][second] == 1) {
					for (int third = 0; third < keyToIndex.size(); third++) {
						// loop through matrix and find second's successors
						if (matrix[second][third] == 1) {
							// third is a successor of second; increase count 
							//if one of third's successors is first
							if (matrix[third][first] == 1) {
								count++;
							}
						}
					}
				}
			}
		}

		return count / 3;
	}

}
