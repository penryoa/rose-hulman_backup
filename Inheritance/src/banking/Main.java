package banking;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		BankAccount account = new BankAccount();
		SavingsAccount savings = new SavingsAccount(1.0);
		
		ArrayList<BankAccount> accounts = new ArrayList<>();
		accounts.add(account);
		accounts.add(savings);
		
		for (BankAccount currentAccount: accounts) {
			currentAccount.deposit(100);
		}
		
	}
}
