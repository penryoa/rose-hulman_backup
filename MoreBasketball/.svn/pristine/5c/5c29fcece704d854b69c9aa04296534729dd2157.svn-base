package stage2;

import java.util.HashMap;

public class Contest {

	public String contestTitle;
	public int shotsPerContest;
	public HashMap<Player, Double> players;

	public Contest(String contestTitle, int shotsPerContest) {
		this.contestTitle = contestTitle;
		this.shotsPerContest = shotsPerContest;
		this.players = new HashMap<Player, Double>();
	}

	public void addPlayer(String name, double accuracy) {
		Player newPlayer = new Player(name, accuracy);
		this.players.put(newPlayer, accuracy);
	}

	public void addPlayer(Player player) {
		this.players.put(player, player.accuracy);
	}

}
