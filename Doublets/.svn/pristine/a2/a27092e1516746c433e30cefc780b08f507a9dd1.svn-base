import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Dict35Test {
	private static LinksInterface testGraph;
	Doublets doublets;
	private static double sPoints = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGraph = new Links("../DoubletsData/english.cleaned.all.35.txt");
	}

	@Before
	public void setUp() throws Exception {
		doublets = new Doublets(testGraph);
	}

	@Test
	public void PriorityQueueTests() {
		comparePriorityQueueAndQueueResult("bear", "head", 3);
		sPoints += 4;
		comparePriorityQueueAndQueueResult("boat", "ship", 6);
		sPoints += 4;
		comparePriorityQueueAndQueueResult("snowy", "flake", 8);
		sPoints += 4;
		comparePriorityQueueAndQueueResult("iced", "warm", 10);
		sPoints += 4;
		comparePriorityQueueAndQueueResult("slow", "fast", 7);
		sPoints += 4;
	}

	private void comparePriorityQueueAndQueueResult(String start, String end, int minLength) {
		ChainManager queueManager = new QueueChainManager();
		Chain queueChain = doublets.findChain(start, end, queueManager);
		assertNotNull(queueChain);
		assertEquals(minLength, queueChain.length());
		assertChain(start, end, queueChain);

		ChainManager priorityQueueManager = new PriorityQueueChainManager(end);
		Chain priorityQueueChain = doublets.findChain(start, end, priorityQueueManager);
		assertChain(start, end, priorityQueueChain);

		assertEquals("Priority Queue's chain should also be the shortest", minLength, priorityQueueChain.length());
		assertTrue("Priority queue should perform better than Queue: NumberOfNexts()",
				priorityQueueManager.getNumberOfNexts() <= queueManager.getNumberOfNexts());
		assertTrue("Priority queue should perform better than Queue: maxSize()",
				priorityQueueManager.maxSize() <= queueManager.maxSize());
	}

	// You may find it helpful to remove the (timeout = 1000) while debugging. 
	@Test(timeout = 1000)
	public void NoPathTest() {
		assertNoResult("awake", "sleep");
		assertNoResult("angry", "happy");
		sPoints += 3;

	}

	private void assertNoResult(String start, String end) {
		ChainManager manager = new QueueChainManager();
		Chain chain = doublets.findChain(start, end, manager);
		assertNull(chain);

		manager = new StackChainManager();
		chain = doublets.findChain(start, end, manager);
		assertNull(chain);

		manager = new PriorityQueueChainManager(end);
		chain = doublets.findChain(start, end, manager);
		assertNull(chain);
	}

	private void assertChain(String start, String end, Chain chain) {
		Iterator<String> itr = chain.iterator();
		String pre = itr.next(), next;
		assertEquals(start, pre);
		while (itr.hasNext()) {
			next = itr.next();
			if (!assertDiffByOne(pre, next))
				fail(pre + " and " + next + " diff more than one character");
			pre = next;
		}
		assertEquals(end, pre);
	}

	private boolean assertDiffByOne(String pre, String next) {
		assertEquals(pre.length(), next.length());
		boolean x = false;
		for (int i = 0; i < pre.length(); i++) {
			if (pre.charAt(i) != next.charAt(i)) {
				if (x) {
					return false;
				} else {
					x = true;
				}
			}
		}
		return x;
	}
	
	@AfterClass
	public static void printScore() {
		System.out.printf("Dict35 tests (includes PriQ) %.0f/23\n", sPoints);
	}
}
