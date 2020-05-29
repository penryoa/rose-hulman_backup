package currency_lab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Currency {
	JButton currencyButton;
	String currencyID;
	GUI gui;
	CurrencyApp app;
	
	public Currency(String ID, GUI gui, CurrencyApp a) {
		this.currencyID = ID;
		this.gui = gui;
		this.app = a;
		this.currencyButton = new JButton("Enter " + this.currencyID);
		this.currencyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notifyCurrencies();
			}
		});
	}
	
	/* because it's a subject, it notifies*/
	public void notifyCurrencies() {
		double amt = gui.getAmount();
		String m1 = String.format("%7.2f %s :\n", amt, this.currencyID);
		gui.outputTextArea.append(m1);
		for (Currency c: app.currencyTable.keySet()) {
			update(amt, this);
		}
	}
	
	/* updates based on the subject*/
	public void update(double amount, Currency c) {
		double otherRate = app.currencyTable.get(c);
		double myRate = app.currencyTable.get(this);
		double convertedAmt = amount * otherRate / myRate;
		String m = String.format("    in %s would be: %7.2f \n",this.currencyID, convertedAmt);
		gui.outputTextArea.append(m);
	}
}
