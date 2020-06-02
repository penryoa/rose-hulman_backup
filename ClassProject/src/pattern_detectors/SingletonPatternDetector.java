package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;
import conversion.Arrow;
import displays.SingletonClassDisplay;

public class SingletonPatternDetector implements PatternDetector {

	/**
	 * Turns singleton class frame blue and add a <<Singleton>> stereotype to the
	 * class Make sure that it is the class FRAME
	 * 
	 */
	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		List<ClassContainer> matchingClasses = new ArrayList<>();
		for (ClassContainer c : cc) {
			boolean privateConstructor = false;
			boolean instanceMethod = false;
			boolean privateSelfField = false;
			
			for (MethodContainer m : c.methods) {
				if (m.name.equals("<init>") && m.access.equals("-")) {
					privateConstructor = true;
				}
				else if (m.returnType.equals(c.fullName) && m.access.contains("static")) {
					instanceMethod = true;
				}
			}
			
			for(FieldContainer f : c.fields) {
				if(f.type.equals(c.fullName) && f.access.contains("-")) {
					privateSelfField = true;
				}
			}
			
			if (privateConstructor && instanceMethod && privateSelfField) {
				matchingClasses.add(c);
			}
		}
		new SingletonClassDisplay().setClassDisplay(matchingClasses);
	}
}
