package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import containers.ClassContainer;
import conversion.Arrow;
import displays.OrangeClassDisplay;

public class ClassesStartWithPDetector implements PatternDetector{

	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		List<ClassContainer> matches = new ArrayList<>();
		for (ClassContainer c : cc) {
			if (c.displayName.startsWith("P") || c.displayName.startsWith("p")){
				matches.add(c);
			}
		}
		new OrangeClassDisplay().setClassDisplay(matches);
	}

}
