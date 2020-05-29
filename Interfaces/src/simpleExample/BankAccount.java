package simpleExample;

public class BankAccount implements Measurable {
	
	private double balance;
	
	public BankAccount() {
		this.balance = 0;
	}
	
	public void setMeasure(double balance) {
		this.balance = balance;
	}
	
	public double getMeasure() {
		return this.balance;
	}
	
	
}
