package stage2;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Scanner;


public class BasketballMain {
	
	ArrayList<Contest> contestList = new ArrayList<Contest>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	public static void main(String[] args) {
		BasketballMain simulator = new BasketballMain();
		System.out.println("Welcome to BasketBall.  Enter commands.  Type 'exit' to end.");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true) {

			String commandLine = scanner.nextLine();
			String result = simulator.handleCommand(commandLine);
			System.out.println(result);
		}
		
	}
	
	private String handleCommand(String command) {
		Scanner input = new Scanner(command);
		String commandType = input.next();
		String toReturn = null;

		if(commandType.equals("create-player")) {
			String name = input.next();
			double accuracy = input.nextDouble();
			handleCreatePlayer(name, accuracy);
			toReturn = "Log: player \""+name+"\" created";
		}
		else if (commandType.equals("create-contest")) {
			String contestName = input.next();
			int shots = input.nextInt();
			handleCreateContest(contestName, shots);
			toReturn = "Log: contest \""+contestName+"\" created";
		}
		else if (commandType.equals("add-player-to-contest")) {
			String playerName = input.next();
			String contestName = input.next();
			handleAddPlayerToContest(playerName, contestName);
			toReturn = "Log: player \""+playerName+"\" added to contest \""+contestName+"\"";
		}
		
		// NEW
		else if (commandType.equals("get-player-best-streak")) {
			String playerName = input.next();
			handleReportBestStreakForPlayer(playerName );
			toReturn = "Log: player \""+playerName+"\" best streak reported.";
		}
		
		else if (commandType.equals("get-player-best-percentage")) {
			String playerName = input.next();
			handleReportBestPercentageForPlayer(playerName );
			toReturn = "Log: player \""+playerName+"\" best percentage reported.";
		}
		//		
		else if (commandType.equals("run-contest")) {
			String contestName = input.next();
			handleRunContest(contestName);
			toReturn = "Log: Contest \""+contestName+"\" was run";
		}
		else if (commandType.equals("view-contest-report")) {
			String playerName = input.next();
			String contestName = input.next();
			handleViewContestReport(playerName, contestName);
			toReturn = "Log: Viewing report on " + playerName +" in contest: " + contestName;
		}
		else if(commandType.equals("exit")) {
			input.close();
			System.exit(0);
		}
		else {
			toReturn = "Unknown command " + commandType;
		}
		input.close();
		return toReturn;
	}

	
	public BasketballMain() {
		//TODO optionally use this to initialize instance variables
		
	}
	
	public void handleCreatePlayer(String name, double accuracy) {
		Player newPlayer = new Player(name, accuracy);
		playerList.add(newPlayer);
	}
	
	public void handleCreateContest(String contestName, int shots) {
		Contest newContest = new Contest(contestName, shots);
		contestList.add(newContest);
	}
	
	public void handleAddPlayerToContest(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						Player player = playerList.get(m);
						Contest contest = contestList.get(k);
						player.addContest(contest);
						contest.addPlayer(player);
					}
				}
			}
		}
	}
	
	public void handleRunContest(String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				Contest contest = contestList.get(k);
				for (Player player : contest.players.keySet()) {
					for (int i = 0; i < contest.shotsPerContest; i++) {
						double num = BasketballUtility.getRandomDouble();
						if (num <= player.accuracy) {
							player.addHit(contest);
						} else {
							player.addMiss(contest);
						}
					}
				}
			}
		}
	}
	
	public void handleViewContestReport(String playerName, String contestName ) {
		System.out.println("Reporting Stats for Player: " + playerName + " in Contest: " + contestName);
		System.out.println("Raw Data:   " + handleGetRawData(playerName, contestName ) );
		System.out.println("Hit Streak: " + handleGetHitStreak(playerName, contestName ));
		System.out.println("Percentage: " + handleGetPercentage(playerName, contestName )*100 + "%" );
	}
	
	public String handleGetRawData(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						Contest contest = contestList.get(k);
						Player player = playerList.get(m);	
						return player.contests.get(contest);
					}
				}
			}
		}
		return "";
	}
	
	public int handleGetHitStreak(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						Contest contest = contestList.get(k);
						Player player = playerList.get(m);
						return player.calculateMaxStreak(contest);
					}
				}
			}
		}
		return -1;
	}
	
	public double handleGetPercentage(String playerName, String contestName) {
		for (int k = 0; k < contestList.size(); k++) {
			if (contestList.get(k).contestTitle.equals(contestName)) {
				for (int m = 0; m < playerList.size(); m++) {
					if (playerList.get(m).name.equals(playerName)) {
						Contest contest = contestList.get(k);
						Player player = playerList.get(m);
						return player.calculatePercentageHits(contest);
					}
				}
			}
		}
		return -1;
	}
	
	//NEW REQUIREMENTS BELOW
	
	public String handleGetBestStreakContestName(String playerName ) {
		for (int k=0; k < playerList.size(); k++) {
			if (playerList.get(k).name.equals(playerName)) {
				Player player = playerList.get(k);
				int maxStreak = 0;
				String bestStreakContestName = "";
				for (Contest contest : player.contests.keySet()) {
					int tempStreak = player.calculateMaxStreak(contest);
					if (tempStreak > maxStreak) {
						maxStreak = tempStreak;
						bestStreakContestName = contest.contestTitle;
					}
				}
				return bestStreakContestName;
			}
		}
		return null;
	}

	public String handleGetBestPercentageContestName(String playerName ) {
		for (int k=0; k < playerList.size(); k++) {
			if (playerList.get(k).name.equals(playerName)) {
				Player player = playerList.get(k);
				double maxPer = 0.0;
				String bestPerContestName = "";
				for (Contest contest : player.contests.keySet()) {
					double tempPer = player.calculatePercentageHits(contest);
					if (tempPer > maxPer) {
						maxPer = tempPer;
						bestPerContestName = contest.contestTitle;
					}
				}
				return bestPerContestName;
			}
		}
		return null;
	}
	
	////////////////////////////////////
	
	//Code that does not require changing
	public void handleReportBestStreakForPlayer(String playerName) {
		String bestGame = handleGetBestStreakContestName(playerName );
		String result = playerName + "'s best streak was in the game \"" + bestGame + "\"";
		result += "\n" + handleGetRawData(playerName,  bestGame);
		result += "\nWith " + handleGetHitStreak(playerName, bestGame) + " shots made in a row!";
		System.out.println( result );
	}
	
	private void handleReportBestPercentageForPlayer(String playerName) {
		String bestGame = handleGetBestPercentageContestName(playerName );
		String result = playerName + "'s best percentage was in the game \"" + bestGame + "\"";
		result += "\n" + handleGetRawData(playerName,  bestGame);
		result += "\nWith " + handleGetPercentage(playerName, bestGame ) *100 + "% of shots made!";
		System.out.println( result );
	}

}
