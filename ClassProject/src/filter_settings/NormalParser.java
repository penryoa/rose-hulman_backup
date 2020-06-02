package filter_settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import containers.ClassContainer;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

public class NormalParser implements Parser {

	public NormalParser() {
	}

	@Override
	public List<ClassContainer> parseDesign(List<String> toParse, List<String> toBlacklist) throws IOException {
		List<ClassContainer> classList = new ArrayList<ClassContainer>();
		for (String className : toParse) {
			// The 3 steps read in a Java class:
			// 1. ASM's ClassReader does the heavy lifting of parsing the compiled Java
			// class.
			ClassReader reader;
			if (!className.contains(".class")) {
				reader = new ClassReader(className);				
			} else {
				File f = new File(className);
				FileInputStream s = new FileInputStream(f);
				reader = new ClassReader(s);
			}

			// 2. ClassNode is just a data container for the parsed class
			ClassNode classNode = new ClassNode();

			// 3. Tell the Reader to parse the specified class and store its data in our
			// ClassNode.
			// EXPAND_FRAMES means: I want my code to work. (Always pass this flag.)
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);

			ClassContainer cc = new ClassContainer(classNode);

			classList.add(cc);

			List<InnerClassNode> ICN = classNode.innerClasses;

			// TODO THIS IS WHERE THE MAGIC HAPPENS.

		}

		List<ClassContainer> toRemove = new ArrayList<>();
		for (ClassContainer c : classList) {
			for (String s : toBlacklist) {
				if (c.fullName.contains(s) && !toParse.contains(c.fullName)) {
					toRemove.add(c);
				}
			}
		}
		classList.removeAll(toRemove);

		return classList;
	}
}
