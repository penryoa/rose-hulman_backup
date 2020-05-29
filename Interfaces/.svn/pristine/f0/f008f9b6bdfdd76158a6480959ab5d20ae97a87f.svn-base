package simpleExample;

public class AverageMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankAccount[] accounts = new BankAccount[3];
		accounts[0] = new BankAccount();
		accounts[0].setBalance(100);
		accounts[1] = new BankAccount();
		accounts[1].setBalance(150);
		accounts[2] = new BankAccount();
		accounts[2].setBalance(200);

		Country[] countries = new Country[3];
		countries[0] = new Country("Uruguay",176220);
		countries[1] = new Country("Thailand",513120);
		countries[2] = new Country("Belgium",30510);
		
		System.out.println("Average area: " + computeAverageArea(countries));
		
		System.out.println("Average balance: " + computeAverageBalance(accounts));
	}
	
	private static double computeAverageArea(Country[] data) {
		double result = 0;
		for(Country item : data) {
			result = result + item.getArea();
		}
		return result / data.length;
	}
	
	private static double computeAverageBalance(BankAccount[] data) {
		double result = 0;
		for(BankAccount item : data) {
			result = result + item.getBalance();
		}
		return result / data.length;
	}

}
