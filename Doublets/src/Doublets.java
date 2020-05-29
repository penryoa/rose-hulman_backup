import java.util.Scanner;
import java.util.Set;

/**
 * @author Olivia Penry and Kaelyn Bock
 */
public class Doublets {
	static private LinksInterface links;
	private static ChainManager chainType;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		Doublets doublet = new Doublets(links);
		// TODO: write a text-based UI to find doublets.

		System.out.println("Welcome to Doublets, a game of 'verbal torture.'");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter starting word");
			String start = scanner.nextLine();

			// if (start != [in dictionary]){
			// System.out.println("The word " + start + " is not valid. Please
			// try
			// again.");
			// return;
			// }

			System.out.println("Enter ending word");
			String end = scanner.nextLine();

			if (start.length() != end.length()) {
				System.out.println("Words must be same length.");
				return;
			}

			System.out.println(" Enter chain manager (s: stack, q: queue, p: priority queue, x: exit)");
			String type = scanner.nextLine();

			if (type.equals("s")) {
				chainType = new StackChainManager();
			} else if (type.equals("q")) {
				chainType = new QueueChainManager();
				// }else if (type.equals("p")) {
				// chainType = new PriorityQueueChainManager(end);
			} else if (type.equals("x")) {
				System.exit(0);
			}

			Chain chain = doublet.findChain(start, end, chainType);
			if (chain == null) {
				System.out.println("No doublet chain exists");
				continue;
			}
		}
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		if (start.length() == end.length()) {
			Chain chain = new Chain();
			manager.add(chain.addLast(start));
			while (!manager.isEmpty()) {
				chain = manager.next();
				if (chain.getLast().equals(end)) {
					return chain;
				}
				Set<String> newSet = links.getCandidates((String) chain.getLast());
				if (newSet == null) {
					continue;
				}
				for (String tryMe : newSet) {
					manager.add(chain.addLast(tryMe));
				}
			}
		}

		return null;
	}

}
