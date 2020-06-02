package containers;

import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.signature.SignatureReader;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import jdk.internal.org.objectweb.asm.tree.LocalVariableNode;
import jdk.internal.org.objectweb.asm.util.TraceSignatureVisitor;

public class FieldContainer {
	protected FieldNode node;
	public LocalVariableNode LVnode;
	boolean display;
	public String access;
	public String type;
	public String typeDescription;
	public String name;

	public FieldContainer(FieldNode f) {
		this.node = f;
		this.name = f.name;
		if (f.signature != null) {
			TraceSignatureVisitor sw = new TraceSignatureVisitor(0);
			SignatureReader sr = new SignatureReader(f.signature);

			sr.acceptType(sw);

			this.type = sw.getDeclaration();
		} else {
			String temp = Type.getType(this.node.desc).toString(); //used temp.split('/') and used temp[0] to get rid of "java.lang."

			TraceSignatureVisitor sw = new TraceSignatureVisitor(0);
			SignatureReader sr = new SignatureReader(temp);

			sr.acceptType(sw);

			this.type = sw.getDeclaration();
		}
		
		TraceSignatureVisitor sw = new TraceSignatureVisitor(0);
		SignatureReader sr = new SignatureReader(this.node.desc);
		sr.acceptType(sw);
		this.typeDescription = sw.getDeclaration();
		
		this.display = true;
		this.access = OpcodeDict.decodeFieldMethodAccessibility(node.access);
	}
}
