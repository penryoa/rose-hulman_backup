

public class BankAccount {
	
	double balance;
	String name;
	
	public BankAccount () {
		this.balance = 0;
	}
	
	public BankAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
	}
	
	
	public void deposit (double money) {
		this.balance += money;
	}
	
	
	public void setName (String name) {
		this.name = name;
	}
	
	
	public double getBalance () {
		return this.balance;
	}
	
	public String getName () {
		return this.name;
	}
}