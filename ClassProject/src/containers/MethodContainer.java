package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.signature.SignatureReader;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.LocalVariableNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.internal.org.objectweb.asm.tree.ParameterNode;
import jdk.internal.org.objectweb.asm.util.TraceSignatureVisitor;

public class MethodContainer {
	protected MethodNode node;
	public List<String> argumentTypes = new ArrayList<String>();
	public List<String> argumentNames = new ArrayList<String>();
	public List<String> argumentTypeDescription = new ArrayList<String>();
	public String returnType;
	public String returnTypeDesc;
	public String access;
	public String name;
	public String color;

	public MethodContainer(MethodNode m) {
		this.node = m;
		this.name = m.name;

		/* get the return type */
		if (this.node.signature != null) {
			// split the signature from parameters and return types
			String[] temp = this.node.signature.split("\\)");
			this.returnType = makeNice(temp[temp.length - 1]);
		} else {
			this.returnType = makeNice(Type.getReturnType(this.node.desc).toString());
		}
		this.returnTypeDesc = makeNice(Type.getReturnType(this.node.desc).toString());

		// initialize the argument types and names
		argumentTypes = new ArrayList<String>();
		argumentNames = new ArrayList<String>();
		argumentTypeDescription = new ArrayList<String>();

		//set this.access
		this.access = OpcodeDict.decodeFieldMethodAccessibility(node.access);

		// make sure this.node.localVariables is not null.
		if (node.localVariables == null) {
			node.localVariables = new ArrayList<LocalVariableNode>();
		}

		/* get the parameters and their names */
		getArgTypesandNames();
	}
	
	private String makeNice(String str) {
		//for some reason, Objects return as nothing, so this is a special case.
		if(str.equals("Ljava/lang/Object;"))
			return "java.lang.Object";
		
		if(str != null && str.length() > 0) {
			TraceSignatureVisitor sv = new TraceSignatureVisitor(0);
			SignatureReader sr = new SignatureReader(str);

			sr.acceptType(sv);

			return sv.getDeclaration();
		}
		return "";
	}
	
	private void getArgTypesandNames() {
		// USE THE DESCRIPTOR
		List<LocalVariableNode> LVN = node.localVariables;
		if (LVN.size() == 0) return; //then there is nothing to do here.
		
		//get the number of arguments
		int argnum = Type.getArgumentTypes(this.node.desc).length;
		
		int i;
		if (this.access.contains("static")) {
			// if its static, the localVariables does not have a "this" argument
			i = 0;
		} else {
			i = 1;
		}
		
		//if there is a "this" variable, then add one to accommodate for it. (i is 0 or 1 accordingly).
		if (argnum < LVN.size()) {
			argnum = argnum + i;
		}
		
		//loop through the local variables to get their type and name.
		for (int j = i; j < argnum && j < LVN.size(); j++) {
			if (LVN.get(j).signature != null) {
				argumentTypes.add(makeNice(LVN.get(j).signature));
			} else {
				argumentTypes.add(makeNice(LVN.get(j).desc));
			}
			argumentNames.add(LVN.get(j).name);
			argumentTypeDescription.add(makeNice(LVN.get(j).desc));
		}
	}
	
	public MethodContainer makeCopy() {
		return new MethodContainer(this.node);
	}
}
