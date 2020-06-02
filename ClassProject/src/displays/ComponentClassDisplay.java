package displays;

import java.util.List;

import containers.ClassContainer;

public class ComponentClassDisplay implements ClassDisplay {

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for(ClassContainer c : classes) {
			c.addStereotype("component");
			c.backgroundColor = "MistyRose";
			c.borderColor = "Magenta";
		}
	}

}
