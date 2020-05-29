package stage2;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BasketballMainTest {

	private static final String RAW_DATA_ALL =     "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	private static final String RAW_DATA_NONE =    "____________________________________________________________________________________________________";

	private BasketballMain simulator;
	private String rawAll0, rawAll1, rawAll2;
	private String rawFifty0, rawFifty1, rawFifty2;
	private String rawNone0, rawNone1, rawNone2;
	
	@Before
	public void setup() {
		simulator = new BasketballMain();
		simulator.handleCreateContest("testContest", 100 );
		simulator.handleCreatePlayer("All", 1.0);
		simulator.handleCreatePlayer("Fifty", 0.5);
		simulator.handleCreatePlayer("None", 0.0);
		
	}
	

	@Test
	public void testHandleCreateAndRunMultipleContests() {
		simulator.handleCreateContest("testContest2", 100 );
		simulator.handleCreateContest("bigContest", 1000 );
		simulator.handleCreateContest("smallContest", 10 );

		
		simulator.handleAddPlayerToContest("All", "testContest2");
		simulator.handleAddPlayerToContest("All", "bigContest");
		simulator.handleAddPlayerToContest("All", "smallContest");
		
		simulator.handleAddPlayerToContest("None", "testContest2");
		simulator.handleAddPlayerToContest("None", "bigContest");
		simulator.handleAddPlayerToContest("None", "smallContest");
		
		
		simulator.handleRunContest("testContest2");
		simulator.handleRunContest("bigContest");
		simulator.handleRunContest("smallContest");
		
		String allContest2Data = simulator.handleGetRawData("All", "testContest2");
		String allbigContestData = simulator.handleGetRawData("All", "bigContest");
		String allSmallContestData = simulator.handleGetRawData("All", "smallContest");
		
		
		String noneContest2Data = simulator.handleGetRawData("None", "testContest2");
		String nonebigContestData = simulator.handleGetRawData("None", "bigContest");
		String noneSmallContestData = simulator.handleGetRawData("None", "smallContest");
		
		assertFalse(  noneContest2Data.contains("X") );
		assertFalse(  nonebigContestData.contains("X") );
		assertFalse(  noneSmallContestData.contains("X") );
		
		assertEquals( "__________", noneSmallContestData.trim()  );
		assertEquals( RAW_DATA_NONE, noneContest2Data.trim()  );
		
		assertFalse(  allContest2Data.contains("_") );
		assertFalse(  allbigContestData.contains("_") );
		assertFalse(  allSmallContestData.contains("_") );
		
		assertEquals( "XXXXXXXXXX", allSmallContestData.trim()  );
		assertEquals( RAW_DATA_ALL, allContest2Data.trim()  );
		

		assertEquals( 10,  allSmallContestData.trim().length() );
		assertEquals( 10,  noneSmallContestData.trim().length() );
		
		assertEquals( 100,  noneContest2Data.trim().length() );
		assertEquals( 100,  allContest2Data.trim().length() );
		
		assertEquals( 1000,  allbigContestData.trim().length() );
		assertEquals( 1000,  nonebigContestData.trim().length() );
	}


	@Test
	public void testHandleRunContest() {
		simulator.handleAddPlayerToContest("All", "testContest");
		simulator.handleAddPlayerToContest("Fifty", "testContest");
		simulator.handleAddPlayerToContest("None", "testContest");
		
		//before contest run
		rawAll0 = simulator.handleGetRawData("All", "testContest");
		rawFifty0 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone0 = simulator.handleGetRawData("None", "testContest");
		
		//after running contest once
		simulator.handleRunContest("testContest");
		rawAll1 = simulator.handleGetRawData("All", "testContest");
		rawFifty1 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone1 = simulator.handleGetRawData("None", "testContest");
		
		simulator.handleRunContest("testContest");
		rawAll2 = simulator.handleGetRawData("All", "testContest");
		rawFifty2 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone2 = simulator.handleGetRawData("None", "testContest");
		
		
		assertFalse( rawFifty0.equals(rawFifty1)  );
		assertFalse( rawAll0.equals(rawAll1)  );
		assertFalse( rawNone0.equals(rawNone1)  );
		
		assertTrue(  rawAll1.equals(rawAll2)  );
		assertTrue(  rawNone1.equals(rawNone2)  );
	}


	@Test
	public void testHandleGetRawData() {
		simulator.handleAddPlayerToContest("All", "testContest");
		simulator.handleAddPlayerToContest("Fifty", "testContest");
		simulator.handleAddPlayerToContest("None", "testContest");
		
		//before contest run
		rawAll0 = simulator.handleGetRawData("All", "testContest");
		rawFifty0 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone0 = simulator.handleGetRawData("None", "testContest");
		
		//after running contest once
		simulator.handleRunContest("testContest");
		rawAll1 = simulator.handleGetRawData("All", "testContest");
		rawFifty1 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone1 = simulator.handleGetRawData("None", "testContest");
		
		simulator.handleRunContest("testContest");
		rawAll2 = simulator.handleGetRawData("All", "testContest");
		rawFifty2 = simulator.handleGetRawData("Fifty", "testContest");
		rawNone2 = simulator.handleGetRawData("None", "testContest");

		//two random 100 shot games have a 1 in 2^100 chance of being identical
		assertFalse( rawFifty0.equals(rawFifty1)  );
		
		//empty should not be equal to non-empty
		assertFalse( rawAll0.equals(rawAll1)  );
		assertFalse( rawNone0.equals(rawNone1)  );
		
		//all makes or all misses should have same value
		assertTrue(  rawAll1.equals(rawAll2)  );
		assertTrue(  rawNone1.equals(rawNone2)  );
		
		assertEquals( RAW_DATA_ALL, rawAll1 );
		assertEquals( RAW_DATA_NONE, rawNone1 );
		
		assertEquals( RAW_DATA_ALL, rawAll2 );
		assertEquals( RAW_DATA_NONE, rawNone2 );

		
	}

	@Test
	public void testHandleGetHitStreakAllOrNone() {
		simulator.handleAddPlayerToContest("All", "testContest");
		simulator.handleAddPlayerToContest("None", "testContest");
		
		simulator.handleRunContest("testContest");
		assertEquals(100, simulator.handleGetHitStreak("All", "testContest")   );
		assertEquals(0, simulator.handleGetHitStreak("None", "testContest")   );

	}
	
	/*
	 * This test will pass if you have a consistent RawData and HitStreak calculation
	 */
	@Test
	public void testHandleGetHitStreakUsingRawDataFifty() {
		simulator.handleAddPlayerToContest("Fifty", "testContest");
		
		assertEquals(0, simulator.handleGetHitStreak("Fifty", "testContest")   );
		simulator.handleRunContest("testContest");
		String fiftyData = simulator.handleGetRawData("Fifty", "testContest");
		
		int maxStreak = 0;
		int currentStreak = 0;
		for (int i=0; i< fiftyData.length(); i++) {
			if (fiftyData.charAt(i) == 'X' ) {
				currentStreak++;
				if ( currentStreak > maxStreak) {
					maxStreak = currentStreak;
				}
			} else {
				currentStreak=0;
			}
		}
		
		assertEquals(maxStreak,  simulator.handleGetHitStreak("Fifty", "testContest")  );

	}
	


	//This test may fail if you change the random seed in BasketballUtility
	@Test
	public void testHandleGetPercentageAllOrNone() {
	
		simulator.handleAddPlayerToContest("All", "testContest");
		simulator.handleAddPlayerToContest("None", "testContest");
		
		simulator.handleRunContest("testContest");
		assertEquals(1.0, simulator.handleGetPercentage("All", "testContest"), 0.0001);
		assertEquals(0.0, simulator.handleGetPercentage("None", "testContest"), 0.0001);
	}
	
	@Test
	public void testHandleGetPercentageUsingRawDataFifty() {
		simulator.handleAddPlayerToContest("Fifty", "testContest");
		simulator.handleRunContest("testContest");
		
		String fiftyData = simulator.handleGetRawData("Fifty", "testContest");
		int made = 0;
		for (int i=0; i< fiftyData.length(); i++) {
			if (fiftyData.charAt(i) == 'X' ) {
				made++;
			} 
		}
		double perc = 1.0*made/fiftyData.length();
		assertEquals( perc, simulator.handleGetPercentage("Fifty", "testContest"), 0.0001);
	}
	
	
	
	//This test depends upon handleGetRawData() being implemented correctly
	@Test
	public void testHandleGetBestPercentageContestNameFifty() {
		
		double bestPercentage = 0;
		String bestContestName =  "";
		
		for (int i=1; i<= 100;i++ ) {
			String contestName = "testMultipleContest"+i;
			simulator.handleCreateContest(contestName, 100 );
			simulator.handleAddPlayerToContest("Fifty", contestName);
			simulator.handleRunContest(contestName);
			
			String fiftyData = simulator.handleGetRawData("Fifty", contestName);
			int made = 0;
			for (int d=0; d< fiftyData.length(); d++) {
				if (fiftyData.charAt(d) == 'X' ) {
					made++;
				} 
			}
			double perc = 1.0*made/fiftyData.length();
			if (perc > bestPercentage) {
				bestPercentage = perc;
				bestContestName = contestName;
			}
			
		}

		assertEquals( bestContestName, simulator.handleGetBestPercentageContestName("Fifty" ) );
		
		//Warning: will not work if "simulator.handleGetPercentage" is not working properly
		assertEquals( bestPercentage, simulator.handleGetPercentage("Fifty", bestContestName ), 0.0001 );
		
	}
	
	
	//This test depends upon handleGetRawData() being implemented correctly
	@Test
	public void testHandleGetBestStreakContestNameFifty() {
		
		double bestStreak = 0;
		String bestContestName =  "";
		
		for (int i=1; i<= 100;i++ ) {
			String contestName = "testMultipleContest"+i;
			simulator.handleCreateContest(contestName, 100 );
			simulator.handleAddPlayerToContest("Fifty", contestName);
			simulator.handleRunContest(contestName);
			
			String fiftyData = simulator.handleGetRawData("Fifty", contestName);
			int maxStreak = 0;
			int currentStreak = 0;
			for (int j=0; j< fiftyData.length(); j++) {
				if (fiftyData.charAt(j) == 'X' ) {
					currentStreak++;
					if ( currentStreak > maxStreak) {
						maxStreak = currentStreak;
					}
				} else {
					currentStreak=0;
				}
			}
			if (maxStreak > bestStreak) {
				bestStreak = maxStreak;
				bestContestName = contestName;
			}

			
		}
		

		assertEquals( bestContestName, simulator.handleGetBestStreakContestName("Fifty" ) );
		
		//Warning: will not work if "simulator.handleGetHitStreak" is not working properly
		assertEquals( bestStreak, simulator.handleGetHitStreak("Fifty", bestContestName ), 0.0001 );
		
	}
	

}
