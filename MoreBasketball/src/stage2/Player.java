package stage2;

import java.util.HashMap;

public class Player {

	public HashMap<Contest, String> contests;
	public double accuracy;
	public String name;

	public Player(String name, double accuracy) {
		this.name = name;
		this.accuracy = accuracy;
		this.contests = new HashMap<Contest, String>();

	}

	public void addContest(Contest contest) {
		this.contests.put(contest, "");
	}

	public void addMiss(Contest contest) {
		String contents = contests.get(contest);
		contests.replace(contest, contents + '_');
	}

	public void addHit(Contest contest) {
		String contents = contests.get(contest);
		contests.replace(contest, contents + 'X');
	}

	public double calculatePercentageHits(Contest contest) {
		String hitMissesString = contests.get(contest);
		double hits = 0.0;
		double shots = 0.0;
		for (int k = 0; k < hitMissesString.length(); k++) {
			if (hitMissesString.charAt(k) == 'X') {
				hits++;
			}
			shots++;
		}
		return hits / shots;
	}

	public int calculateMaxStreak(Contest contest) {
		String hitMissesString = contests.get(contest);
		int maxCount = 0;
		for (int m = 0; m < hitMissesString.length(); m++) {
			int tempCount = 0;
			if (hitMissesString.charAt(m) == 'X') {
				for (int k = m; k < hitMissesString.length(); k++) {
					if (hitMissesString.charAt(k) == 'X') {
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
		return maxCount;
	}
}
