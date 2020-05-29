import java.util.Scanner;

public class MysB {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		 // need a line and a first character
		
		while (true) {
			System.out.println("Which company will you work for?");
			String line = s.nextLine();
			if (line.length() == 0) { // ASSUMES THE COMPANY DOES NOT START WITH
										// A NUMBER
				System.out.println("Invalid; please reenter valid company.");
			} else {
				break;
			}
		}

		String[] companies = { "Amazon.com", "Barnes Group", "Citigroup", "Walt Disney Company (The)", "eBay",
				"Facebook", "Alphabet Inc.", "Hyatt Hotels Corporation Class", "Intel Corporation", "Juniper Networks",
				"Kellogg Company", "Lockheed Martin Corporation", "Microsoft Corporation", "Netflix",
				"Oracle Corporation", "The Priceline Group Inc.", "QUALCOMM Incorporated", "Raytheon Company",
				"Saleforce.com Inc.", "AT&T Inc.", "United Technologies Corporation", "Visa Inc.",
				"Wells Fargo & Company", "Xerox Corporation", "Yelp Inc.", "Zynga Inc. " };

		char first = 'a';
		
		System.out.println("You will work at GameStop always" );

	}

}
