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

public class AdjacencyListGraph<T> extends Graph<T> {
	Map<T, Vertex> keyToVertex;

	private class Vertex {
		T key;
		List<Vertex> successors;
		List<Vertex> predecessors;

		Vertex(T key) {
			this.key = key;
			this.successors = new ArrayList<Vertex>();
			this.predecessors = new ArrayList<Vertex>();
		}
	}

	AdjacencyListGraph(Set<T> keys) {
		this.keyToVertex = new HashMap<T, Vertex>();
		for (T key : keys) {
			Vertex v = new Vertex(key);
			this.keyToVertex.put(key, v);
		}
	}

	@Override
	public int size() {
		return keyToVertex.keySet().size();
	}

	@Override
	public int numEdges() {
		int count = 0;
		// System.out.println(keyToVertex.keySet());
		for (T key : keyToVertex.keySet()) {
			for (Vertex vtx : keyToVertex.get(key).successors) {
				// System.out.println("From: " + key + " to: " + vtx.key);
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean addEdge(T from, T to) {
		if (keyToVertex.containsKey(from) && keyToVertex.containsKey(to)) {
			Vertex fromVertex = keyToVertex.get(from);
			Vertex toVertex = keyToVertex.get(to);

			if (fromVertex.successors.contains(toVertex) && toVertex.predecessors.contains(fromVertex)) {
				return false;
			}

			fromVertex.successors.add(toVertex);
			toVertex.predecessors.add(fromVertex);

			return true;
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean hasVertex(T key) {
		return keyToVertex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (keyToVertex.containsKey(from) && keyToVertex.containsKey(to)) {
			Vertex fromVertex = keyToVertex.get(from);
			Vertex toVertex = keyToVertex.get(to);

			if (fromVertex.successors.contains(toVertex) && toVertex.predecessors.contains(fromVertex)) {
				return true;
			}

			return false;
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		if (keyToVertex.containsKey(from) && keyToVertex.containsKey(to)) {
			Vertex fromVertex = keyToVertex.get(from);
			Vertex toVertex = keyToVertex.get(to);

			if (!fromVertex.successors.contains(toVertex) && !toVertex.predecessors.contains(fromVertex)) {
				return false;
			}

			fromVertex.successors.remove(toVertex);
			toVertex.predecessors.remove(fromVertex);

			return true;
		}
		throw new NoSuchElementException();
	}

	@Override
	public int outDegree(T key) {
		if (keyToVertex.containsKey(key)) {
			return keyToVertex.get(key).successors.size();
		}
		throw new NoSuchElementException();
	}

	@Override
	public int inDegree(T key) {
		if (keyToVertex.containsKey(key)) {
			return keyToVertex.get(key).predecessors.size();
		}
		throw new NoSuchElementException();
	}

	@Override
	public Set<T> keySet() {
		return keyToVertex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		if (keyToVertex.containsKey(key)) {
			Set<T> temp = new HashSet<T>();
			for (Vertex vtx : keyToVertex.get(key).successors) {
				temp.add(vtx.key);
			}
			return temp;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Set<T> predecessorSet(T key) {
		if (keyToVertex.containsKey(key)) {
			Set<T> temp = new HashSet<T>();
			for (Vertex vtx : keyToVertex.get(key).predecessors) {
				temp.add(vtx.key);
			}
			return temp;
		}
		throw new NoSuchElementException();
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		if (keyToVertex.containsKey(key)) {
			List<Vertex> vtxSuccessors = keyToVertex.get(key).successors;
			List<T> keys = new ArrayList<T>();
			for (int i = 0; i < vtxSuccessors.size(); i++) {
				keys.add(vtxSuccessors.get(i).key);
			}
			return keys.iterator();
		}
		throw new NoSuchElementException();
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if (keyToVertex.containsKey(key)) {
			List<Vertex> vtxPredecessors = keyToVertex.get(key).predecessors;
			List<T> keys = new ArrayList<T>();
			for (int i = 0; i < vtxPredecessors.size(); i++) {
				keys.add(vtxPredecessors.get(i).key);
			}
			return keys.iterator();
		}
		throw new NoSuchElementException();
	}

	/*
	 * I never got this to fully work; I'm starting to wonder if a stack
	 * could do a better job? idk. I'm going to bed.
	 */
	@Override
	public Set<T> stronglyConnectedComponent(T key) {
		if (keyToVertex.containsKey(key)) {
			Set<T> emptySet = new HashSet<>();
			if (keyToVertex.get(key).predecessors.size() == 0) {
				emptySet.add(key);
				return emptySet;
			}
			return connectedHelper(key, key, emptySet);
		}
		throw new NoSuchElementException();
	}

	public Set<T> connectedHelper(T key, T current, Set<T> mySet) {
		if (current != key && mySet.contains(current)) {
			// this path is invalid
			return new HashSet<T>();
		} else if (current == key && mySet.size() != 0) {
			// this is a valid path that has ended
			return mySet;
		}

		// add the current T element to the Set
		mySet.add(current);

		// copy original set without being the same exact set; do the same for a
		// temporary max
		Set<T> originalSet = new HashSet<>();
		Set<T> maxSet = new HashSet<>();
		for (T element : mySet) {
			originalSet.add(element);
			maxSet.add(element);
		}
		boolean wasChanged = false;

		// check to see if the successors can loop to the same key
		for (Vertex e : keyToVertex.get(current).successors) {
			Set<T> tempSet = connectedHelper(key, e.key, originalSet);
			if (tempSet.size() > maxSet.size()) {
				// change something
				wasChanged = true;
				maxSet = tempSet;
			}

		}
		// if no changes were made, return an empty set; this was an
		// unsuccessful venture. ):
		if (!wasChanged) {
			return new HashSet<>();
		} else {
			return maxSet;
		}

		
	}

	@Override
	public List<T> shortestPath(T startLabel, T endLabel) {
		if (keyToVertex.containsKey(startLabel) && keyToVertex.containsKey(endLabel)) {

			List<T> currentPath = new ArrayList<>();
			currentPath.add(startLabel);

			if (startLabel == endLabel) {
				// no need to keep going; just return a list with that element
				return currentPath;
			}

			// Make a LinkedList of all the potential paths, adding the first
			// path.
			LinkedList<List<T>> myList = new LinkedList<List<T>>();
			myList.addFirst(currentPath);

			// keep going through each path until there are no options. return
			// null if no path found.
			while (!myList.isEmpty()) {
				currentPath = myList.removeFirst();
				T lastElementOfCurrent = currentPath.get(currentPath.size() - 1);
				if (lastElementOfCurrent.equals(endLabel)) {
					// good path
					return currentPath;
				}

				// successors of last element in currentPath
				List<Vertex> successors = this.keyToVertex.get(lastElementOfCurrent).successors;

				// loop through all successors and add a new path if you can
				if (!successors.isEmpty()) {
					for (Vertex v : successors) {
						if (!currentPath.contains(v.key)) {
							List<T> newPath = new ArrayList<T>();
							newPath.addAll(currentPath);
							newPath.add(v.key);
							myList.addLast(newPath);
						}
					}
				}
			}
			return null;
		}
		throw new NoSuchElementException();
	}

}
