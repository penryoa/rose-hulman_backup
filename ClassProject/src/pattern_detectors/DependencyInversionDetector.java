package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import containers.ClassContainer;
import containers.MethodContainer;
import conversion.Arrow;
import displays.ClassDisplay;
import displays.DependencyInversionDisplay;

public class DependencyInversionDetector implements PatternDetector {

//	String reason = "";
	/**
	 * Detects violations of the dependency inversion principle.
	 * 
	 * 1. no variable should hold a reference to a concrete class. (NO NEW)
	 * 2. no class should derive from a concrete class. (Superclass must be abstract)
	 * 3. no method should override an implemented method of any of its base classes (Cannot override a concrete method)
	 */
	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		for (ClassContainer c : cc) {
			boolean violates = true;
			while (true) {
				// 1
				if (c.concretes.size() != 0) {
					//System.out.println("criminal: " + c.displayName + ". concretes: " + c.concretes);
//					reason = "Concrete Constructor";
					break; //violation
				}

				// 2
				ClassContainer superClass = null;
				String sName = c.superName;
				if (sName != null && sName != "" && !sName.contains("Object")) {
					// Find the class in the ClassContainer list.
					// Executive decision, if the super class is not in the
					// list of class containers, then we assume that it is abstract.
					for (ClassContainer c2 : cc) {
						if (sName.replace('/', '.').equals(c2.fullName)) {
							superClass = c2;
							break; // inner loop
						}
					}
					
					// Now we must make sure that the superClass is either abstract or null
					if(superClass == null) {
						//then we assume that it is abstract and move on.
					} else {
						//check that it is abstract. if not, break
						if(!superClass.access.contains("abstract")) {
//							reason = "Extends Concrete";
							break; //violation
						}
					}
				}
				
				//3
				//now we know that superClass is an abstract class, or it is null.
				if(superClass != null) {
					//now go through all of the methods of c. If they are also in superClass, it must be an abstract method.
					boolean overridesNonAbstract = false;
					for(MethodContainer m : c.methods) {
						//don't worry about the inits.
						if(!m.name.equals("<init>") && !m.name.equals("<clinit>")) {
							for(MethodContainer m2 : superClass.methods) {
								//if the method name equals the other, make sure that it is abstract.
								//TODO may also need to compare the return and arguments types						
								if(m.name.equals(m2.name)) {
									if (!m2.access.contains("abstract")) {
										overridesNonAbstract = true;
										break; //inner loop
									}
								}
							}
							if(overridesNonAbstract) {
								break; //inner loop
							}
						}
					}
					
					//if there was an override of a non abstract method, then it violates.
					if(overridesNonAbstract) {
//						reason = "NonAbstract Override";
						break; //violation
					}
				}

				violates = false;
				break; //not a violation
			}

			if (violates) {
				markViolation(c);
			}
		}
	}

	private void markViolation(ClassContainer c) {
		ClassDisplay DI = new DependencyInversionDisplay();
		List<ClassContainer> cl = new ArrayList<>();
		cl.add(c);
		DI.setClassDisplay(cl);
	}

}
