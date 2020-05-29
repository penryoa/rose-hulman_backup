package stage1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A basketball simulator class which allows players and contest to be created.
 * In a contest all the players shoot a certain number of shots. Reports can be
 * generated for individual players on particular contests.
 */
public class BasketballMain {

//	ArrayList<BasketballContest> contestList;
//	ArrayList<Player> playerList;
	ArrayList<BasketballContest> contestList = new ArrayList<BasketballContest>();
	ArrayList<Player> playerList = new ArrayList<Player>();

	/**
	 * This method sets up an instance of the BasketballMain class You should
	 * not need to modify this method
	 * 
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args) {
		BasketballMain simulator = new BasketballMain();
		System.out.println("Welcome to BasketBall.  Enter commands.  Type 'exit' to end.");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {

			String commandLine = scanner.nextLine();
			String result = simulator.handleCommand(commandLine);
			System.out.println(result);
		}

	}

	/**
	 * This method handles interacting with the user You should not modify this
	 * method
	 * 
	 * @param String
	 *            representing command take from user input
	 */
	private String handleCommand(String command) {
		Scanner input = new Scanner(command);
		String commandType = input.next();
		String toReturn = null;

		if (commandType.equals("create-player")) {
			String name = input.next();
			double accuracy = input.nextDouble();
			handleCreatePlayer(name, accuracy);
			toReturn = "Log: player \"" + name + "\" created";
		} else if (commandType.equals("create-contest")) {
			String contestName = input.next();
			int shots = input.nextInt();
			handleCreateContest(contestName, shots);
			toReturn = "Log: contest \"" + contestName + "\" created";
		} else if (commandType.equals("add-player-to-contest")) {
			String playerName = input.next();
			String contestName = input.next();
			handleAddPlayerToContest(playerName, contestName);
			toReturn = "Log: player \"" + playerName + "\" added to contest \"" + contestName + "\"";
		} else if (commandType.equals("run-contest")) {
			String contestName = input.next();
			handleRunContest(contestName);
			toReturn = "Log: Contest \"" + contestName + "\" was run";
		} else if (commandType.equals("view-contest-report")) {
			String playerName = input.next();
			String contestName = input.next();
			handleViewContestReport(playerName, contestName);
			toReturn = "Log: Viewing report on " + playerName + " in contest: " + contestName;
		} else if (commandType.equals("exit")) {
			input.close();
			System.exit(0);
		} else {
			toReturn = "Unknown command " + commandType;
		}
		input.close();
		return toReturn;
	}

	/**
	 * Use this constructor to initialize any variables you may want to track in
	 * your simulator
	 */
	public BasketballMain() {
	}

	/**
	 * 
	 * Should handle creating and storing information about a player
	 * 
	 * @param name
	 *            Name of the player
	 * @param accuracy
	 *            the chance of making a shot (0-1.0)
	 */
	public void handleCreatePlayer(String name, double accuracy) {
		Player newPlayer = new Player(name, accuracy);
		playerList.add(newPlayer);
	}

	/**
	 * Should handle creating and storing information about a contest
	 * 
	 * @param contestName
	 *            Name of the contest
	 * @param shots
	 *            The number of shots in the contest
	 */
	public void handleCreateContest(String contestName, int shots) {
		BasketballContest newContest = new BasketballContest(contestName, shots);
		contestList.add(newContest);
	}

	/**
	 * Make sure when a specific contest is run that a specific player
	 * participates
	 * 
	 * @param playerName
	 *            Name of player
	 * @param contestName
	 *            The name of contest
	 */
	public void handleAddPlayerToContest(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						contestList.get(k).addPlayer(playerList.get(m));
					}
				}
			}
		}
	}

	/**
	 * This method should run a contest with all players that have been added to
	 * that contest If this contest has already been run, the old data should be
	 * replace by this newly generated data
	 * 
	 * @param contestName
	 *            The name of contest
	 */
	public void handleRunContest(String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (Player player : contestList.get(k).players.keySet()) {
					for (int i = 0; i < contestList.get(k).shotsPerContest; i++) {
						double num = BasketballUtility.getRandomDouble();
						if (num <= player.accuracy) {
							player.addHit();
						} else {
							player.addMiss();
						}
					}
				}
			}
		}
	}

	/**
	 * This method provide a report using the other methods in this document You
	 * should not have to chance this method
	 * 
	 * @param playerName
	 *            name of player
	 * @param contestName
	 *            name of contest
	 */
	public void handleViewContestReport(String playerName, String contestName) {
		System.out.println("Reporting Stats for Player: " + playerName + " in Contest: " + contestName);
		System.out.println("Raw Data:   " + handleGetRawData(playerName, contestName));
		System.out.println("Hit Streak: " + handleGetHitStreak(playerName, contestName));
		System.out.println("Percentage: " + handleGetPercentage(playerName, contestName) * 100 + "%");
	}

	/**
	 * This method returns a String representing the hits and misses of a
	 * particular player on a particular contest. The string should be in the
	 * form: __X__XXX_X_XX_XX_X__X Where an "_" is a miss and a "X" is a hit
	 * 
	 * @param playerName
	 *            Name of player
	 * @param contestName
	 *            The name of contest
	 * 
	 * @return String representing hits and misses
	 */
	public String handleGetRawData(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						return playerList.get(m).hitsMissesString;
					}
				}
			}
		}
		return "";
	}

	/**
	 * This method returns an int representing the maximum number of hits in a
	 * row for a player on a particular contest. In this example
	 * __X__XXX_X_XX_XX_X__X The return value should be 3 because there were
	 * three hits in a row.
	 * 
	 * @param playerName
	 *            Name of player
	 * @param contestName
	 *            The name of contest
	 * 
	 * @return int representing maximum hits in a row
	 */
	public int handleGetHitStreak(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						return contestList.get(k).playerMaxStreak(playerName);
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 
	 * This method returns a double representing hit percentage of a player on a
	 * particular contest. If a player hits 5 out 10 total shots the player
	 * should return 0.5
	 * 
	 * @param playerName
	 *            Name of player
	 * @param contestName
	 *            The name of contest
	 * 
	 * @return double representing hit percentage
	 */
	public double handleGetPercentage(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						return playerList.get(m).percentageHits;
					}
				}
			}
		}
		return -1;
	}

}
