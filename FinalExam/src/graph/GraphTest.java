package graph;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;


public class GraphTest {

	private static int graphPoints = 0;
	
	
	public Graph<Integer> makeExampleCompleteBipartite(int leftSize, int rightSize, boolean adjList) {
		HashSet<Integer> keys = new HashSet<Integer>();
		for (Integer i = 0; i < leftSize + rightSize; i++) {
			keys.add(i);
		}
		Graph<Integer> g;
		if (adjList) {
			g = new AdjacencyListGraph<Integer>(keys);
		} else {
			g = new AdjacencyMatrixGraph<Integer>(keys);
		}
		for (int i = 0; i < leftSize; i++) {
			for (int j = leftSize; j < leftSize + rightSize; j++) {
				g.addEdge(i, j);
				g.addEdge(j, i);
			}
		}
		return g;
	}
	
	public Graph<Integer> makeExampleComplete(int size, boolean adjList) {
		HashSet<Integer> keys = new HashSet<Integer>();
		for (Integer i = 0; i < size; i++) {
			keys.add(i);
		}
		Graph<Integer> g;
		if (adjList) {
			g = new AdjacencyListGraph<Integer>(keys);
		} else {
			g = new AdjacencyMatrixGraph<Integer>(keys);
		}
		for (int i = 0; i < size; i++) {
			for (int j = i+1; j < size; j++) {
				g.addEdge(i, j);
				g.addEdge(j, i);
			}
		}
		return g;
	}
	

	
	@Test
	public void testNumDirectedTrianglesSimpleAL() {
		HashSet<String> keysThree = new HashSet<String>();
		keysThree.add("A");
		keysThree.add("B");
		keysThree.add("C");
		Graph<String> g3 = new AdjacencyListGraph<String>(keysThree);
		g3.addEdge("A", "B");
		g3.addEdge("B", "C");
		assertEquals(0,g3.numDirectedTriangles());
		g3.addEdge("C", "A");
		assertEquals(1,g3.numDirectedTriangles());
		g3.addEdge("B", "A");
		g3.addEdge("C", "B");
		assertEquals(1,g3.numDirectedTriangles());
		g3.addEdge("A", "C");
		assertEquals(2,g3.numDirectedTriangles());
		graphPoints += 2;
	}

	
	@Test
	public void testNumDirectedTrianglesSimpleAM() {
		HashSet<String> keysThree = new HashSet<String>();
		keysThree.add("A");
		keysThree.add("B");
		keysThree.add("C");
		Graph<String> g3 = new AdjacencyMatrixGraph<String>(keysThree);
		g3.addEdge("A", "B");
		g3.addEdge("B", "C");
		assertEquals(0,g3.numDirectedTriangles());
		g3.addEdge("C", "A");
		assertEquals(1,g3.numDirectedTriangles());
		g3.addEdge("B", "A");
		g3.addEdge("C", "B");
		assertEquals(1,g3.numDirectedTriangles());
		g3.addEdge("A", "C");
		assertEquals(2,g3.numDirectedTriangles());
		graphPoints += 2;
	}


	
	@Test
	public void testNumDirectedTrianglesExampleAL() {
		HashSet<String> keysExample = new HashSet<String>();
		keysExample.add("A");
		keysExample.add("B");
		keysExample.add("C");
		keysExample.add("D");
		keysExample.add("E");
		keysExample.add("F");
		Graph<String> exampleGraph = new AdjacencyListGraph<String>(keysExample);
		assertEquals(0,exampleGraph.numDirectedTriangles());
		exampleGraph.addEdge("A", "B");
		exampleGraph.addEdge("B", "C");
		exampleGraph.addEdge("B", "D");
		exampleGraph.addEdge("C", "A");
		exampleGraph.addEdge("C", "E");
		exampleGraph.addEdge("C", "F");
		exampleGraph.addEdge("D", "E");
		exampleGraph.addEdge("E", "B");
		exampleGraph.addEdge("E", "F");
		assertEquals(3,exampleGraph.numDirectedTriangles());
		graphPoints += 3;
	}
	

