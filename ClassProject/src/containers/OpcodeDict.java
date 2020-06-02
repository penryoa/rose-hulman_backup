package containers;

import java.util.LinkedHashMap;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

public class OpcodeDict {
	
	public static LinkedHashMap<Integer, String> accessIntFieldMethod = new LinkedHashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(Opcodes.ACC_PUBLIC, "+"); // public: class, field, method
			put(Opcodes.ACC_PRIVATE, "-"); // private: class, field, method
			put(Opcodes.ACC_PROTECTED, "#"); // protected: class, field, method
			put(Opcodes.ACC_STATIC, "{static}"); // static: field, method
			put(Opcodes.ACC_SUPER, "{super}"); // super: class
			put(Opcodes.ACC_INTERFACE, "interface"); // interface: class
			put(Opcodes.ACC_ABSTRACT, "abstract"); // abstract: class, method
//			put(Opcodes.ACC_, ""); // final: class, field, method, parameter
//			put(0x0020, ""); // synchronized: method
//			put(0x0040, ""); // volatile: field
//			put(0x0040, ""); // bridge: method
//			put(0x0080, ""); // varargs: method
//			put(0x0080, ""); // transient: field
//			put(0x0100, ""); // native: method
//			put(0x0800, ""); // strict: method
//			put(0x1000, ""); // synthetic: class, field, method, parameter
//			put(0x2000, ""); // annotation: class
//			put(0x4000, ""); // enum: class(?) field inner
//			put(0x8000, ""); // mandated: parameter
		}
	};
	
	public static LinkedHashMap<Integer, String> accessIntClass = new LinkedHashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(Opcodes.ACC_PUBLIC, "+"); // public: class, field, method
			put(Opcodes.ACC_PRIVATE, "-"); // private: class, field, method
			put(Opcodes.ACC_PROTECTED, "#"); // protected: class, field, method
			put(Opcodes.ACC_INTERFACE, "interface"); // interface: class
			put(Opcodes.ACC_ABSTRACT, "abstract class"); // abstract: class, method
			put(Opcodes.ACC_STATIC, "{static}"); // static: field, method
//			put(0x0020, "super"); // super: class
//			put(0x0010, ""); // final: class, field, method, parameter
//			put(0x0020, ""); // synchronized: method
//			put(0x0040, ""); // volatile: field
//			put(0x0040, ""); // bridge: method
//			put(0x0080, ""); // varargs: method
//			put(0x0080, ""); // transient: field
//			put(0x0100, ""); // native: method
//			put(0x0800, ""); // strict: method
//			put(0x1000, ""); // synthetic: class, field, method, parameter
//			put(0x2000, ""); // annotation: class
//			put(0x4000, ""); // enum: class(?) field inner
//			put(0x8000, ""); // mandated: parameter
		}
	};
	
	public static String decodeFieldMethodAccessibility(int access) {
		StringBuilder str = new StringBuilder();
		for (Integer i : accessIntFieldMethod.keySet()) {
			if ((i & access) != 0) {
				str.append(accessIntFieldMethod.get(i));
				str.append(' ');
			}
		}
		if (str.length() > 0) {
			str.deleteCharAt(str.length() - 1);
		} else {
			str.append(accessIntClass.get(Opcodes.ACC_PROTECTED));
		}
		return str.toString();
	}
	
	public static String[] decodeClassAccessibility(int access) {
		String[] toReturn = new String[2];
		StringBuilder str = new StringBuilder();
		for (Integer i : accessIntClass.keySet()) {
			if ((i & access) != 0) {
				if (accessIntClass.get(i).equals("+") || accessIntClass.get(i).equals("-")
						|| accessIntClass.get(i).equals("#")) {
					toReturn[0] = accessIntClass.get(i);
				} else {
					str.append(accessIntClass.get(i));
					break; // this break is to discourage crap like "interface abstract"
				}
			}
		}
		// if no modifiers
		if (str.length() == 0) {
			str.append("class");
		}
		// Defualt protected
		if (toReturn[0] == null) {
			toReturn[0] = accessIntClass.get(Opcodes.ACC_PROTECTED);
		}
		toReturn[1] = str.toString();
		return toReturn;
	}
}
