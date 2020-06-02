package conversion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;

public class ArrowFactory {
	Set<Arrow> arrows;
	List<ClassContainer> classContainers;
	List<String> classNames;
	List<String> internalListNames;

	public ArrowFactory(List<ClassContainer> c) {
		this.arrows = new HashSet<>();
		this.classContainers = c;
		this.classNames = new ArrayList<String>();
		this.internalListNames = new ArrayList<String>() {
			{
				add("java.util.Collection");
				add("java.util.Map");
				add("java.util.Arrays");
				// TODO add Iterations
			}
		};
		for (ClassContainer con : classContainers) {
			this.classNames.add(con.fullName);
		}
	}

	/**
	 * Initiates the construction of each of the arrow types
	 * 
	 * @return
	 */
	public Set<Arrow> createArrows() {
		for (ClassContainer c : classContainers) {
			appendAssociations(c);
			appendDependencies(c);
			appendExtentions(c);
			appendImplementations(c);
		}
		removeDuplicates();
		return this.arrows;
	}

	/**
	 * Looks through a ClassNode's interfaces and makes an arrow from the node to an
	 * interface if the it is passed in when the app is ran.
	 * 
	 * @param c
	 */
	private void appendImplementations(ClassContainer c) {
		for (String i : c.interfaces) {
			i = i.replaceAll("/", ".");
			if (classNames.contains(i)) {
				// No cardinality on extensions
				makeArrow(c.fullName, "implements", i, "", "|>"); // ..|>
			}
		}
	}

	/**
	 * Makes arrows from a class to its superclass if the superclass was passed in
	 * when the app is ran
	 * 
	 * @param c
	 */
	private void appendExtentions(ClassContainer c) {
		String superclass = c.superName;
		if (superclass != null) {
			superclass = superclass.replaceAll("/", ".");
			if (classNames.contains(superclass)) {
				// No cardinality on implementations
				makeArrow(c.fullName, "extends", superclass, "", "|>"); // --|>
			}
		}
	}

	/**
	 * Makes arrows from a class to its fields' classes if the field's class is
	 * passed in when the app is ran
	 * 
	 * @param c
	 */
	private void appendAssociations(ClassContainer c) {
		for (FieldContainer f : c.fields) {
			for (String nameClass : classNames) {
				if (f.type.contains(nameClass)) {
//					System.out.println("===> f.type: " + f.type);
					if (isCollection(f.type, f.typeDescription)) {
						makeArrow(c.fullName, "association", nameClass, "1..*", ">"); // -->
					} else {
						makeArrow(c.fullName, "association", nameClass, "", ">"); // -->
					}
				}
			}
		}
	}

	/**
	 * Returns true if the classToCheck is a Collection
	 * 
	 * @param classToCheck
	 * @param classDesc
	 * @return
	 */
	private boolean isCollection(String classToCheck, String classDesc) {
//		if ((classToCheck.contains("<") && classToCheck.contains(">"))
//				|| (classToCheck.contains("[") && classToCheck.contains("]"))) {
//			System.out.println("\t\tClass desc: " + classDesc);
		try {
			Class<?> clazz = Class.forName(classDesc);
//				System.out.println("========= clazz.getName(): " + clazz.getName() + '\n');
			if (internalListNames.contains(clazz.getName())) {
				return true;
			}
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> cl : interfaces) {
				if (internalListNames.contains(cl.getName())) {
					return true;
				}
			}
		} catch (ClassNotFoundException e) {
//				System.out.println("\tDid not find class " + classToCheck + '\n');
		}
//		}
		return false;
	}

	/**
	 * Checks all of a class's methods' arguments and makes dependency arrows if the
	 * argument's class was passed in when the app was ran
	 * 
	 * @param c
	 */
	private void appendDependencies(ClassContainer c) {
		for (MethodContainer m : c.methods) {
			for (String nameArg : m.argumentTypes) {
				int i = 0;
				for (String nameClass : classNames) {
					if (!nameArg.equals(nameClass)) {
						// looks through each argument's types
						dependencyHelper(nameArg, nameClass, c, m.argumentTypeDescription.get(i));
					}
				}
				i++;
			}
			
			// return type
			dependencyHelper(m.returnType, m.returnType, c, m.returnTypeDesc);
			
		}
		for (String nameArg : c.concretes) {
			int i = 0;
			for (String nameClass : classNames) {
				if (nameArg.equals(nameClass)) {
					// looks through each argument's types
					dependencyHelper(nameArg, nameClass, c, nameArg);
				}
			}
			i++;
		}
	}

	/**
	 * Constructs a dependency arrow and adds it to the arrow set if appropriate.
	 * This is used three times each method, so it may as well have its own method.
	 * 
	 * @param argType
	 * @param toCheck
	 * @param c
	 * @param classFromDesc
	 */
	public void dependencyHelper(String argType, String toCheck, ClassContainer c, String classFromDesc) {
		if (argType.contains(toCheck)) {
			if (isCollection(argType, classFromDesc)) {
				makeArrow(c.fullName, "dependency", toCheck, "1..*", ">");
			} else {
				makeArrow(c.fullName, "dependency", toCheck, "", ">");
			}
		}
	}

	/**
	 * Adds a relationship arrow to the given String strToAdd if it does not already
	 * contain that exact same relationship arrow AND both classes are being rendered.
	 * 
	 * @param from
	 * @param type
	 * @param to
	 */
	private void makeArrow(String from, String type, String to, String cardinalityTo, String arrowheadTo) {
		//this bit of logic makes sure that arrows only point to and from classes that are being rendered.
		boolean toRend = false;
		boolean fromRend = false;
		for(ClassContainer cc : classContainers) {
			if(cc.fullName.equals(to)) {
				toRend = true;
			}
			if(cc.fullName.equals(from)) {
				fromRend = true;
			}
		}
		
		//if they are not both true, then don't make an arrow
		if(!(toRend && fromRend)) {
			return;
		}
		
		Arrow arr = new Arrow();
		arr.srcName = from;
		arr.destName = to;
		arr.type = type;
		arr.cardinalityTo = cardinalityTo;
		arr.arrowheadTo = arrowheadTo;
		this.arrows.add(arr);
	}

	private void removeDuplicates() {
		// FIXME: Change if we have time
		Set<Arrow> toRemove = new HashSet<Arrow>();
		for (Arrow arr : arrows) {
			for (Arrow arr2 : arrows) {
				if (arr.srcName.equals(arr2.srcName) && arr.destName.equals(arr2.destName)) {
					if (arr.cardinalityTo.equals("") && !arr2.cardinalityTo.equals("")) {
						toRemove.add(arr);
					} else if (!arr.cardinalityTo.equals("") && arr2.cardinalityTo.equals("")) {
						toRemove.add(arr2);
					} else if (arr.type.equals("association") && arr2.type.equals("dependency")) {
						toRemove.add(arr2);
					} else if (arr2.type.equals("association") && arr.type.equals("dependency")) {
						toRemove.add(arr);
					}
				}
			}
		}
		arrows.removeAll(toRemove);
	}

}