	@Test
	public void testNumDirectedTrianglesExampleAM() {
		HashSet<String> keysExample = new HashSet<String>();
		keysExample.add("A");
		keysExample.add("B");
		keysExample.add("C");
		keysExample.add("D");
		keysExample.add("E");
		keysExample.add("F");
		Graph<String> exampleGraph = new AdjacencyMatrixGraph<String>(keysExample);
		assertEquals(0,exampleGraph.numDirectedTriangles());
		exampleGraph.addEdge("A", "B");
		exampleGraph.addEdge("B", "C");
		exampleGraph.addEdge("B", "D");
		exampleGraph.addEdge("C", "A");
		exampleGraph.addEdge("C", "E");
		exampleGraph.addEdge("C", "F");
		exampleGraph.addEdge("D", "E");
		exampleGraph.addEdge("E", "B");
		exampleGraph.addEdge("E", "F");
		assertEquals(3,exampleGraph.numDirectedTriangles());
		graphPoints += 3;
	}

	
	@Test
	public void testNumDirectedTrianglesCompleteBipartiteLargeRandomAL() {
		testNumDirectedTrianglesCompleteBipartiteLargeRandom(true);
	}

	@Test
	public void testNumDirectedTrianglesCompleteBipartiteLargeRandomAM() {
		testNumDirectedTrianglesCompleteBipartiteLargeRandom(false);
	}	

	public void testNumDirectedTrianglesCompleteBipartiteLargeRandom(boolean adjList) {
		int partitionSize = 50;
		Graph<Integer> largeBipartite = makeExampleCompleteBipartite(partitionSize,partitionSize,adjList);
		
		// Pick three random distinct vertices in the first part of the partition.
		Random rand = new Random();
		int first = rand.nextInt(partitionSize);
		int second = first;
		while (second == first) {
			second = rand.nextInt(partitionSize);
		}
		int third = first;
		while (third == first || third == second) {
			third = rand.nextInt(partitionSize);
		}

		// Start with a complete bipartite graph (no 3-cycles.)
		// We will add a triangle in the first part of the partition.
		assertEquals(0,largeBipartite.numDirectedTriangles());
		largeBipartite.addEdge(first, second); // now have a triangle with first,second,x for all x in 2nd partition
		assertEquals(partitionSize, largeBipartite.numDirectedTriangles());
		largeBipartite.addEdge(second, third); // now also have a triangle with second,third,x for all x in 2nd partition
		assertEquals(2*partitionSize, largeBipartite.numDirectedTriangles());
		largeBipartite.addEdge(third, first); // now also have a triangle with third,first,x for all x in 2nd partition
		  									  // plus the triangle first,second,third
		assertEquals(3*partitionSize + 1, largeBipartite.numDirectedTriangles());
		graphPoints += 3;
	}
	

	@Test
	public void testNumDirectedTrianglesCompleteLargeRandomAL() {
		int size = 150;
		int numTris = size*(size-1)*(size-2) / 3;
		
		Graph<Integer> largeBipartite = makeExampleComplete(size,true);  // AdjList
		long startTime = System.nanoTime();
		assertEquals(numTris,largeBipartite.numDirectedTriangles());
		long endTime = System.nanoTime();
		
		// must work in <= 2 seconds
		String timeInMilliseconds = Long.toString((endTime-startTime)/1000000);
		assertTrue(timeInMilliseconds + " ms > 1000 ms is too slow",endTime - startTime <= 1000000000); 
		
		graphPoints += 2;
	}
	

	@Test
	public void testNumDirectedTrianglesCompleteLargeRandomAM() {
		int size = 150;
		int numTris = size*(size-1)*(size-2) / 3;
		
		Graph<Integer> largeBipartite = makeExampleComplete(size,false);  // AdjMatrix
		long startTime = System.nanoTime();
		assertEquals(numTris,largeBipartite.numDirectedTriangles());
		long endTime = System.nanoTime();
		
		// must work in <= 2 seconds
		String timeInMilliseconds = Long.toString((endTime-startTime)/1000000);
		assertTrue(timeInMilliseconds + " ms > 1000 ms is too slow",endTime - startTime <= 1000000000); 
		
		graphPoints += 2;
	}
	

	@AfterClass
	public static void displayPoints() {
		
		System.out.printf("3. numDirectedTriangles TOTAL POINTS:                    " +
						"%2d/20\n", graphPoints);
	}

}
