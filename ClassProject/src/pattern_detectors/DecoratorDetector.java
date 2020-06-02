package pattern_detectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.CheckedInputStream;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;
import conversion.Arrow;
import displays.ComponentClassDisplay;
import displays.ConcreteDecoratorDisplay;
import displays.DecoratorArrowDisplay;
import displays.DecoratorClassDisplay;

public class DecoratorDetector implements PatternDetector {

	private List<ClassContainer> cc;
	private Set<Arrow> arrows;

	/**
	 * A class is a decorator if: (a) It's an abstract class. (b) It
	 * implements/extends a component C. (c) It has a field of class C.
	 */
	/*
	 * A class is a concrete decorator if: (a) It's a concrete class. (b) It has a
	 * superclass that is a decorator D that fits the above requirements. (c) All of
	 * the component's methods are implemented in the concrete decorator.
	 */
	@Override
	public void detectPattern(List<ClassContainer> cc, Set<Arrow> arrows) {
		this.cc = cc;
		this.arrows = arrows;
		List<ClassContainer> decoratorClasses = new ArrayList<>();
		List<ClassContainer> componentClasses = new ArrayList<>();
		List<ClassContainer> concreteClasses = new ArrayList<>();
		List<Arrow> componentArrows = new ArrayList<>();

		getAllDecorators(decoratorClasses, componentClasses, componentArrows);

		/* Check for Concrete Decorators */
		for (ClassContainer clazz : cc) {
//			System.out.println("--------------------------\nChecking class " + clazz.fullName);
			if (clazz.access.equals("class")) {
//				System.out.println("\tWorking with class " + clazz.fullName);
				if (decoratorClasses.contains(getAbstractSuperClass(clazz))) {
					if (checkMethods(clazz, getComponentClass(getAbstractSuperClass(clazz)))) {
//						System.out.println("\t... IS a concrete decorator.\n");
						if (!concreteClasses.contains(clazz)) {
							concreteClasses.add(clazz);
						}
//					} else {
//						System.out.println(
//								"\t... NOT a concrete decorator bc it doesn't implement all the component's methods.\n");
					}

//				} else {
//					System.out.println("\t... NOT a concrete decorator bc it doesn't extend a decorator.\n");

				}
			}
		}
		new DecoratorArrowDisplay().setArrowDisplay(componentArrows);
		new DecoratorClassDisplay().setClassDisplay(decoratorClasses);
		new ComponentClassDisplay().setClassDisplay(componentClasses);
		new ConcreteDecoratorDisplay().setClassDisplay(concreteClasses);
	}

	/**
	 * This does all the work for the abstract decorators.
	 * 
	 * @param decoratorClasses
	 * @param componentClasses
	 * @param componentArrows
	 */
	private void getAllDecorators(List<ClassContainer> decoratorClasses, List<ClassContainer> componentClasses,
			List<Arrow> componentArrows) {
//		System.out.println("===============================================\nLet's get the decorators:");

		for (ClassContainer clazz : cc) {

			ClassContainer decoratorClass = null;
			ClassContainer componentClass = null;

			if (clazz.access.contains("class")) {
				// clazz *may* have a component: either extends or implements the component
//				System.out.println("\tFound abstract class " + clazz.fullName);
				componentClass = getComponentClass(clazz);
				if (componentClass != null) {
					decoratorClass = clazz;
//					System.out.println("\t\t... is a decorator with component " + componentClass.fullName);
					if (!decoratorClasses.contains(decoratorClass)) {
						decoratorClasses.add(decoratorClass);
					}
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
//				} else {
//					System.out.println("\t\t... is NOT a decorator because it doesn't have a valid component.");
				}
			}
		}
//		System.out.println("===============================================\n");
	}

	/**
	 * Our concrete class will always extend an abstract class.
	 * 
	 * @param c
	 * @return
	 */
	private ClassContainer getAbstractSuperClass(ClassContainer c) {
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
//		System.out.println("\t\t--> Checking if " + potentialDecorator.fullName + " has a component:");
		ClassContainer component = null;
		if (potentialDecorator.superName != null && potentialDecorator.superName != "") {
//			System.out.println("\t\t\t... the potential decorator could be extending an abstract class.");
			component = containerFromName(potentialDecorator.superName);
		
			if (component != null && !checkFields(potentialDecorator, component.fullName)
					&& !instanceIsPassedIntoConstructor(potentialDecorator, component)) {
				// the potential decorator doesn't have a field of type Component OR the field
				// isn't passed in the constructor, so invalid
				// decorator.
//				System.out.println("\t\t\t... ... but it isn't.");
				component = null;
//			} else {
//				System.out.println("\t\t\t... ... and it is!");
			}
		}
		if (component == null && !potentialDecorator.interfaces.isEmpty()) {
			// check if component is an interface; it's definitely not an abstract class.
			for (String interfaceName : potentialDecorator.interfaces) {
//				System.out.println(
//						"\t\t\t... the potential decorator could be implementing an interface " + interfaceName);
				component = containerFromName(interfaceName);
				if (component != null && !checkFields(potentialDecorator, component.fullName)
						&& !instanceIsPassedIntoConstructor(potentialDecorator, component)) {
					// the potential decorator doesn't have a field of type Component OR the field
					// isn't passed in the constructor, so invalid
					// decorator.
//					System.out.println("\t\t\t... ... but it isn't.");
					component = null;
//				} else {
//					System.out.println("\t\t\t... ... and it is!");
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
//			System.out.println("\t\t--> Checking field container types " + fc.type + " to " + type);
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
//				System.out.println("FOUND METHOD CONTAINING INIT: " + m.name);
//				System.out.println(m.argumentTypes);
				if (m.argumentTypes.contains(potentialComponent.fullName)) {
//					System.out.println("THE COMPONENT IS INDEED PASSED IN THE CONSTRUCTOR");
					isPassedIn = true;
					break;
				}
			}
		}
		return isPassedIn;
	}

	/**
	 * Makes sure that all the public methods of the component are
	 * implemented/overridden in the concrete decorator.
	 * 
	 * @param concreteDecorator
	 * @param component
	 * @return
	 */
	private boolean checkMethods(ClassContainer concreteDecorator, ClassContainer component) {
		List<String> decoratorMethodNames = new ArrayList<>();
		for (MethodContainer m : concreteDecorator.methods) {
//			System.out.println("\t\tMethodNames: " + m.name);
			decoratorMethodNames.add(m.name);
		}
		for (MethodContainer m : component.methods) {
			if (!m.name.contains("init") && !m.name.contains("clinit") && m.access.contains("+")) {
//				System.out.println("\t\t\t... contains " + m.name + "?"); //
				if (!decoratorMethodNames.contains(m.name)) {
//					System.out.println("\t\t\t\tNo"); //
					return false;
				} else { //
//					System.out.println("\t\t\t\tYes"); //
				}
			}
		}
		return true;
	}
}
