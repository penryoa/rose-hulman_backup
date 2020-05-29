package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyListGraph<T> extends Graph<T> {
	Map<T,Vertex> keyToVertex;
	
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
		this.keyToVertex = new HashMap<T,Vertex>();
		for (T key : keys) {
			Vertex v = new Vertex(key);
			this.keyToVertex.put(key, v);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addEdge(T from, T to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasVertex(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int outDegree(T key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inDegree(T key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<T> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<T> successorSet(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<T> predecessorSet(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<T> stronglyConnectedComponent(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> shortestPath(T startLabel, T endLabel) {
		// TODO Auto-generated method stub
		return null;
	}

}
