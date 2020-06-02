import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import containers.ClassContainer;
import conversion.Arrow;
import conversion.ArrowFactory;
import conversion.Converter;
import conversion.UMLConverter;
import filter_settings.Alteration;
import pattern_detectors.PatternDetector;

public class UMLConversionApp {

	private Converter converter;
	private ParseSettings settings;

	/**
	 * Decides which file(s) we're going to generate our diagram from.
	 * @param arguments
	 */
	public UMLConversionApp(String[] arguments) {
		this.settings = ParseSettings.getInstance();
		String filePath = null;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter path to settings file(press return to use default): ");
		while (true) {
			String line = s.nextLine();
			if (line.isEmpty()) {
				break;
			} else {
				try {
					filePath = line;
					BufferedReader r = new BufferedReader(new FileReader(filePath));
					r.close();
					break;
				} catch (IOException e) {
					System.out.println("Invalid file path. Please reenter.");
					filePath = null;
				}
			}
		}
		settings.parseSettingsFile(arguments, filePath);
	}

	/**
	 * Get the necessary files to convert, then have the converter convert.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		UMLConversionApp app = new UMLConversionApp(args);
		app.createDiagram();
	}

	/**
	 * Initiates each of the following steps:
	 * 		1. Make all the ClassContainers
	 * 		2. Apply the Alterations (filters through unwanted classes)
	 * 		3. Create the Arrows
	 * 		4. Apply the Pattern Detectors
	 * 		5. Generate the code (put into an output file)
	 * @throws IOException
	 */
	public void createDiagram() throws IOException {
		/* Step 1 */
		List<ClassContainer> parsedClasses = settings.currentParser.parseDesign(settings.classes, settings.blackList);
		
		/* Step 2 */
		for (Alteration alt : settings.alterations) {
			alt.alter(parsedClasses);
		}
		
		/* Step 3 */
		Set<Arrow> arrows = new ArrowFactory(parsedClasses).createArrows();
		
		/* Step 4 */
		for (PatternDetector d : settings.patternDetectors) {
			d.detectPattern(parsedClasses, arrows);
		}
		
		/* Step 5 */
		converter = new UMLConverter(parsedClasses, arrows); // TODO converter type should be passed in, UMLConverter is default
		String UMLCode = converter.convert();
		
		PrintWriter w = new PrintWriter("src/output/output_file.txt");
		w.print(UMLCode);
		w.close();
		
		System.out.println("\nOutput is in the output file! (src/output/output_file)");
	}

}
