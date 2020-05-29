package stage1;


/**
 * A basketball simulator. See the specification.
 *
 * @author Olivia Penry
 */
public class BasketballMain {

	public static void main(String[] args) {
		
//		 TEST ONE
		System.out.println("Test One: the Spurs");
		int numberOfShots = 25;
		BasketballContest spurs = new BasketballContest("Spurs Contest", numberOfShots);
		spurs.addPlayer("Duncan", 0.75);
		spurs.addPlayer("Parker", 0.70);
		spurs.addPlayer("Ginobli", 0.65);
		
		for (Player player: spurs.players.keySet()) {
			for (int i=0; i< numberOfShots; i++) {
				double num = BasketballUtility.getRandomDouble();
				if (num <= player.accuracy) {
					player.addHit();
				} else {
					player.addMiss();
				}
			}
		}
		
		spurs.playerScoreReport("Duncan");
		spurs.playerScoreReport("Parker");
		spurs.playerScoreReport("Ginobli");
		spurs.playerPercentageHit("Duncan");
		spurs.playerPercentageHit("Parker");
		spurs.playerPercentageHit("Ginobli");
		System.out.println("Duncan max streak: " + spurs.playerMaxStreak("Duncan"));
		System.out.println("Parker max streak: " + spurs.playerMaxStreak("Parker"));
		System.out.println("Ginobli max streak: " + spurs.playerMaxStreak("Ginobli"));
	
	
//		TEST TWO
		System.out.println();
		System.out.println("Test Two: the Pacers");
		
		numberOfShots = 20;
		BasketballContest pacers = new BasketballContest("Pacers Contest", numberOfShots);
		pacers.addPlayer("Stephenson", 0.43);
		pacers.addPlayer("Oladipo", 0.44);
		pacers.addPlayer("Sabonis", 0.48);
	
		for (Player player: pacers.players.keySet()) {
			for (int i=0; i< numberOfShots; i++) {
				double num = BasketballUtility.getRandomDouble();
				if (num <= player.accuracy) {
					player.addHit();
				} else {
					player.addMiss();
				}
			}
		}
	
		pacers.playerScoreReport("Stephenson");
		pacers.playerScoreReport("Oladipo");
		pacers.playerScoreReport("Sabonis");
		pacers.playerPercentageHit("Stephenson");
		pacers.playerPercentageHit("Oladipo");
		pacers.playerPercentageHit("Sabonis");
		System.out.println("Stephenson max streak: " + pacers.playerMaxStreak("Stephenson"));
		System.out.println("Oladipo max streak: " + pacers.playerMaxStreak("Oladipo"));
		System.out.println("Sabonis max streak: " + pacers.playerMaxStreak("Sabonis"));
	}
}
