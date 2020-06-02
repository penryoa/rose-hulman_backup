package displays;

import java.util.List;

import containers.ClassContainer;

public class ConcreteDecoratorDisplay implements ClassDisplay{

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for(ClassContainer c : classes) {
			c.addStereotype("concrete decorator");
			c.backgroundColor = "MistyRose";
			c.borderColor = "Magenta";
		}
	}
}

