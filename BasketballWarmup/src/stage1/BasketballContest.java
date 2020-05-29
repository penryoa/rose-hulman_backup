package stage1;

import java.util.HashMap;

public class BasketballContest {

	public String contestTitle;
	public int shotsPerContest;
	public HashMap<Player, Double> players;

	public BasketballContest(String contestTitle, int shotsPerContest) {
		this.contestTitle = contestTitle;
		this.shotsPerContest = shotsPerContest;
		this.players = new HashMap<Player, Double>();
	}

	public void addPlayer(String name, double accuracy) {
		Player newPlayer = new Player(name, accuracy);
		this.players.put(newPlayer, accuracy);
	}

	// public void printPlayerReport(Player player) {
	// player.printReport();
	// }

	public void playerScoreReport(String player) {
		for (Player players : players.keySet()) {
			if (player.equals(players.name)) {
				System.out.println(players.name + ": " + players.hitsMissesString);
			}
		}
	}

	public void playerPercentageHit(String player) {
		for (Player players : players.keySet()) {
			if (player.equals(players.name)) {
				System.out.println(players.name + " percentage wins: " + players.percentageHits);
			}
		}
	}

	public int playerMaxStreak(String player) {
		
		int maxCount = 0;
		
		for (Player players : players.keySet()) {
			if (player.equals(players.name)) {
				
				for (int m = 0; m < players.hitsMissesString.length()-1; m++) {
					int tempCount = 0;
					if (players.hitsMissesString.charAt(m) == 'X') {
						for (int k = m; k < players.hitsMissesString.length(); k++) {
							if (players.hitsMissesString.charAt(k) == 'X') {
								tempCount += 1;
							} else {
								break;
								}
							}
						}
					if (tempCount > maxCount) {
						maxCount = tempCount;	
					}
				}
			}
		}
		return maxCount;
	}
	
	
	
}
