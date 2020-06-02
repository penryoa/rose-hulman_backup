package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;
import conversion.Arrow;
import displays.BadDecoratorClassDisplay;
import displays.ComponentClassDisplay;
import displays.ConcreteDecoratorDisplay;
import displays.DecoratorArrowDisplay;
import displays.DecoratorClassDisplay;

public class BadDecoratorDetector implements PatternDetector{

	private List<ClassContainer> cc;
	private Set<Arrow> arrows;
	private List<ClassContainer> badDecoratorClasses = new ArrayList<>();
	private List<ClassContainer> decoratorClasses = new ArrayList<>();
	private List<ClassContainer> componentClasses = new ArrayList<>();
	private List<ClassContainer> concreteClasses = new ArrayList<>();
	private List<Arrow> componentArrows = new ArrayList<>();



	/**
	 * A class is a decorator if it fits all the requirements below: 1. It extends
	 * an abstract class/implements an interface, S, and S extends/implements a
	 * class C. 2. It implements every method of C. (is this just for a good
	 * decorator...?) 3. It holds field of type C. 4. It's a concrete class.
	 */
	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		this.cc = cc;
		this.arrows = arrows;


		getAllDecorators();

		/* Check for Concrete Decorators */
		for (ClassContainer clazz : cc) {
			if (clazz.access.equals("class")) {
				ClassContainer decorator = getSuperClass(clazz);
				if (decoratorClasses.contains(decorator) || badDecoratorClasses.contains(decorator)) {
					setGoodAndBadDecorators(clazz, getComponentClass(decorator));
				}
			}
		}
		new DecoratorArrowDisplay().setArrowDisplay(componentArrows);
		new DecoratorClassDisplay().setClassDisplay(decoratorClasses);
		new ComponentClassDisplay().setClassDisplay(componentClasses);
		new ConcreteDecoratorDisplay().setClassDisplay(concreteClasses);
		new BadDecoratorClassDisplay().setClassDisplay(badDecoratorClasses);
	}
	
	/**
	 * This does all the work for the abstract decorators.
	 * 
	 * @param decoratorClasses
	 * @param componentClasses
	 * @param componentArrows
	 */
	private void getAllDecorators() {
		for (ClassContainer clazz : cc) {
			ClassContainer decoratorClass = null;
			ClassContainer componentClass = null;

			if (clazz.access.contains("class")) {
				// clazz *may* have a component: either extends or implements the component
				componentClass = getComponentClass(clazz);
				if (componentClass != null) {
					decoratorClass = clazz;
					setGoodAndBadDecorators(decoratorClass, componentClass);
					
					if (!componentClasses.contains(componentClass)) {
						componentClasses.add(componentClass);
					}
					for (Arrow arr : this.arrows) {
						if ((decoratorClass != null && arr.srcName.contains(decoratorClass.fullName)
								&& arr.destName.contains(componentClass.fullName)) && arr.type.equals("association")
								&& !componentArrows.contains(arr)) {
							componentArrows.add(arr);
						}
					}
				}
			}
		}
	}




	/**
	 * Our concrete class will always extend an abstract class.
	 * 
	 * @param c
	 * @return
	 */
	private ClassContainer getSuperClass(ClassContainer c) {
		ClassContainer subclassContainer = null;
		String sName = c.superName;
		if (sName != null && sName != "" && !sName.contains("Object")) {
			ClassContainer absClassCont = containerFromName(sName);
			if (absClassCont != null && absClassCont.access.contains("class")) {
				subclassContainer = absClassCont;
			}
		}
		return subclassContainer;
	}

	/**
	 * Gets the class container of a *potential* decorator's component. The
	 * component is an abstract class or an interface.
	 * 
	 * @param potentialDecorator
	 * @return
	 */
	private ClassContainer getComponentClass(ClassContainer potentialDecorator) {
		ClassContainer component = null;
		if (potentialDecorator.superName != null && potentialDecorator.superName != "") {
			component = containerFromName(potentialDecorator.superName);
		
			if (component != null && !checkFields(potentialDecorator, component.fullName)
					&& !instanceIsPassedIntoConstructor(potentialDecorator, component)) {
				// the potential decorator doesn't have a field of type Component OR the field
				// isn't passed in the constructor, so invalid
				// decorator.
				component = null;
			}
		}
		if (component == null && !potentialDecorator.interfaces.isEmpty()) {
			// check if component is an interface; it's definitely not an abstract class.
			for (String interfaceName : potentialDecorator.interfaces) {
				component = containerFromName(interfaceName);
				if (component != null && !checkFields(potentialDecorator, component.fullName)
						&& !instanceIsPassedIntoConstructor(potentialDecorator, component)) {
					// the potential decorator doesn't have a field of type Component OR the field
					// isn't passed in the constructor, so invalid
					// decorator.
					component = null;
				} 
			}
		}
		return component;
	}

	/**
	 * Returns null if the class is not in our ClassContainers; returns the
	 * respective ClassContainer otherwise. This logic is taken from
	 * AdapterDecorator.
	 * 
	 * @param name
	 * @param cc
	 * @return
	 */
	private ClassContainer containerFromName(String name) {
		ClassContainer target = null;
		for (ClassContainer clazz : cc) {
			if (name.replace('/', '.').equals(clazz.fullName)) {
				target = clazz;
				break;
			}
		}
		return target;
	}

	/**
	 * Checks to see if the given class contains a field of the given type
	 * 
	 * @param clazz
	 * @param type
	 * @return
	 */
	private boolean checkFields(ClassContainer clazz, String type) {
		for (FieldContainer fc : clazz.fields) {
			if (fc.type.contains(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if an instance of type potentialComponent is passed in to the potentialDecorator.
	 * @param potentialDecorator
	 * @param potentialComponent
	 * @return
	 */
	private boolean instanceIsPassedIntoConstructor(ClassContainer potentialDecorator,
			ClassContainer potentialComponent) {
		boolean isPassedIn = false;
		for (MethodContainer m : potentialDecorator.methods) {
			if (m.name.contains("init")) {
				if (m.argumentTypes.contains(potentialComponent.fullName)) {
					isPassedIn = true;
					break;
				}
			}
		}
		return isPassedIn;
	}

	
	/**
	 * Makes sure that all the public methods of the component are
	 * implemented/overridden in the decorator. If not, we add in a new method
	 * container to the decorator with a highlighted color.
	 * 
	 * @param decorator
	 * @param component
	 * @return
	 */
	private boolean checkMethods(ClassContainer decorator, ClassContainer component) {
		List<String> decoratorMethodNames = new ArrayList<>();
		boolean isValid = true;
		for (MethodContainer m : decorator.methods) {
			decoratorMethodNames.add(m.name);
		}
		for (MethodContainer m : component.methods) {
			if (!m.name.contains("init") && !m.name.contains("clinit") && m.access.contains("+")
					&& !decoratorMethodNames.contains(m.name)) {
				MethodContainer missingMethod = m.makeCopy();
				missingMethod.color = "FF0000";
				decorator.methods.add(missingMethod);
				isValid= false;
			}
		}
		return isValid;
	}
	
	private void setGoodAndBadDecorators(ClassContainer decoratorClass, ClassContainer componentClass) {
		if (checkMethods(decoratorClass, componentClass)) {
			if (!decoratorClasses.contains(decoratorClass)) {
				decoratorClasses.add(decoratorClass);
			}
		} else {
			if (!badDecoratorClasses.contains(decoratorClass)) {
				badDecoratorClasses.add(decoratorClass);
			}
		}
	}

}
