package displays;

import java.util.List;
import containers.ClassContainer;

public class DependencyInversionDisplay implements ClassDisplay{

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for(ClassContainer c : classes) {
			c.backgroundColor = "yellow";
			c.addStereotype("DIP Violation");
		}
	}

}
