import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displays.BadDecoratorClassDisplay;
import filter_settings.Alteration;
import filter_settings.NormalParser;
import filter_settings.Parser;
import filter_settings.PrivateAlteration;
import filter_settings.ProtectedAlteration;
import filter_settings.PublicAlteration;
import filter_settings.RecursiveParser;
import filter_settings.RecursiveParserV2;
import filter_settings.SyntheticToggleAlteration;
import pattern_detectors.AdapterDetector;
import pattern_detectors.BadDecoratorDetector;
import pattern_detectors.BidirectionalDetector;
import pattern_detectors.ClassesStartWithPDetector;
import pattern_detectors.CompOverInheritanceDetector;
import pattern_detectors.DecoratorDetector;
import pattern_detectors.DependencyInversionDetector;
import pattern_detectors.PatternDetector;
import pattern_detectors.SingletonPatternDetector;

public class ParseSettings {
	private static ParseSettings instance = new ParseSettings();

	// Globals
	private static String PARSER_FLAG = "-p";
	private static String ALTERATIONS_FLAG = "-a";
	private static String DETECTOR_FLAG = "-d";
	private static String BLACKLIST_FLAG = "-b";
	private static String FILE_FLAG = "-f";

	/* reference maps */
	private Map<String, Parser> parserMap;
	private Map<String, Alteration> alterationMap;
	private Map<String, PatternDetector> detectorMap;

	public List<Alteration> alterations;
	public List<PatternDetector> patternDetectors;  //?
	public Parser currentParser = new NormalParser();
	public List<String> classes = new ArrayList<String>();
	public List<String> blackList = new ArrayList<String>();

	private ParseSettings() {
		/* FIXME fills in the maps; we won't need this later */
		setMaps();
	}

	public synchronized static ParseSettings getInstance() {
		return instance;
	}

	protected void parseSettingsFile(String[] commandLineArguments, String filePath) {
		if (filePath == null || filePath == "") {
			filePath = "src/inputs/ThisProject";
		}
		filterArgs(filePathToArgs(filePath));
		// command line arguments should take place after
		filterArgs(commandLineArguments);
	}

	/**
	 * Turns a file path to an array of strings.
	 * 
	 * @param filePath
	 * @return
	 */
	private String[] filePathToArgs(String filePath) {
		List<String> inputs = new ArrayList<>();
		try {
			BufferedReader r = new BufferedReader(new FileReader(filePath));
			String line = r.readLine();
			while (line != null) {
				if (!line.isEmpty()) {
					inputs.add(line);
				}
				line = r.readLine();
			}
			r.close();
		} catch (IOException e) {
			System.out.println("Invalid file path: " + filePath);
			e.printStackTrace();
		}
		String[] output = new String[inputs.size()];
		for (int i = 0; i < inputs.size(); i++) {
			output[i] = inputs.get(i);
		}
		return output;
	}

	/**
	 * Filters the input arguments into alterations, classes, and parsers
	 */
	private void filterArgs(String[] arguments) {
		/*
		 * FIXME This is a pretty nasti boi
		 */
		if (arguments != null) {
			for (String temp : arguments) {
				if (temp.contains(PARSER_FLAG)) {
					temp = temp.replace(PARSER_FLAG, "");
					currentParser = parserMap.get(temp);
				} else if (temp.contains(ALTERATIONS_FLAG)) {
					temp = temp.replace(ALTERATIONS_FLAG, "");
					alterations.add(alterationMap.get(temp));
				} else if (temp.contains(DETECTOR_FLAG)) {
					temp = temp.replace(DETECTOR_FLAG, "");
					patternDetectors.add(detectorMap.get(temp));
				} else if (temp.contains(BLACKLIST_FLAG)) {
					temp = temp.replace(BLACKLIST_FLAG, "");
					blackList.add(temp);
				} else if (temp.contains(FILE_FLAG)){
					temp = temp.replace(FILE_FLAG, "");
					File f = new File(temp);
					String[] subNode = f.list();
					if (subNode != null) {
						for (String filename : subNode) {
							File f2 = new File(f.getPath() + "/" + filename);
							if (f2.getPath().contains(".class")) {
								String toAdd = f2.getPath();
								classes.add(toAdd);
							} else if (!f2.getName().substring(0, 1).equals(".")){
								for (String className : f2.list()) {
									File f3 = new File(f2.getPath() + "/" + className);
									if (f3.getPath().contains(".class")) {
										String toAdd = f3.getPath();
										classes.add(toAdd);
										// version of class reader with .class
									}
								}
							}
						}
					} else {
						classes.add(temp);
					}
				} else {
					classes.add(temp);
				}
			}
		}
	}

	/**
	 * 
	 * FIXME All the following methods are for testing. We'll pass in a file path
	 * instead of all this junk after while; we've got to get this working first.
	 * 
	 */
	private void setMaps() {
		/* parsers */
		parserMap = new HashMap<String, Parser>();
		parserMap.put("Normal", new NormalParser());
		parserMap.put("Recursive", new RecursiveParser());
		parserMap.put("RecursiveV2", new RecursiveParserV2());

		/* alterations */
		alterations = new ArrayList<Alteration>();
		alterationMap = new HashMap<String, Alteration>();
		alterationMap.put("Private", new PrivateAlteration());
		alterationMap.put("Public", new PublicAlteration());
		alterationMap.put("Protected", new ProtectedAlteration());
		alterationMap.put("Syn", new SyntheticToggleAlteration());

		/* detectors */
		patternDetectors = new ArrayList<PatternDetector>(); //?
		
		detectorMap = new HashMap<String, PatternDetector>();
		detectorMap.put("Singleton", new SingletonPatternDetector());
		detectorMap.put("Comp", new CompOverInheritanceDetector());
		detectorMap.put("TestingP", new ClassesStartWithPDetector());
		detectorMap.put("Bidirectional", new BidirectionalDetector());
		detectorMap.put("Adapter", new AdapterDetector());
		detectorMap.put("Dependency", new DependencyInversionDetector());
		detectorMap.put("Decorator", new DecoratorDetector());
		detectorMap.put("BadDecorator", new BadDecoratorDetector());
		
		patternDetectors.add(new BidirectionalDetector()); //?
	}
}
