package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyMatrixGraph<T> extends Graph<T> {
	Map<T, Integer> keyToIndex;
	List<T> indexToKey;
	int[][] matrix;

	AdjacencyMatrixGraph(Set<T> keys) {
		int size = keys.size();
		this.keyToIndex = new HashMap<T, Integer>();
		this.indexToKey = new ArrayList<T>();
		this.matrix = new int[size][size];
		// need to populate keyToIndex and indexToKey with info from keys
		int i = 0;
		for (T key : keys) {
			indexToKey.add(key);
			keyToIndex.put(key, i);
			i++;
		}
	}

	@Override
	public int size() {
		return indexToKey.size(); // nope; wrong.
	}

	@Override
	public int numEdges() {
		int count = 0;
		for (int m = 0; m < keyToIndex.size(); m++) {
			for (int n = 0; n < keyToIndex.size(); n++) {
				if (matrix[m][n] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean addEdge(T from, T to) {
		if (keyToIndex.containsKey(from) && keyToIndex.containsKey(to)) {
			int indexFrom = keyToIndex.get(from);
			int indexTo = keyToIndex.get(to);
			if (matrix[indexFrom][indexTo] == 1) {
				return false;
			} else {
				matrix[indexFrom][indexTo] = 1;
				return true;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean hasVertex(T key) {
		return keyToIndex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (keyToIndex.containsKey(from) && keyToIndex.containsKey(to)) {
			int indexFrom = keyToIndex.get(from);
			int indexTo = keyToIndex.get(to);
			if (matrix[indexFrom][indexTo] == 1) {
				return true;
			} else {
				return false;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		if (keyToIndex.containsKey(from) && keyToIndex.containsKey(to)) {
			int indexFrom = keyToIndex.get(from);
			int indexTo = keyToIndex.get(to);
			if (matrix[indexFrom][indexTo] == 0) {
				return false;
			} else {
				matrix[indexFrom][indexTo] = 0;
				return true;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int outDegree(T key) {
		if (keyToIndex.containsKey(key)) {
			int count = 0;
			int keyIndex = keyToIndex.get(key);
			for (int k = 0; k < keyToIndex.size(); k++) {
				if (matrix[keyIndex][k] == 1) {
					count++;
				}
			}
			return count;
		}
		throw new NoSuchElementException();
	}

	@Override
	public int inDegree(T key) {
		if (keyToIndex.containsKey(key)) {
			int count = 0;
			int keyIndex = keyToIndex.get(key);
			for (int k = 0; k < keyToIndex.size(); k++) {
				if (matrix[k][keyIndex] == 1) {
					count++;
				}
			}
			return count;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Set<T> keySet() {
		return keyToIndex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		if (keyToIndex.containsKey(key)) {
			Set<T> tempSet = new HashSet<>();
			int keyIndex = keyToIndex.get(key);
			for (int h = 0; h < keyToIndex.size(); h++) {
				if (matrix[keyIndex][h] == 1) {
					tempSet.add(indexToKey.get(h));
				}
			}
			return tempSet;
		}
		throw new NoSuchElementException();
	}

	public List<T> successorList(T key) {
		if (keyToIndex.containsKey(key)) {
			List<T> tempList = new ArrayList<>();
			int keyIndex = keyToIndex.get(key);
			for (int h = 0; h < keyToIndex.size(); h++) {
				if (matrix[keyIndex][h] == 1) {
					tempList.add(indexToKey.get(h));
				}
			}
			return tempList;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Set<T> predecessorSet(T key) {
		if (keyToIndex.containsKey(key)) {
			Set<T> tempSet = new HashSet<>();
			int keyIndex = keyToIndex.get(key);
			for (int h = 0; h < keyToIndex.size(); h++) {
				if (matrix[h][keyIndex] == 1) {
					tempSet.add(indexToKey.get(h));
				}
			}
			return tempSet;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		return successorSet(key).iterator();
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		return predecessorSet(key).iterator();
	}

	@Override
	public Set<T> stronglyConnectedComponent(T key) {
		if (keyToIndex.containsKey(key)) {

			return null;
		}
		throw new NoSuchElementException();
	}

	@Override
	public List<T> shortestPath(T startLabel, T endLabel) {
		if (keyToIndex.containsKey(startLabel) && keyToIndex.containsKey(endLabel)) {

			List<T> currentPath = new ArrayList<>();
			currentPath.add(startLabel);

			if (startLabel == endLabel) {
				// no need to keep going; just return a list with that element
				return currentPath;
			}

			// Make a LinkedList of all the potential paths, adding the first
			// path. (Blake M mentioned that he used a Linked List and iterated
			// through that. I thought it was a good idea and did that myself!)
			LinkedList<List<T>> listOfPaths = new LinkedList<List<T>>();
			listOfPaths.addFirst(currentPath);

			
			// keep going through each path until there are no options. return null if no path found.
			while (!listOfPaths.isEmpty()) {
				currentPath = listOfPaths.removeFirst();
				T lastElementOfCurrentPath = currentPath.get(currentPath.size() - 1);
				if (lastElementOfCurrentPath.equals(endLabel)) {
					// valid path that reached end node
					return currentPath;
				}
				// successors of last element in currentPath (I made a helper
				// function successorList)
				List<T> successors = successorList(lastElementOfCurrentPath);

				// loop through all successors and add a new path if you can
				if (!successors.isEmpty()) {
					for (T v : successors) {
						if (!currentPath.contains(v)) {
							List<T> newPath = new ArrayList<T>();
							newPath.addAll(currentPath);
							newPath.add(v);
							listOfPaths.addLast(newPath);
						}
					}
				}
			}
			return null;
		}
		throw new NoSuchElementException();
	}

}
