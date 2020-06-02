package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;
import conversion.Arrow;
import displays.AdapterArrow;
import displays.AdapterDisplay;
import displays.ArrowDisplay;
import displays.ClassDisplay;

public class AdapterDetector implements PatternDetector {

	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		//this method will look for the adapter in the pattern. And through the adapter it will
		//find the target and the adaptee. Once all three candidates have been identified, the pattern
		//will be verified by the method checkAdapter. If that passes, then the classes will be marked
		//by the method markPattern
		
		for (ClassContainer c : cc) {
			while (true) {
				
				// check that the superName is actually something.
				boolean isInterface = false;
				String sName = c.superName;
				if (sName == null || sName == "" || sName.contains("Object")) {
					// Make sure that there is only one interface then.
					List<String> ints = c.interfaces;
					if(ints.size() == 1) {
						sName = ints.get(0);
						isInterface = true;
					} else {
						break;
					}
				}
								
				// make sure that the super class is in the set of ClassContainers
				ClassContainer target = null;
				for(ClassContainer c2 : cc) {
					if(sName.replace('/', '.').equals(c2.fullName)) {
						target = c2;
						break;
					}
				}
				if(target == null) {
					break;
				}
				

				// Then this class must receive one class in the init function.
				String singleArg = null;
				for(MethodContainer m : c.methods) {
					if (m.name.contains("init") && !m.name.contains("clinit") && m.argumentTypes.size() == 1) {
						singleArg = m.argumentTypeDescription.get(0); //TODO this was changed from argumentTypes
						break;
					}
				}
				if(singleArg == null) {
					break;
				}
				
				// make sure that that name is in the ClassContainer list
				ClassContainer adaptee = null;
				for(ClassContainer c2 : cc) {
					if(singleArg.equals(c2.fullName)) {
						adaptee = c2;
						break;
					}
				}
				if(adaptee == null) {
					break;
				}
								
				// Make sure that the target and the adaptee are not the same.
				if(target.equals(adaptee)) {
					break;
				}
				
				//now check that the adapter is actually true.
				boolean validAdapter = checkAdapter(c, target, adaptee);
				if(!validAdapter) {
					break;
				}
				
				//now, mark each of the ClassContainers accordingly
				markPattern(c, target, adaptee);
				
				//now mark the arrow
				for(Arrow a : arrows) {
					if(a.srcName.equals(c.fullName) && a.destName.equals(adaptee.fullName) && a.type.equals("association")) {
						markArrow(a);
					}
				}
				
				break;
			}
		}
	}
	
	private boolean checkAdapter(ClassContainer adapter, ClassContainer target, ClassContainer adaptee) {
		
		// the adapter must contain only methods that are from target.
		for(MethodContainer m : adapter.methods) {
			boolean hasMethod = false;
			if(!m.name.equals("<init>") && !m.name.equals("<clinit>")) {
				for(MethodContainer m2 : target.methods) {
					//TODO may also need to compare the return and arguments types						
					if(m.name.equals(m2.name)) {
						hasMethod = true;
						break;
					}
				}
				if(!hasMethod) {
					return false;
				}
			}
		}
		
		// the adapter must save an adaptee (maybe as private?)
		boolean hasField = false;
		for(FieldContainer f : adapter.fields) {
			if(f.typeDescription.equals(adaptee.fullName)) { //TODO changed from type to typeDescription
				hasField = true;
			}
		}
		if(!hasField) {
			return false;
		}
		
		return true;
	}
	
	private void markPattern(ClassContainer adapter, ClassContainer target, ClassContainer adaptee) {
		ClassDisplay AD = new AdapterDisplay();
		List<ClassContainer> classes = new ArrayList<>();
		classes.add(adapter);
		classes.add(adaptee);
		classes.add(target);
		AD.setClassDisplay(classes);
	}
	
	private void markArrow(Arrow a) {
		ArrowDisplay AA = new AdapterArrow();
		List<Arrow> arrows = new ArrayList<>();
		arrows.add(a);
		AA.setArrowDisplay(arrows);
	}
}
