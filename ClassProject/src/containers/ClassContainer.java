package containers;

import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.AnnotationNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.internal.org.objectweb.asm.util.Printer;
import jdk.internal.org.objectweb.asm.util.Textifier;
import jdk.internal.org.objectweb.asm.util.TraceMethodVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ClassContainer {
	protected ClassNode node;
	public String access;
	public String visibility;
	public String superName;
	public String displayName;
	public String fullName;
	public String borderColor = null;
	public String backgroundColor = null;

	// Lists of data
	public List<MethodContainer> methods;
	public List<FieldContainer> fields;
	public List<String> interfaces;
	public List<String> concretes;
	public List<String> fieldTypes;
	public List<String> stereotypes = new ArrayList<>();
	
	//	this is for removing certain unwanted concretes. For now, it only has in it.
	private List<String> badConcretes = new ArrayList<>();

	// the following two lines were taken from
	// https://stackoverflow.com/questions/19152526/asm-outputting-java-bytecode-and-opcode/19173813
	private static Printer printer = new Textifier();
	private static TraceMethodVisitor mp = new TraceMethodVisitor(printer);

	public ClassContainer(ClassNode node) {
		this.node = node;
		this.fullName = this.node.name.replaceAll("/", ".");
		String[] temp = OpcodeDict.decodeClassAccessibility(node.access);
		this.visibility = temp[0];
		this.access = temp[1];

		// get the name and get rid of any crap before the last '/' or '.'
		String[] nameArray = node.name.split("/");
		nameArray = nameArray[nameArray.length - 1].split("\\.");
		this.displayName = nameArray[nameArray.length - 1];

		this.methods = new ArrayList<MethodContainer>();
		for (MethodNode m : node.methods) {
			this.methods.add(new MethodContainer(m));
		}
		this.fields = new ArrayList<FieldContainer>();
		this.fieldTypes = new ArrayList<String>();
		for (FieldNode f : node.fields) {
			FieldContainer fc = new FieldContainer(f);
			this.fields.add(fc);
			this.fieldTypes.add(fc.type);
		}

		this.interfaces = this.node.interfaces;
		this.superName = this.node.superName;
		
		//add whatever bad concrete classes you want to badConcretes.
		badConcretes.add("java.lang.StringBuilder"); //bad class because "a" + "b" creates a StringBuilder without calling new

		// some of the code below was referenced from:
		// https://stackoverflow.com/questions/19152526/asm-outputting-java-bytecode-and-opcode/19173813
		List<MethodNode> methods = node.methods;
		concretes = new ArrayList<String>();
		for (MethodNode m : methods) {
			InsnList inList = m.instructions;
			// System.out.println(m.name);
			for (int i = 0; i < inList.size(); i++) {
				String line = insnToString(inList.get(i));

//            	 if(line.contains("NEW")) {	//old way
//                	 String[] s = line.split(" ");
//            		 concretes.add(s[s.length-1].replaceAll("\n", ""));
//            	 }

				String[] s = line.split(" ");

				List<String> list = new ArrayList<>();
				for (int j = 0; j < s.length; j++) {
					if (!s[j].trim().equals("") && s[j] != null)
						list.add(s[j]);
				}

				if (list.size() == 2) { // make sure that it is of size two. If it is, it may be a NEW line
					if (list.get(0).equals("NEW")) { // the first word must be exactly NEW
						concretes.remove(list.get(1).replaceAll("\n", "").replaceAll("/", ".")); //remove duplicate (can only be 1)
						concretes.add(list.get(1).replaceAll("\n", "").replaceAll("/", "."));    //add new
						concretes.removeAll(badConcretes); //remove it if it was bad
					}
				}
			}
		}
	}

//	this method was taken from
//	https://stackoverflow.com/questions/19152526/asm-outputting-java-bytecode-and-opcode/19173813
	public static String insnToString(AbstractInsnNode insn) {
		// System.out.println("op: " + Integer.toHexString(insn.getOpcode()));

		insn.accept(mp);
		StringWriter sw = new StringWriter();
		printer.print(new PrintWriter(sw));
		printer.getText().clear();
		return sw.toString();
	}

	@Override
	public boolean equals(Object o) {
		try {
			ClassContainer cc = (ClassContainer) o;
			return this.fullName.equals(cc.fullName);
		} catch (Exception e) {
			return false;
		}
	}

	public void addStereotype(String s) {
		this.stereotypes.add(s);
	}
}
