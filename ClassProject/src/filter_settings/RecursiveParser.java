package filter_settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import containers.ClassContainer;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

public class RecursiveParser implements Parser {

	public RecursiveParser() {
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

			// get the arraylist of ClassContainers that is from the interfaces
			ArrayList<ClassContainer> tempList = (ArrayList<ClassContainer>) this.parseDesign(classNode.interfaces, toBlacklist);
			for (ClassContainer cc2 : tempList) {
				if (!cc2.fullName.contains("Object"))
					classList.add(cc2);
			}

			// get the arraylist of ClassContainers that is from the superclass given that
			// it is not null
			if (classNode.superName != null) {
				ArrayList<String> superString = new ArrayList<String>();
				superString.add(classNode.superName);
				tempList = (ArrayList<ClassContainer>) this.parseDesign(superString, toBlacklist);
				for (ClassContainer cc2 : tempList) {
					if (!cc2.fullName.contains("Object"))
						classList.add(cc2);
				}
			}
		}

		List<ClassContainer> toRemove = new ArrayList<>();
		for (ClassContainer c : classList) {
			for (String s : toBlacklist) {
				if (c.fullName.contains(s)) {
					toRemove.add(c);
				}
			}
		}
		classList.removeAll(toRemove);

		return classList;
	}
}