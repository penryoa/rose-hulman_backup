package displays;

import java.util.List;
import containers.ClassContainer;

public class BadDecoratorClassDisplay implements ClassDisplay{

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for(ClassContainer c : classes) {
			c.addStereotype("bad decorator");
			c.backgroundColor = "PaleTurquoise";
			c.borderColor = "DarkTurquoise";
		}
	}

}
