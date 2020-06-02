package filter_settings;

import java.io.IOException;
import java.util.List;

import containers.ClassContainer;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

public interface Parser {
	public List<ClassContainer> parseDesign(List<String> toParse, List<String> toBlacklist) throws IOException;
}
