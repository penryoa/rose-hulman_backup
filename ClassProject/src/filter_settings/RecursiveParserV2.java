package filter_settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import containers.ClassContainer;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

public class RecursiveParserV2 implements Parser {

	public RecursiveParserV2() {
	}

	@Override
	public List<ClassContainer> parseDesign(List<String> toParse, List<String> toBlacklist) throws IOException {
		List<ClassContainer> classList = new ArrayList<ClassContainer>();

		for (String className : toParse) {
			// The 3 steps read in a Java class:
			// 1. ASM's ClassReader does the heavy lifting of parsing the compiled Java
			// class.
			ClassReader reader = new ClassReader(className);
			// 2. ClassNode is just a data container for the parsed class
			ClassNode classNode = new ClassNode();

			// 3. Tell the Reader to parse the specified class and store its data in our
			// ClassNode.
			// EXPAND_FRAMES means: I want my code to work. (Always pass this flag.)
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			// put the current class into a class container and add it to what is to be
			// returned.
			ClassContainer cc = new ClassContainer(classNode);
			classList.add(cc);

			// get all of the concretes
			ArrayList<ClassContainer> tempconcretes = (ArrayList<ClassContainer>) this.parseDesignRecur(cc.concretes,
					toBlacklist);
			for (ClassContainer cc2 : tempconcretes) {
				if (!classList.contains(cc2))
					classList.add(cc2);
			}

			// get all of the fields
			ArrayList<ClassContainer> tempfields = (ArrayList<ClassContainer>) this.parseDesignRecur(cc.fieldTypes,
					toBlacklist);
			for (ClassContainer cc2 : tempfields) {
				if (!classList.contains(cc2))
					classList.add(cc2);
			}

			// get the arraylist of ClassContainers that is from the interfaces
			ArrayList<ClassContainer> tempList = (ArrayList<ClassContainer>) this.parseDesignRecur(classNode.interfaces,
					toBlacklist);
			for (ClassContainer cc2 : tempList) {
				if (!classList.contains(cc2))
					classList.add(cc2);
			}

			// get the arraylist of ClassContainers that is from the superclass given that
			// it is not null
			if (classNode.superName != null) {
				ArrayList<String> superString = new ArrayList<String>();
				superString.add(classNode.superName);
				ArrayList<ClassContainer> tempList2 = (ArrayList<ClassContainer>) this.parseDesignRecur(superString,
						toBlacklist);
				for (ClassContainer cc2 : tempList2) {
					if (!classList.contains(cc2))
						classList.add(cc2);
				}
			}
		}

		return classList;
	}

	public List<ClassContainer> parseDesignRecur(List<String> toParseProt, List<String> blacklist) {
		//make a copy
		List<String> toParse = new ArrayList<>();
		
		for(String s : toParseProt) { 
			toParse.add(s);
		}
		List<ClassContainer> classList = new ArrayList<ClassContainer>();

		// Since List does not have a clone method, I need to clone it here so I can mod
		// my own.
		List<String> moddedBlacklist = new ArrayList<>();
		for (String s : blacklist) {
			moddedBlacklist.add(s);
		}

		// remove everything that is on the blacklist
		for (int i = toParse.size() - 1; i >= 0; i--) {
			for (String blackListName : moddedBlacklist) {
				if (toParse.get(i).contains(blackListName)) {
					toParse.remove(i);
					break;
				}
			}
		}

		// now add all of the classes that passed the blacklist onto the blacklist so
		// recursive calls know
		// not to add it again
		for (String toListName : toParse) {
			moddedBlacklist.add(toListName);
		}

		for (String className : toParse) {
			// The 3 steps read in a Java class:
			// 1. ASM's ClassReader does the heavy lifting of parsing the compiled Java
			// class.
			try {
				ClassReader reader = new ClassReader(className);
				// 2. ClassNode is just a data container for the parsed class
				ClassNode classNode = new ClassNode();

				// 3. Tell the Reader to parse the specified class and store its data in our
				// ClassNode.
				// EXPAND_FRAMES means: I want my code to work. (Always pass this flag.)
				reader.accept(classNode, ClassReader.EXPAND_FRAMES);

				// put the current class into a class container and add it to what is to be
				// returned.
				ClassContainer cc = new ClassContainer(classNode);
				classList.add(cc);

				// get all of the concretes
				ArrayList<ClassContainer> tempconcretes = (ArrayList<ClassContainer>) this
						.parseDesignRecur(cc.concretes, moddedBlacklist);
				for (ClassContainer cc2 : tempconcretes) {
					if (!classList.contains(cc2))
						classList.add(cc2);
				}

				// get all of the fields
				ArrayList<ClassContainer> tempfields = (ArrayList<ClassContainer>) this.parseDesignRecur(cc.fieldTypes,
						moddedBlacklist);
				for (ClassContainer cc2 : tempfields) {
					if (!classList.contains(cc2))
						classList.add(cc2);
				}

				// get the arraylist of ClassContainers that is from the interfaces
				ArrayList<ClassContainer> tempList = (ArrayList<ClassContainer>) this
						.parseDesignRecur(classNode.interfaces, moddedBlacklist);
				for (ClassContainer cc2 : tempList) {
					if (!classList.contains(cc2))
						classList.add(cc2);
				}

				// get the arraylist of ClassContainers that is from the superclass given that
				// it is not null
				if (classNode.superName != null) {
					ArrayList<String> superString = new ArrayList<String>();
					superString.add(classNode.superName);
					ArrayList<ClassContainer> tempList2 = (ArrayList<ClassContainer>) this.parseDesignRecur(superString,
							moddedBlacklist);
					for (ClassContainer cc2 : tempList2) {
						if (!classList.contains(cc2))
							classList.add(cc2);
					}
				}
			} catch (java.io.IOException e) {
				moddedBlacklist.add(className);
			}
		}
		return classList;
	}
}
