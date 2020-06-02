package displays;

import java.util.List;

import containers.ClassContainer;

public class SingletonClassDisplay implements ClassDisplay {

	@Override
	public void setClassDisplay(List<ClassContainer> classes) {
		for (ClassContainer c : classes) {
			c.addStereotype("Singleton");
			c.borderColor = "Blue";
		}
	}
}
