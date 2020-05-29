package simpleExample;

public class AverageMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankAccount[] accounts = new BankAccount[3];
		accounts[0] = new BankAccount();
		accounts[0].setMeasure(100);
		accounts[1] = new BankAccount();
		accounts[1].setMeasure(150);
		accounts[2] = new BankAccount();
		accounts[2].setMeasure(200);

		Country[] countries = new Country[3];
		countries[0] = new Country("Uruguay",176220);
		countries[1] = new Country("Thailand",513120);
		countries[2] = new Country("Belgium",30510);
		
		
		Measurable[] measurable = new Measurable[3];
		measurable[0] =  new BankAccount();
		measurable[0].setMeasure(100);
		measurable[0] = new Country("USA", 9999);
		
		
		System.out.println("Average area: " + computeAverageMeasure(countries));
		
		System.out.println("Average balance: " + computeAverageMeasure(accounts));
	}
	
	private static double computeAverageMeasure(Measurable[] data) {
		double result = 0;
		for(Measurable item : data) {
			result = result + item.getMeasure();
		}
		return result / data.length;
	}

}
