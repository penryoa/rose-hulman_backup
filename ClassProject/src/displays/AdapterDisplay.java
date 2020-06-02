package displays;

import java.util.List;
import containers.ClassContainer;

public class AdapterDisplay implements ClassDisplay{

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		//they will be in the order: 0 - adapter. 1 - adaptee. 2 - target
		ClassContainer adapter = classes.get(0);
		ClassContainer adaptee = classes.get(1);
		ClassContainer target = classes.get(2);
		
		adapter.backgroundColor = "red";
		adaptee.backgroundColor = "red";
		target.backgroundColor = "red";
		
		adapter.addStereotype("adapter");
		adaptee.addStereotype("adaptee");
		target.addStereotype("target");
		
	}

}
