package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import containers.ClassContainer;
import conversion.Arrow;
import displays.ArrowDisplay;
import displays.ClassDisplay;
import displays.OrangeArrowDisplay;
import displays.OrangeClassDisplay;

public class CompOverInheritanceDetector implements PatternDetector {

	/**
	 * Makes classes who favor composition over inheritance orange, along with offending
	 * arrows.
	 * 
	 * How do we define if a class favors composition over inheritance?
	 * (override too many methods? idk.)
	 */
	@Override
	public void detectPattern(List<ClassContainer> classes, Set<Arrow> arrows) {
		int extendsCt = 0;
		int implementCt = 0;
		String className;
		List<Arrow> badArrs = new ArrayList<Arrow>();
		List<ClassContainer> badClasses = new ArrayList<ClassContainer>();
		for (ClassContainer cc: classes) {
			try {
				className = Class.forName(cc.fullName).getName();
				for (Arrow arr: arrows) {
					if (arr.srcName.contains(className)) {
						if (arr.type.equals("implements")) {
//							System.out.println("\tWe have an IMPLEMENTS arrow from " + arr.srcName + " to " + arr.destName);
							implementCt++;
						} else if (arr.type.equals("extends")) {
//							System.out.println("\tWe have an EXTENDS arrow from " + arr.srcName + " to " + arr.destName);
							extendsCt++;
							badArrs.add(arr);
						}
					}
				}
			} catch (ClassNotFoundException e) {
				// This should always find the class since it is given by ASM;
				e.printStackTrace();
			}
			// Change this line depending on how you define comp > inheritance
			if (extendsCt > 0) {
				badClasses.add(cc);
			}
			extendsCt = 0;
			implementCt = 0;
		}
		
		// shouldn't be any class.
		ClassDisplay cDisp = new OrangeClassDisplay();
		cDisp.setClassDisplay(badClasses);
		
		ArrowDisplay aDisp = new OrangeArrowDisplay();
		aDisp.setArrowDisplay(badArrs);
	}
}
