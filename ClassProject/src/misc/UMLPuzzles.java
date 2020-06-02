package misc;


import java.awt.Frame;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class UMLPuzzles {
	GenericClass<Integer> genericClass;
	ListListListString list;
	ListListListNinjaString<Long> list2;
	StringMap map;
	StringArray array;
	TwoStrings pair;
	OneTooManyDependencies[] dependencies;
}

class GenericClass<T>{
	GenericClass<String> clazz;
}

class ListListListString{
	List<List<List<String>>> list;
}

class ListListListNinjaString<String>{
	List<List<List<String>>> list;
}

class StringMap{
	Map<Integer, String> map;
}

class StringArray{
	String[] strings;
}

class TwoStrings{
	String string1;
	String string2;
}

class OneTooManyDependencies{
	String[] hasManyStrings;
	List<UMLPuzzles> puzzles;
	public Frame[] dependsOnMuchSwing(JComponent[] components) {
		return new JFrame[0];
	}
	public String[] dependsOnManyStrings() {
		return null;
	}
}
