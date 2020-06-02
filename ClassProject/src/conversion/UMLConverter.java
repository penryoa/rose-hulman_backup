package conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import containers.ClassContainer;
import containers.FieldContainer;
import containers.MethodContainer;

public class UMLConverter implements Converter {

	List<ClassContainer> toConvert;
	Set<Arrow> arrows;
	Map<String, String> arrowTypes = new HashMap<String, String>() {
		{
			put("dependency", "..");
			put("implements", "..");
			put("association", "--");
			put("extends", "--");
			put("bidirectional", "--");
		}
	};;

	/**
	 * Starts up the convert() method where the magic happens
	 * 
	 * @param toConvert
	 * @param arrows
	 */
	public UMLConverter(List<ClassContainer> toConvert, Set<Arrow> arrows) {
		this.arrows = arrows;
		this.toConvert = toConvert;
	}

	/**
	 * This is the main method we worry with here. It'll call all the methods
	 * required to convert the classes to the UML syntax.
	 * 
	 * @param toConvert
	 * @return
	 */
	@Override
	public String convert() {
		StringBuilder finalString = new StringBuilder();
		finalString.append("@startuml\n");
		List<String> classNames = new ArrayList<String>();
		for (ClassContainer c : toConvert) {
			classNames.add(c.fullName);
		}

		for (ClassContainer c : toConvert) {
			StringBuilder str = new StringBuilder();

			appendClassName(str, c);

			/* fields and methods */
			appendFields(str, c);
			appendMethods(str, c);

			/* end the curly brace */
			str.append("}\n");

			finalString.append(str);
		}

		/* add the arrows at the end */
		drawArrows(arrows, finalString);

		finalString.append("@enduml");
		return finalString.toString();
	}

	protected void appendClassName(StringBuilder str, ClassContainer cContainer) {
		/* visibility */
		str.append(cContainer.access);

		/* class name */
		str.append(" " + cContainer.fullName);

		/* add on colors and label */
		for (String stereotype : cContainer.stereotypes) {
			str.append(" <<" + stereotype + ">>");
		}

		if (cContainer.backgroundColor != null) {
			str.append(" #" + cContainer.backgroundColor);
		}
		if (cContainer.borderColor != null) {
			str.append(" ##" + cContainer.borderColor);
		}

		/* and that's it! */
		str.append(" {\n");
	}

	/**
	 * Takes a StringBuilder and appends on the fields of the given ClassContainer
	 * 
	 * @param str
	 * @param cContainer
	 */
	private void appendFields(StringBuilder str, ClassContainer cContainer) {
		for (FieldContainer fContainer : cContainer.fields) {
			/* visibility */
			str.append('\t');
			str.append(fContainer.access);
			str.append(" ");

			/* field name */
			str.append(fContainer.name);
			str.append(" : ");

			/* field type */
			str.append(fContainer.type);
			str.append('\n');
		}
	}

	/**
	 * Takes in a StringBuilder and appends the methods of the given ClassContainer
	 * 
	 * @param str
	 * @param cContainer
	 */
	private void appendMethods(StringBuilder str, ClassContainer cContainer) {
		for (MethodContainer mContainer : cContainer.methods) {
			if (!mContainer.name.equals("<init>") && !mContainer.name.equals("<clinit>")) {
				/* visibility */
				str.append('\t');
				str.append(mContainer.access);
				str.append(" ");
				
				/* method color (if applicable) */
				if (mContainer.color != null && mContainer.color != "") {
					str.append("<font color=" +'"' + "#" + mContainer.color + '"' + ">"); //FIXME can't figure out syntax
				}

				/* method name */
				str.append(mContainer.name);

				/* parameters */
				appendMethodParameters(str, mContainer);

				/* return type */
				str.append(": ");
				str.append(mContainer.returnType);

				/* method color end (if applicable) */
				if (mContainer.color != null && mContainer.color != "") {
					str.append(" </font>");
				}

				str.append('\n');
			}
		}
	}

	/**
	 * Takes in a StringBuilder and appends the method parameters of a given
	 * MethodContainer
	 * 
	 * @param str
	 * @param mContainer
	 */
	private void appendMethodParameters(StringBuilder str, MethodContainer mContainer) {
		str.append('(');

		if (mContainer.argumentTypes.size() > 0) {
			int i = 0;
			for (String p : mContainer.argumentTypes) {
				str.append(mContainer.argumentNames.get(i));
				str.append(" : ");
				str.append(p);
				str.append(", ");
				i++;
			}
			str.deleteCharAt(str.length() - 1); // get rid of the space at the end
			str.deleteCharAt(str.length() - 1); // get rid of the comma at the end
		}
		str.append(')');
	}

	private void drawArrows(Set<Arrow> arrows, StringBuilder str) {
		for (Arrow arr : arrows) {
			StringBuilder toAppend = new StringBuilder();
			toAppend.append('"' + arr.srcName + '"');

			if (!arr.cardinalityFrom.equals("")) {
				toAppend.append('"' + arr.cardinalityFrom + '"');
			}

			toAppend.append(arr.arrowheadFrom);

			toAppend.append(arrowTypes.get(arr.type));

			toAppend.append(arr.arrowheadTo);

			if (!arr.cardinalityTo.equals("")) {
				toAppend.append('"' + arr.cardinalityTo + '"');
			}

			toAppend.append('"' + arr.destName + '"');

			if (arr.color != "") {
				toAppend.append(" #" + arr.color);
			}

			if (arr.stereotype != "") {
				toAppend.append(" :" + arr.stereotype);
			}
			toAppend.append('\n');
			str.append(toAppend);
		}
	}
}
