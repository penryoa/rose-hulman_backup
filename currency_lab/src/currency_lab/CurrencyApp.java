package currency_lab;

import java.util.HashMap;
import java.util.Map;

public class CurrencyApp {

	Map<Currency, Double> currencyTable = new HashMap<>(); //currencyID (String) and its rate (int)
	GUI gui = new GUI();
	
	public CurrencyApp() {
		Currency pounds = new Currency("pounds", gui, this);
		this.currencyTable.put(pounds, 1.27);

		Currency dollars = new Currency("US dollars", gui, this);
		this.currencyTable.put(dollars, 1.00);

		Currency euros = new Currency("Euros", gui, this);
		this.currencyTable.put(euros, 1.14);
		
		Currency gold = new Currency("gold", gui, this);
		this.currencyTable.put(gold, 2.00);
	}
	
	public static void main(String[] args) {
		String text = "Push the desired button to enter your amount to convert.\n"
				+ "Choices are shown in the buttons.\n"
				+ "Results will show equivalent amounts in the other currencies.";
		
		CurrencyApp app = new CurrencyApp();
		app.gui.run(app, text);
	}

}
