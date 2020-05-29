package stage1;

public class Player {
	
	public String hitsMissesString;
	public double accuracy;
	public String name;
	public double percentageHits;
	public double hits;
	public double shotsTaken;
	
	public Player(String name, double accuracy) {
		this.name=name;
		this.accuracy=accuracy;
		this.hits=0.0;
		this.shotsTaken=0.0;
		this.hitsMissesString = "";
	}


	public void addMiss() {
		this.hitsMissesString += "_";
		this.shotsTaken+=1.0;
		this.percentageHits=(hits/shotsTaken);
	}	
	
	public void addHit() {
		this.hitsMissesString += "X";
		this.shotsTaken+=1.0;
		this.hits+=1.0;
		this.percentageHits=(hits/shotsTaken);
	}
	
//	public String printReport() {
//		String report = ("Player: " + this.name + "      " + 
//						"Accuracy: " + this.accuracy + "      " +
//						"% of shots hit: " + percentageWins);
//		return report;
//	}
}